package org.daac.sw.pojo.rest;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class FileUrl {
	@ApiModelProperty(value = "device")
	private String device;
	@ApiModelProperty(value = "service")
	private String service;

}
