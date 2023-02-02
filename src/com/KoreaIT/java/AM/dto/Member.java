package com.KoreaIT.java.AM.dto;

public class Member extends Dto{
	
	public String loginID;
	public String loginPW;
	public String name;

	public Member(int memberID, String ID, String PW, String name) {
		this.id = memberID;
		this.loginID = ID;
		this.loginPW = PW;
		this.name = name;
	}
}
