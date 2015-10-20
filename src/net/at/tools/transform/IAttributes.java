package net.at.tools.transform;

public class IAttributes {
	/** �ݒ�t�@�C���̗v�f�� */
	public static final String NODE_NAME_BASE_TRANSFORM = "baseTransform";
	public static final String NODE_NAME_TRANSFORM = "transform";
	public static final String NODE_NAME_FILES = "files";
	public static final String NODE_NAME_FILE = "file";
	public static final String NODE_NAME_DATA_GROUP = "dataGroup";
	public static final String NODE_NAME_DATA = "data";

	/** DataGroup�v�f�̎��(type) */
	public static final String TYPE_NAME_HEADER = "header";
	public static final String TYPE_NAME_BODY = "body";
	public static final String TYPE_NAME_TRAILER = "trailer";
	public static final String TYPE_NAME_END = "end";

	/** �f�[�^��ʏ�� */
	public static final String DATA_TYPE_NUMBER = "9";
	public static final String DATA_TYPE_SINGLE_CHAR = "X";
	public static final String DATA_TYPE_DOUBLE_CHAR = "X(JP)";

	/** �f�[�^����t���O */
	public static final String DATA_FLAG_SUCCESS = "0";

	/***** Data�L�[ *****/
	/*** Common ***/
	/** �t�@�C���� */
	public static final String DATA_KEY_FILE_NAME = "FileName";
	/** �G���[�R�[�h */
	public static final String DATA_KEY_ERROR_CODE = "ErrorCode";
	/** ������ */
	public static final String DATA_KEY_TOTAL_COUNT = "Total Count";
	/** �����z */
	public static final String DATA_KEY_TOTAL_AMOUNT = "Total Amount";
	/** �������� */
	public static final String DATA_KEY_SUCCESS_COUNT = "Success Count";
	/** �������z */
	public static final String DATA_KEY_SUCCESS_AMOUNT = "Success Amount";
	/** ���s���� */
	public static final String DATA_KEY_FAILURE_COUNT = "Failure Count";
	/** ���s���z */
	public static final String DATA_KEY_FAILURE_AMOUNT = "Failure Amount";
	/** ���z */
	public static final String DATA_KEY_BILLING_AMOUNT = "Billing Amount";
	/** ���ʃR�[�h */
	public static final String DATA_KEY_RESULT_CODE = "Result Code";
}
