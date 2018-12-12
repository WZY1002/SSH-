package com.pl.service;

/**
 * 值对象:VO(Value Object) 
 * 1)封装控制层相关方法返回的数据 
 * 2)统一服务端相关方法返回的数据格式
 */
public class JsonResult {
	public static final Boolean SUCCESS = true;
	public static final Boolean ERROR = false;
	/** 状态:(SUCCESS,ERROR) */
	private Boolean state;
	/** 状态信息 */
	private String message;
	/** 具体数据 */
	private Object data;

	public Boolean getState() {
		return state;
	}

	public void setState(Boolean state) {
		this.state = state;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
}
