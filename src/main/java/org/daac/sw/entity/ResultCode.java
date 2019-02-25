package org.daac.sw.entity;
/**
 * 响应的枚举
 * @author 93999
 *
 */
public enum ResultCode {
	SUCCESS("20000","SUCCESS"),
	ERROR("20001","ERROR"),
	LOGINERROR("20002","用户名或密码错误"),
	ACCESSERROR("20003","权限不足"),
	REPERROR("20005","重复操作");


	
	private String code;
	private String msg;
	public String getCode() {
		return code;
	}
	private ResultCode(String code, String msg) {
		this.code = code;
		this.msg = msg;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
}
