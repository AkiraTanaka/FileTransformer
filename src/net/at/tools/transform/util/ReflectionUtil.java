package net.at.tools.transform.util;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import net.at.tools.transform.annotation.CopyIgnoreAnnotation;

import org.apache.log4j.Logger;

public class ReflectionUtil {
	/** ���O */
	static Logger m_logger = Logger.getLogger(ReflectionUtil.class);

	/**
	 * �N���X�C���X�^���X�擾
	 * @param classPath �N���X�p�X
	 * @param paramInfo �p�����[�^���
	 * @return �N���X�C���X�^���X
	 * @throws ClassNotFoundException
	 * @throws SecurityException
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 */
	public static <T> T getClassInstance(String classPath, Object paramInfo) throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		@SuppressWarnings("unchecked")
		Class<T> clazz = (Class<T>)Class.forName(classPath);
		T instance = getClassInstance(clazz, paramInfo);
		return instance;
	}
	public static <T> T getClassInstance(Class<T> clazz) throws NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException  {
		Constructor<T> ct = clazz.getConstructor();
		T instance = ct.newInstance();
		return instance;
	}
	public static <T> T getClassInstance(Class<T> clazz, Object paramInfo) throws NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException  {
		Constructor<T> ct = clazz.getConstructor(paramInfo.getClass());
		T instance = ct.newInstance(paramInfo);
		return instance;
	}

	/**
     * �t�B�[���h����l���擾
     * @param name �t�B�[���h��
     * @param clz �ݒ肷��N���X
	 * @return �t�B�[���h�̒l
	 * @throws IntrospectionException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 */
    public static <T> Object getFieldValue(String name, T clz) throws IntrospectionException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		PropertyDescriptor prop = new PropertyDescriptor(name, clz.getClass());
	    Method getter = prop.getReadMethod();
	    return getter.invoke(clz);
    }

    /**
     * �t�B�[���h�ɒl��ݒ�
     * @param name �t�B�[���h��
     * @param value �ݒ�l
     * @param clz �ݒ肷��N���X
     * @throws SecurityException
     * @throws NoSuchFieldException
     * @throws IntrospectionException
     * @throws InvocationTargetException
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     */
    public static <T> void setFieldValue(String name, String value, T clz) throws SecurityException, IntrospectionException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
    	Object setValue = value;
		try {
			Field field = clz.getClass().getDeclaredField(name);
	    	String type = field.getType().getName();
	    	if (type.equals("boolean")) {
	    		setValue = Boolean.valueOf(value);
			} else if (type.equals("double")) {
	    		setValue = Double.parseDouble(value);
			} else if (type.equals("int")) {
	    		setValue = Integer.parseInt(value);
			}
	    	setFieldValue(name, setValue, clz);
		} catch (NoSuchFieldException e) {
			// �Ȃ��t�B�[���h������̂Ŗ���
		}
    }
    public static <T> void setFieldValue(String name, Object value, T clz) throws IntrospectionException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		PropertyDescriptor prop = new PropertyDescriptor(name, clz.getClass());
	    Method setter = prop.getWriteMethod();
	    setter.invoke(clz, value);
    }

	/**
	 * �f�B�[�v�R�s�[
	 * @throws InvocationTargetException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 * @throws SecurityException
	 * @throws NoSuchMethodException
	 * @throws IntrospectionException
	 */
	public static <T> T deepCopy(T copyInfo) throws NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, IntrospectionException {
		@SuppressWarnings("unchecked")
		T newInfo = (T)getClassInstance(copyInfo.getClass());
		ReflectionUtil.copyFieldValue(copyInfo, newInfo);
		return newInfo;
	}

    /**
     * From���f���N���X����To���f���N���X�ւ̃t�B�[���h�̒l���R�s�[
     * @param name �t�B�[���h��
     * @param value �ݒ�l
     * @param clz �ݒ肷��N���X
     * @throws SecurityException
     */
    public static <F, T> void copyFieldValue(F fromClz, T toClz) throws SecurityException {
    	for (Field field : fromClz.getClass().getDeclaredFields()) {
    		String fieldName = field.getName();
    		// @CopyIgnoreAnnotation�����Ă���t�B�[���h�͖�������
    		CopyIgnoreAnnotation anno = field.getDeclaredAnnotation(CopyIgnoreAnnotation.class);
    		if (anno == null) {
            	try {
                	Object value = getFieldValue(fieldName, fromClz);
    				toClz.getClass().getDeclaredField(fieldName);
    	       		setFieldValue(fieldName, value, toClz);
    			} catch (NoSuchFieldException | SecurityException e) {
    				// �Ȃ��t�B�[���h������̂Ŗ���
    			} catch (Exception ex) {
    				m_logger.error("�t�B�[���h�̃R�s�[�Ɏ��s���܂���: " + fieldName, ex);
    			}
			}
		}
    }
}
