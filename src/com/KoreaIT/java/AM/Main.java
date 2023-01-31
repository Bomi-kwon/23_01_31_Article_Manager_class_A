package com.KoreaIT.java.AM;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		System.out.println("== 프로그램 시작 ==");
		
		Scanner sc = new Scanner(System.in);
		int lastArticleId = 0;
		List<Article> articles = new ArrayList<>();
		
		while(true) {
			System.out.printf("명령어 : ");
			String cmd = sc.nextLine().trim();
			
			if(cmd.equals("System exit")) {
				break;
			}
			else if(cmd.length() == 0) {
				System.out.println("명령어를 입력하세요.");
				continue;
			}
			else if(cmd.equals("article list")) {
				if(articles.size() == 0) {
					System.out.println("게시글이 없습니다.");
					continue;
				}
				System.out.println("번호      |      제목");
				for (int i = articles.size()-1 ; i >= 0 ; i--) {
					Article article = articles.get(i);
					System.out.printf("%d        |   %s\n", article.id, article.title);
				}
			}
			else if(cmd.equals("article write")) {
				System.out.printf("제목 : ");
				String title = sc.nextLine();
				System.out.printf("내용 : ");
				String body = sc.nextLine();
				int id = lastArticleId+1;
				lastArticleId = id;
				Article article = new Article(id, title, body);
				articles.add(article);
				System.out.printf("%d번글이 생성되었습니다.\n",id);
			}
			else if(cmd.startsWith("article detail ")) {
				String[] cmdBits = cmd.split(" ");
				int searchId = Integer.parseInt(cmdBits[2]);
				
				Article foundArticle = null;
				
				for (int i = 0 ; i < articles.size() ; i++) {
					Article article = articles.get(i);
					if(article.id == searchId) {
						foundArticle = article;
						break;
					}
				}
				if (foundArticle == null) {
					System.out.printf("%d번 게시물은 존재하지 않습니다.\n",searchId);
					continue;
				}
				else {
					System.out.printf("번호 : %d\n날짜 : %s\n제목 : %s\n내용 : %s\n", foundArticle.id, LocalDateTime.now(), foundArticle.title, foundArticle.body);
				}
			}
			else if(cmd.startsWith("article delete ")) {
				String[] cmdBits = cmd.split(" ");
				int searchId = Integer.parseInt(cmdBits[2]);
				
				int foundindex = -1;
				for (int i = 0 ; i < articles.size() ; i++) {
					Article article = articles.get(i);
					if(article.id == searchId) {
						foundindex = i;
						break;
					}
				}
				if (foundindex == -1) {
					System.out.printf("%d번 게시물은 존재하지 않습니다.\n",searchId);
					continue;
				}
				else {
					articles.remove(foundindex);
					System.out.printf("%d번 게시물이 삭제되었습니다.\n",searchId);
				}
			}
			
			else {
				System.out.println("존재하지 않는 명령어입니다.");
			}
		}

	sc.close();

	System.out.println("== 프로그램 끝 ==");
}}

class Article {
	int id;
	String title;
	String body;

	public Article(int id, String title, String body) {
		this.id = id;
		this.title = title;
		this.body = body;
	}
}