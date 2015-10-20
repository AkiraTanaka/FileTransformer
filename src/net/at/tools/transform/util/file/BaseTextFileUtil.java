package net.at.tools.transform.util.file;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.PatternSyntaxException;

import org.apache.log4j.Logger;

/**
 * テキストファイルに関するUtility
 */
public class BaseTextFileUtil {
	/** ログ */
	static Logger m_logger = Logger.getLogger(BaseTextFileUtil.class);

	/**
	 * テキストファイルを読み込むメソッド。
	 * @return テキストファイルのテキスト。読み込みに失敗した場合はnull
	 */
	public static List<String> readTextFile(Path filePath) throws FileNotFoundException, IOException {
		return readTextFile(filePath.toAbsolutePath().toString());
	}
	public static List<String> readTextFile(File fileInfo) throws FileNotFoundException, IOException {
		return readTextFile(fileInfo.getCanonicalPath());
	}
	public static List<String> readTextFile(String filePath) throws FileNotFoundException, IOException {
		BufferedReader br = null;
		List<String> strList = new ArrayList<String>();
		try {
			m_logger.info("読込ファイル : " + filePath);
			br = new BufferedReader(new FileReader(filePath));
			String str;
			while ((str = br.readLine()) != null)
				strList.add(str);
		} catch (FileNotFoundException ex) {
			throw ex;
		} catch (IOException ex) {
			throw ex;
		} finally {
			try {
				if (br != null) {
					br.close();
				}
			} catch (IOException e) {}
		}
		return strList;
	}

	/**
	 * テキストファイルを書き込むメソッド。
	 * @param isOverwrite true : 追記; false : 上書き保存;
	 */
	public static void writeTextFile(Path filePath, String text) throws FileNotFoundException, IOException {
		writeTextFile(filePath.toAbsolutePath().toString(), text, false);
	}
	public static void writeTextFile(String filePath, String text) throws FileNotFoundException, IOException {
		writeTextFile(filePath, text, false);
	}
	public static void writeTextFile(String filePath, String text, boolean isOverwrite) throws FileNotFoundException, IOException {
		PrintWriter writer = null;
		try {
			File file = new File(filePath);
    		writer = new PrintWriter(new BufferedWriter(new FileWriter(file, isOverwrite)));
			writer.print(text);
			m_logger.info("出力ファイル : " + filePath);
		} catch (FileNotFoundException ex) {
			throw ex;
		} catch (IOException ex) {
			throw ex;
		} finally {
			if (writer != null) {
				writer.close();
			}
		}
	}
	public static void writeTextFile(String filePath, InputStream is, boolean isOverwrite) throws FileNotFoundException, IOException {
		FileOutputStream fos = null;
		try {
			byte[] buf = new byte[4096];
			fos = new FileOutputStream(filePath, isOverwrite);
			int index = 0;
			while((index = is.read(buf)) > 0) {
				fos.write(buf, 0, index);
			}
			m_logger.info("出力ファイル : " + filePath);
		} catch (FileNotFoundException ex) {
			throw ex;
		} catch (IOException ex) {
			throw ex;
		} finally {
			fos.close();
		}
	}

	/**
	 * テキストファイルを置換ワードマップで書きかえるメソッド。
	 * @param file 対象ファイル
	 * @param replaceWordMap 置換ワードマップ
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public static void replaceFileByReplaceWordMap(File file, Map<String, String> replaceWordMap) throws FileNotFoundException, IOException {
		m_logger.info("ファイルの置換を開始します : " + file.getAbsolutePath());
		StringBuilder sb = new StringBuilder();
		List<String> textList = readTextFile(file);
		for (int i = 0; i < textList.size(); i++) {
			String text = textList.get(i);
			for (String key : replaceWordMap.keySet()) {
				try {
					text = text.replaceAll(key, replaceWordMap.get(key));
				} catch (PatternSyntaxException e) {
					m_logger.warn("文字列をエスケープしてください : " + key);
				}
			}
			sb.append(text);
			// 最後の行は改行しない。
			if (i != textList.size() - 1) {
				sb.append(System.getProperty("line.separator"));
			}
		}
		writeTextFile(file.getAbsolutePath(), sb.toString(), false);
		System.out.println("ファイルの置換が完了しました。");
	}

	/**
	 * ファイルの拡張子を取得
	 * @param file 拡張子を取得したいファイル
	 * @return 拡張子
	 */
	public static String getExtension(File file) {
		String extension = "";
		String[] splitList = file.getName().split("\\.");
		int length = splitList.length;
		if (length != 0)
			extension = splitList[length - 1];
		return extension;
	}
}
