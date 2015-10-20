package net.at.tools.transform.converter;

import java.util.ArrayList;
import java.util.List;

import net.at.tools.transform.info.DataInfo;
import net.at.tools.transform.settings.model.files.DataSettingModel;

import org.apache.log4j.Logger;

public class CSVConverter extends BaseConverter {
	/** ���O */
	static Logger m_logger = Logger.getLogger(CSVConverter.class);

	/**
	 * �R���X�g���N�^
	 */
	protected CSVConverter() {
		super(",");
	}

	/**
	 * ������𕪊�
	 * @param text �������镶����
	 * @param enclosure �͂ݕ���
	 * @return �������������񃊃X�g
	 */
	public List<String> separate(String text, String enclosure) {
		List<String> textList = new ArrayList<>();
		for (String splitText : text.split(m_delimiter)) {
			if (enclosure != null && enclosure.isEmpty() == false) {
				if (splitText.startsWith(enclosure)) {
					splitText = splitText.substring(1);
				}
				if (splitText.length() > 1 && splitText.endsWith(enclosure)) {
					splitText = splitText.substring(0, splitText.length()-1);
				}
			}
			textList.add(splitText);
		}
		return textList;
	}

	/**
	 * �ݒ���ɏ]���ĕ�����𕪊�
	 * @param text ������
	 * @param dataSettingInfoList �ݒ���
	 * @param charCode �����R�[�h
	 * @param enclosure �͂ݕ���
	 * @return ������f�[�^���
	 */
	@Override
	public List<DataInfo> separateData(String text, List<DataSettingModel> dataSettingInfoList, String charCode, String enclosure) {
		List<DataInfo> dataInfoList = new ArrayList<>();
		List<String> splitList = separate(text, enclosure);
		if (splitList.size() != dataSettingInfoList.size()) {
			m_logger.error("�ݒ�t�@�C���Ɛ��������܂��� : " + "[�Ǎ��t�@�C��] " + splitList.size() + ", [�ݒ�t�@�C��] " + dataSettingInfoList.size());
		} else {
			for (int i = 0; i < dataSettingInfoList.size(); i++) {
				DataSettingModel dataSettingInfo = dataSettingInfoList.get(i);
				String dataValue = splitList.get(i);
				dataInfoList.add(new DataInfo(dataValue, dataSettingInfo));
			}
		}
		return dataInfoList;
	}
}
