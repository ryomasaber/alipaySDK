package api.com.jy.alipay.util;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class StringUtil extends StringUtils {

	private static Log log = LogFactory.getLog(StringUtil.class);
	public static void main(String[] args) {

		System.out.println(replaceCenter("112211","*"));
		System.out.println(replaceCenter("121",1,"*"));

	}

    // 将字符串转移为ASCII码
    public static String getCnASCII(String cnStr) {
        StringBuffer strBuf = new StringBuffer();
        byte[] bGBK = cnStr.getBytes();
        for (int i = 0; i < bGBK.length; i++) {
            // System.out.println(Integer.toHexString(bGBK[i]&0xff));
            strBuf.append(Integer.toHexString(bGBK[i] & 0xff));
        }
        return strBuf.toString();
    }


	/**
	 * 计算显示的页数,特殊数据已被处理,不会报错
	 * @param total			总数据个数
	 * @param pageSize		每页显示的个数
	 * @return
	 */
	public static int getPageNum(int total, int pageSize){
		if(total <=0 || pageSize <= 0){
			return 0;
		}
		if(total%pageSize == 0){
			return total/pageSize;
		}else{
			return total/pageSize + 1;
		}
	}

	/**
	 * 验证某字符串是否符合邮箱格式
	 * @param str
	 * @return
	 */
	public static boolean isEmail(String str){
		String regex = "\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*";
	    Pattern   p   =   Pattern.compile(regex);
	    Matcher   m   =   p.matcher(str);
	    return m.matches();
	}

	/**
	 * 验证某字符串是否符合手机格式
	 * @param str
	 * @return
	 */
	public static boolean isMobile(String str){
		String regular = "1[3,4,5,8]{1}\\d{9}";
		Pattern pattern = Pattern.compile(regular);
		boolean flag = false;
		if (str != null) {
			Matcher matcher = pattern.matcher(str);
			flag = matcher.matches();
		}
		return flag;
	}

	/**
	 * 解决GET方式乱码问题
	 * @param s
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static String encode(String s) throws UnsupportedEncodingException{
		if(null == s || s.length() == 0){
			return "";
		}
		s = new String(s.getBytes("iso-8859-1"),"UTF-8");
		return s;
	}

	/**
	 * 计算当前的起始页数，特殊数据已被处理,不会报错
	 * @param startPage	当前的起始数
	 * @param pageSize	每页个数
	 * @return
	 */
	public static int getStartPage(int startPage, int pageSize){
		if(startPage <= 0 || pageSize <= 0){
			return 0;
		}
		return startPage/pageSize;
	}



	/**
	 * 根据提供的参数，生成md5值</br>
	 * 会对传过来的值用UTF-8方式编码
	 * @param source
	 * @return	正常的字符串，出错会返回null
	 */

	public static String getMD5(String ss) {
		byte[] source;
		try {
			source = ss.getBytes("UTF-8");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
			return null;
		}
		String s = null;
		char hexDigits[] = { // 用来将字节转换成 16 进制表示的字符
		'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd',
				'e', 'f' };
		try {
			java.security.MessageDigest md = java.security.MessageDigest
					.getInstance("MD5");
			md.update(source);
			byte tmp[] = md.digest(); // MD5 的计算结果是一个 128 位的长整数，
			// 用字节表示就是 16 个字节
			char str[] = new char[16 * 2]; // 每个字节用 16 进制表示的话，使用两个字符，
			// 所以表示成 16 进制需要 32 个字符
			int k = 0; // 表示转换结果中对应的字符位置
			for (int i = 0; i < 16; i++) { // 从第一个字节开始，对 MD5 的每一个字节
				// 转换成 16 进制字符的转换
				byte byte0 = tmp[i]; // 取第 i 个字节
				str[k++] = hexDigits[byte0 >>> 4 & 0xf]; // 取字节中高 4 位的数字转换,
				// >>> 为逻辑右移，将符号位一起右移
				str[k++] = hexDigits[byte0 & 0xf]; // 取字节中低 4 位的数字转换
			}
			s = new String(str); // 换后的结果转换为字符串

		} catch (Exception e) {
		}
		return s;
	}

	/**
	 * 本方法封装了往前台设置的header,contentType等信息
	 * @param message			需要传给前台的数据
	 * @param type				指定传给前台的数据格式,如"html","json"等
	 * @param response			HttpServletResponse对象
	 * @throws IOException
	 * @createDate 2010-12-31 17:55:41
	 */
	public static void writeToWeb(String message, String type, HttpServletResponse response) throws IOException{
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setContentType("text/" + type +"; charset=utf-8");
		response.getWriter().write(message);
		response.getWriter().close();
	}





	/**
	 * 如果传过来个空，则返回""</br>
	 * 否则返回原对象
	 * @param o
	 * @return
	 */
	public static Object nullToSpace(Object o){
		if(null == o){
			return "";
		}
		return o;
	}

	/**
	 * 提供字符串是否可转换成数值型的判断</br>
	 * 如果可转成数值，则返回false</br>
	 * 如果不可转成数值，则返回true</br>
	 * isnan == is not a number</br>
	 * @param s	需要测试的字符串
	 * @return	true or false
	 */
	public static boolean isNAN(String s){
		if(null == s || s.length() == 0){
			return true;
		}
		Pattern pattern = Pattern.compile("[-+]?[0-9]*\\.?[0-9]+$");
		Matcher isNum = pattern.matcher(s);
		if(isNum.matches()){
			return false;
		}else{
			return true;
		}
	}

	public static boolean isInteger(String s){
		if(null == s || s.length() == 0){
			return false;
		}
		Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
		return pattern.matcher(s).matches();
	}

	/**
	 * 删除掉JSON串前后的[]，某些插件不能解析带[]的json格式
	 * @param json
	 * @return
	 */
	public static String treatJson(String json){
		if(null == json || json.length() < 3){
			return "";
		}
		json = json.substring(1, json.length());
		json = json.substring(0, json.length()-1);
		return json;
	}


	/**
	 * 取随机的32位uuid
	 * @return
	 */
	public static String getUUID () {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}


	/**
	 * 判断一个字符串是不是空或者为""
	 * @param s
	 * @return
	 */
	public static boolean isNullOrSpace(String s){
		if(null == s || s.length() == 0){
			return true;
		}else{
			return false;
		}
	}

	/**
	 * 把Map<String,Object>处理成实体类
	 * @param clazz		想要的实体类
	 * @param list		包含信息的列表
	 * @return
	 */
	public static <T> List<T> mapToList(Class<T> clazz, List<Map<String,Object>> list){

		if(null == list || list.size() == 0){
			return null;
		}
		List<T> result = new ArrayList<T>();
		Map<String,Object> map;
		for(Iterator<Map<String,Object>> iter = list.iterator(); iter.hasNext();){
			map = iter.next();
			result.add(mapToObject(clazz, map));
		}
		return result;
	}

	/**
	 * 把Map<String,Object>处理成实体类
	 * @param clazz		想要的实体类
	 * @param map		包含信息的Map对象
	 * @return
	 */
	public static <T> T mapToObject(Class<T> clazz, Map<String,Object> map){

		if(null == map){
			return null;
		}

		Field[] fields = clazz.getDeclaredFields();	//取到所有类下的属性，也就是变量名
		Field field;
		T o = null;
		try {
			o = clazz.newInstance();
		} catch (InstantiationException e1) {
			e1.printStackTrace();
		} catch (IllegalAccessException e1) {
			e1.printStackTrace();
		}
		for(int i=0; i<fields.length; i++){
			field = fields[i];
			String fieldName = field.getName();
			//把属性的第一个字母处理成大写
			String stringLetter=fieldName.substring(0, 1).toUpperCase();
			//取得set方法名，比如setBbzt
			String setName="set"+stringLetter+fieldName.substring(1);
			//真正取得get方法。
			Method setMethod = null;
			Class<?> fieldClass = field.getType();
			try {
				Object value = map.get(fieldName);
				if (value != null && String.valueOf(value).trim().length() > 0 && isHaveSuchMethod(clazz, setName)) {
					if (fieldClass == String.class) {
						setMethod = clazz.getMethod(setName, fieldClass);
						setMethod.invoke(o, String.valueOf(value));// 为其赋值
					} else if (fieldClass == Integer.class || fieldClass == int.class) {
						setMethod = clazz.getMethod(setName, fieldClass);
						setMethod.invoke(o,
								Integer.parseInt(String.valueOf(value)));// 为其赋值
					} else if (fieldClass == Boolean.class || fieldClass == boolean.class) {
						setMethod = clazz.getMethod(setName, fieldClass);
						setMethod.invoke(o,
								Boolean.getBoolean(String.valueOf(value)));// 为其赋值
					} else if (fieldClass == Short.class || fieldClass == short.class) {
						setMethod = clazz.getMethod(setName, fieldClass);
						setMethod.invoke(o,
								Short.parseShort(String.valueOf(value)));// 为其赋值
					} else if (fieldClass == Long.class || fieldClass == long.class) {
						setMethod = clazz.getMethod(setName, fieldClass);
						setMethod.invoke(o,
								Long.parseLong(String.valueOf(value)));// 为其赋值
					} else if (fieldClass == Double.class || fieldClass == double.class) {
						setMethod = clazz.getMethod(setName, fieldClass);
						setMethod.invoke(o,
								Double.parseDouble(String.valueOf(value)));// 为其赋值
					} else if (fieldClass == Float.class || fieldClass == float.class) {
						setMethod = clazz.getMethod(setName, fieldClass);
						setMethod.invoke(o,
								Float.parseFloat(String.valueOf(value)));// 为其赋值
					} else if (fieldClass == BigInteger.class) {
						setMethod = clazz.getMethod(setName, fieldClass);
						setMethod.invoke(o, BigInteger.valueOf(Long
								.parseLong(String.valueOf(value))));// 为其赋值
					} else if (fieldClass == BigDecimal.class) {
						setMethod = clazz.getMethod(setName, fieldClass);
						setMethod.invoke(o, BigDecimal.valueOf(Double
								.parseDouble(String.valueOf(value))));// 为其赋值
					} else if (fieldClass == Date.class) {
						setMethod = clazz.getMethod(setName, fieldClass);
						if (map.get(fieldName).getClass() == java.sql.Date.class) {
							setMethod.invoke(o, new Date(
									((java.sql.Date) value).getTime()));// 为其赋值
						} else if (map.get(fieldName).getClass() == java.sql.Time.class) {
							setMethod.invoke(o, new Date(
									((java.sql.Time) value).getTime()));// 为其赋值
						} else if (map.get(fieldName).getClass() == java.sql.Timestamp.class) {
							setMethod.invoke(o, new Date(
									((java.sql.Timestamp) value).getTime()));// 为其赋值
						}
					} else if(fieldClass == List.class){

					}
				}
			} catch (Exception e) {
			}

		}
		return o;
	}


	public static <T> T requestToObject(HttpServletRequest request, Class<T> clazz){

		if(null == request){
			return null;
		}

		Field[] fields = clazz.getDeclaredFields();	//取到所有类下的属性，也就是变量名
		Field field;
		T o = null;
		try {
			o = clazz.newInstance();
		} catch (InstantiationException e1) {
			e1.printStackTrace();
		} catch (IllegalAccessException e1) {
			e1.printStackTrace();
		}
		for(int i=0; i<fields.length; i++){
			field = fields[i];
			String fieldName = field.getName();
			//把属性的第一个字母处理成大写
			String stringLetter=fieldName.substring(0, 1).toUpperCase();
			//取得set方法名，比如setBbzt
			String setName="set"+stringLetter+fieldName.substring(1);
			//真正取得get方法。
			Method setMethod = null;
			Class<?> fieldClass = field.getType();
			try {
				Object value = request.getParameter(fieldName);
				Object valueArray = request.getParameterValues(fieldName);
				if (value != null && isHaveSuchMethod(clazz, setName)) {
					if(String.valueOf(value).trim().length() > 0){
						if (fieldClass == String.class) {
							setMethod = clazz.getMethod(setName, fieldClass);
							setMethod.invoke(o, String.valueOf(value));// 为其赋值
						} else if (fieldClass == Integer.class || fieldClass == int.class) {
							setMethod = clazz.getMethod(setName, fieldClass);
							setMethod.invoke(o, Integer.parseInt(String.valueOf(value)));// 为其赋值
						} else if (fieldClass == Boolean.class || fieldClass == boolean.class) {
							setMethod = clazz.getMethod(setName, fieldClass);
							setMethod.invoke(o,
									Boolean.getBoolean(String.valueOf(value)));// 为其赋值
						} else if (fieldClass == Short.class || fieldClass == short.class) {
							setMethod = clazz.getMethod(setName, fieldClass);
							setMethod.invoke(o,
									Short.parseShort(String.valueOf(value)));// 为其赋值
						} else if (fieldClass == Long.class || fieldClass == long.class) {
							setMethod = clazz.getMethod(setName, fieldClass);
							setMethod.invoke(o,
									Long.parseLong(String.valueOf(value)));// 为其赋值
						} else if (fieldClass == Double.class || fieldClass == double.class) {
							setMethod = clazz.getMethod(setName, fieldClass);
							setMethod.invoke(o,
									Double.parseDouble(String.valueOf(value)));// 为其赋值
						} else if (fieldClass == Float.class || fieldClass == float.class) {
							setMethod = clazz.getMethod(setName, fieldClass);
							setMethod.invoke(o,
									Float.parseFloat(String.valueOf(value)));// 为其赋值
						} else if (fieldClass == BigInteger.class) {
							setMethod = clazz.getMethod(setName, fieldClass);
							setMethod.invoke(o, BigInteger.valueOf(Long
									.parseLong(String.valueOf(value))));// 为其赋值
						} else if (fieldClass == BigDecimal.class) {
							setMethod = clazz.getMethod(setName, fieldClass);
							setMethod.invoke(o, BigDecimal.valueOf(Double
									.parseDouble(String.valueOf(value))));// 为其赋值
						} else if (fieldClass == Date.class) {
							setMethod = clazz.getMethod(setName, fieldClass);
							String tempValue = value.toString();
							Date tempDate = null;
							// 根据字符串长度确定要用何种形式转换
							if (tempValue.length() > 0 && tempValue.length() < 12) {
								tempDate = StringToDate(value.toString(), "yyyy-MM-dd");
							} else if (tempValue.length() >= 13
									&& tempValue.length() < 21) {
								tempDate = StringToDate(value.toString(), "yyyy-MM-dd HH:mm:ss");
							}
							// 如果转换成功了，就赋值，如果不成功就让它空着吧。
							if (null != tempDate) {
								setMethod.invoke(o, tempDate);// 为其赋值
							}
						}
					}else{
						Object oo = null;
						setMethod = clazz.getMethod(setName, fieldClass);
						setMethod.invoke(o, oo);// 为其赋值
					}

				}
				if (valueArray != null && isHaveSuchMethod(clazz, setName)) {
					if (fieldClass == String[].class) {
						setMethod = clazz.getMethod(setName, fieldClass);
						setMethod.invoke(o, value == null ? null : valueArray);// 为其赋值
					}
				}
			} catch (Exception e) {
			}

		}
		return o;
	}
	/**
	 * 自动将传过来的参数放到实体，本方法仅适用于修改页面
	 * 本方法会把接到的空字符串也set进实体
	 * @param request
	 * @param entity
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> T requestToObject(HttpServletRequest request, T entity){

		if(null == request || null == entity){
			return null;
		}
		Class<T> clazz = (Class<T>) entity.getClass();
		Field[] fields = clazz.getDeclaredFields();	//取到所有类下的属性，也就是变量名
		Field field;
		for(int i=0; i<fields.length; i++){
			field = fields[i];
			String fieldName = field.getName();
			//把属性的第一个字母处理成大写
			String stringLetter=fieldName.substring(0, 1).toUpperCase();
			//取得set方法名，比如setBbzt
			String setName="set"+stringLetter+fieldName.substring(1);
			//真正取得get方法。
			Method setMethod = null;
			Class<?> fieldClass = field.getType();
			try {
				Object value = request.getParameter(fieldName);
				Object valueArray = request.getParameterValues(fieldName);
				if (value != null && isHaveSuchMethod(clazz, setName)) {
					if(String.valueOf(value).trim().length() > 0){
						if (fieldClass == String.class) {
							setMethod = clazz.getMethod(setName, fieldClass);
							setMethod.invoke(entity, String.valueOf(value));// 为其赋值
						} else if (fieldClass == Integer.class || fieldClass == int.class) {
							setMethod = clazz.getMethod(setName, fieldClass);
							setMethod.invoke(entity, Integer.parseInt(String.valueOf(value)));// 为其赋值
						} else if (fieldClass == Boolean.class || fieldClass == boolean.class) {
							setMethod = clazz.getMethod(setName, fieldClass);
							setMethod.invoke(entity,
									Boolean.getBoolean(String.valueOf(value)));// 为其赋值
						} else if (fieldClass == Short.class || fieldClass == short.class) {
							setMethod = clazz.getMethod(setName, fieldClass);
							setMethod.invoke(entity,
									Short.parseShort(String.valueOf(value)));// 为其赋值
						} else if (fieldClass == Long.class || fieldClass == long.class) {
							setMethod = clazz.getMethod(setName, fieldClass);
							setMethod.invoke(entity,
									Long.parseLong(String.valueOf(value)));// 为其赋值
						} else if (fieldClass == Double.class || fieldClass == double.class) {
							setMethod = clazz.getMethod(setName, fieldClass);
							setMethod.invoke(entity,
									Double.parseDouble(String.valueOf(value)));// 为其赋值
						} else if (fieldClass == Float.class || fieldClass == float.class) {
							setMethod = clazz.getMethod(setName, fieldClass);
							setMethod.invoke(entity,
									Float.parseFloat(String.valueOf(value)));// 为其赋值
						} else if (fieldClass == BigInteger.class) {
							setMethod = clazz.getMethod(setName, fieldClass);
							setMethod.invoke(entity, BigInteger.valueOf(Long
									.parseLong(String.valueOf(value))));// 为其赋值
						} else if (fieldClass == BigDecimal.class) {
							setMethod = clazz.getMethod(setName, fieldClass);
							setMethod.invoke(entity, BigDecimal.valueOf(Double
									.parseDouble(String.valueOf(value))));// 为其赋值
						} else if (fieldClass == Date.class) {
							setMethod = clazz.getMethod(setName, fieldClass);
							String tempValue = value.toString();
							Date tempDate = null;
							// 根据字符串长度确定要用何种形式转换
							if (tempValue.length() > 0 && tempValue.length() < 12) {
								tempDate = StringToDate(value.toString(), "yyyy-MM-dd");
							} else if (tempValue.length() >= 13
									&& tempValue.length() < 21) {
								tempDate = StringToDate(value.toString(), "yyyy-MM-dd HH:mm:ss");
							}
							// 如果转换成功了，就赋值，如果不成功就让它空着吧。
							if (null != tempDate) {
								setMethod.invoke(entity, tempDate);// 为其赋值
							}
						}
					}else{
						Object oo = null;
						setMethod = clazz.getMethod(setName, fieldClass);
						setMethod.invoke(entity, oo);// 为其赋值
					}

				}
				if (valueArray != null && isHaveSuchMethod(clazz, setName)) {
					if (fieldClass == String[].class) {
						setMethod = clazz.getMethod(setName, fieldClass);
						setMethod.invoke(entity, value == null ? null : valueArray);// 为其赋值
					}
				}
			} catch (Exception e) {
			}

		}
		return entity;
	}


	/**
	 * 判断某个类里是否有某个方法
	 * @param clazz
	 * @param methodName
	 * @return
	 */
	public static boolean isHaveSuchMethod(Class<?> clazz, String methodName){
		Method[] methodArray = clazz.getMethods();
		boolean result = false;
		if(null != methodArray){
			for(int i=0; i<methodArray.length; i++){
				if(methodArray[i].getName().equals(methodName)){
					result = true;
					break;
				}
			}
		}
		return result;
	}



	public static void beanCopy(Object source, Object target) {

		if (null == source || null == target) {
			return ;
		}


		Class<?> sourceClazz = source.getClass();
		Class<?> targetClazz = target.getClass();
		Field[] fields = targetClazz.getDeclaredFields(); // 取到所有类下的属性，也就是变量名
		Field field;

		for (int i = 0; i < fields.length; i++) {
			field = fields[i];
			String fieldName = field.getName();
			// 把属性的第一个字母处理成大写
			String stringLetter = fieldName.substring(0, 1).toUpperCase();
			// 取得setter方法名，比如setBbzt
			String setName = "set" + stringLetter + fieldName.substring(1);
			// 取得getter方法名
			String getName = "get" + stringLetter + fieldName.substring(1);
			// 真正取得get方法。
			Method setMethod = null;
			// 真正取得set方法
			Method sourceGetMethod = null;

			Class<?> fieldClass = field.getType();
			try {
				if (isHaveSuchMethod(targetClazz, setName)) {
					setMethod = targetClazz.getMethod(setName, fieldClass);
					if (isHaveSuchMethod(sourceClazz, getName)) {
						sourceGetMethod = sourceClazz.getMethod(getName);
					}
					Object sourceValue = sourceGetMethod.invoke(source);
					if (null != sourceValue) {
						setMethod.invoke(target, sourceValue);// 为其赋值
					}
				}
			} catch (Exception e) {

			}

		}
		return ;
	}


	/**
	 * 将来源对象的值 ，赋给目标对象</br>
	 * @param source	来源对象
	 * @param target	目标对象
	 * @param isCopyNull	如果source中的值为null时，是否将其赋给target对象
	 */
	public static void beanCopy(Object source, Object target, boolean isCopyNull) {

		if (null == source || null == target) {
			return ;
		}


		Class<?> sourceClazz = source.getClass();
		Class<?> targetClazz = target.getClass();
		Field[] fields = targetClazz.getDeclaredFields(); // 取到所有类下的属性，也就是变量名
		Field field;

		for (int i = 0; i < fields.length; i++) {
			field = fields[i];
			String fieldName = field.getName();
			// 把属性的第一个字母处理成大写
			String stringLetter = fieldName.substring(0, 1).toUpperCase();
			// 取得setter方法名，比如setBbzt
			String setName = "set" + stringLetter + fieldName.substring(1);
			// 取得getter方法名
			String getName = "get" + stringLetter + fieldName.substring(1);
			// 真正取得get方法。
			Method setMethod = null;
			// 真正取得set方法
			Method sourceGetMethod = null;

			Class<?> fieldClass = field.getType();
			try {
				if (isHaveSuchMethod(targetClazz, setName)) {
					setMethod = targetClazz.getMethod(setName, fieldClass);
					if (isHaveSuchMethod(sourceClazz, getName)) {
						sourceGetMethod = sourceClazz.getMethod(getName);
					}
					Object sourceValue = sourceGetMethod.invoke(source);
					if (null != sourceValue) {
						setMethod.invoke(target, sourceValue);// 为其赋值
					}else{
						if(isCopyNull){
							setMethod.invoke(target, sourceValue);
						}
					}
				}
			} catch (Exception e) {

			}
		}
		return ;
	}



	/**
	 * 按照给定的分隔标志，将列表封闭成字符串
	 * @param list
	 * @param reg
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public String join(List list, String reg){
		StringBuffer sb = new StringBuffer();
		if(null == list || list.size() == 0){
			return null;
		}
		for(Iterator iter = list.iterator(); iter.hasNext();){
			sb.append(iter.next()).append(reg);
		}
		int length = sb.length();
		if(length > 0){
			sb = sb.delete(length -1, length);
		}
		return sb.toString();
	}

	/**
	 * 生成六位随机数
	 * @return
	 */
	public static Integer getRandom(){
		Random ran=new Random();
		int r=0;
		m1:while(true){
		    int n=ran.nextInt(1000000);
		    r=n;
		    int[] bs=new int[6];
		    for(int i=0;i<bs.length;i++){
				bs[i]=n%10;
				n/=10;
		    }
		Arrays.sort(bs);
		for(int i=1;i<bs.length;i++){
			if(bs[i-1]==bs[i]){
			continue m1;
			}
		}
			break;
	}
		return r;
	}

	/**
	 * 替换中间的字符串 只保留前2位和后两位
	 * @param str 长度 >=5
	 * @param target
	 * @return
	 */
	public static String replaceCenter(String str,String target){

		if(str==null) return "";

		int length = str.length();

		if(length<5) return str;

		StringBuffer sb = new StringBuffer();

		String a = str.substring(0,2);
		String b = str.substring(2, length-2);
		String c = str.substring(length-2,length);

		sb.append(a);

		for(int i= 0;i<b.length();i++){
			sb.append(target);
		}

		sb.append(c);

		return sb.toString();
	}

	/**
	 * 替换中间的字符串 只保留前2位和后两位
	 * @param str
	 * @param len
	 * @param target
	 * @return
	 */
	public static String replaceCenter(String str,int len,String target){

		if(str==null) return "";

		int length = str.length();

		if(length<2) return str;

		if(len>length-2) len=2;

		StringBuffer sb = new StringBuffer();

		String a = str.substring(0,1);
		String b = str.substring(1, length-1);
		String c = str.substring(length-1,length);

		sb.append(a);

		for(int i= 0;i<len;i++){
			sb.append(target);
		}

		sb.append(c);

		return sb.toString();
	}

	/**
	 * 判断字符串编码
	 * @param str
	 * @return
	 */
	public static String getEncoding(String str) {
		String encode = "GB2312";
		try {
			if (str.equals(new String(str.getBytes(encode), encode))) {
				String s = encode;
				return s;
			}
		} catch (Exception exception) {
		}
		encode = "ISO-8859-1";
		try {
			if (str.equals(new String(str.getBytes(encode), encode))) {
				String s1 = encode;
				return s1;
			}
		} catch (Exception exception1) {
		}
		encode = "UTF-8";
		try {
			if (str.equals(new String(str.getBytes(encode), encode))) {
				String s2 = encode;
				return s2;
			}
		} catch (Exception exception2) {
		}
		encode = "GBK";
		try {
			if (str.equals(new String(str.getBytes(encode), encode))) {
				String s3 = encode;
				return s3;
			}
		} catch (Exception exception3) {
		}
		return "";
	}

	/**
	 * 将字符串转为UTF-8编码
	 * @param str
	 * @return
	 */
	public static String convertStr2UTF8(String str){
		try {
			if(org.apache.commons.lang.StringUtils.isEmpty(str)) return "";
			if(getEncoding(str).equals("ISO-8859-1"))
				return new String(str.getBytes(getEncoding(str)), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return str;
	}

	/**
	 * 字符串转化为日期</br>
	 * @param str	需要被转换为日期的字符串
	 * @param format 格式，常用的为 yyyy-MM-dd HH:mm:ss
	 * @return	java.util.Date，如果出错会返回null
	 */
	public static Date StringToDate(String str, String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		Date date = null;
		try {
			date = sdf.parse(str);
		} catch (ParseException e) {
			if(log.isErrorEnabled()){
				log.error("将字符串转换成日期时出错", e);
			}
		}
		return date;
	}

}
