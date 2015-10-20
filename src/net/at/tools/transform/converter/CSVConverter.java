package net.at.tools.transform.converter;

import java.util.ArrayList;
import java.util.List;

import net.at.tools.transform.info.DataInfo;
import net.at.tools.transform.settings.model.files.DataSettingModel;

import org.apache.log4j.Logger;

public class CSVConverter extends BaseConverter {
	/** ログ */
	static Logger m_logger = Logger.getLogger(CSVConverter.class);

	/**
	 * コンストラクタ
	 */
	protected CSVConverter() {
		super(",");
	}

	/**
	 * 文字列を分割
	 * @param text 分割する文字列
	 * @param enclosure 囲み文字
	 * @return 分割した文字列リスト
	 */
	public List<String> separate(String text, String enclosure) {
		List<String> textList = new ArrayList<>();
		for (String splitText : text.split(m_delimiter)) {
			if (enclosure != null && enclosure.isEmpty() == false) {
				if (splitText.startsWith(enclosure)) {
					splitText = splitText.substring(1);
				}
				if (splitText.length() > 1 && splitText.endsWith(enclosure)) {
					splitText = splitText.substring(0, splitText.length()-1);
				}
			}
			textList.add(splitText);
		}
		return textList;
	}

	/**
	 * 設定情報に従って文字列を分割
	 * @param text 文字列
	 * @param dataSettingInfoList 設定情報
	 * @param charCode 文字コード
	 * @param enclosure 囲み文字
	 * @return 分割後データ情報
	 */
	@Override
	public List<DataInfo> separateData(String text, List<DataSettingModel> dataSettingInfoList, String charCode, String enclosure) {
		List<DataInfo> dataInfoList = new ArrayList<>();
		List<String> splitList = separate(text, enclosure);
		if (splitList.size() != dataSettingInfoList.size()) {
			m_logger.error("設定ファイルと数が合いません : " + "[読込ファイル] " + splitList.size() + ", [設定ファイル] " + dataSettingInfoList.size());
		} else {
			for (int i = 0; i < dataSettingInfoList.size(); i++) {
				DataSettingModel dataSettingInfo = dataSettingInfoList.get(i);
				String dataValue = splitList.get(i);
				dataInfoList.add(new DataInfo(dataValue, dataSettingInfo));
			}
		}
		return dataInfoList;
	}
}
