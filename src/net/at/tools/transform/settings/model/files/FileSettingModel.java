package net.at.tools.transform.settings.model.files;

import java.util.ArrayList;
import java.util.List;

import net.at.tools.transform.IAttributes;
import net.at.tools.transform.settings.model.BaseSettingModel;

import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class FileSettingModel extends BaseSettingModel {
    /** ID(���) */
	private String id = "";
    /** �t�@�C���� */
	private String nameRegix = "";
    /** �t�@�C����(�a��) */
	private String title = "";
    /** ��؂蕶�� */
	private String delimiter = "";
    /** �͂ݕ��� */
	private String enclosure = "";
    /** �t�@�C���`�� */
	private String fileFormat = "";
    /** �����R�[�h */
	private String charCode = "";
	/** ���s���� */
	private String lineFeedCode = "";
	/** �e�f�[�^�O���[�vID */
	private String dataGroupBaseId = "";
    /** �f�[�^�O���[�v���X�g */
	private List<DataGroupSettingModel> dataGroupList = new ArrayList<>();

    /**
     * �R���X�g���N�^
     * @param node �v�f���
     */
	public FileSettingModel(Node node) {
		setSettingsInfo((Element)node);
	}

	/**
	 * type����DataGroup���擾
	 * @param groupName
	 * @return
	 */
	public DataGroupSettingModel getDataGroup(String groupType) {
		for (DataGroupSettingModel dataGroupInfo : dataGroupList) {
			if (dataGroupInfo.getType().equals(groupType)) {
				return dataGroupInfo;
			}
		}
		return null;
	}
	public DataGroupSettingModel getHeaderDataGroup() {
		return getDataGroup(IAttributes.TYPE_NAME_HEADER);
	}
	public DataGroupSettingModel getBodyDataGroup() {
		return getDataGroup(IAttributes.TYPE_NAME_BODY);
	}
	public DataGroupSettingModel getTrailerDataGroup() {
		return getDataGroup(IAttributes.TYPE_NAME_TRAILER);
	}
	public DataGroupSettingModel getEndDataGroup() {
		return getDataGroup(IAttributes.TYPE_NAME_END);
	}

	/***** getter/setter *****/
	/**
	 * ID(���)���擾���܂��B
	 * @return ID(���)
	 */
	public String getId() {
	    return id;
	}

	/**
	 * ID(���)��ݒ肵�܂��B
	 * @param id ID(���)
	 */
	public void setId(String id) {
	    this.id = id;
	}

	/**
	 * �ϊ���̃t�@�C�������擾���܂��B
	 * @return �t�@�C����
	 */
	public String getTransratedName() {
	    return nameRegix;
	}

	/**
	 * �t�@�C�������擾���܂��B
	 * @return �t�@�C����
	 */
	public String getNameRegix() {
	    return nameRegix;
	}
	/**
	 * �t�@�C������ݒ肵�܂��B
	 * @param nameRegix �t�@�C����
	 */
	public void setNameRegix(String nameRegix) {
	    this.nameRegix = nameRegix;
	}
	/**
	 * �t�@�C����(�a��)���擾���܂��B
	 * @return �t�@�C����(�a��)
	 */
	public String getTitle() {
	    return title;
	}
	/**
	 * �t�@�C����(�a��)��ݒ肵�܂��B
	 * @param title �t�@�C����(�a��)
	 */
	public void setTitle(String title) {
	    this.title = title;
	}
	/**
	 * ��؂蕶�����擾���܂��B
	 * @return ��؂蕶��
	 */
	public String getDelimiter() {
	    return delimiter;
	}
	/**
	 * ��؂蕶����ݒ肵�܂��B
	 * @param delimiter ��؂蕶��
	 */
	public void setDelimiter(String delimiter) {
	    this.delimiter = delimiter;
	}
	/**
	 * �͂ݕ������擾���܂��B
	 * @return �͂ݕ���
	 */
	public String getEnclosure() {
	    return enclosure;
	}

	/**
	 * �͂ݕ�����ݒ肵�܂��B
	 * @param enclosure �͂ݕ���
	 */
	public void setEnclosure(String enclosure) {
	    this.enclosure = enclosure;
	}

	/**
	 * �t�@�C���`�����擾���܂��B
	 * @return �t�@�C���`��
	 */
	public String getFileFormat() {
	    return fileFormat;
	}
	/**
	 * �t�@�C���`����ݒ肵�܂��B
	 * @param fileFormat �t�@�C���`��
	 */
	public void setFileFormat(String fileFormat) {
	    this.fileFormat = fileFormat;
	}
	/**
	 * �����R�[�h���擾���܂��B
	 * @return �����R�[�h
	 */
	public String getCharCode() {
	    return charCode;
	}
	/**
	 * �����R�[�h��ݒ肵�܂��B
	 * @param charCode �����R�[�h
	 */
	public void setCharCode(String charCode) {
	    this.charCode = charCode;
	}
	/**
	 * ���s�������擾���܂��B
	 * @return ���s����
	 */
	public String getLineFeedCode() {
	    return lineFeedCode;
	}
	/**
	 * ���s������ݒ肵�܂��B
	 * @param lineFeedCode ���s����
	 */
	public void setLineFeedCode(String lineFeedCode) {
		if (lineFeedCode == null || lineFeedCode.isEmpty()) {
			lineFeedCode = System.getProperty("line.separator");
		} else if (lineFeedCode.equals("CRLF")) {
			lineFeedCode = "\r\n";
		} else if (lineFeedCode.equals("LF")) {
			lineFeedCode = "\n";
		}
	    this.lineFeedCode = lineFeedCode;
	}

	/**
	 * �e�f�[�^�O���[�vID���擾���܂��B
	 * @return �e�f�[�^�O���[�vID
	 */
	public String getDataGroupBaseId() {
	    return dataGroupBaseId;
	}

	/**
	 * �e�f�[�^�O���[�vID��ݒ肵�܂��B
	 * @param dataGroupBaseId �e�f�[�^�O���[�vID
	 */
	public void setDataGroupBaseId(String dataGroupBaseId) {
	    this.dataGroupBaseId = dataGroupBaseId;
	}

	/**
	 * �f�[�^�O���[�v���X�g���擾���܂��B
	 * @return �f�[�^�O���[�v���X�g
	 */
	public List<DataGroupSettingModel> getDataGroupList() {
	    return dataGroupList;
	}
	/**
	 * �f�[�^�O���[�v���X�g��ݒ肵�܂��B
	 * @param dataGroupList �f�[�^�O���[�v���X�g
	 */
	public void setDataGroupList(List<DataGroupSettingModel> dataGroupList) {
	    this.dataGroupList = dataGroupList;
	}
}
