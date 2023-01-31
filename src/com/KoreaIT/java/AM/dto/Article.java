package com.KoreaIT.java.AM.dto;

public class Article {
	public int id;
	public String regDate;
	public String title;
	public String body;
	public int hit;

	public Article(int id, String regDate, String title, String body) {
		this(id, regDate, title, body, 0);
		// 다른 생성자한테 일 시키기!!
	}

	public Article(int id, String regDate, String title, String body, int hit) {
		this.id = id;
		this.regDate = regDate;
		this.title = title;
		this.body = body;
		this.hit = hit;
	}

	public void increasehit() {
		hit++;
	}
}