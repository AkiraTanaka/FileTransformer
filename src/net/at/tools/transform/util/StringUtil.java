package net.at.tools.transform.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {
	private static Map<String, String> m_convertDateWordMap = new HashMap<>();
	static {
		m_convertDateWordMap.put("\\{YYYYMMDD\\}", "yyyyMMdd");
		m_convertDateWordMap.put("\\{YYYYMM\\}", "yyyyMM");
		m_convertDateWordMap.put("\\{MMDD\\}", "MMdd");
		m_convertDateWordMap.put("\\{hhmmss\\}", "hhmmss");
	}

	/**
	 * 正規表現に該当する文字列か確認
	 * @param regix 正規表現
	 * @param text 文字列
	 * @return 結果
	 */
	public static boolean findRegixText(String regix, String text) {
		Pattern p = Pattern.compile(regix, Pattern.CASE_INSENSITIVE);
		Matcher m = p.matcher(text);
		return m.find();
	}

	/**
	 * 特定の文字を変換
	 * @param word 変換前文字
	 * @return 変換後文字
	 */
	public static String convertSpecificWord(String word) {
		// 日付の変換
		Date now = new Date();
		for (String regix : m_convertDateWordMap.keySet()) {
			String form = m_convertDateWordMap.get(regix);
			String replaceDateWord =new SimpleDateFormat(form).format(now);
			word = word.replaceAll(regix, replaceDateWord);
		}
		return word;
	}

	/**
	 * LPADを行います。
	 * 文字列[str]の左に指定した文字列[addStr]を[len]に
	 * 満たすまで挿入します。
	 * @param str 対象文字列
	 * @param len 補充するまでの桁数（LPADを行った後のサイズを指定します。）
	 * @param addStr 挿入する文字列
	 * @return 変換後の文字列。
	 */
	public static String lpad(String str, int len, String addStr) {
	    return fillString(str, "L", len, addStr);
	}

	/**
	 * RPADを行います。
	 * 文字列[str]の右に指定した文字列[addStr]を[len]に
	 * 満たすまで挿入します。
	 * @param str 対象文字列
	 * @param len 補充するまでの桁数（RPADを行った後のサイズを指定します。）
	 * @param addStr 挿入する文字列
	 * @return 変換後の文字列。
	 */
	public static String rpad(String str, int len, String addStr) {
	    return fillString(str, "R", len, addStr);
	}

	/**
	 * 文字列[str]に対して、補充する文字列[addStr]を
	 * [position]の位置に[len]に満たすまで挿入します。
	 *
	 * ※[str]がnullや空リテラルの場合でも[addStr]を
	 * [len]に満たすまで挿入した結果を返します。
	 * @param text 対象文字列
	 * @param position 前に挿入 ⇒ L or l 後に挿入 ⇒ R or r
	 * @param maxByte 最大バイト数
	 * @param addStr 挿入する文字列
	 * @return 変換後の文字列。
	 */
	public static String fillString(String text, String position, int maxByte, String addStr) {
	    if (addStr == null || addStr.length() == 0) {
	        throw new IllegalArgumentException ("挿入する文字列の値が不正です : " + addStr);
	    }
	    if (text == null) {
	        text = "";
	    }
		int textLength = text.getBytes().length;
		int addLength = maxByte - (textLength);
		int addCount = addLength / addStr.getBytes().length;
		for (int i = 0; i < addCount; i++) {
			if (position.equalsIgnoreCase("l")) {
				text = addStr + text;
			} else {
				text += addStr;
			}
		}
	    return text;
	}
}
