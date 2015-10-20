package net.at.tools.transform.settings.model.files;

import net.at.tools.transform.settings.model.BaseSettingModel;

import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class DataSettingModel extends BaseSettingModel {
    /** 順序 */
	private int no = -1;
    /** 項目キー(一意) */
	private String key = "";
    /** 項目名(和名) */
	private String name = "";
    /** 型 */
	private String type = "";
	/** 最大サイズ(Byte) */
	private int maxByte = -1;
	/** 固定値 */
	private String fixedValue = "";
	/** 参照データID */
	private String refId = "";
	/** トリムフラグ */
	private boolean trimFlag = false;
    /** 主キーフラグ */
	private boolean primaryKeyFlag = false;

    /**
     * コンストラクタ
     * @param node 要素情報
     */
	public DataSettingModel() { }
	public DataSettingModel(Node node) {
		setSettingsInfo((Element)node);
	}

	/**
	 * 順序を取得します。
	 * @return 順序
	 */
	public int getNo() {
	    return no;
	}
	/**
	 * 順序を設定します。
	 * @param no 順序
	 */
	public void setNo(int no) {
	    this.no = no;
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
	 * 項目名(和名)を取得します。
	 * @return 項目名(和名)
	 */
	public String getName() {
	    return name;
	}
	/**
	 * 項目名(和名)を設定します。
	 * @param name 項目名(和名)
	 */
	public void setName(String name) {
	    this.name = name;
	}
	/**
	 * 型を取得します。
	 * @return 型
	 */
	public String getType() {
	    return type;
	}
	/**
	 * 型を設定します。
	 * @param type 型
	 */
	public void setType(String type) {
	    this.type = type;
	}
	/**
	 * 最大サイズ(Byte)を取得します。
	 * @return 最大サイズ(Byte)
	 */
	public int getMaxByte() {
	    return maxByte;
	}
	/**
	 * 最大サイズ(Byte)を設定します。
	 * @param maxByte 最大サイズ(Byte)
	 */
	public void setMaxByte(int maxByte) {
	    this.maxByte = maxByte;
	}

	/**
	 * 固定値を取得します。
	 * @return 固定値
	 */
	public String getFixedValue() {
	    return fixedValue;
	}

	/**
	 * 固定値を設定します。
	 * @param fixedValue 固定値
	 */
	public void setFixedValue(String fixedValue) {
	    this.fixedValue = fixedValue;
	}

	/**
	 * 参照データIDを取得します。
	 * @return 参照データID
	 */
	public String getRefId() {
	    return refId;
	}

	/**
	 * 参照データIDを設定します。
	 * @param refId 参照データID
	 */
	public void setRefId(String refId) {
	    this.refId = refId;
	}

	/**
	 * トリムフラグを取得します。
	 * @return トリムフラグ
	 */
	public boolean isTrimFlag() {
	    return trimFlag;
	}

	/**
	 * トリムフラグを設定します。
	 * @param trimFlag トリムフラグ
	 */
	public void setTrimFlag(boolean trimFlag) {
	    this.trimFlag = trimFlag;
	}

	/**
	 * 主キーフラグを取得します。
	 * @return 主キーフラグ
	 */
	public boolean isPrimaryKeyFlag() {
	    return primaryKeyFlag;
	}

	/**
	 * 主キーフラグを設定します。
	 * @param primaryKeyFlag 主キーフラグ
	 */
	public void setPrimaryKeyFlag(boolean primaryKeyFlag) {
	    this.primaryKeyFlag = primaryKeyFlag;
	}
}