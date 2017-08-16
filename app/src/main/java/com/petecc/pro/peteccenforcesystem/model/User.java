package com.petecc.pro.peteccenforcesystem.model;

public class User {

	private String USERNAME;//用户名
	private int ORGLEVEL;//机构级别
	private String USERORGNAME;//用户机构名
	private String USERREALNAME;//用户名
//	private String USERID;//用户ID
	private String ORGCODE;//机构Code
	private String ORGSEQ;//机构seq
	private String USERORGID;//用户机构ID
	private String EMPCODE;//工号
	private String USERID;//用户ID
	private String token;//token
	public String getToken() {
		return token==null?"":token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getUsername() {
		return USERNAME==null?"":USERNAME;
	}
	public void setUsername(String USERNAME) {
		this.USERNAME = USERNAME;
	}
	public int getOrglevel() {
		return ORGLEVEL;
	}
	public void setOrglevel(int ORGLEVEL) {
		this.ORGLEVEL = ORGLEVEL;
	}
	public String getOrgname() {
		return USERORGNAME==null?"":USERORGNAME;
	}
	public void setOrgname(String USERORGNAME) {
		this.USERORGNAME = USERORGNAME;
	}
	public String getRealname() {
		return USERREALNAME==null?"":USERREALNAME;
	}
	public void setRealname(String realname) {
		this.USERREALNAME = USERREALNAME;
	}
	/*public String getEmpid() {
		return empid;
	}
	public void setEmpid(String empid) {
		this.empid = empid;
	}*/
	public String getOrgcode() {
		return ORGCODE==null?"":ORGCODE;
	}
	public void setOrgcode(String ORGCODE) {
		this.ORGCODE = ORGCODE;
	}
	public String getOrgseq() {
		return ORGSEQ==null?"":ORGSEQ;
	}
	public void setOrgseq(String ORGSEQ) {
		this.ORGSEQ = ORGSEQ;
	}
	public String getOrgid() {
		return USERORGID==null?"":USERORGID;
	}
	public void setOrgid(String USERORGID) {
		this.USERORGID = USERORGID;
	}
	public String getEmpcode() {
		return EMPCODE==null?"":EMPCODE;
	}
	public void setEmpcode(String EMPCODE) {
		this.EMPCODE = EMPCODE;
	}
	public String getUserid() {
		return USERID==null?"":USERID;
	}
	public void setUserid(String USERID) {
		this.USERID = USERID;
	}

	@Override
	public String toString() {
		return "User{" +
				"username='" + USERNAME + '\'' +
				", orglevel=" + ORGLEVEL +
				", orgname='" + USERORGNAME + '\'' +
				", realname='" + USERREALNAME + '\'' +
				", orgcode='" + ORGCODE + '\'' +
				", orgseq='" + ORGSEQ + '\'' +
				", orgid='" + USERORGID + '\'' +
				", empcode='" + EMPCODE + '\'' +
				", userid='" + USERID + '\'' +
				'}';
	}
}
