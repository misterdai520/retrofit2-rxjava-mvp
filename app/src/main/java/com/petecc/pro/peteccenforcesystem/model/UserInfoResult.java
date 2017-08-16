package com.petecc.pro.peteccenforcesystem.model;

public class UserInfoResult {
	private boolean terminalExistFlag;
	private User listObject;
	private String errMessage;
	public boolean isRequestFlag() {
		return terminalExistFlag;
	}
	public void setRequestFlag(boolean terminalExistFlag) {
		this.terminalExistFlag = terminalExistFlag;
	}
	public User getUser() {
		return listObject;
	}
	public void setUser(User listObject) {
		this.listObject = listObject;
	}
	public String getErrorMsg() {
		return errMessage==null?"":errMessage;
	}
	public void setErrorMsg(String errorMsg) {
		this.errMessage = errMessage;
	}

	@Override
	public String toString() {
		return "UserInfoResult{" +
				"requestFlag=" + terminalExistFlag +
				", user=" + listObject.toString() +
				", errorMsg='" + errMessage + '\'' +
				'}';
	}
}
