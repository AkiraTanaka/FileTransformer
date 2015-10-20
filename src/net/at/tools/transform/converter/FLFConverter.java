package net.at.tools.transform.converter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import net.at.tools.transform.info.DataInfo;
import net.at.tools.transform.info.LineDataInfo;
import net.at.tools.transform.settings.model.files.DataSettingModel;

import org.apache.log4j.Logger;

public class FLFConverter extends BaseConverter {
	/** ログ */
	static Logger m_logger = Logger.getLogger(FLFConverter.class);

	/**
	 * コンストラクタ
	 */
	protected FLFConverter() {
		super("");
	}

	/**
	 * 文字列を分割
	 * @param text 分割する文字列
	 * @param enclosure 囲み文字
	 * @return 分割した文字列リスト
	 */
	@Override
	public List<String> separate(String text, String enclosure) {
		List<String> textList = new ArrayList<>();
		return textList;
	}

	/**
	 * 設定情報に従って文字列を分割
	 * @param text 文字列
	 * @param dataSettingInfoList 設定情報
	 * @param charCode 文字コード
	 * @param enclosure 囲み文字
	 * @return 分割後データ情報
	 * @throws Exception
	 */
	@Override
	public List<DataInfo> separateData(String text, List<DataSettingModel> dataSettingInfoList, String charCode, String enclosure) {
		List<DataInfo> dataInfoList = new ArrayList<>();
		int startIndex = 0;
		byte[] byteTextList = text.getBytes();
		for (DataSettingModel dataSettingInfo : dataSettingInfoList) {
			int maxByte = dataSettingInfo.getMaxByte();
			int endIndex = startIndex + maxByte;
			if (endIndex > byteTextList.length) {
				m_logger.error("ファイルの文字数より設定の文字数の方が長いです : [文字数]" + byteTextList.length + "[開始位置]" + startIndex + "[終了位置]" + endIndex);
				break;
			} else {
					byte[] byteData = Arrays.copyOfRange(byteTextList, startIndex, endIndex);
					String dataValue = new String(byteData);
					dataInfoList.add(new DataInfo(dataValue, dataSettingInfo));
			}
			startIndex = endIndex;
		}
		return dataInfoList;
	}


	/**
	 * 文字リストを結合
	 * @param lineInfoList 文字リスト
	 * @param enclosure 囲み文字
	 * @return 結合した文字列リスト
	 */
	@Override
	public List<String> concatData(List<LineDataInfo> lineInfoList, String enclosure) {
		List<String> dataConcatList = new ArrayList<>();
		for (LineDataInfo lineInfo : lineInfoList) {
			StringBuilder sb = new StringBuilder();
			for (DataInfo dataInfo : lineInfo.getDataInfoList()) {
				sb.append(getFilledValue(dataInfo));
			}
			dataConcatList.add(sb.toString());
		}
		return dataConcatList;
	}
}
