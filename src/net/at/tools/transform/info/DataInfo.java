package net.at.tools.transform.info;

import net.at.tools.transform.IAttributes;
import net.at.tools.transform.annotation.CopyIgnoreAnnotation;
import net.at.tools.transform.settings.model.files.DataSettingModel;
import net.at.tools.transform.util.ReflectionUtil;
import net.at.tools.transform.util.StringUtil;

import org.apache.log4j.Logger;

public class DataInfo {
	/** ���O */
	@CopyIgnoreAnnotation
	static Logger m_logger = Logger.getLogger(DataInfo.class);

    /** �l */
	private String value = "";
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
	/** �Q�ƃf�[�^ID */
	private String refId = "";
	/** �g�����t���O */
	private boolean trimFlag = false;

	/**
	 * �R���X�g���N�^
	 * @param settings �ݒ���
	 */
	public DataInfo() {	}
	public DataInfo(DataSettingModel settings) {
		this(StringUtil.convertSpecificWord(settings.getFixedValue()), settings);
	}
	public DataInfo(String value, DataSettingModel settings) {
		if (settings != null) {
			try {
				ReflectionUtil.copyFieldValue(settings, this);
			} catch (Exception ex) {
				m_logger.error("�ݒ�t�@�C������ݒ�ł��܂���: " + settings.getName(), ex);
			}
		} else {
			m_logger.error("�ݒ�t�@�C����񂪂���܂���");
		}
		this.value = value;
	}

	/**
	 * �f�B�[�v�R�s�[
	 */
	public DataInfo deepCopy() {
		DataInfo dataInfo = new DataInfo();
		try {
			ReflectionUtil.copyFieldValue(this, dataInfo);
		} catch (SecurityException ex) {
			m_logger.error("�R�s�[�Ɏ��s���܂���: ", ex);
		}
		return dataInfo;
	}

	/**
	 * ���l���ǂ�������
	 * @return ���l���ǂ���
	 */
	public boolean isNumber() {
		if (this.type.equals(IAttributes.DATA_TYPE_NUMBER)) {
			return true;
		}
		return false;
	}
	/**
	 * ���p�����񂩂ǂ�������
	 * @return ���p������̃f�[�^���ǂ���
	 */
	public boolean isSingleChar() {
		if (this.type.equals(IAttributes.DATA_TYPE_SINGLE_CHAR)) {
			return true;
		}
		return false;
	}

	/**
	 * ���l���擾
	 * @return ���l
	 */
	public long getLongValue() {
		return convertLongValue(this.value);
	}

	/**
	 * ���l�ɕϊ�
	 * @param text �e�L�X�g
	 * @return ���l
	 */
	public long convertLongValue(String text) {
		long value = 0;
		try {
			value = Long.parseLong(text);
		} catch(NumberFormatException ex) {
			m_logger.error("�����ɕϊ��ł��܂��� : [" + this.name + "] " + text, ex);
		}
		return value;
	}

	@Override
	public String toString() {
		return key + ":" + value;
	};

	/**
	 * �l���擾���܂��B
	 * @return �l
	 */
	public String getValue() {
	    return value;
	}
	/**
	 * �l��ݒ肵�܂��B
	 * @param value �l
	 */
	public void setValue(String value) {
		this.value = value;
	}

	/**
	 * �T�C�Y�`�F�b�N�����Ēl��ݒ�
	 * @param value �l
	 */
	public void setValueCheckedSize(String value) {
		setValueCheckedSize(value, this.trimFlag);
	}
	public void setValueCheckedSize(String value, boolean isTrim) {
		if (isTrim) {
			value = value.trim();
			// �����̏ꍇ�͐����ɕϊ����ė]�v�ȂO���g��������
			if (isNumber()) {
				long longValue = convertLongValue(value);
				value = String.valueOf(longValue);
			}
		}
		if (value.getBytes().length > maxByte) {
			m_logger.warn("�ő�T�C�Y�𒴂��Ă��܂� : [key]" + this.key + "[�ݒ�l]" + value + "[�ő吔] " + maxByte);
		} else {
			setValue(value);
		}
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
}
