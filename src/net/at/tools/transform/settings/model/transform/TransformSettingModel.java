package net.at.tools.transform.settings.model.transform;

import net.at.tools.transform.settings.model.BaseSettingModel;

import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class TransformSettingModel extends BaseSettingModel {
    /** 入力ファイル設定キー */
	private String inboundFile = "";
    /** 出力ファイル設定キー */
	private String outboundFile = "";
    /** 変換クラス */
	private String transformerClass = "";

    /**
     * コンストラクタ
     * @param node 要素情報
     */
	public TransformSettingModel(Node node) {
		setSettingsInfo((Element)node);
	}

	/**
	 * 入力ファイル設定キーを取得します。
	 * @return 入力ファイル設定キー
	 */
	public String getInboundFile() {
	    return inboundFile;
	}

	/**
	 * 入力ファイル設定キーを設定します。
	 * @param inboundFile 入力ファイル設定キー
	 */
	public void setInboundFile(String inboundFile) {
	    this.inboundFile = inboundFile;
	}

	/**
	 * 出力ファイル設定キーを取得します。
	 * @return 出力ファイル設定キー
	 */
	public String getOutboundFile() {
	    return outboundFile;
	}

	/**
	 * 出力ファイル設定キーを設定します。
	 * @param outboundFile 出力ファイル設定キー
	 */
	public void setOutboundFile(String outboundFile) {
	    this.outboundFile = outboundFile;
	}

	/**
	 * 変換クラスを取得します。
	 * @return 変換クラス
	 */
	public String getTransformerClass() {
	    return transformerClass;
	}

	/**
	 * 変換クラスを設定します。
	 * @param transformerClass 変換クラス
	 */
	public void setTransformerClass(String transformerClass) {
	    this.transformerClass = transformerClass;
	}

}
