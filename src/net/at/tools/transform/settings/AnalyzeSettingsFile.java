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
	/** ログ */
	static Logger m_logger = Logger.getLogger(AnalyzeSettingsFile.class);

	private static final String FILE_PATH = "etc\\FileTransformerSettings.xml";
	/** 設定ファイル情報リスト */
	private List<FileSettingModel> m_settingsInfoList = new ArrayList<>();
	/** 基本変換設定情報リスト */
	private BaseTransformSettingModel m_baseTransformInfo = null;
	/** 設定ファイル最終更新日時 */
	private static FileTime m_fileUpdateDate = null;

	/**
	 * コンストラクタ
	 */
	public AnalyzeSettingsFile() {
		FileTime fileUpdateDate = getFileUpdateDate(FILE_PATH);
		if (m_fileUpdateDate == null || m_fileUpdateDate.compareTo(fileUpdateDate) < 0) {
			m_logger.info("設定ファイルが更新されたため、ファイルを読込みます:" + FILE_PATH);
			analyze(FILE_PATH);
			m_fileUpdateDate = fileUpdateDate;
		}
	}


	/*** 変換設定取得処理 ***/
	/**
	 * 設定変換情報リスト取得
	 * @return 設定変換情報リスト
	 */
	public BaseTransformSettingModel getBaseTransformInfo() {
		return m_baseTransformInfo;
	}
	/**
	 * ファイル名から該当する設定ファイル情報を取得
	 * @param file ファイルフルパス
	 * @return 設定ファイル情報
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

	/*** ファイル取得処理 ***/
	/**
	 * ファイルIDから該当する設定ファイル情報を取得
	 * @param file ファイルフルパス
	 * @return 設定ファイル情報
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
	 * ファイル名から該当する設定ファイル情報を取得
	 * @param file ファイルフルパス
	 * @return 設定ファイル情報
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


	/*** ファイル解析処理 ***/
	/**
	 * 設定ファイルの最終更新日時を取得
	 * @param settingsFilePath 設定ファイルパス
	 * @return 最終更新日時
	 */
	private FileTime getFileUpdateDate(String settingsFilePath) {
		Path path = Paths.get(settingsFilePath);
		try {
			return Files.getLastModifiedTime(path);
		} catch (IOException ex) {
			m_logger.error("設定ファイルの更新日付が取得できません :" + settingsFilePath, ex);
			return null;
		}
	}
	/**
	 * 設定ファイルの解析
	 * @param settingsFilePath 設定ファイルパス
	 * @return 解析結果
	 */
	public boolean analyze(String settingsFilePath) {
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder documentBuilder = factory.newDocumentBuilder();
			Document document = documentBuilder.parse(settingsFilePath);
			// XML読込
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
			m_logger.error("設定ファイルが読込めません:" + settingsFilePath, ex);
			return false;
		}
		return true;
	}
	/**** for test refID使う時の処理途中まで...	 * /
//	private void referBaseData(List<FileSettingModel> settingsInfoList) {
//		for (FileSettingModel newSettingInfo : settingsInfoList) {
//			if (newSettingInfo.getDataGroupBaseId().isEmpty() == false) {
//				for (FileSettingModel baseSettingInfo : settingsInfoList) {
//					if(newSettingInfo.getDataGroupBaseId().equals(baseSettingInfo.getId())) {
//						List<DataGroupSettingModel> newDataGroupInfoList = new ArrayList<>();
//						for (DataGroupSettingModel baseDataGroupInfo : baseSettingInfo.getDataGroupList()) {
//							try {
//								DataGroupSettingModel newInfo = baseDataGroupInfo.deepCopy();
//								//上書きする
//								DataGroupSettingModel newSettingDataGroupInfo = newSettingInfo.getDataGroup(newInfo.getType());
//								if (newSettingDataGroupInfo != null) {
//									for (DataSettingModel newDataInfo : newSettingDataGroupInfo.getDataList()) {
//
//									}
//								}
//								newDataGroupInfoList.add(newInfo);
//							} catch (Exception ex) {
//								m_logger.error("コピーに失敗しました", ex);
//							}
//						}
//						newSettingInfo.setDataGroupList(newDataGroupInfoList);
//					}
//				}
//			}
//		}
//	}

	/*** files要素解析 ***/
	/**
	 * files要素を解析
	 * @param parentNode Files要素
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
	 * DataGroup要素を解析して取得
	 * @param parentNode Data要素
	 * @param dataListName DataType要素
	 */
	private void analyzeDataGroupElement(Node parentNode, List<DataGroupSettingModel> dataGroupInfoList) {
		List<Node> nodeList = XmlFileUtil.getChildNodeList(parentNode, IAttributes.NODE_NAME_DATA_GROUP);
		for (Node dataGroupNode : nodeList) {
			DataGroupSettingModel dataGroupinfo = new DataGroupSettingModel(dataGroupNode);
			// data要素設定
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

	/*** transform要素解析 ***/
	/**
	 * transform要素を解析
	 * @param parentNode transform要素
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
