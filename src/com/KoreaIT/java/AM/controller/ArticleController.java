package com.KoreaIT.java.AM.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.KoreaIT.java.AM.dto.Article;
import com.KoreaIT.java.AM.util.Util;

public class ArticleController {
	private List<Article> articles;
	private Scanner sc;

	public ArticleController(List<Article> articles, Scanner sc) {
		this.articles = articles;
		this.sc = sc;
	}

	public void dowrite() {
		String regDate = Util.getNowDateStr();
		System.out.printf("제목 : ");
		String title = sc.nextLine();
		System.out.printf("내용 : ");
		String body = sc.nextLine();
		
		int id = articles.size() + 1;
		
		Article article = new Article(id, regDate, title, body);
		articles.add(article);
		System.out.printf("%d번글이 생성되었습니다.\n", id);

	}

	public void domodify(String cmd) {
		String[] cmdBits = cmd.split(" ");
		int searchId = Integer.parseInt(cmdBits[2]);

		Article foundArticle = getArticleById(searchId);

		if (foundArticle == null) {
			System.out.printf("%d번 게시물은 존재하지 않습니다.\n", searchId);
			return;
		} else {
			System.out.printf("제목 : ");
			String modified_title = sc.nextLine();
			foundArticle.title = modified_title;
			System.out.printf("내용 : ");
			String modified_body = sc.nextLine();
			foundArticle.body = modified_body;
			System.out.printf("%d번 글이 수정되었습니다.\n", searchId);
		}
	}
	
	

	public void showdetail(String cmd) {
		String[] cmdBits = cmd.split(" ");
		int searchId = Integer.parseInt(cmdBits[2]);

		Article foundArticle = getArticleById(searchId);

		if (foundArticle == null) {
			System.out.printf("%d번 게시물은 존재하지 않습니다.\n", searchId);
			return;
		} else {
			foundArticle.increasehit();
			System.out.printf("번호 : %d\n날짜 : %s\n제목 : %s" + "\n내용 : %s\n조회수 : %d회\n", foundArticle.id,
					foundArticle.regDate, foundArticle.title, foundArticle.body, foundArticle.hit);
		}		
	}

	public void dodelete(String cmd) {
		String[] cmdBits = cmd.split(" ");
		int searchId = Integer.parseInt(cmdBits[2]);

		int foundindex = getArticleByIndex(searchId);

		if (foundindex == -1) {
			System.out.printf("%d번 게시물은 존재하지 않습니다.\n", searchId);
			return;
		} else {
			articles.remove(foundindex);
			System.out.printf("%d번 게시물이 삭제되었습니다.\n", searchId);
		}		
	}

	public void showlist(String cmd) {
		if (articles.size() == 0) {
			System.out.println("게시글이 없습니다.");
			return;
		}

		String searchKeyword = cmd.substring("article list".length()).trim();

		if (searchKeyword.length() <= 0) {
			System.out.println("번호    |    제목     |     조회수");
			for (int i = articles.size() - 1; i >= 0; i--) {
				Article article = articles.get(i);
				System.out.printf("%-4d   |   %4s    |    %4d\n", article.id, article.title, article.hit);
			}
		}

		List<Article> forPrintArticles = articles;

		if (searchKeyword.length() > 0) {
			forPrintArticles = new ArrayList<>();

			for (Article article : articles) {
				if (article.title.contains(searchKeyword)) {
					forPrintArticles.add(article);
				}
			}
			if (forPrintArticles.size() == 0) {
				System.out.println("검색 결과가 없습니다.");
				return;
			}

			System.out.println("번호    |    제목     |     조회수");
			for (int i = forPrintArticles.size() - 1; i >= 0; i--) {
				Article article = forPrintArticles.get(i);
				System.out.printf("%-4d   |   %4s    |    %4d\n", article.id, article.title, article.hit);
			}
		}		
	}
	
	private Article getArticleById(int searchId) {
		int index = getArticleByIndex(searchId);
		if (index != -1) {
			return articles.get(index);
		}
		return null;
	}
	
	private int getArticleByIndex(int searchId) {
		int i = 0;
		for (Article article : articles) {
			if (article.id == searchId) {
				return i;
			}
			i++;
		}
		return -1;
	}

}
