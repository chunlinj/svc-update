/**
  * Copyright 2019 bejson.com 
  */
package org.daac.sw.pojo;

/**
 * Auto-generated: 2019-01-22 20:26:40
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class Device {

	
	private String _id;
	private String deviceId;
    private String lastActiveTime;
    private String swVersion;
    private String cfgVersion;
    private Status status;
	public String get_id() {
		return _id;
	}
	public void set_id(String _id) {
		this._id = _id;
	}
	public String getDeviceId() {
		return deviceId;
	}
	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}
	public String getLastActiveTime() {
		return lastActiveTime;
	}
	public void setLastActiveTime(String lastActiveTime) {
		this.lastActiveTime = lastActiveTime;
	}
	public String getSwVersion() {
		return swVersion;
	}
	public void setSwVersion(String swVersion) {
		this.swVersion = swVersion;
	}
	public String getCfgVersion() {
		return cfgVersion;
	}
	public void setCfgVersion(String cfgVersion) {
		this.cfgVersion = cfgVersion;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	public Device(String _id, String deviceId, String lastActiveTime, String swVersion, String cfgVersion,
                  Status status) {
		super();
		this._id = _id;
		this.deviceId = deviceId;
		this.lastActiveTime = lastActiveTime;
		this.swVersion = swVersion;
		this.cfgVersion = cfgVersion;
		this.status = status;
	}
	public Device() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Device [_id=" + _id + ", deviceId=" + deviceId + ", lastActiveTime=" + lastActiveTime + ", swVersion="
				+ swVersion + ", cfgVersion=" + cfgVersion + ", status=" + status + "]";
	}
    
}