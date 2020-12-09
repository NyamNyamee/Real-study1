package com.spring.inwoo.vo;

public class GalleryVo {
	private Long no;
	private String orgFileName;
	private String saveFileName;
	private String comments;
	private Long fileSize;
	private String fileExtName;
	private String regDate;
	private Long userNo;
	private String userName;
	
	public Long getNo() {
		return no;
	}
	public String getOrgFileName() {
		return orgFileName;
	}
	public String getSaveFileName() {
		return saveFileName;
	}
	public String getComments() {
		return comments;
	}
	public Long getFileSize() {
		return fileSize;
	}
	public String getFileExtName() {
		return fileExtName;
	}
	public String getRegDate() {
		return regDate;
	}
	public Long getUserNo() {
		return userNo;
	}
	public String getUserName() {
		return userName;
	}
	public void setNo(Long no) {
		this.no = no;
	}
	public void setOrgFileName(String orgFileName) {
		this.orgFileName = orgFileName;
	}
	public void setSaveFileName(String saveFileName) {
		this.saveFileName = saveFileName;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public void setFileSize(Long fileSize) {
		this.fileSize = fileSize;
	}
	public void setFileExtName(String fileExtName) {
		this.fileExtName = fileExtName;
	}
	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}
	public void setUserNo(Long userNo) {
		this.userNo = userNo;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	@Override
	public String toString() {
		return "GalleryVo [no=" + no + ", orgFileName=" + orgFileName + ", saveFileName=" + saveFileName + ", comments="
				+ comments + ", fileSize=" + fileSize + ", fileExtName=" + fileExtName + ", regDate=" + regDate
				+ ", userNo=" + userNo + ", userName=" + userName + "]";
	}
}








