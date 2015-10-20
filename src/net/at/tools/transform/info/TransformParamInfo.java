package net.at.tools.transform.info;

import java.nio.file.Path;

import net.at.tools.transform.settings.model.files.FileSettingModel;

public class TransformParamInfo {
    /** 読込ファイル情報 */
	private Path readingFileInfo = null;
    /** 入力ファイル設定情報 */
	private FileSettingModel inboundSettingInfo = null;
    /** 出力ファイル設定情報 */
	private FileSettingModel outboundSettingInfo = null;
	/** 出力フォルダ */
	protected Path outboundFolederPath = null;

	/**
	 * コンストラクタ
	 * @param readingFileInfo 読込ファイル情報
	 * @param inboundSettingInfo 入力ファイル設定情報
	 * @param outboundSettingInfo 出力ファイル設定情報
	 * @param outboundFolederPath 出力フォルダ
	 */
	public TransformParamInfo(Path readingFileInfo, FileSettingModel inboundSettingInfo, FileSettingModel outboundSettingInfo, Path outboundFolederPath) {
		this.readingFileInfo = readingFileInfo;
		this.inboundSettingInfo = inboundSettingInfo;
		this.outboundSettingInfo = outboundSettingInfo;
		this.outboundFolederPath = outboundFolederPath;
	}


	/**
	 * 読込ファイル情報を取得します。
	 * @return 読込ファイル情報
	 */
	public Path getReadingFileInfo() {
	    return readingFileInfo;
	}
	/**
	 * 読込ファイル情報を設定します。
	 * @param readingFileInfo 読込ファイル情報
	 */
	public void setReadingFileInfo(Path readingFileInfo) {
	    this.readingFileInfo = readingFileInfo;
	}
	/**
	 * 入力ファイル設定情報を取得します。
	 * @return 入力ファイル設定情報
	 */
	public FileSettingModel getInboundSettingInfo() {
	    return inboundSettingInfo;
	}
	/**
	 * 入力ファイル設定情報を設定します。
	 * @param inboundSettingInfo 入力ファイル設定情報
	 */
	public void setInboundSettingInfo(FileSettingModel inboundSettingInfo) {
	    this.inboundSettingInfo = inboundSettingInfo;
	}
	/**
	 * 出力ファイル設定情報を取得します。
	 * @return 出力ファイル設定情報
	 */
	public FileSettingModel getOutboundSettingInfo() {
	    return outboundSettingInfo;
	}
	/**
	 * 出力ファイル設定情報を設定します。
	 * @param outboundSettingInfo 出力ファイル設定情報
	 */
	public void setOutboundSettingInfo(FileSettingModel outboundSettingInfo) {
	    this.outboundSettingInfo = outboundSettingInfo;
	}

	/**
	 * 出力フォルダを取得します。
	 * @return 出力フォルダ
	 */
	public Path getOutboundFolederPath() {
	    return outboundFolederPath;
	}

	/**
	 * 出力フォルダを設定します。
	 * @param outboundFoleder 出力フォルダ
	 */
	public void setOutboundFolederPath(Path outboundFolederPath) {
	    this.outboundFolederPath = outboundFolederPath;
	}

}
