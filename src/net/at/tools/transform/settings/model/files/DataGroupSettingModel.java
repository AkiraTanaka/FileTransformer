package net.at.tools.transform.settings.model.files;

import java.util.ArrayList;
import java.util.List;

import net.at.tools.transform.annotation.CopyIgnoreAnnotation;
import net.at.tools.transform.info.DataInfo;
import net.at.tools.transform.settings.model.BaseSettingModel;
import net.at.tools.transform.util.ReflectionUtil;

import org.apache.log4j.Logger;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class DataGroupSettingModel extends BaseSettingModel {
	/** ログ */
	@CopyIgnoreAnnotation
	static Logger m_logger = Logger.getLogger(DataInfo.class);

    /** 種別 */
	private String type = "";
    /** 判別キーNo */
	private int primaryKeyNo = -1;
    /** 判別キー値 */
	private String primaryKeyValue = "";
	/** Data部定義 */
	@CopyIgnoreAnnotation
	private List<DataSettingModel> dataList = new ArrayList<>();

    /**
     * コンストラクタ
     * @param node 要素情報
     */
	public DataGroupSettingModel() { }
	public DataGroupSettingModel(Node node) {
		setSettingsInfo((Element)node);
	}

	/**
	 * ディープコピー
	 */
	public DataGroupSettingModel deepCopy() {
		DataGroupSettingModel newInfo = new DataGroupSettingModel();
		List<DataSettingModel> newDataList = new ArrayList<>();
		for (DataSettingModel dataInfo : this.dataList) {
			DataSettingModel newDataInfo = new DataSettingModel();
			try {
				ReflectionUtil.copyFieldValue(dataInfo, newDataInfo);
				newDataList.add(newDataInfo);
			} catch (SecurityException ex) {
				m_logger.error("コピーに失敗しました: ", ex);
				return null;
			}
		}
		newInfo.setType(this.type);
		newInfo.setPrimaryKeyNo(this.primaryKeyNo);
		newInfo.setPrimaryKeyValue(this.primaryKeyValue);
		newInfo.setDataList(newDataList);
		return newInfo;
	}

	/**
	 * 種別を取得します。
	 * @return 種別
	 */
	public String getType() {
	    return type;
	}
	/**
	 * 種別を設定します。
	 * @param type 種別
	 */
	public void setType(String type) {
	    this.type = type;
	}

	/**
	 * 判別キーNoを取得します。
	 * @return 判別キーNo
	 */
	public int getPrimaryKeyNo() {
	    return primaryKeyNo;
	}

	/**
	 * 判別キーNoを設定します。
	 * @param primaryKeyNo 判別キーNo
	 */
	public void setPrimaryKeyNo(int primaryKeyNo) {
	    this.primaryKeyNo = primaryKeyNo;
	}

	/**
	 * 判別キー値を取得します。
	 * @return 判別キー値
	 */
	public String getPrimaryKeyValue() {
	    return primaryKeyValue;
	}

	/**
	 * 判別キー値を設定します。
	 * @param primaryKeyValue 判別キー値
	 */
	public void setPrimaryKeyValue(String primaryKeyValue) {
	    this.primaryKeyValue = primaryKeyValue;
	}

	/**
	 * Data部定義を取得します。
	 * @return Data部定義
	 */
	public List<DataSettingModel> getDataList() {
	    return dataList;
	}
	/**
	 * Data部定義を設定します。
	 * @param dataList Data部定義
	 */
	public void setDataList(List<DataSettingModel> dataList) {
	    this.dataList = dataList;
	}
}