package net.at.tools.transform.settings.model.transform;

import java.util.ArrayList;
import java.util.List;

import net.at.tools.transform.settings.model.BaseSettingModel;

import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class BaseTransformSettingModel extends BaseSettingModel {
    /** ID(���) */
	private String id = "";
    /** ���̓t�H���_ */
	private String inboundFolder = "";
    /** �o�̓t�H���_ */
	private String outboundFolder = "";
    /** �ϊ��ݒ��񃊃X�g */
	private List<TransformSettingModel> transformInfoList = new ArrayList<>();

    /**
     * �R���X�g���N�^
     * @param node �v�f���
     */
	public BaseTransformSettingModel(Node node) {
		setSettingsInfo((Element)node);
	}

	/**
	 * inboundKey����ϊ��ݒ�����擾
	 * @param inboundKey
	 * @return �ϊ��ݒ���
	 */
	public TransformSettingModel getDataGroup(String inboundKey) {
		for (TransformSettingModel transformInfo : transformInfoList) {
			if (transformInfo.getInboundFile().equals(inboundKey)) {
				return transformInfo;
			}
		}
		return null;
	}

	/**
	 * ID(���)��ݒ肵�܂��B
	 * @param id ID(���)
	 */
	public void setId(String id) {
	    this.id = id;
	}

	/**
	 * ID(���)���擾���܂��B
	 * @return ID(���)
	 */
	public String getId() {
	    return id;
	}

	/**
	 * ���̓t�H���_���擾���܂��B
	 * @return ���̓t�H���_
	 */
	public String getInboundFolder() {
	    return inboundFolder;
	}

	/**
	 * ���̓t�H���_��ݒ肵�܂��B
	 * @param inboundFolder ���̓t�H���_
	 */
	public void setInboundFolder(String inboundFolder) {
	    this.inboundFolder = inboundFolder;
	}

	/**
	 * �o�̓t�H���_���擾���܂��B
	 * @return �o�̓t�H���_
	 */
	public String getOutboundFolder() {
	    return outboundFolder;
	}

	/**
	 * �o�̓t�H���_��ݒ肵�܂��B
	 * @param outboundFolder �o�̓t�H���_
	 */
	public void setOutboundFolder(String outboundFolder) {
	    this.outboundFolder = outboundFolder;
	}

	/**
	 * �ϊ��ݒ��񃊃X�g���擾���܂��B
	 * @return �ϊ��ݒ��񃊃X�g
	 */
	public List<TransformSettingModel> getTransformInfoList() {
	    return transformInfoList;
	}

	/**
	 * �ϊ��ݒ��񃊃X�g��ݒ肵�܂��B
	 * @param transformInfoList �ϊ��ݒ��񃊃X�g
	 */
	public void setTransformInfoList(List<TransformSettingModel> transformInfoList) {
	    this.transformInfoList = transformInfoList;
	}

}
