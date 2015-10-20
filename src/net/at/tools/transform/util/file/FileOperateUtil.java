package net.at.tools.transform.util.file;

import java.io.File;

import org.apache.log4j.Logger;

/**
 * ファイル操作に関するUtility
 */
public class FileOperateUtil {
	/** ログ */
	static Logger m_logger = Logger.getLogger(FileOperateUtil.class);

	public static boolean createBackUp(File folder) {
	    if (folder.exists() == true) {
	    	String folderPath = folder.getAbsolutePath();
	    	String newFolderName = FileOperateUtil.getNewFolderName(folder);
	    	folder.renameTo(new File(newFolderName));
	    	m_logger.info("バックアップを行いました : " + newFolderName);
			new File(folderPath).mkdir();
	    	return true;
	    }
		return false;
	}

	public static String getNewFolderName(File folder) {
		String newFolderName = folder.getName();
		File baseFolder = folder.getParentFile();
		for (int i = 1; i < 50; i++) {
			if (isSameFileName(baseFolder, newFolderName) == false) {
				return folder.getParentFile().getAbsolutePath() + File.separator + newFolderName;
			}
			newFolderName = folder.getName() + "_" + i;
		}
		return "";
	}

	public static String getNewFileName(String name) {
		return getNewFileName(new File(name));
	}
	public static String getNewFileName(File file) {
		if (file.isFile() == true) {
			return getNewFileName(file.getParentFile(), file.getName());
		}
		else {
	    	m_logger.error("ファイルを指定してください : " + file);
		}
		return "";
	}
	public static String getNewFileName(File folder, String fileName) {
		if (folder.isFile() == true) {
			return null;
		}
		String newFileName = fileName;
		for (int i = 0; i < 10; i++) {
			if (isSameFileName(folder, newFileName) == true) {
				return newFileName;
			}
			newFileName = fileName + "_" + i;
		}
		return null;
	}
	private static boolean isSameFileName(File folder, String fileName) {
		for (File file : folder.listFiles()) {
			if (file.getName().equals(fileName) == true) {
				return true;
			}
		}
		return false;
	}
}
