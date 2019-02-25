package org.daac.sw.pojo.response;

import io.swagger.annotations.ApiModelProperty;

import java.util.List;

public class BranchResponse extends AbaseResponse {
	@ApiModelProperty(value="所有分支名称")
	private List<String> branch;

	public List<String> getBranch() {
		return branch;
	}

	public void setBranch(List<String> branch) {
		this.branch = branch;
	}

	@Override
	public String toString() {
		return "BranchesResponse [branch=" + branch + "]";
	}

	public BranchResponse(String returnCode, String returnMsg, List<String> branch) {
		super(returnCode, returnMsg);
		this.branch = branch;
	}

	public BranchResponse() {
		super();
		// TODO Auto-generated constructor stub
	}



}
