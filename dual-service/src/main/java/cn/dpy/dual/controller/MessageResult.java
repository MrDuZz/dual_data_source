package cn.dpy.dual.controller;

import com.alibaba.fastjson.JSONObject;
//import io.swagger.annotations.ApiModel;
//import io.swagger.annotations.ApiModelProperty;

//@ApiModel(value = "通用响应对象")
public class MessageResult {

//	@ApiModelProperty(value="响应编码")
	private int code;

//	@ApiModelProperty(value="响应消息")
	private String message;

//	@ApiModelProperty(value="总页数")
	private String totalPage;

//	@ApiModelProperty(value="总数量")
	private String totalElement;
	
//	@ApiModelProperty(value="响应数据体")
	private Object data;
	
	public MessageResult() {}

	public MessageResult(int code, String msg) {
		this.code = code;
		this.message = msg;
	}

	public MessageResult(int code, String msg, Object object) {
		this.code = code;
		this.message = msg;
		this.data = object;
	}

	public static MessageResult success() {
		return new MessageResult(0, "SUCCESS");
	}

	public static MessageResult success(String msg) {
		return new MessageResult(0, msg);
	}

	public static MessageResult success(String msg, Object data) {
		return new MessageResult(0, msg, data);
	}

	public static MessageResult error(int code, String msg) {
		return new MessageResult(code, msg);
	}

	public static MessageResult error(String msg) {
		return new MessageResult(500, msg);
	}

	public String getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(String totalPage) {
		this.totalPage = totalPage;
	}

	public String getTotalElement() {
		return totalElement;
	}

	public void setTotalElement(String totalElement) {
		this.totalElement = totalElement;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return JSONObject.toJSONString(this);
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public static MessageResult getSuccessInstance(String message, Object data) {
		MessageResult result = success(message);
		result.setData(data);
		return result;
	}
}
