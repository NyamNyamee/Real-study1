package com.spring.inwoo.vo;

public class BoardVo {
	// 글번호
	private Long no;
	// 제목
	private String title;
	// 내용
	private String content;
	// 작성시간
	private String regDate;
	// 작성자 이름
	private String userName;
	// 조회수
	private Integer hit;
	// 원본 글번호
	private Integer groupNo;
	// 게시글이 나타날 순서
	private Integer orderNo;
	// 답글의 깊이(원본글에 대해 몇번째 답글인지)
	private Integer depth;
	// 작성자 번호
	private Long userNo;
	
	public Long getNo() {
		return no;
	}
	public String getTitle() {
		return title;
	}
	public String getContent() {
		return content;
	}
	public String getRegDate() {
		return regDate;
	}
	public Integer getHit() {
		return hit;
	}
	public Integer getGroupNo() {
		return groupNo;
	}
	public Integer getOrderNo() {
		return orderNo;
	}
	public Integer getDepth() {
		return depth;
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
	public void setTitle(String title) {
		this.title = title;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}
	public void setHit(Integer hit) {
		this.hit = hit;
	}
	public void setGroupNo(Integer groupNo) {
		this.groupNo = groupNo;
	}
	public void setOrderNo(Integer orderNo) {
		this.orderNo = orderNo;
	}
	public void setDepth(Integer depth) {
		this.depth = depth;
	}
	public void setUserNo(Long userNo) {
		this.userNo = userNo;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	@Override
	public String toString() {
		return "BoardVo [no=" + no + ", title=" + title + ", content=" + content + ", regDate=" + regDate + ", hit="
				+ hit + ", groupNo=" + groupNo + ", orderNo=" + orderNo + ", depth=" + depth + ", userNo=" + userNo
				+ ", userName=" + userName + "]";
	}
}








