package net.at.tools.transform.info;

import java.nio.file.Path;

import net.at.tools.transform.settings.model.files.FileSettingModel;

public class TransformParamInfo {
    /** �Ǎ��t�@�C����� */
	private Path readingFileInfo = null;
    /** ���̓t�@�C���ݒ��� */
	private FileSettingModel inboundSettingInfo = null;
    /** �o�̓t�@�C���ݒ��� */
	private FileSettingModel outboundSettingInfo = null;
	/** �o�̓t�H���_ */
	protected Path outboundFolederPath = null;

	/**
	 * �R���X�g���N�^
	 * @param readingFileInfo �Ǎ��t�@�C�����
	 * @param inboundSettingInfo ���̓t�@�C���ݒ���
	 * @param outboundSettingInfo �o�̓t�@�C���ݒ���
	 * @param outboundFolederPath �o�̓t�H���_
	 */
	public TransformParamInfo(Path readingFileInfo, FileSettingModel inboundSettingInfo, FileSettingModel outboundSettingInfo, Path outboundFolederPath) {
		this.readingFileInfo = readingFileInfo;
		this.inboundSettingInfo = inboundSettingInfo;
		this.outboundSettingInfo = outboundSettingInfo;
		this.outboundFolederPath = outboundFolederPath;
	}


	/**
	 * �Ǎ��t�@�C�������擾���܂��B
	 * @return �Ǎ��t�@�C�����
	 */
	public Path getReadingFileInfo() {
	    return readingFileInfo;
	}
	/**
	 * �Ǎ��t�@�C������ݒ肵�܂��B
	 * @param readingFileInfo �Ǎ��t�@�C�����
	 */
	public void setReadingFileInfo(Path readingFileInfo) {
	    this.readingFileInfo = readingFileInfo;
	}
	/**
	 * ���̓t�@�C���ݒ�����擾���܂��B
	 * @return ���̓t�@�C���ݒ���
	 */
	public FileSettingModel getInboundSettingInfo() {
	    return inboundSettingInfo;
	}
	/**
	 * ���̓t�@�C���ݒ����ݒ肵�܂��B
	 * @param inboundSettingInfo ���̓t�@�C���ݒ���
	 */
	public void setInboundSettingInfo(FileSettingModel inboundSettingInfo) {
	    this.inboundSettingInfo = inboundSettingInfo;
	}
	/**
	 * �o�̓t�@�C���ݒ�����擾���܂��B
	 * @return �o�̓t�@�C���ݒ���
	 */
	public FileSettingModel getOutboundSettingInfo() {
	    return outboundSettingInfo;
	}
	/**
	 * �o�̓t�@�C���ݒ����ݒ肵�܂��B
	 * @param outboundSettingInfo �o�̓t�@�C���ݒ���
	 */
	public void setOutboundSettingInfo(FileSettingModel outboundSettingInfo) {
	    this.outboundSettingInfo = outboundSettingInfo;
	}

	/**
	 * �o�̓t�H���_���擾���܂��B
	 * @return �o�̓t�H���_
	 */
	public Path getOutboundFolederPath() {
	    return outboundFolederPath;
	}

	/**
	 * �o�̓t�H���_��ݒ肵�܂��B
	 * @param outboundFoleder �o�̓t�H���_
	 */
	public void setOutboundFolederPath(Path outboundFolederPath) {
	    this.outboundFolederPath = outboundFolederPath;
	}

}
