package org.daac.sw.pojo.response;

import io.swagger.annotations.ApiModelProperty;

public abstract class AbaseResponse {

	@ApiModelProperty(value="响应状态码")
	private String returnCode;
	@ApiModelProperty(value="响应信息")
	private String returnMsg;

	public AbaseResponse(String returnCode, String returnMsg) {
		super();
		this.returnCode = returnCode;
		this.returnMsg = returnMsg;
	}

	public AbaseResponse() {
		super();
	}

	@Override
	public String toString() {
		return "AbaseResponse [returnCode=" + returnCode + ", returnMsg=" + returnMsg + "]";
	}

	public String getReturnCode() {
		return returnCode;
	}

	public void setReturnCode(String returnCode) {
		this.returnCode = returnCode;
	}

	public String getReturnMsg() {
		return returnMsg;
	}

	public void setReturnMsg(String returnMsg) {
		this.returnMsg = returnMsg;
	}
}
