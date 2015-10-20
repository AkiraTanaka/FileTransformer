package net.at.tools.transform.transformer;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.at.tools.transform.BaseTransformer;
import net.at.tools.transform.IAttributes;
import net.at.tools.transform.converter.BaseConverter;
import net.at.tools.transform.info.LineDataInfo;
import net.at.tools.transform.info.TransformParamInfo;
import net.at.tools.transform.settings.model.files.DataGroupSettingModel;
import net.at.tools.transform.settings.model.files.FileSettingModel;
import net.at.tools.transform.util.file.BaseTextFileUtil;

public class RequestAToBTransformer extends BaseTransformer {
	/**
	 * �R���X�g���N�^
	 * @param paramInfo �p�����[�^���
	 */
	public RequestAToBTransformer(TransformParamInfo paramInfo) {
		super(paramInfo);
	}

	/**
	 * �t�@�C���̕���
	 * @throws IOException
	 * @throws FileNotFoundException
	 */
	public Map<String, List<LineDataInfo>> analyze() throws FileNotFoundException, IOException {
		List<String> textLineList = BaseTextFileUtil.readTextFile(m_readingFileInfo);
		Map<String, List<String>> groupTextMap = new HashMap<>();
		for (String text : textLineList) {
			// �O���[�v����������͍̂ŏ��̈ꕶ���̐��l���ǂ���
			String firstChar = text.substring(0, 1);
			for (DataGroupSettingModel groupSetting : m_inboundSettingInfo.getDataGroupList()) {
				if (firstChar.equals(groupSetting.getPrimaryKeyValue())) {
					String groupType = groupSetting.getType();
					if (groupTextMap.containsKey(groupType) == false) {
						groupTextMap.put(groupType, new ArrayList<>());
					}
					groupTextMap.get(groupType).add(text);
				}
			}
		}
		Map<String, List<LineDataInfo>> dataInfoMap = new HashMap<>();
		for (String key : groupTextMap.keySet()) {
			separateData(key, groupTextMap.get(key), dataInfoMap);
		}
		return dataInfoMap;
	}

	/**
	 * �t�@�C���o��
	 * @param outboundSettingInfo �o�̓t�@�C���ݒ���
	 * @param dataInfoMap �o�̓t�@�C���f�[�^�}�b�v
	 * @return �o�̓t�@�C��������
	 */
	@Override
	public String create(FileSettingModel outboundSettingInfo, Map<String, List<LineDataInfo>> dataInfoMap) {
		String lineFeed = outboundSettingInfo.getLineFeedCode();
		List<LineDataInfo> writeLineInfoList = new ArrayList<>();
		// head��
		LineDataInfo writeHeaderData = new LineDataInfo(outboundSettingInfo.getHeaderDataGroup());
		writeLineInfoList.add(writeHeaderData);
		// body��
		int allTotalCount = 0;
		int allTotalAmount = 0;
		List<LineDataInfo> bodyDataListInfo = dataInfoMap.get(IAttributes.TYPE_NAME_BODY);
		LineDataInfo writeBodyData = new LineDataInfo(outboundSettingInfo.getBodyDataGroup());
		for (LineDataInfo bodyListInfo : bodyDataListInfo) {
			LineDataInfo writeLineInfo = writeBodyData.deepCopy();
			convertBodyByRefId(writeLineInfo, bodyListInfo);
			writeLineInfoList.add(writeLineInfo);
			allTotalCount++;
			allTotalAmount += bodyListInfo.getLongValueByKey(IAttributes.DATA_KEY_BILLING_AMOUNT);
		}
		// trailer��
		LineDataInfo writeTrailerData = new LineDataInfo(outboundSettingInfo.getTrailerDataGroup());
		writeTrailerData.setValueByKey(IAttributes.DATA_KEY_TOTAL_COUNT, allTotalCount);
		writeTrailerData.setValueByKey(IAttributes.DATA_KEY_TOTAL_AMOUNT, allTotalAmount);
		writeLineInfoList.add(writeTrailerData);
		// end��
		LineDataInfo writeEndData = new LineDataInfo(outboundSettingInfo.getEndDataGroup());
		writeLineInfoList.add(writeEndData);
		// �t�@�C���ϊ�
		BaseConverter performer = BaseConverter.getPerformer(outboundSettingInfo.getDelimiter());
		List<String> writeDataList = performer.concatData(writeLineInfoList, outboundSettingInfo.getEnclosure());
		StringBuilder sb = new StringBuilder();
		for (String writeData : writeDataList) {
			sb.append(writeData).append(lineFeed);
		}
		return sb.toString();
	}
}
