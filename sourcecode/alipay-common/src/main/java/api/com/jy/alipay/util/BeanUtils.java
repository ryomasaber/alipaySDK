package api.com.jy.alipay.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * 扩展Apache Commons BeanUtils, 提供一些反射方面缺失功能的封装.
 */
public class BeanUtils extends org.apache.commons.beanutils.BeanUtils {

	protected static final Log logger = LogFactory.getLog(BeanUtils.class);

    private static Map getterMethodInfoCache = Collections.synchronizedMap(new HashMap());
    private static Map setterMethodInfoCache = Collections.synchronizedMap(new HashMap() );
    public static boolean useCache=true;


	/**Create Date: 2010-9-11下午06:35:38 by wangsw  Modified Date: 2010-9-11下午06:35:38 by wangsw
	 * Description :  序列化克隆对象	深度克隆对象
	 * @throws java.io.IOException
	 * @throws ClassNotFoundException
	 * @Exception
	 */
	public static Object cloneObject(Object obj) {
		ByteArrayOutputStream bos=new ByteArrayOutputStream();
		ObjectOutputStream oos;
		try {
			oos = new ObjectOutputStream(bos);
			oos.writeObject(obj);
			ByteArrayInputStream bis=new ByteArrayInputStream(bos.toByteArray());
			ObjectInputStream ois=new ObjectInputStream(bis);
			return ois.readObject();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}


    // Map --> Bean 1: 利用Introspector,PropertyDescriptor实现 Map --> Bean
    public static void transMap2Bean(Map<String, Object> map, Object obj) {

        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();

            for (PropertyDescriptor property : propertyDescriptors) {
                String key = property.getName();

                if (map.containsKey(key)) {
                    Object value = map.get(key);
                    // 得到property对应的setter方法
                    Method setter = property.getWriteMethod();
                    setter.invoke(obj, value);
                }

            }

        } catch (Exception e) {
            System.out.println("transMap2Bean Error " + e);
        }
        return;
    }

    // Bean --> Map 1: 利用Introspector和PropertyDescriptor 将Bean --> Map
    public static Map<String, Object> transBean2Map(Object obj) {
        if(obj == null){
            return null;
        }
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
            for (int i=0;i<propertyDescriptors.length;i++) {
                String key = propertyDescriptors[i].getName();

                // 过滤class属性
                if (!key.equals("class")) {
                    // 得到property对应的getter方法
                    Method getter = propertyDescriptors[i].getReadMethod();
                    Object value = getter.invoke(obj);
                    if(value != null){
                        map.put(key, value);
                    }
                }
            }
        } catch (Exception e) {
        	e.printStackTrace();
            System.out.println("transBean2Map Error " + e);
        }
        return map;
    }

    public static boolean isStandardProperty(Class clazz) {
        return clazz.isPrimitive()                  ||
                clazz.isAssignableFrom(Byte.class)      ||
                clazz.isAssignableFrom(Short.class)     ||
                clazz.isAssignableFrom(Integer.class)   ||
                clazz.isAssignableFrom(Long.class)      ||
                clazz.isAssignableFrom(Float.class)     ||
                clazz.isAssignableFrom(Double.class)    ||
                clazz.isAssignableFrom(Character.class) ||
                clazz.isAssignableFrom(String.class)    ||
                clazz.isAssignableFrom(Boolean.class);
    }


}
