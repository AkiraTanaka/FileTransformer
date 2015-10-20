package net.at.tools.transform.settings.model.transform;

import java.util.ArrayList;
import java.util.List;

import net.at.tools.transform.settings.model.BaseSettingModel;

import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class BaseTransformSettingModel extends BaseSettingModel {
    /** ID(一意) */
	private String id = "";
    /** 入力フォルダ */
	private String inboundFolder = "";
    /** 出力フォルダ */
	private String outboundFolder = "";
    /** 変換設定情報リスト */
	private List<TransformSettingModel> transformInfoList = new ArrayList<>();

    /**
     * コンストラクタ
     * @param node 要素情報
     */
	public BaseTransformSettingModel(Node node) {
		setSettingsInfo((Element)node);
	}

	/**
	 * inboundKeyから変換設定情報を取得
	 * @param inboundKey
	 * @return 変換設定情報
	 */
	public TransformSettingModel getDataGroup(String inboundKey) {
		for (TransformSettingModel transformInfo : transformInfoList) {
			if (transformInfo.getInboundFile().equals(inboundKey)) {
				return transformInfo;
			}
		}
		return null;
	}

	/**
	 * ID(一意)を設定します。
	 * @param id ID(一意)
	 */
	public void setId(String id) {
	    this.id = id;
	}

	/**
	 * ID(一意)を取得します。
	 * @return ID(一意)
	 */
	public String getId() {
	    return id;
	}

	/**
	 * 入力フォルダを取得します。
	 * @return 入力フォルダ
	 */
	public String getInboundFolder() {
	    return inboundFolder;
	}

	/**
	 * 入力フォルダを設定します。
	 * @param inboundFolder 入力フォルダ
	 */
	public void setInboundFolder(String inboundFolder) {
	    this.inboundFolder = inboundFolder;
	}

	/**
	 * 出力フォルダを取得します。
	 * @return 出力フォルダ
	 */
	public String getOutboundFolder() {
	    return outboundFolder;
	}

	/**
	 * 出力フォルダを設定します。
	 * @param outboundFolder 出力フォルダ
	 */
	public void setOutboundFolder(String outboundFolder) {
	    this.outboundFolder = outboundFolder;
	}

	/**
	 * 変換設定情報リストを取得します。
	 * @return 変換設定情報リスト
	 */
	public List<TransformSettingModel> getTransformInfoList() {
	    return transformInfoList;
	}

	/**
	 * 変換設定情報リストを設定します。
	 * @param transformInfoList 変換設定情報リスト
	 */
	public void setTransformInfoList(List<TransformSettingModel> transformInfoList) {
	    this.transformInfoList = transformInfoList;
	}

}
