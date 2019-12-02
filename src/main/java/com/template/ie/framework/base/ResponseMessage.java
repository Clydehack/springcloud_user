package com.template.ie.framework.base;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ResponseMessage implements Serializable {
	
	private static final long serialVersionUID = 1946981358215053002L;

	@ApiModelProperty(value = "应答状态")
	private Integer state;
	
	@ApiModelProperty(value = "描述")
	private String desc;
	
	@ApiModelProperty(value = "信息编码")
	private String code;
	
	@ApiModelProperty(value = "应答数据")
	private Object data;
	
	/**
	 * 创建正确应答
	 * @param desc	成功信息描述
	 * @param data	需要给前端返回的数据
	 * @return
	 */
	public static String createOkMsg(String desc){
		return createOkMsg(desc, null);
	}
	public static String createOkMsg(String desc, Object data){
		try {
			Map<String, Object> temp = new HashMap<String, Object>();
			temp.put("state", 1);
			temp.put("desc", desc);
			if(data != null) temp.put("data", data);
			return JSON.toJSONString(temp, SerializerFeature.WriteMapNullValue);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	public static String createOkMsg(String desc, String token, Object data){
		try {
			Map<String, Object> temp = new HashMap<String, Object>();
			temp.put("state", 1);
			temp.put("desc", desc);
			temp.put("token", token);
			if(data != null) temp.put("data", data);
			return JSON.toJSONString(temp, SerializerFeature.WriteMapNullValue);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 创建错误应答
	 * @param desc	错误信息描述
	 * @param code	错误编码
	 * @param data	需要给前端返回的数据
	 * @return
	 */
	public static String createErrMsg(String desc, String code){
		return createErrMsg(desc, code, null);
	}
	public static String createErrMsg(String desc, String code, Object data){
		try {
			Map<String, Object> temp = new HashMap<String, Object>();
			temp.put("state", 0);
			temp.put("desc", desc);
			temp.put("code", code);
			if(data != null) temp.put("data", data);
			return JSON.toJSONString(temp);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}