package net.at.tools.transform;

public class IAttributes {
	/** 設定ファイルの要素名 */
	public static final String NODE_NAME_BASE_TRANSFORM = "baseTransform";
	public static final String NODE_NAME_TRANSFORM = "transform";
	public static final String NODE_NAME_FILES = "files";
	public static final String NODE_NAME_FILE = "file";
	public static final String NODE_NAME_DATA_GROUP = "dataGroup";
	public static final String NODE_NAME_DATA = "data";

	/** DataGroup要素の種別(type) */
	public static final String TYPE_NAME_HEADER = "header";
	public static final String TYPE_NAME_BODY = "body";
	public static final String TYPE_NAME_TRAILER = "trailer";
	public static final String TYPE_NAME_END = "end";

	/** データ種別情報 */
	public static final String DATA_TYPE_NUMBER = "9";
	public static final String DATA_TYPE_SINGLE_CHAR = "X";
	public static final String DATA_TYPE_DOUBLE_CHAR = "X(JP)";

	/** データ判定フラグ */
	public static final String DATA_FLAG_SUCCESS = "0";

	/***** Dataキー *****/
	/*** Common ***/
	/** ファイル名 */
	public static final String DATA_KEY_FILE_NAME = "FileName";
	/** エラーコード */
	public static final String DATA_KEY_ERROR_CODE = "ErrorCode";
	/** 総件数 */
	public static final String DATA_KEY_TOTAL_COUNT = "Total Count";
	/** 総金額 */
	public static final String DATA_KEY_TOTAL_AMOUNT = "Total Amount";
	/** 成功件数 */
	public static final String DATA_KEY_SUCCESS_COUNT = "Success Count";
	/** 成功金額 */
	public static final String DATA_KEY_SUCCESS_AMOUNT = "Success Amount";
	/** 失敗件数 */
	public static final String DATA_KEY_FAILURE_COUNT = "Failure Count";
	/** 失敗金額 */
	public static final String DATA_KEY_FAILURE_AMOUNT = "Failure Amount";
	/** 金額 */
	public static final String DATA_KEY_BILLING_AMOUNT = "Billing Amount";
	/** 結果コード */
	public static final String DATA_KEY_RESULT_CODE = "Result Code";
}
