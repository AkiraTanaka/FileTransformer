package net.at.tools.transform.converter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import net.at.tools.transform.info.DataInfo;
import net.at.tools.transform.info.LineDataInfo;
import net.at.tools.transform.settings.model.files.DataSettingModel;

import org.apache.log4j.Logger;

public class FLFConverter extends BaseConverter {
	/** ���O */
	static Logger m_logger = Logger.getLogger(FLFConverter.class);

	/**
	 * �R���X�g���N�^
	 */
	protected FLFConverter() {
		super("");
	}

	/**
	 * ������𕪊�
	 * @param text �������镶����
	 * @param enclosure �͂ݕ���
	 * @return �������������񃊃X�g
	 */
	@Override
	public List<String> separate(String text, String enclosure) {
		List<String> textList = new ArrayList<>();
		return textList;
	}

	/**
	 * �ݒ���ɏ]���ĕ�����𕪊�
	 * @param text ������
	 * @param dataSettingInfoList �ݒ���
	 * @param charCode �����R�[�h
	 * @param enclosure �͂ݕ���
	 * @return ������f�[�^���
	 * @throws Exception
	 */
	@Override
	public List<DataInfo> separateData(String text, List<DataSettingModel> dataSettingInfoList, String charCode, String enclosure) {
		List<DataInfo> dataInfoList = new ArrayList<>();
		int startIndex = 0;
		byte[] byteTextList = text.getBytes();
		for (DataSettingModel dataSettingInfo : dataSettingInfoList) {
			int maxByte = dataSettingInfo.getMaxByte();
			int endIndex = startIndex + maxByte;
			if (endIndex > byteTextList.length) {
				m_logger.error("�t�@�C���̕��������ݒ�̕������̕��������ł� : [������]" + byteTextList.length + "[�J�n�ʒu]" + startIndex + "[�I���ʒu]" + endIndex);
				break;
			} else {
					byte[] byteData = Arrays.copyOfRange(byteTextList, startIndex, endIndex);
					String dataValue = new String(byteData);
					dataInfoList.add(new DataInfo(dataValue, dataSettingInfo));
			}
			startIndex = endIndex;
		}
		return dataInfoList;
	}


	/**
	 * �������X�g������
	 * @param lineInfoList �������X�g
	 * @param enclosure �͂ݕ���
	 * @return �������������񃊃X�g
	 */
	@Override
	public List<String> concatData(List<LineDataInfo> lineInfoList, String enclosure) {
		List<String> dataConcatList = new ArrayList<>();
		for (LineDataInfo lineInfo : lineInfoList) {
			StringBuilder sb = new StringBuilder();
			for (DataInfo dataInfo : lineInfo.getDataInfoList()) {
				sb.append(getFilledValue(dataInfo));
			}
			dataConcatList.add(sb.toString());
		}
		return dataConcatList;
	}
}
