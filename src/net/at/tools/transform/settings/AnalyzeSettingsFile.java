package net.at.tools.transform.settings;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.FileTime;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import net.at.tools.transform.IAttributes;
import net.at.tools.transform.settings.model.files.DataGroupSettingModel;
import net.at.tools.transform.settings.model.files.DataSettingModel;
import net.at.tools.transform.settings.model.files.FileSettingModel;
import net.at.tools.transform.settings.model.transform.BaseTransformSettingModel;
import net.at.tools.transform.settings.model.transform.TransformSettingModel;
import net.at.tools.transform.util.StringUtil;
import net.at.tools.transform.util.file.XmlFileUtil;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

public class AnalyzeSettingsFile {
	/** ���O */
	static Logger m_logger = Logger.getLogger(AnalyzeSettingsFile.class);

	private static final String FILE_PATH = "etc\\FileTransformerSettings.xml";
	/** �ݒ�t�@�C����񃊃X�g */
	private List<FileSettingModel> m_settingsInfoList = new ArrayList<>();
	/** ��{�ϊ��ݒ��񃊃X�g */
	private BaseTransformSettingModel m_baseTransformInfo = null;
	/** �ݒ�t�@�C���ŏI�X�V���� */
	private static FileTime m_fileUpdateDate = null;

	/**
	 * �R���X�g���N�^
	 */
	public AnalyzeSettingsFile() {
		FileTime fileUpdateDate = getFileUpdateDate(FILE_PATH);
		if (m_fileUpdateDate == null || m_fileUpdateDate.compareTo(fileUpdateDate) < 0) {
			m_logger.info("�ݒ�t�@�C�����X�V���ꂽ���߁A�t�@�C����Ǎ��݂܂�:" + FILE_PATH);
			analyze(FILE_PATH);
			m_fileUpdateDate = fileUpdateDate;
		}
	}


	/*** �ϊ��ݒ�擾���� ***/
	/**
	 * �ݒ�ϊ���񃊃X�g�擾
	 * @return �ݒ�ϊ���񃊃X�g
	 */
	public BaseTransformSettingModel getBaseTransformInfo() {
		return m_baseTransformInfo;
	}
	/**
	 * �t�@�C��������Y������ݒ�t�@�C�������擾
	 * @param file �t�@�C���t���p�X
	 * @return �ݒ�t�@�C�����
	 */
	public TransformSettingModel getTransformInfobyInboundName(Path file) {
		FileSettingModel fileSettingInfo = getSettingsInfobyName(file);
		if (fileSettingInfo != null) {
			for (TransformSettingModel setting : m_baseTransformInfo.getTransformInfoList()) {
				if (setting.getInboundFile().equals(fileSettingInfo.getId())) {
					return setting;
				}
			}
		}
		return null;
	}

	/*** �t�@�C���擾���� ***/
	/**
	 * �t�@�C��ID����Y������ݒ�t�@�C�������擾
	 * @param file �t�@�C���t���p�X
	 * @return �ݒ�t�@�C�����
	 */
	public FileSettingModel getSettingsInfobyId(String id) {
		if (id != null) {
			for (FileSettingModel settings : m_settingsInfoList) {
				if (settings.getId().equals(id)) {
					return settings;
				}
			}
		}
		return null;
	}

	/**
	 * �t�@�C��������Y������ݒ�t�@�C�������擾
	 * @param file �t�@�C���t���p�X
	 * @return �ݒ�t�@�C�����
	 */
	public FileSettingModel getSettingsInfobyName(Path file) {
		String fileName = file.getFileName().toString();
		for (FileSettingModel settings : m_settingsInfoList) {
			String fileRegix = StringUtil.convertSpecificWord(settings.getNameRegix());
			if (StringUtil.findRegixText(fileRegix, fileName)) {
				return settings;
			}
		}
		return null;
	}


