package net.at.tools.transform.info;

import net.at.tools.transform.IAttributes;
import net.at.tools.transform.annotation.CopyIgnoreAnnotation;
import net.at.tools.transform.settings.model.files.DataSettingModel;
import net.at.tools.transform.util.ReflectionUtil;
import net.at.tools.transform.util.StringUtil;

import org.apache.log4j.Logger;

public class DataInfo {
	/** ログ */
	@CopyIgnoreAnnotation
	static Logger m_logger = Logger.getLogger(DataInfo.class);

    /** 値 */
	private String value = "";
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
	/** 参照データID */
	private String refId = "";
	/** トリムフラグ */
	private boolean trimFlag = false;

	/**
	 * コンストラクタ
	 * @param settings 設定情報
	 */
	public DataInfo() {	}
	public DataInfo(DataSettingModel settings) {
		this(StringUtil.convertSpecificWord(settings.getFixedValue()), settings);
	}
	public DataInfo(String value, DataSettingModel settings) {
		if (settings != null) {
			try {
				ReflectionUtil.copyFieldValue(settings, this);
			} catch (Exception ex) {
				m_logger.error("設定ファイル情報を設定できません: " + settings.getName(), ex);
			}
		} else {
			m_logger.error("設定ファイル情報がありません");
		}
		this.value = value;
	}

	/**
	 * ディープコピー
	 */
	public DataInfo deepCopy() {
		DataInfo dataInfo = new DataInfo();
		try {
			ReflectionUtil.copyFieldValue(this, dataInfo);
		} catch (SecurityException ex) {
			m_logger.error("コピーに失敗しました: ", ex);
		}
		return dataInfo;
	}

	/**
	 * 数値かどうか判定
	 * @return 数値かどうか
	 */
	public boolean isNumber() {
		if (this.type.equals(IAttributes.DATA_TYPE_NUMBER)) {
			return true;
		}
		return false;
	}
	/**
	 * 半角文字列かどうか判定
	 * @return 半角文字列のデータかどうか
	 */
	public boolean isSingleChar() {
		if (this.type.equals(IAttributes.DATA_TYPE_SINGLE_CHAR)) {
			return true;
		}
		return false;
	}

	/**
	 * 数値を取得
	 * @return 数値
	 */
	public long getLongValue() {
		return convertLongValue(this.value);
	}

	/**
	 * 数値に変換
	 * @param text テキスト
	 * @return 数値
	 */
	public long convertLongValue(String text) {
		long value = 0;
		try {
			value = Long.parseLong(text);
		} catch(NumberFormatException ex) {
			m_logger.error("数字に変換できません : [" + this.name + "] " + text, ex);
		}
		return value;
	}

	@Override
	public String toString() {
		return key + ":" + value;
	};

	/**
	 * 値を取得します。
	 * @return 値
	 */
	public String getValue() {
	    return value;
	}
	/**
	 * 値を設定します。
	 * @param value 値
	 */
	public void setValue(String value) {
		this.value = value;
	}

	/**
	 * サイズチェックをして値を設定
	 * @param value 値
	 */
	public void setValueCheckedSize(String value) {
		setValueCheckedSize(value, this.trimFlag);
	}
	public void setValueCheckedSize(String value, boolean isTrim) {
		if (isTrim) {
			value = value.trim();
			// 数字の場合は数字に変換して余計な０をトリムする
			if (isNumber()) {
				long longValue = convertLongValue(value);
				value = String.valueOf(longValue);
			}
		}
		if (value.getBytes().length > maxByte) {
			m_logger.warn("最大サイズを超えています : [key]" + this.key + "[設定値]" + value + "[最大数] " + maxByte);
		} else {
			setValue(value);
		}
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
}
