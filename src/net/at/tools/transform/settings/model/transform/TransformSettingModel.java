package net.at.tools.transform.settings.model.transform;

import net.at.tools.transform.settings.model.BaseSettingModel;

import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class TransformSettingModel extends BaseSettingModel {
    /** ���̓t�@�C���ݒ�L�[ */
	private String inboundFile = "";
    /** �o�̓t�@�C���ݒ�L�[ */
	private String outboundFile = "";
    /** �ϊ��N���X */
	private String transformerClass = "";

    /**
     * �R���X�g���N�^
     * @param node �v�f���
     */
	public TransformSettingModel(Node node) {
		setSettingsInfo((Element)node);
	}

	/**
	 * ���̓t�@�C���ݒ�L�[���擾���܂��B
	 * @return ���̓t�@�C���ݒ�L�[
	 */
	public String getInboundFile() {
	    return inboundFile;
	}

	/**
	 * ���̓t�@�C���ݒ�L�[��ݒ肵�܂��B
	 * @param inboundFile ���̓t�@�C���ݒ�L�[
	 */
	public void setInboundFile(String inboundFile) {
	    this.inboundFile = inboundFile;
	}

	/**
	 * �o�̓t�@�C���ݒ�L�[���擾���܂��B
	 * @return �o�̓t�@�C���ݒ�L�[
	 */
	public String getOutboundFile() {
	    return outboundFile;
	}

	/**
	 * �o�̓t�@�C���ݒ�L�[��ݒ肵�܂��B
	 * @param outboundFile �o�̓t�@�C���ݒ�L�[
	 */
	public void setOutboundFile(String outboundFile) {
	    this.outboundFile = outboundFile;
	}

	/**
	 * �ϊ��N���X���擾���܂��B
	 * @return �ϊ��N���X
	 */
	public String getTransformerClass() {
	    return transformerClass;
	}

	/**
	 * �ϊ��N���X��ݒ肵�܂��B
	 * @param transformerClass �ϊ��N���X
	 */
	public void setTransformerClass(String transformerClass) {
	    this.transformerClass = transformerClass;
	}

}
