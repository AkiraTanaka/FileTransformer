package net.at.tools.transform.settings.model;

import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;

import net.at.tools.transform.util.ReflectionUtil;

import org.apache.log4j.Logger;
import org.w3c.dom.DOMException;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

public class BaseSettingModel {
	/** ログ */
	static Logger m_logger = Logger.getLogger(BaseSettingModel.class);

	/**
	 * コンストラクタ
	 * 動的に設定ファイルの値をフィールドに設定
	 * @param element 設定ファイルの親要素
	 */
    protected void setSettingsInfo(Element element) {
    	if (element != null) {
        	NamedNodeMap attributes = element.getAttributes();
        	if (attributes != null) {
	    		for (int i = 0; i < attributes.getLength(); i++) {
	    			Node attribute = attributes.item(i);
	    			try {
	    				ReflectionUtil.setFieldValue(attribute.getNodeName(), attribute.getNodeValue(), this);
	    			} catch (SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | DOMException | IntrospectionException ex) {
	    				m_logger.error("設定ファイルの読み込みに失敗しました : " + element.getNodeName(), ex);
	    			}
	    		}
        	}
		}
    }

}
