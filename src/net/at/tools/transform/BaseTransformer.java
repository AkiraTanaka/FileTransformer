package net.at.tools.transform;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import net.at.tools.transform.converter.BaseConverter;
import net.at.tools.transform.info.DataInfo;
import net.at.tools.transform.info.LineDataInfo;
import net.at.tools.transform.info.TransformParamInfo;
import net.at.tools.transform.settings.model.files.DataGroupSettingModel;
import net.at.tools.transform.settings.model.files.FileSettingModel;
import net.at.tools.transform.util.StringUtil;
import net.at.tools.transform.util.file.BaseTextFileUtil;

import org.apache.log4j.Logger;

public abstract class BaseTransformer {
	/** ログ */
	static Logger m_logger = Logger.getLogger(BaseTransformer.class);

	/** 読込ファイル情報 */
	protected Path m_readingFileInfo = null;
	/** 入力ファイル設定情報 */
	protected FileSettingModel m_inboundSettingInfo = null;
	/** 出力ファイル設定情報 */
	protected FileSettingModel m_outboundSettingInfo = null;
	/** 出力フォルダ */
	protected Path m_outboundFolederPath = null;

	/**
	 * コンストラクタ
	 * @param readingFileInfo 読込ファイル情報
	 * @param inboundSettingInfo 入力ファイル設定情報
	 * @param outboundSettingInfo 出力ファイル設定情報
	 */
	public BaseTransformer(TransformParamInfo paramInfo) {
		m_readingFileInfo = paramInfo.getReadingFileInfo();
		m_inboundSettingInfo = paramInfo.getInboundSettingInfo();
		m_outboundSettingInfo = paramInfo.getOutboundSettingInfo();
		m_outboundFolederPath = paramInfo.getOutboundFolederPath();
	}

	/*** 抽象メソッド ***/
	/**
	 * 入力ファイルの解析
	 * @throws IOException
	 * @throws FileNotFoundException
	 */
	public abstract Map<String, List<LineDataInfo>> analyze() throws FileNotFoundException, IOException;
	/**
	 * ファイル出力
	 * @param outboundSettingInfo 出力ファイル設定情報
	 * @param dataInfoMap 出力ファイルデータマップ
	 * @return 出力ファイル文字列
	 */
	public abstract String create(FileSettingModel outboundSettingInfo, Map<String, List<LineDataInfo>> dataInfoMap);

	/*** 具象メソッド ***/
	/**
	 * ファイル変換
	 */
	public void transform() {
		Map<String, List<LineDataInfo>> dataInfoMap = null;
		try {
			dataInfoMap = analyze();
		} catch (IOException ex) {
			m_logger.error("ファイルの読込に失敗しました:" + m_readingFileInfo.toString(), ex);
			return;
		}
		String text = create(m_outboundSettingInfo, dataInfoMap);
		String fileName = StringUtil.convertSpecificWord(m_outboundSettingInfo.getNameRegix());
		String fileFullPath = m_outboundFolederPath.toAbsolutePath().toString() + File.separator + fileName;
		try {
			BaseTextFileUtil.writeTextFile(fileFullPath, text);
		} catch (IOException ex) {
			m_logger.error("ファイルの書込に失敗しました:" + fileFullPath, ex);
		}
	}

	/**
	 * データ分割
	 * @param typeName データグループ種別名
	 * @param text データ
	 * @param dataInfoMap データ情報マップ
	 */
	protected void separateData(String typeName, List<String> textList, Map<String, List<LineDataInfo>> dataInfoMap) {
		DataGroupSettingModel dataGroupInfo = m_inboundSettingInfo.getDataGroup(typeName);
		if (dataGroupInfo != null) {
			List<LineDataInfo> dataInfoList = new ArrayList<>();
			String charCode = m_inboundSettingInfo.getCharCode();
			BaseConverter performer = BaseConverter.getPerformer(m_inboundSettingInfo.getDelimiter());
			if (performer != null) {
				for (String text : textList) {
					LineDataInfo lineInfo = new LineDataInfo();
					List<DataInfo> tempDataInfoList = performer.separateData(text, dataGroupInfo.getDataList(), charCode, m_inboundSettingInfo.getEnclosure());
					lineInfo.setDataInfoList(tempDataInfoList);
					dataInfoList.add(lineInfo);
				}
			}
			dataInfoMap.put(typeName, dataInfoList);
		}
	}

	/***** 変換処理 *****/
	/** 同名のIDの値を参照する際に設定 */
	private static final String SAME_REF_ID = "【SameName】";

	/**
	 * body部を変換
	 * @param writeLineInfo 作成データ情報
	 * @param refLineInfo 元データ情報
	 */
	protected void convertBodyByRefId(LineDataInfo writeLineInfo, LineDataInfo refLineInfo) {
		for (DataInfo writeData : writeLineInfo.getDataInfoList()) {
			String refId = writeData.getRefId();
			if (refId != null && refId.isEmpty() == false) {
				if (refId.equals(SAME_REF_ID)) {
					refId = writeData.getKey();
				}
				String refValue = refLineInfo.getValueByKey(refId);
				writeData.setValueCheckedSize(refValue);
			}
		}
	}
}
