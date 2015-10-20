package net.at.tools.transform.converter;

import java.util.ArrayList;
import java.util.List;

import net.at.tools.transform.info.DataInfo;
import net.at.tools.transform.info.LineDataInfo;
import net.at.tools.transform.settings.model.files.DataSettingModel;
import net.at.tools.transform.util.StringUtil;

public abstract class BaseConverter {
	/** 実行クラスマップ */
	private static final List<BaseConverter> performerList = new ArrayList<>();
	/** 区切り文字 */
	protected String m_delimiter = "";

	static {
		performerList.add(new FLFConverter());
		performerList.add(new CSVConverter());
	}

	/**
	 * コンストラクタ
	 * @param delimiter 区切り文字
	 */
	public BaseConverter(String delimiter) {
		m_delimiter = delimiter;
	}

	/***** 抽象メソッド *****/
	/**
	 * 文字列を分割
	 * @param text 分割する文字列
	 * @param enclosure 囲み文字
	 * @return 分割した文字列リスト
	 */
	public abstract List<String> separate(String text, String enclosure);

	/**
	 * 設定情報に従って文字列を分割
	 * @param text 文字列
	 * @param dataSettingInfoList 設定情報
	 * @param charCode 文字コード
	 * @param enclosure 囲み文字
	 * @return 分割後データ情報
	 */
	public abstract List<DataInfo> separateData(String text, List<DataSettingModel> dataSettingInfoList, String charCode, String enclosure);

	/***** 処理メソッド *****/
	/**
	 * 文字リストを結合
	 * @param lineInfoList 文字リスト
	 * @param enclosure 囲み文字
	 * @return 結合した文字列リスト
	 */
	public List<String> concatData(List<LineDataInfo> lineInfoList, String enclosure) {
		List<String> dataConcatList = new ArrayList<>();
		for (LineDataInfo lineInfo : lineInfoList) {
			StringBuilder sb = new StringBuilder();
			for (DataInfo dataInfo : lineInfo.getDataInfoList()) {
				if (sb.length() > 0) {
					sb.append(m_delimiter);
				}
				String value = dataInfo.getValue();
				value = enclosure + value + enclosure;
				sb.append(value);
			}
			dataConcatList.add(sb.toString());
		}
		return dataConcatList;
	}

	/***** staticメソッド *****/
	/**
	 * デリミタに応じて実行クラスを返す
	 * @param delimiter デリミタ
	 * @return 実行クラス
	 */
	public static BaseConverter getPerformer(String delimiter) {
		for (BaseConverter performer : performerList) {
			if (performer.m_delimiter.equals(delimiter)) {
				return performer;
			}
		}
		return null;
	}

	/***** 汎用メソッド *****/
	public String getFilledValue(DataInfo dataInfo) {
		String value = dataInfo.getValue();
		int maxByte = dataInfo.getMaxByte();
		if (dataInfo.isNumber()) {
			value = StringUtil.lpad(value, maxByte, "0");
		} else if (dataInfo.isSingleChar()) {
			value = StringUtil.rpad(value, maxByte, " ");
		} else {
			value = StringUtil.rpad(value, maxByte, "　");
		}
		return value;
	}
}