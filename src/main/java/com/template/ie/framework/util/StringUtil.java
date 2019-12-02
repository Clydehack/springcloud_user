package com.template.ie.framework.util;

import java.util.Date;
import java.util.UUID;

import org.springframework.stereotype.Component;

@Component
public class StringUtil {
	
	/**
	 * 获取uuid
	 */
	public static String getUUID() {
		return UUID.randomUUID().toString();
	}
	
	/**
	 * 获取没有横杠的uuid
	 */
	public static String getNoHorizontalUUID() {
		String uuid = UUID.randomUUID().toString();
		return uuid.replaceAll("-", "");
	}
	
	/**
	 * 得到特定字符出现次数
	 * @param srcText 源
	 * @param findText  目标
	 * @return int
	 */
	public static int appearNum(String srcText, String findText) {
	    int count = 0;
	    int index = 0;
	    while ((index = srcText.indexOf(findText, index)) != -1) {
	        index = index + findText.length();
	        count++;
	    }
	    return count;
	}
	/**
	 * 转换为下划线
	 *
	 * @param camelCaseName
	 * @return
	 */
	public static String underscoreName(String camelCaseName) {
		StringBuilder result = new StringBuilder();
		if (camelCaseName != null && camelCaseName.length() > 0) {
			result.append(camelCaseName.substring(0, 1).toLowerCase());
			for (int i = 1; i < camelCaseName.length(); i++) {
				char ch = camelCaseName.charAt(i);
				if (Character.isUpperCase(ch)) {
					result.append("_");
					result.append(Character.toLowerCase(ch));
				} else {
					result.append(ch);
				}
			}
		}
		return result.toString();
	}

	/**
	 * 转换为驼峰
	 *
	 * @param underscoreName
	 * @return
	 */
	public static String camelCaseName(String underscoreName) {
		StringBuilder result = new StringBuilder();
		if (underscoreName != null && underscoreName.length() > 0) {
			boolean flag = false;
			for (int i = 0; i < underscoreName.length(); i++) {
				char ch = underscoreName.charAt(i);
				if ("_".charAt(0) == ch) {
					flag = true;
				} else {
					if (flag) {
						result.append(Character.toUpperCase(ch));
						flag = false;
					} else {
						result.append(ch);
					}
				}
			}
		}
		return result.toString();
	}
	/**
	 * 验证字符串类型是否为空
	 * 
	 * @param inputString 空值返回true，非空返回false
	 * @return
	 */
	public static Boolean isNull(String inputString) {
		if (inputString == null || "".equals(inputString) || "null".equals(inputString)
				|| "undefined".equals(inputString)) {
			return true;
		}
		return false;
	}

	public static Boolean isNull(Date date) {
		if (date == null) {
			return true;
		}
		return false;
	}

	public static boolean isNull(Integer param) {
		if (param == null) {
			return true;
		}
		return false;
	}

	/**
	 * 检验字符是否是正整数
	 * 
	 * @param pageStr
	 * @return
	 */
	public static boolean isPosInt(String str) {
		if (str == null || "".equals(str.trim()))
			return false;
		for (int i = str.length(); --i >= 0;) {
			if (!Character.isDigit(str.charAt(i))) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 隐藏姓名
	 * 
	 * @param name
	 * @return
	 */
	public static String hideName(String name) {
		String str = "**";
		StringBuilder sb = new StringBuilder(name);
		int i = 0;
		if (name.length() == 2) {
			i = 2;
		} else {
			i = name.length() - 1;
		}
		sb.replace(1, i, str);
		return sb.toString();
	}

	/**
	 * 隐藏手机号中间四位
	 * 
	 * @param phone
	 * @return
	 */
	public static String hidePhoneNum(String phone) {
		return phone.replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2");
	}

	/**
	 * 隐藏快递地址
	 * 
	 * @param address
	 * @return
	 */
	public static String hideAddress(String address) {
		String str = "****";
		StringBuilder sb = new StringBuilder(address);
		int i = 0;
		int j = 0;
		if (address.length() <= 9) {
			i = 9;
		} else {
			i = address.length() - 3;
		}
		if (address.length() < 6) {
			j = address.length() - address.length() + 1;
		} else {
			j = 6;
		}
		sb.replace(j, i, str);
		return sb.toString();
	}

}