package net.at.tools.transform.settings.model.files;

import java.util.ArrayList;
import java.util.List;

import net.at.tools.transform.annotation.CopyIgnoreAnnotation;
import net.at.tools.transform.info.DataInfo;
import net.at.tools.transform.settings.model.BaseSettingModel;
import net.at.tools.transform.util.ReflectionUtil;

import org.apache.log4j.Logger;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class DataGroupSettingModel extends BaseSettingModel {
	/** ���O */
	@CopyIgnoreAnnotation
	static Logger m_logger = Logger.getLogger(DataInfo.class);

    /** ��� */
	private String type = "";
    /** ���ʃL�[No */
	private int primaryKeyNo = -1;
    /** ���ʃL�[�l */
	private String primaryKeyValue = "";
	/** Data����` */
	@CopyIgnoreAnnotation
	private List<DataSettingModel> dataList = new ArrayList<>();

    /**
     * �R���X�g���N�^
     * @param node �v�f���
     */
	public DataGroupSettingModel() { }
	public DataGroupSettingModel(Node node) {
		setSettingsInfo((Element)node);
	}

	/**
	 * �f�B�[�v�R�s�[
	 */
	public DataGroupSettingModel deepCopy() {
		DataGroupSettingModel newInfo = new DataGroupSettingModel();
		List<DataSettingModel> newDataList = new ArrayList<>();
		for (DataSettingModel dataInfo : this.dataList) {
			DataSettingModel newDataInfo = new DataSettingModel();
			try {
				ReflectionUtil.copyFieldValue(dataInfo, newDataInfo);
				newDataList.add(newDataInfo);
			} catch (SecurityException ex) {
				m_logger.error("�R�s�[�Ɏ��s���܂���: ", ex);
				return null;
			}
		}
		newInfo.setType(this.type);
		newInfo.setPrimaryKeyNo(this.primaryKeyNo);
		newInfo.setPrimaryKeyValue(this.primaryKeyValue);
		newInfo.setDataList(newDataList);
		return newInfo;
	}

	/**
	 * ��ʂ��擾���܂��B
	 * @return ���
	 */
	public String getType() {
	    return type;
	}
	/**
	 * ��ʂ�ݒ肵�܂��B
	 * @param type ���
	 */
	public void setType(String type) {
	    this.type = type;
	}

	/**
	 * ���ʃL�[No���擾���܂��B
	 * @return ���ʃL�[No
	 */
	public int getPrimaryKeyNo() {
	    return primaryKeyNo;
	}

	/**
	 * ���ʃL�[No��ݒ肵�܂��B
	 * @param primaryKeyNo ���ʃL�[No
	 */
	public void setPrimaryKeyNo(int primaryKeyNo) {
	    this.primaryKeyNo = primaryKeyNo;
	}

	/**
	 * ���ʃL�[�l���擾���܂��B
	 * @return ���ʃL�[�l
	 */
	public String getPrimaryKeyValue() {
	    return primaryKeyValue;
	}

	/**
	 * ���ʃL�[�l��ݒ肵�܂��B
	 * @param primaryKeyValue ���ʃL�[�l
	 */
	public void setPrimaryKeyValue(String primaryKeyValue) {
	    this.primaryKeyValue = primaryKeyValue;
	}

	/**
	 * Data����`���擾���܂��B
	 * @return Data����`
	 */
	public List<DataSettingModel> getDataList() {
	    return dataList;
	}
	/**
	 * Data����`��ݒ肵�܂��B
	 * @param dataList Data����`
	 */
	public void setDataList(List<DataSettingModel> dataList) {
	    this.dataList = dataList;
	}
}