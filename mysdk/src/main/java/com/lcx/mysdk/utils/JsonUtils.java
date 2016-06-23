package com.lcx.mysdk.utils;

import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 
 * @ClassName(类名) : JsonUtils
 * @Description(描述) : json转换工具类
 * @author(作者) ：liuchunxu
 * @date (开发日期) ：2015年8月17日 下午5:17:17
 *
 */
public class JsonUtils {

	private static final String TAG = JsonUtils.class.getSimpleName();
	private static String sdf = "yyyy-MM-dd HH:mm:ss";
	private static Gson gson = null;
	
	/**
	 * @Description(功能描述) : 单例获取 ObjectMapper
	 * @author(作者) ： liuchunxu
	 * @date (开发日期) : 2015年3月5日 上午8:56:07
	 * @exception :
	 * @return ObjectMapper
	 */
	public static Gson buildObjectMapper() {
		if (gson == null) {
			gson = new GsonBuilder().setDateFormat(sdf).create();
		}
		return gson;
	}

	/**
	 * @Description(功能描述) : json 转换成 map
	 * @author(作者) ： liuchunxu
	 * @date (开发日期) : 2015年3月5日 上午9:03:18
	 * @exception :
	 * @param json :json字符串
	 * @return Map<String,Object>
	 */
	public static Map<String, Object> jsonToMap(String json) {
		Map<String, Object> map = null;
		try {
			map = JSON.parseObject(json, new TypeReference<Map<String, Object>>(){});
		} catch (Exception e) {
			Log.e(TAG, "json:转换出错");
			e.printStackTrace();
		}
		return map;
	}

	/**
	 * @Description(功能描述) : json 转换成 实体
	 * @author(作者) ： liuchunxu
	 * @date (开发日期) : 2015年3月5日 上午8:57:13
	 * @exception :
	 * @param json ：json字符串
	 * @param entity ：实体class
	 * @param <T> : 泛型
	 * @return T : 返回实体
	 */
	public static <T> T jsonToEntity(String json, Class<T> entity) {
		try {
			return JSON.parseObject(json, entity);
		} catch (Exception e) {
			Log.e(TAG, "json:转换出错");
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * @Description(功能描述) : json转成实体集合
	 * @author(作者) ： liuchunxu
	 * @date (开发日期) : 2015年3月5日 上午9:06:15
	 * @exception :
	 * @param json :json字符串
	 * @param entity ：实体class
	 * @param <T> : 泛型
	 * @return List<T> ： 返回实体集合
	 */
	public static <T> List<T> jsonToEntityList(String json, final Class<T> entity) {
		List<T> list = new ArrayList<T>();
		try {
			list = JSON.parseArray(json, entity);
			return list;
		} catch (Exception e) {
			Log.e(TAG, "json:转换出错");
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * @Description(功能描述) : 对象转换成json字符串
	 * @author(作者) ： liuchunxu
	 * @date (开发日期) : 2015年3月5日 上午10:59:16
	 * @exception :
	 * @param obj :对象
	 * @return String ：json字符转结果
	 */
	public static String stringify(Object obj) {
		try {
			String json = buildObjectMapper().toJson(obj);
			Log.d(TAG, json);
			return json;
		} catch (Exception e) {
			Log.e(TAG, "json:转换出错");
			e.printStackTrace();
			return null;
		}
	}
}
