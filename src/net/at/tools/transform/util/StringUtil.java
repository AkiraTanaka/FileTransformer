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
	 * ���K�\���ɊY�����镶���񂩊m�F
	 * @param regix ���K�\��
	 * @param text ������
	 * @return ����
	 */
	public static boolean findRegixText(String regix, String text) {
		Pattern p = Pattern.compile(regix, Pattern.CASE_INSENSITIVE);
		Matcher m = p.matcher(text);
		return m.find();
	}

	/**
	 * ����̕�����ϊ�
	 * @param word �ϊ��O����
	 * @return �ϊ��㕶��
	 */
	public static String convertSpecificWord(String word) {
		// ���t�̕ϊ�
		Date now = new Date();
		for (String regix : m_convertDateWordMap.keySet()) {
			String form = m_convertDateWordMap.get(regix);
			String replaceDateWord =new SimpleDateFormat(form).format(now);
			word = word.replaceAll(regix, replaceDateWord);
		}
		return word;
	}

	/**
	 * LPAD���s���܂��B
	 * ������[str]�̍��Ɏw�肵��������[addStr]��[len]��
	 * �������܂ő}�����܂��B
	 * @param str �Ώە�����
	 * @param len ��[����܂ł̌����iLPAD���s������̃T�C�Y���w�肵�܂��B�j
	 * @param addStr �}�����镶����
	 * @return �ϊ���̕�����B
	 */
	public static String lpad(String str, int len, String addStr) {
	    return fillString(str, "L", len, addStr);
	}

	/**
	 * RPAD���s���܂��B
	 * ������[str]�̉E�Ɏw�肵��������[addStr]��[len]��
	 * �������܂ő}�����܂��B
	 * @param str �Ώە�����
	 * @param len ��[����܂ł̌����iRPAD���s������̃T�C�Y���w�肵�܂��B�j
	 * @param addStr �}�����镶����
	 * @return �ϊ���̕�����B
	 */
	public static String rpad(String str, int len, String addStr) {
	    return fillString(str, "R", len, addStr);
	}

	/**
	 * ������[str]�ɑ΂��āA��[���镶����[addStr]��
	 * [position]�̈ʒu��[len]�ɖ������܂ő}�����܂��B
	 *
	 * ��[str]��null��󃊃e�����̏ꍇ�ł�[addStr]��
	 * [len]�ɖ������܂ő}���������ʂ�Ԃ��܂��B
	 * @param text �Ώە�����
	 * @param position �O�ɑ}�� �� L or l ��ɑ}�� �� R or r
	 * @param maxByte �ő�o�C�g��
	 * @param addStr �}�����镶����
	 * @return �ϊ���̕�����B
	 */
	public static String fillString(String text, String position, int maxByte, String addStr) {
	    if (addStr == null || addStr.length() == 0) {
	        throw new IllegalArgumentException ("�}�����镶����̒l���s���ł� : " + addStr);
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
