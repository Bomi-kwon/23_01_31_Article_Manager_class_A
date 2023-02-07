package com.KoreaIT.java.AM.dto;

public class Article extends Dto{
	public String title;
	public String body;
	public int hit;
	public int memberId;

	public Article(int id, String regDate, int memberId,String title, String body) {
		this(id, regDate, memberId,title, body, 0);
		// 다른 생성자한테 일 시키기!!
	}

	public Article(int id, String regDate, int memberId,String title, String body, int hit) {
		this.id = id;
		this.regDate = regDate;
		this.memberId = memberId;
		this.title = title;
		this.body = body;
		this.hit = hit;
	}

	public void increasehit() {
		hit++;
	}
}