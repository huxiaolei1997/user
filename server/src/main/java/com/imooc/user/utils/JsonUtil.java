package com.imooc.user.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

/**
 * Created by 廖师兄
 * 2018-02-21 10:40
 */
public class JsonUtil {

	private static ObjectMapper objectMapper = new ObjectMapper();

	/**
	 * 转换为json字符串
	 * @param object
	 * @return
	 */
	public static String toJson(Object object) {
		try {
			return objectMapper.writeValueAsString(object);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static Object fromJson(String string, Class classType) {
		try {
			objectMapper.readValue(string, classType);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
