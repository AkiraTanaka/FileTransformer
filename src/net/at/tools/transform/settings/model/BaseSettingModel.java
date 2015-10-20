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
	/** ���O */
	static Logger m_logger = Logger.getLogger(BaseSettingModel.class);

	/**
	 * �R���X�g���N�^
	 * ���I�ɐݒ�t�@�C���̒l���t�B�[���h�ɐݒ�
	 * @param element �ݒ�t�@�C���̐e�v�f
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
	    				m_logger.error("�ݒ�t�@�C���̓ǂݍ��݂Ɏ��s���܂��� : " + element.getNodeName(), ex);
	    			}
	    		}
        	}
		}
    }

}
