package net.at.tools.transform.settings.model.files;

import net.at.tools.transform.settings.model.BaseSettingModel;

import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class DataSettingModel extends BaseSettingModel {
    /** ���� */
	private int no = -1;
    /** ���ڃL�[(���) */
	private String key = "";
    /** ���ږ�(�a��) */
	private String name = "";
    /** �^ */
	private String type = "";
	/** �ő�T�C�Y(Byte) */
	private int maxByte = -1;
	/** �Œ�l */
	private String fixedValue = "";
	/** �Q�ƃf�[�^ID */
	private String refId = "";
	/** �g�����t���O */
	private boolean trimFlag = false;
    /** ��L�[�t���O */
	private boolean primaryKeyFlag = false;

    /**
     * �R���X�g���N�^
     * @param node �v�f���
     */
	public DataSettingModel() { }
	public DataSettingModel(Node node) {
		setSettingsInfo((Element)node);
	}

	/**
	 * �������擾���܂��B
	 * @return ����
	 */
	public int getNo() {
	    return no;
	}
	/**
	 * ������ݒ肵�܂��B
	 * @param no ����
	 */
	public void setNo(int no) {
	    this.no = no;
	}
	/**
	 * ���ڃL�[(���)���擾���܂��B
	 * @return ���ڃL�[(���)
	 */
	public String getKey() {
	    return key;
	}
	/**
	 * ���ڃL�[(���)��ݒ肵�܂��B
	 * @param key ���ڃL�[(���)
	 */
	public void setKey(String key) {
	    this.key = key;
	}
	/**
	 * ���ږ�(�a��)���擾���܂��B
	 * @return ���ږ�(�a��)
	 */
	public String getName() {
	    return name;
	}
	/**
	 * ���ږ�(�a��)��ݒ肵�܂��B
	 * @param name ���ږ�(�a��)
	 */
	public void setName(String name) {
	    this.name = name;
	}
	/**
	 * �^���擾���܂��B
	 * @return �^
	 */
	public String getType() {
	    return type;
	}
	/**
	 * �^��ݒ肵�܂��B
	 * @param type �^
	 */
	public void setType(String type) {
	    this.type = type;
	}
	/**
	 * �ő�T�C�Y(Byte)���擾���܂��B
	 * @return �ő�T�C�Y(Byte)
	 */
	public int getMaxByte() {
	    return maxByte;
	}
	/**
	 * �ő�T�C�Y(Byte)��ݒ肵�܂��B
	 * @param maxByte �ő�T�C�Y(Byte)
	 */
	public void setMaxByte(int maxByte) {
	    this.maxByte = maxByte;
	}

	/**
	 * �Œ�l���擾���܂��B
	 * @return �Œ�l
	 */
	public String getFixedValue() {
	    return fixedValue;
	}

	/**
	 * �Œ�l��ݒ肵�܂��B
	 * @param fixedValue �Œ�l
	 */
	public void setFixedValue(String fixedValue) {
	    this.fixedValue = fixedValue;
	}

	/**
	 * �Q�ƃf�[�^ID���擾���܂��B
	 * @return �Q�ƃf�[�^ID
	 */
	public String getRefId() {
	    return refId;
	}

	/**
	 * �Q�ƃf�[�^ID��ݒ肵�܂��B
	 * @param refId �Q�ƃf�[�^ID
	 */
	public void setRefId(String refId) {
	    this.refId = refId;
	}

	/**
	 * �g�����t���O���擾���܂��B
	 * @return �g�����t���O
	 */
	public boolean isTrimFlag() {
	    return trimFlag;
	}

	/**
	 * �g�����t���O��ݒ肵�܂��B
	 * @param trimFlag �g�����t���O
	 */
	public void setTrimFlag(boolean trimFlag) {
	    this.trimFlag = trimFlag;
	}

	/**
	 * ��L�[�t���O���擾���܂��B
	 * @return ��L�[�t���O
	 */
	public boolean isPrimaryKeyFlag() {
	    return primaryKeyFlag;
	}

	/**
	 * ��L�[�t���O��ݒ肵�܂��B
	 * @param primaryKeyFlag ��L�[�t���O
	 */
	public void setPrimaryKeyFlag(boolean primaryKeyFlag) {
	    this.primaryKeyFlag = primaryKeyFlag;
	}
}