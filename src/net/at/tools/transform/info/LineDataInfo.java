package net.at.tools.transform.info;

import java.util.ArrayList;
import java.util.List;

import net.at.tools.transform.settings.model.files.DataGroupSettingModel;
import net.at.tools.transform.settings.model.files.DataSettingModel;

public class LineDataInfo {
    /** 項目キー(一意) */
	private String key = "";
    /** 順序 */
	private int lineNo = -1;
    /** データ一覧 */
	private List<DataInfo> dataInfoList = new ArrayList<>();

	/** コンストラクタ */
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
	 * データキーからデータ情報を取得
	 * @param key データキー
	 * @return データ情報
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
	 * データキーからデータ値を取得
	 * @param key
	 * @return データ値
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
	 * データキーからデータ値を設定
	 * @param key データキー
	 * @param value データ値
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
	 * データキーからデータ数値を設定
	 * @param key データキー
	 * @param value データ数値
	 */
	public void setNumValueByKey(String key, String value) {
		value = value.trim();
		setValueByKey(key, Long.parseLong(value));
	}

	/**
	 * ディープコピー
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
	 * 項目キー(一意)を取得します。
	 * @return 項目キー(一意)
	 */
	public String getKey() {
	    return key;
	}
	/**
	 * 項目キー(一意)を設定します。
	 * @param key 項目キー(一意)
	 */
	public void setKey(String key) {
	    this.key = key;
	}
	/**
	 * 順序を取得します。
	 * @return 順序
	 */
	public int getLineNo() {
	    return lineNo;
	}
	/**
	 * 順序を設定します。
	 * @param lineNo 順序
	 */
	public void setLineNo(int lineNo) {
	    this.lineNo = lineNo;
	}
	/**
	 * データ一覧を取得します。
	 * @return データ一覧
	 */
	public List<DataInfo> getDataInfoList() {
	    return dataInfoList;
	}
	/**
	 * データ一覧を設定します。
	 * @param dataInfoList データ一覧
	 */
	public void setDataInfoList(List<DataInfo> dataInfoList) {
	    this.dataInfoList = dataInfoList;
	}
}
