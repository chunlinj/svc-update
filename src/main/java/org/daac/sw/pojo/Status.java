package org.daac.sw.pojo;

public class Status {

	private String reset;
	private String swUpdate;
	private String cfgUpdate;
	public String getReset() {
		return reset;
	}
	public void setReset(String reset) {
		this.reset = reset;
	}
	public String getSwUpdate() {
		return swUpdate;
	}
	public void setSwUpdate(String swUpdate) {
		this.swUpdate = swUpdate;
	}
	public String getCfgUpdate() {
		return cfgUpdate;
	}
	public void setCfgUpdate(String cfgUpdate) {
		this.cfgUpdate = cfgUpdate;
	}
	@Override
	public String toString() {
		return "Status [reset=" + reset + ", swUpdate=" + swUpdate + ", cfgUpdate=" + cfgUpdate + "]";
	}
	public Status(String reset, String swUpdate, String cfgUpdate) {
		super();
		this.reset = reset;
		this.swUpdate = swUpdate;
		this.cfgUpdate = cfgUpdate;
	}
	public Status() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	 
}
