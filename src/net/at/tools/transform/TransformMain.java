package net.at.tools.transform;

import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Calendar;

import net.at.tools.transform.info.TransformParamInfo;
import net.at.tools.transform.settings.AnalyzeSettingsFile;
import net.at.tools.transform.settings.model.files.FileSettingModel;
import net.at.tools.transform.settings.model.transform.BaseTransformSettingModel;
import net.at.tools.transform.settings.model.transform.TransformSettingModel;
import net.at.tools.transform.util.ReflectionUtil;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

/**
 * ファイル変換処理メインクラス
 */
public class TransformMain {
	/** ログ */
	static Logger m_logger = Logger.getLogger(TransformMain.class);

	/**
	 * メインメソッド
	 * @param args
	 */
	public static void main(String[] args) {
		PropertyConfigurator.configure("etc/log4j.properties");
		TransformMain tmain = new TransformMain();
		// 将来的にはwhileで常駐させたい
		tmain.crawlFile();
	}

	/**
	 * ファイルを探して変換を実行する
	 */
	private void crawlFile() {
		AnalyzeSettingsFile settingInfo = new AnalyzeSettingsFile();
		BaseTransformSettingModel baseTransformSetting = settingInfo.getBaseTransformInfo();
		Path inboundFolder = Paths.get(baseTransformSetting.getInboundFolder());
		try (DirectoryStream<Path> ds = Files.newDirectoryStream(inboundFolder)) {
			m_logger.info("[解析フォルダ] " + inboundFolder.toAbsolutePath().toString());
			for (Path file : ds) {
				m_logger.info("ファイルをチェックします: " + file.getFileName().toString());
				TransformSettingModel transformSettingInfo = settingInfo.getTransformInfobyInboundName(file);
				if (transformSettingInfo != null) {
					long startTime = Calendar.getInstance().getTimeInMillis();
					m_logger.info("変換設定が見つかったため、変換を開始します");
					m_logger.info("[変換クラス] " + transformSettingInfo.getTransformerClass());
					FileSettingModel inboundSettings = settingInfo.getSettingsInfobyId(transformSettingInfo.getInboundFile());
					FileSettingModel outboundSettings = settingInfo.getSettingsInfobyId(transformSettingInfo.getOutboundFile());
					String outboundFolder = baseTransformSetting.getOutboundFolder();
					Path outboundFolderPath = Paths.get(outboundFolder);
					TransformParamInfo paramInfo = new TransformParamInfo(file, inboundSettings, outboundSettings, outboundFolderPath);
					String classPath = transformSettingInfo.getTransformerClass();
					try {
						// 変換クラス取得
						BaseTransformer transformer = (BaseTransformer)ReflectionUtil.getClassInstance(classPath, paramInfo);
						if (transformer != null) {
							transformer.transform();
							m_logger.info("変換が完了しました");
						}
					} catch (Exception ex) {
						m_logger.error("変換クラスのインスタンス取得に失敗しました: " + classPath, ex);
					}
					long processTime = (Calendar.getInstance().getTimeInMillis() - startTime)/1000;
					m_logger.debug("process time: " + processTime + "秒");
				}
			}
		} catch (Exception ex) {
			m_logger.error("ファイルの変換に失敗しました", ex);
		}
	}
}
