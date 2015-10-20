package net.at.tools.transform.converter;

import java.util.ArrayList;
import java.util.List;

import net.at.tools.transform.info.DataInfo;
import net.at.tools.transform.info.LineDataInfo;
import net.at.tools.transform.settings.model.files.DataSettingModel;
import net.at.tools.transform.util.StringUtil;

public abstract class BaseConverter {
	/** ���s�N���X�}�b�v */
	private static final List<BaseConverter> performerList = new ArrayList<>();
	/** ��؂蕶�� */
	protected String m_delimiter = "";

	static {
		performerList.add(new FLFConverter());
		performerList.add(new CSVConverter());
	}

	/**
	 * �R���X�g���N�^
	 * @param delimiter ��؂蕶��
	 */
	public BaseConverter(String delimiter) {
		m_delimiter = delimiter;
	}

	/***** ���ۃ��\�b�h *****/
	/**
	 * ������𕪊�
	 * @param text �������镶����
	 * @param enclosure �͂ݕ���
	 * @return �������������񃊃X�g
	 */
	public abstract List<String> separate(String text, String enclosure);

	/**
	 * �ݒ���ɏ]���ĕ�����𕪊�
	 * @param text ������
	 * @param dataSettingInfoList �ݒ���
	 * @param charCode �����R�[�h
	 * @param enclosure �͂ݕ���
	 * @return ������f�[�^���
	 */
	public abstract List<DataInfo> separateData(String text, List<DataSettingModel> dataSettingInfoList, String charCode, String enclosure);

	/***** �������\�b�h *****/
	/**
	 * �������X�g������
	 * @param lineInfoList �������X�g
	 * @param enclosure �͂ݕ���
	 * @return �������������񃊃X�g
	 */
	public List<String> concatData(List<LineDataInfo> lineInfoList, String enclosure) {
		List<String> dataConcatList = new ArrayList<>();
		for (LineDataInfo lineInfo : lineInfoList) {
			StringBuilder sb = new StringBuilder();
			for (DataInfo dataInfo : lineInfo.getDataInfoList()) {
				if (sb.length() > 0) {
					sb.append(m_delimiter);
				}
				String value = dataInfo.getValue();
				value = enclosure + value + enclosure;
				sb.append(value);
			}
			dataConcatList.add(sb.toString());
		}
		return dataConcatList;
	}

	/***** static���\�b�h *****/
	/**
	 * �f���~�^�ɉ����Ď��s�N���X��Ԃ�
	 * @param delimiter �f���~�^
	 * @return ���s�N���X
	 */
	public static BaseConverter getPerformer(String delimiter) {
		for (BaseConverter performer : performerList) {
			if (performer.m_delimiter.equals(delimiter)) {
				return performer;
			}
		}
		return null;
	}

	/***** �ėp���\�b�h *****/
	public String getFilledValue(DataInfo dataInfo) {
		String value = dataInfo.getValue();
		int maxByte = dataInfo.getMaxByte();
		if (dataInfo.isNumber()) {
			value = StringUtil.lpad(value, maxByte, "0");
		} else if (dataInfo.isSingleChar()) {
			value = StringUtil.rpad(value, maxByte, " ");
		} else {
			value = StringUtil.rpad(value, maxByte, "�@");
		}
		return value;
	}
}