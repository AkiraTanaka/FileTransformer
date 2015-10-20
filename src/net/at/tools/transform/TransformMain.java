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
 * �t�@�C���ϊ��������C���N���X
 */
public class TransformMain {
	/** ���O */
	static Logger m_logger = Logger.getLogger(TransformMain.class);

	/**
	 * ���C�����\�b�h
	 * @param args
	 */
	public static void main(String[] args) {
		PropertyConfigurator.configure("etc/log4j.properties");
		TransformMain tmain = new TransformMain();
		// �����I�ɂ�while�ŏ풓��������
		tmain.crawlFile();
	}

	/**
	 * �t�@�C����T���ĕϊ������s����
	 */
	private void crawlFile() {
		AnalyzeSettingsFile settingInfo = new AnalyzeSettingsFile();
		BaseTransformSettingModel baseTransformSetting = settingInfo.getBaseTransformInfo();
		Path inboundFolder = Paths.get(baseTransformSetting.getInboundFolder());
		try (DirectoryStream<Path> ds = Files.newDirectoryStream(inboundFolder)) {
			m_logger.info("[��̓t�H���_] " + inboundFolder.toAbsolutePath().toString());
			for (Path file : ds) {
				m_logger.info("�t�@�C�����`�F�b�N���܂�: " + file.getFileName().toString());
				TransformSettingModel transformSettingInfo = settingInfo.getTransformInfobyInboundName(file);
				if (transformSettingInfo != null) {
					long startTime = Calendar.getInstance().getTimeInMillis();
					m_logger.info("�ϊ��ݒ肪�����������߁A�ϊ����J�n���܂�");
					m_logger.info("[�ϊ��N���X] " + transformSettingInfo.getTransformerClass());
					FileSettingModel inboundSettings = settingInfo.getSettingsInfobyId(transformSettingInfo.getInboundFile());
					FileSettingModel outboundSettings = settingInfo.getSettingsInfobyId(transformSettingInfo.getOutboundFile());
					String outboundFolder = baseTransformSetting.getOutboundFolder();
					Path outboundFolderPath = Paths.get(outboundFolder);
					TransformParamInfo paramInfo = new TransformParamInfo(file, inboundSettings, outboundSettings, outboundFolderPath);
					String classPath = transformSettingInfo.getTransformerClass();
					try {
						// �ϊ��N���X�擾
						BaseTransformer transformer = (BaseTransformer)ReflectionUtil.getClassInstance(classPath, paramInfo);
						if (transformer != null) {
							transformer.transform();
							m_logger.info("�ϊ����������܂���");
						}
					} catch (Exception ex) {
						m_logger.error("�ϊ��N���X�̃C���X�^���X�擾�Ɏ��s���܂���: " + classPath, ex);
					}
					long processTime = (Calendar.getInstance().getTimeInMillis() - startTime)/1000;
					m_logger.debug("process time: " + processTime + "�b");
				}
			}
		} catch (Exception ex) {
			m_logger.error("�t�@�C���̕ϊ��Ɏ��s���܂���", ex);
		}
	}
}
