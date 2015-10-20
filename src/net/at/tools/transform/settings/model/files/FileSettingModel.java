package net.at.tools.transform.settings.model.files;

import java.util.ArrayList;
import java.util.List;

import net.at.tools.transform.IAttributes;
import net.at.tools.transform.settings.model.BaseSettingModel;

import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class FileSettingModel extends BaseSettingModel {
    /** ID(一意) */
	private String id = "";
    /** ファイル名 */
	private String nameRegix = "";
    /** ファイル名(和名) */
	private String title = "";
    /** 区切り文字 */
	private String delimiter = "";
    /** 囲み文字 */
	private String enclosure = "";
    /** ファイル形式 */
	private String fileFormat = "";
    /** 文字コード */
	private String charCode = "";
	/** 改行文字 */
	private String lineFeedCode = "";
	/** 親データグループID */
	private String dataGroupBaseId = "";
    /** データグループリスト */
	private List<DataGroupSettingModel> dataGroupList = new ArrayList<>();

    /**
     * コンストラクタ
     * @param node 要素情報
     */
	public FileSettingModel(Node node) {
		setSettingsInfo((Element)node);
	}

	/**
	 * typeからDataGroupを取得
	 * @param groupName
	 * @return
	 */
	public DataGroupSettingModel getDataGroup(String groupType) {
		for (DataGroupSettingModel dataGroupInfo : dataGroupList) {
			if (dataGroupInfo.getType().equals(groupType)) {
				return dataGroupInfo;
			}
		}
		return null;
	}
	public DataGroupSettingModel getHeaderDataGroup() {
		return getDataGroup(IAttributes.TYPE_NAME_HEADER);
	}
	public DataGroupSettingModel getBodyDataGroup() {
		return getDataGroup(IAttributes.TYPE_NAME_BODY);
	}
	public DataGroupSettingModel getTrailerDataGroup() {
		return getDataGroup(IAttributes.TYPE_NAME_TRAILER);
	}
	public DataGroupSettingModel getEndDataGroup() {
		return getDataGroup(IAttributes.TYPE_NAME_END);
	}

	/***** getter/setter *****/
	/**
	 * ID(一意)を取得します。
	 * @return ID(一意)
	 */
	public String getId() {
	    return id;
	}

	/**
	 * ID(一意)を設定します。
	 * @param id ID(一意)
	 */
	public void setId(String id) {
	    this.id = id;
	}

	/**
	 * 変換後のファイル名を取得します。
	 * @return ファイル名
	 */
	public String getTransratedName() {
	    return nameRegix;
	}

	/**
	 * ファイル名を取得します。
	 * @return ファイル名
	 */
	public String getNameRegix() {
	    return nameRegix;
	}
	/**
	 * ファイル名を設定します。
	 * @param nameRegix ファイル名
	 */
	public void setNameRegix(String nameRegix) {
	    this.nameRegix = nameRegix;
	}
	/**
	 * ファイル名(和名)を取得します。
	 * @return ファイル名(和名)
	 */
	public String getTitle() {
	    return title;
	}
	/**
	 * ファイル名(和名)を設定します。
	 * @param title ファイル名(和名)
	 */
	public void setTitle(String title) {
	    this.title = title;
	}
	/**
	 * 区切り文字を取得します。
	 * @return 区切り文字
	 */
	public String getDelimiter() {
	    return delimiter;
	}
	/**
	 * 区切り文字を設定します。
	 * @param delimiter 区切り文字
	 */
	public void setDelimiter(String delimiter) {
	    this.delimiter = delimiter;
	}
	/**
	 * 囲み文字を取得します。
	 * @return 囲み文字
	 */
	public String getEnclosure() {
	    return enclosure;
	}

	/**
	 * 囲み文字を設定します。
	 * @param enclosure 囲み文字
	 */
	public void setEnclosure(String enclosure) {
	    this.enclosure = enclosure;
	}

	/**
	 * ファイル形式を取得します。
	 * @return ファイル形式
	 */
	public String getFileFormat() {
	    return fileFormat;
	}
	/**
	 * ファイル形式を設定します。
	 * @param fileFormat ファイル形式
	 */
	public void setFileFormat(String fileFormat) {
	    this.fileFormat = fileFormat;
	}
	/**
	 * 文字コードを取得します。
	 * @return 文字コード
	 */
	public String getCharCode() {
	    return charCode;
	}
	/**
	 * 文字コードを設定します。
	 * @param charCode 文字コード
	 */
	public void setCharCode(String charCode) {
	    this.charCode = charCode;
	}
	/**
	 * 改行文字を取得します。
	 * @return 改行文字
	 */
	public String getLineFeedCode() {
	    return lineFeedCode;
	}
	/**
	 * 改行文字を設定します。
	 * @param lineFeedCode 改行文字
	 */
	public void setLineFeedCode(String lineFeedCode) {
		if (lineFeedCode == null || lineFeedCode.isEmpty()) {
			lineFeedCode = System.getProperty("line.separator");
		} else if (lineFeedCode.equals("CRLF")) {
			lineFeedCode = "\r\n";
		} else if (lineFeedCode.equals("LF")) {
			lineFeedCode = "\n";
		}
	    this.lineFeedCode = lineFeedCode;
	}

	/**
	 * 親データグループIDを取得します。
	 * @return 親データグループID
	 */
	public String getDataGroupBaseId() {
	    return dataGroupBaseId;
	}

	/**
	 * 親データグループIDを設定します。
	 * @param dataGroupBaseId 親データグループID
	 */
	public void setDataGroupBaseId(String dataGroupBaseId) {
	    this.dataGroupBaseId = dataGroupBaseId;
	}

	/**
	 * データグループリストを取得します。
	 * @return データグループリスト
	 */
	public List<DataGroupSettingModel> getDataGroupList() {
	    return dataGroupList;
	}
	/**
	 * データグループリストを設定します。
	 * @param dataGroupList データグループリスト
	 */
	public void setDataGroupList(List<DataGroupSettingModel> dataGroupList) {
	    this.dataGroupList = dataGroupList;
	}
}
