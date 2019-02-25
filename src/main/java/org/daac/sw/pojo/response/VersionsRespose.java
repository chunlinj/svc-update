package org.daac.sw.pojo.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.daac.sw.pojo.rest.DevAndServiceFile;

@Data
public class VersionsRespose extends AbaseResponse {

	@ApiModelProperty(value = "url")
	private DevAndServiceFile fileUrl;

}
