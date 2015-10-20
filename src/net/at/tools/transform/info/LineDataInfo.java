package net.at.tools.transform.info;

import java.util.ArrayList;
import java.util.List;

import net.at.tools.transform.settings.model.files.DataGroupSettingModel;
import net.at.tools.transform.settings.model.files.DataSettingModel;

public class LineDataInfo {
    /** ���ڃL�[(���) */
	private String key = "";
    /** ���� */
	private int lineNo = -1;
    /** �f�[�^�ꗗ */
	private List<DataInfo> dataInfoList = new ArrayList<>();

	/** �R���X�g���N�^ */
	public LineDataInfo() {
	}
	public LineDataInfo(DataGroupSettingModel groupInfo) {
		if (groupInfo != null) {
			for (DataSettingModel dataSettingInfo : groupInfo.getDataList()) {
				DataInfo dataInfo = new DataInfo(dataSettingInfo);
				dataInfoList.add(dataInfo);
			}
		}
	}

	/**
	 * �f�[�^�L�[����f�[�^�����擾
	 * @param key �f�[�^�L�[
	 * @return �f�[�^���
	 */
	public DataInfo getDataInfoByKey(String key) {
		for (DataInfo dataInfo : dataInfoList) {
			if (dataInfo.getKey().equals(key)) {
				return dataInfo;
			}
		}
		return null;
	}
	/**
	 * �f�[�^�L�[����f�[�^�l���擾
	 * @param key
	 * @return �f�[�^�l
	 */
	public String getValueByKey(String key) {
		DataInfo datainfo = getDataInfoByKey(key);
		String value = "";
		if (datainfo != null) {
			value = datainfo.getValue();
		}
		return value;
	}
	public long getLongValueByKey(String key) {
		DataInfo datainfo = getDataInfoByKey(key);
		long longValue = 0;
		if (datainfo != null) {
			longValue = datainfo.getLongValue();
		}
		return longValue;
	}

	/**
	 * �f�[�^�L�[����f�[�^�l��ݒ�
	 * @param key �f�[�^�L�[
	 * @param value �f�[�^�l
	 */
	public void setValueByKey(String key, long value) {
		setValueByKey(key, value, false);
	}
	public void setValueByKey(String key, long value, boolean isTrim) {
		setValueByKey(key, String.valueOf(value), isTrim);
	}
	public void setValueByKey(String key, String value) {
		setValueByKey(key, value, false);
	}
	public void setValueByKey(String key, String value, boolean isTrim) {
		DataInfo datainfo = getDataInfoByKey(key);
		if (datainfo != null) {
			datainfo.setValue(value);
		}
	}
	/**
	 * �f�[�^�L�[����f�[�^���l��ݒ�
	 * @param key �f�[�^�L�[
	 * @param value �f�[�^���l
	 */
	public void setNumValueByKey(String key, String value) {
		value = value.trim();
		setValueByKey(key, Long.parseLong(value));
	}

	/**
	 * �f�B�[�v�R�s�[
	 */
	public LineDataInfo deepCopy() {
		LineDataInfo cloneData = new LineDataInfo();
		List<DataInfo> infoList = new ArrayList<>();
		for (DataInfo dataInfo : this.getDataInfoList()) {
			infoList.add(dataInfo.deepCopy());
		}
		cloneData.setDataInfoList(infoList);
		return cloneData;
	}

	/**
	 * ���ڃL�[(���)���擾���܂��B
	 * @return ���ڃL�[(���)
	 */
	public String getKey() {
	    return key;
	}
	/**
	 * ���ڃL�[(���)��ݒ肵�܂��B
	 * @param key ���ڃL�[(���)
	 */
	public void setKey(String key) {
	    this.key = key;
	}
	/**
	 * �������擾���܂��B
	 * @return ����
	 */
	public int getLineNo() {
	    return lineNo;
	}
	/**
	 * ������ݒ肵�܂��B
	 * @param lineNo ����
	 */
	public void setLineNo(int lineNo) {
	    this.lineNo = lineNo;
	}
	/**
	 * �f�[�^�ꗗ���擾���܂��B
	 * @return �f�[�^�ꗗ
	 */
	public List<DataInfo> getDataInfoList() {
	    return dataInfoList;
	}
	/**
	 * �f�[�^�ꗗ��ݒ肵�܂��B
	 * @param dataInfoList �f�[�^�ꗗ
	 */
	public void setDataInfoList(List<DataInfo> dataInfoList) {
	    this.dataInfoList = dataInfoList;
	}
}