	/*** �t�@�C����͏��� ***/
	/**
	 * �ݒ�t�@�C���̍ŏI�X�V�������擾
	 * @param settingsFilePath �ݒ�t�@�C���p�X
	 * @return �ŏI�X�V����
	 */
	private FileTime getFileUpdateDate(String settingsFilePath) {
		Path path = Paths.get(settingsFilePath);
		try {
			return Files.getLastModifiedTime(path);
		} catch (IOException ex) {
			m_logger.error("�ݒ�t�@�C���̍X�V���t���擾�ł��܂��� :" + settingsFilePath, ex);
			return null;
		}
	}
	/**
	 * �ݒ�t�@�C���̉��
	 * @param settingsFilePath �ݒ�t�@�C���p�X
	 * @return ��͌���
	 */
	public boolean analyze(String settingsFilePath) {
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder documentBuilder = factory.newDocumentBuilder();
			Document document = documentBuilder.parse(settingsFilePath);
			// XML�Ǎ�
			Element root = document.getDocumentElement();
			Node filesNode = XmlFileUtil.getChildNode(root, IAttributes.NODE_NAME_FILES);
			if (filesNode != null) {
				m_settingsInfoList = analyzeFilesElement(filesNode);
			}
			Node baseTransformNode = XmlFileUtil.getChildNode(root, IAttributes.NODE_NAME_BASE_TRANSFORM);
			if (baseTransformNode != null) {
				m_baseTransformInfo = analyzeTransformElement(baseTransformNode);
			}
		} catch (SAXException | IOException | ParserConfigurationException ex) {
			m_logger.error("�ݒ�t�@�C�����Ǎ��߂܂���:" + settingsFilePath, ex);
			return false;
		}
		return true;
	}
	/**** for test refID�g�����̏����r���܂�...	 * /
//	private void referBaseData(List<FileSettingModel> settingsInfoList) {
//		for (FileSettingModel newSettingInfo : settingsInfoList) {
//			if (newSettingInfo.getDataGroupBaseId().isEmpty() == false) {
//				for (FileSettingModel baseSettingInfo : settingsInfoList) {
//					if(newSettingInfo.getDataGroupBaseId().equals(baseSettingInfo.getId())) {
//						List<DataGroupSettingModel> newDataGroupInfoList = new ArrayList<>();
//						for (DataGroupSettingModel baseDataGroupInfo : baseSettingInfo.getDataGroupList()) {
//							try {
//								DataGroupSettingModel newInfo = baseDataGroupInfo.deepCopy();
//								//�㏑������
//								DataGroupSettingModel newSettingDataGroupInfo = newSettingInfo.getDataGroup(newInfo.getType());
//								if (newSettingDataGroupInfo != null) {
//									for (DataSettingModel newDataInfo : newSettingDataGroupInfo.getDataList()) {
//
//									}
//								}
//								newDataGroupInfoList.add(newInfo);
//							} catch (Exception ex) {
//								m_logger.error("�R�s�[�Ɏ��s���܂���", ex);
//							}
//						}
//						newSettingInfo.setDataGroupList(newDataGroupInfoList);
//					}
//				}
//			}
//		}
//	}

	/*** files�v�f��� ***/
	/**
	 * files�v�f�����
	 * @param parentNode Files�v�f
	 */
	private List<FileSettingModel> analyzeFilesElement(Node parentNode) {
		List<FileSettingModel> fileInfoList = new ArrayList<>();
		List<Node> fileNodeList = XmlFileUtil.getChildNodeList(parentNode, IAttributes.NODE_NAME_FILE);
		for (Node fileNode : fileNodeList) {
			FileSettingModel fileModel = new FileSettingModel(fileNode);
			List<DataGroupSettingModel> dataGroupInfoList = new ArrayList<>();
			analyzeDataGroupElement(fileNode, dataGroupInfoList);
			fileModel.setDataGroupList(dataGroupInfoList);
			fileInfoList.add(fileModel);
		}
		return fileInfoList;
	}

	/**
	 * DataGroup�v�f����͂��Ď擾
	 * @param parentNode Data�v�f
	 * @param dataListName DataType�v�f
	 */
	private void analyzeDataGroupElement(Node parentNode, List<DataGroupSettingModel> dataGroupInfoList) {
		List<Node> nodeList = XmlFileUtil.getChildNodeList(parentNode, IAttributes.NODE_NAME_DATA_GROUP);
		for (Node dataGroupNode : nodeList) {
			DataGroupSettingModel dataGroupinfo = new DataGroupSettingModel(dataGroupNode);
			// data�v�f�ݒ�
			List<DataSettingModel> dataInfoList = new ArrayList<>();
			List<Node> dataNodeList = XmlFileUtil.getChildNodeList(dataGroupNode, IAttributes.NODE_NAME_DATA);
			for (Node node : dataNodeList) {
				DataSettingModel dataModel = new DataSettingModel((Element)node);
				dataInfoList.add(dataModel);
			}
			dataGroupinfo.setDataList(dataInfoList);
			dataGroupInfoList.add(dataGroupinfo);
		}
	}

	/*** transform�v�f��� ***/
	/**
	 * transform�v�f�����
	 * @param parentNode transform�v�f
	 */
	private BaseTransformSettingModel analyzeTransformElement(Node parentNode) {
		BaseTransformSettingModel baseTransformInfo = new BaseTransformSettingModel(parentNode);
		List<TransformSettingModel> tranformInfoList = new ArrayList<>();
		List<Node> transformNodeList = XmlFileUtil.getChildNodeList(parentNode, IAttributes.NODE_NAME_TRANSFORM);
		for (Node transformNode : transformNodeList) {
			TransformSettingModel tranformInfo = new TransformSettingModel(transformNode);
			tranformInfoList.add(tranformInfo);
		}
		baseTransformInfo.setTransformInfoList(tranformInfoList);
		return baseTransformInfo;
	}
}
