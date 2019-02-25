package org.daac.sw.pojo.rest;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class DevAndServiceFile {
	
	@ApiModelProperty(value = "fileUrl")
	private FileUrl fileUrl;
	
	@ApiModelProperty(value = "fileUrl")
	private String version;
	
}
