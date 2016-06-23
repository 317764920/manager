package com.lcx.mysdk.utils;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Map;
import java.util.UUID;

/**
 * @ClassName(类名) : CommonUtil
 * @Description(描述) : 辅助类
 * @author(作者) ：liuchunxu
 */
public abstract class CommonUtil {

	/**
	 * @Description(功能描述) : 是否为空
	 * @author(作者) ： liuchunxu
	 * @date (开发日期) : 2015年2月15日 上午8:59:56
	 * @exception :
	 * @param pObj
	 *            :
	 * @return boolean true:为空
	 */
	@SuppressWarnings("rawtypes")
	public static boolean isEmpty(Object pObj) {
		if (pObj == null) {
			return true;
		}
		if ("".equals(pObj)) {
			return true;
		}
		if (pObj instanceof String) {
			if (((String) pObj).length() == 0) {
				return true;
			}
		} else if (pObj instanceof Collection) {
			if (((Collection) pObj).size() == 0) {
				return true;
			}
		} else if (pObj instanceof Map) {
			if (((Map) pObj).size() == 0) {
				return true;
			}
		}
		return false;
	}

	/**
	 * @Description(功能描述) : 是否不为空
	 * @author(作者) ： liuchunxu
	 * @date (开发日期) : 2015年2月15日 上午8:59:14
	 * @exception :
	 * @param pObj
	 *            ：
	 * @return boolean
	 */
	@SuppressWarnings("rawtypes")
	public static boolean isNotEmpty(Object pObj) {
		if (pObj == null) {
			return false;
		}
		if ("".equals(pObj)) {
			return false;
		}
		if (pObj instanceof String) {
			if (((String) pObj).length() == 0) {
				return false;
			}
		} else if (pObj instanceof Collection) {
			if (((Collection) pObj).size() == 0) {
				return false;
			}
		} else if (pObj instanceof Map) {
			if (((Map) pObj).size() == 0) {
				return false;
			}
		}
		return true;
	}

	// public static String fileNameEncode(String source) {
	// // return Base64.encodeBase64URLSafeString(source.getBytes());
	// return UUID.randomUUID().toString();
	// }
	//
	// public static String fileNameDecode(String source) {
	// return new String(Base64.decodeBase64(source));
	// }

	/**
	 * @Description(功能描述) : javaBean 转 map
	 * @author(作者) ： liuchunxu
	 * @date (开发日期) : 2015年2月10日 下午3:39:42
	 * @exception :
	 * @param bean
	 *            原数据
	 * @param result
	 *            转换结果
	 */
	public static void bean2Map(Object bean, Map<String, String> result) {
		Method[] methods = bean.getClass().getDeclaredMethods();
		try {
			for (Method method : methods) {
				if (method.getName().startsWith("get")) {
					String field = method.getName();
					field = field.substring(field.indexOf("get") + 3);
					field = field.toLowerCase().charAt(0) + field.substring(1);
					Object value = method.invoke(bean, (Object[]) null);
					result.put(field, null == value ? "" : value.toString());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @Description(功能描述) : javaBean 转 javaBean
	 * @author(作者) ： liuchunxu
	 * @date (开发日期) : 2015年2月10日 下午3:41:22
	 * @exception :
	 * @param source
	 *            原对象
	 * @param result
	 *            转换结果
	 */
	public static void bean2Bean(Object source, Object result) {
		Method[] smethods = source.getClass().getDeclaredMethods();
		Method[] dmethods = result.getClass().getDeclaredMethods();
		try {
			for (Method smethod : smethods) {
				if (smethod.getName().startsWith("get")) {
					String sfield = smethod.getName();
					sfield = sfield.substring(sfield.indexOf("get") + 3);
					sfield = sfield.toLowerCase().charAt(0) + sfield.substring(1);
					Object value = smethod.invoke(source, (Object[]) null);
					if (!CommonUtil.isEmpty(value)) {
						for (Method dmethod : dmethods) {
							if (dmethod.getName().startsWith("set")) {
								String dfield = dmethod.getName();
								dfield = dfield.substring(dfield.indexOf("set") + 3);
								dfield = dfield.toLowerCase().charAt(0) + dfield.substring(1);
								if (dfield.equals(sfield)) {
									dmethod.invoke(result, new Object[] { value });
								}
							}
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * @Description(功能描述) : map 转换成 bean
	 * @author(作者) ： liuchunxu
	 * @date (开发日期) : 2015年2月10日 下午3:42:18
	 * @exception :
	 * @param map
	 *            原对象
	 * @param result
	 *            转换结果
	 */
	public static void map2Bean(Map<String, String> map, Object result) {
		Method[] methods = result.getClass().getDeclaredMethods();
		for (Method method : methods) {
			try {
				if (method.getName().startsWith("set")) {
					String field = method.getName();
					field = field.substring(field.indexOf("set") + 3);
					field = field.toLowerCase().charAt(0) + field.substring(1);
					method.invoke(result, new Object[] { map.get(field) });
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static File byte2File(byte[] buf, String filePath) {
		BufferedOutputStream bos = null;
		FileOutputStream fos = null;
		File file = null;
		try {
			File dir = new File(filePath + File.separator + UUID.randomUUID());
			if (!dir.exists() && dir.isDirectory()) {
				dir.mkdirs();
			}
			file = new File(filePath);
			fos = new FileOutputStream(file);
			bos = new BufferedOutputStream(fos);
			bos.write(buf);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (bos != null) {
				try {
					bos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (fos != null) {
				try {
					fos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return file;
	}

	public static String ToSBC(String input) {
		char c[] = input.toCharArray();
		for (int i = 0; i < c.length; i++) {
			if (c[i] == ' ') {
				c[i] = '\u3000';
			} else if (c[i] < '\177') {
				c[i] = (char) (c[i] + 65248);
			}
		}
		return new String(c);
	}
}
