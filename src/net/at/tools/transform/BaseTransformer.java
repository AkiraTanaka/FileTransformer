package net.at.tools.transform;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import net.at.tools.transform.converter.BaseConverter;
import net.at.tools.transform.info.DataInfo;
import net.at.tools.transform.info.LineDataInfo;
import net.at.tools.transform.info.TransformParamInfo;
import net.at.tools.transform.settings.model.files.DataGroupSettingModel;
import net.at.tools.transform.settings.model.files.FileSettingModel;
import net.at.tools.transform.util.StringUtil;
import net.at.tools.transform.util.file.BaseTextFileUtil;

import org.apache.log4j.Logger;

public abstract class BaseTransformer {
	/** ���O */
	static Logger m_logger = Logger.getLogger(BaseTransformer.class);

	/** �Ǎ��t�@�C����� */
	protected Path m_readingFileInfo = null;
	/** ���̓t�@�C���ݒ��� */
	protected FileSettingModel m_inboundSettingInfo = null;
	/** �o�̓t�@�C���ݒ��� */
	protected FileSettingModel m_outboundSettingInfo = null;
	/** �o�̓t�H���_ */
	protected Path m_outboundFolederPath = null;

	/**
	 * �R���X�g���N�^
	 * @param readingFileInfo �Ǎ��t�@�C�����
	 * @param inboundSettingInfo ���̓t�@�C���ݒ���
	 * @param outboundSettingInfo �o�̓t�@�C���ݒ���
	 */
	public BaseTransformer(TransformParamInfo paramInfo) {
		m_readingFileInfo = paramInfo.getReadingFileInfo();
		m_inboundSettingInfo = paramInfo.getInboundSettingInfo();
		m_outboundSettingInfo = paramInfo.getOutboundSettingInfo();
		m_outboundFolederPath = paramInfo.getOutboundFolederPath();
	}

	/*** ���ۃ��\�b�h ***/
	/**
	 * ���̓t�@�C���̉��
	 * @throws IOException
	 * @throws FileNotFoundException
	 */
	public abstract Map<String, List<LineDataInfo>> analyze() throws FileNotFoundException, IOException;
	/**
	 * �t�@�C���o��
	 * @param outboundSettingInfo �o�̓t�@�C���ݒ���
	 * @param dataInfoMap �o�̓t�@�C���f�[�^�}�b�v
	 * @return �o�̓t�@�C��������
	 */
	public abstract String create(FileSettingModel outboundSettingInfo, Map<String, List<LineDataInfo>> dataInfoMap);

	/*** ��ۃ��\�b�h ***/
	/**
	 * �t�@�C���ϊ�
	 */
	public void transform() {
		Map<String, List<LineDataInfo>> dataInfoMap = null;
		try {
			dataInfoMap = analyze();
		} catch (IOException ex) {
			m_logger.error("�t�@�C���̓Ǎ��Ɏ��s���܂���:" + m_readingFileInfo.toString(), ex);
			return;
		}
		String text = create(m_outboundSettingInfo, dataInfoMap);
		String fileName = StringUtil.convertSpecificWord(m_outboundSettingInfo.getNameRegix());
		String fileFullPath = m_outboundFolederPath.toAbsolutePath().toString() + File.separator + fileName;
		try {
			BaseTextFileUtil.writeTextFile(fileFullPath, text);
		} catch (IOException ex) {
			m_logger.error("�t�@�C���̏����Ɏ��s���܂���:" + fileFullPath, ex);
		}
	}

	/**
	 * �f�[�^����
	 * @param typeName �f�[�^�O���[�v��ʖ�
	 * @param text �f�[�^
	 * @param dataInfoMap �f�[�^���}�b�v
	 */
	protected void separateData(String typeName, List<String> textList, Map<String, List<LineDataInfo>> dataInfoMap) {
		DataGroupSettingModel dataGroupInfo = m_inboundSettingInfo.getDataGroup(typeName);
		if (dataGroupInfo != null) {
			List<LineDataInfo> dataInfoList = new ArrayList<>();
			String charCode = m_inboundSettingInfo.getCharCode();
			BaseConverter performer = BaseConverter.getPerformer(m_inboundSettingInfo.getDelimiter());
			if (performer != null) {
				for (String text : textList) {
					LineDataInfo lineInfo = new LineDataInfo();
					List<DataInfo> tempDataInfoList = performer.separateData(text, dataGroupInfo.getDataList(), charCode, m_inboundSettingInfo.getEnclosure());
					lineInfo.setDataInfoList(tempDataInfoList);
					dataInfoList.add(lineInfo);
				}
			}
			dataInfoMap.put(typeName, dataInfoList);
		}
	}

	/***** �ϊ����� *****/
	/** ������ID�̒l���Q�Ƃ���ۂɐݒ� */
	private static final String SAME_REF_ID = "�ySameName�z";

	/**
	 * body����ϊ�
	 * @param writeLineInfo �쐬�f�[�^���
	 * @param refLineInfo ���f�[�^���
	 */
	protected void convertBodyByRefId(LineDataInfo writeLineInfo, LineDataInfo refLineInfo) {
		for (DataInfo writeData : writeLineInfo.getDataInfoList()) {
			String refId = writeData.getRefId();
			if (refId != null && refId.isEmpty() == false) {
				if (refId.equals(SAME_REF_ID)) {
					refId = writeData.getKey();
				}
				String refValue = refLineInfo.getValueByKey(refId);
				writeData.setValueCheckedSize(refValue);
			}
		}
	}
}
