package com.KoreaIT.java.AM;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
	private static List<Article> articles;
	// 접근지정자 private, protected, public
	
	static {//static 생성자
		articles = new ArrayList<>();
	}

	public static void main(String[] args) {
		System.out.println("== 프로그램 시작 ==");
		makeTestData();
		Scanner sc = new Scanner(System.in);
		

		while (true) {
			System.out.printf("명령어 : ");
			String cmd = sc.nextLine().trim();

			if (cmd.equals("System exit")) {
				break;
			} else if (cmd.length() == 0) {
				System.out.println("명령어를 입력하세요.");
				continue;
			} else if (cmd.equals("article list")) {
				if (articles.size() == 0) {
					System.out.println("게시글이 없습니다.");
					continue;
				}
				System.out.println("번호    |    제목    |    조회수");
				for (int i = articles.size() - 1; i >= 0; i--) {
					Article article = articles.get(i);
					System.out.printf("%4d        |   %4s    |      %4d\n", article.id, article.title, article.hit);
				}
			} else if (cmd.equals("article write")) {

				String regDate = Util.getNowDateStr();
				System.out.printf("제목 : ");
				String title = sc.nextLine();
				System.out.printf("내용 : ");
				String body = sc.nextLine();
				int id = articles.size() + 1;
				Article article = new Article(id, regDate, title, body);
				articles.add(article);
				System.out.printf("%d번글이 생성되었습니다.\n", id);
			} else if (cmd.startsWith("article modify ")) {
				String[] cmdBits = cmd.split(" ");
				int searchId = Integer.parseInt(cmdBits[2]);

				Article foundArticle = null;

				for (int i = 0; i < articles.size(); i++) {
					Article article = articles.get(i);
					if (article.id == searchId) {
						foundArticle = article;
						break;
					}
				}
				if (foundArticle == null) {
					System.out.printf("%d번 게시물은 존재하지 않습니다.\n", searchId);
					continue;
				} else {
					System.out.printf("제목 : ");
					String modified_title = sc.nextLine();
					foundArticle.title = modified_title;
					System.out.printf("내용 : ");
					String modified_body = sc.nextLine();
					foundArticle.body = modified_body;
					System.out.printf("%d번 글이 수정되었습니다.\n", searchId);
				}
			} else if (cmd.startsWith("article detail ")) {
				
				String[] cmdBits = cmd.split(" ");
				int searchId = Integer.parseInt(cmdBits[2]);

				Article foundArticle = null;

				for (int i = 0; i < articles.size(); i++) {
					Article article = articles.get(i);
					if (article.id == searchId) {
						foundArticle = article;
						break;
					}
				}
				if (foundArticle == null) {
					System.out.printf("%d번 게시물은 존재하지 않습니다.\n", searchId);
					continue;
				} else {
					foundArticle.increasehit();
					System.out.printf("번호 : %d\n날짜 : %s\n제목 : %s\n내용 : %s\n조회수 : %d회\n", foundArticle.id, foundArticle.regDate,
							foundArticle.title, foundArticle.body, foundArticle.hit);
				}
			} else if (cmd.startsWith("article delete ")) {
				String[] cmdBits = cmd.split(" ");
				int searchId = Integer.parseInt(cmdBits[2]);

				int foundindex = -1;
				for (int i = 0; i < articles.size(); i++) {
					Article article = articles.get(i);
					if (article.id == searchId) {
						foundindex = i;
						break;
					}
				}
				if (foundindex == -1) {
					System.out.printf("%d번 게시물은 존재하지 않습니다.\n", searchId);
					continue;
				} else {
					articles.remove(foundindex);
					System.out.printf("%d번 게시물이 삭제되었습니다.\n", searchId);
				}
			}

			else {
				System.out.println("존재하지 않는 명령어입니다.");
			}
		}

		sc.close();

		System.out.println("== 프로그램 끝 ==");
	}

	private static void makeTestData() {
		Article article1 = new Article(1, Util.getNowDateStr(), "제목1", "내용1", 10);
		Article article2 = new Article(2, Util.getNowDateStr(), "제목2", "내용2", 20);
		Article article3 = new Article(3, Util.getNowDateStr(), "제목3", "내용3", 30);
		articles.add(article1);
		articles.add(article2);
		articles.add(article3);
		System.out.println("테스트를 위한 데이터를 생성합니다.");
	}
}

class Article {
	int id;
	String regDate;
	String title;
	String body;
	int hit;
	
	public Article(int id, String regDate, String title, String body) {
		this(id, regDate, title, body, 0);
		//다른 생성자한테 일 시키기!!
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