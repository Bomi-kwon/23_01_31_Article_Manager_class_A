package com.KoreaIT.java.AM.controller;

import com.KoreaIT.java.AM.dto.Member;

public abstract class Controller {
	public static Member matched_member;
	//ArticleController와 MemberController에서 모두 쓸수있는
	//연동되는 변수 만들기. static 쓰면 설계도에만 딱 하나 만드는 변수
	
	public abstract void doAction(String cmd, String actionMethodName);
	
	public abstract void makeTestData();
}
