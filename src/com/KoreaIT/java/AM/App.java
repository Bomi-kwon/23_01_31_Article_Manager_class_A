package com.KoreaIT.java.AM;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.KoreaIT.java.AM.controller.ArticleController;
import com.KoreaIT.java.AM.controller.Controller;
import com.KoreaIT.java.AM.controller.MemberController;
import com.KoreaIT.java.AM.dto.Article;
import com.KoreaIT.java.AM.dto.Member;
import com.KoreaIT.java.AM.util.Util;

public class App {
	private List<Article> articles;
	private List<Member> members;
	// 접근지정자 private, protected, public

	public App() {// static 생성자
		articles = new ArrayList<>();
		members = new ArrayList<>();
	}

	public void start() {
		System.out.println("== 프로그램 시작 ==");
		makeTestData();
		Scanner sc = new Scanner(System.in);
		MemberController memberController = new MemberController(members, sc);
		ArticleController articleController = new ArticleController(articles, sc);

		while (true) {
			System.out.printf("명령어 : ");
			String cmd = sc.nextLine().trim();

			if (cmd.equals("System exit")) {
				break;
			}

			else if (cmd.length() == 0) {
				System.out.println("명령어를 입력하세요.");
				continue;
			}
			
			String[] cmdBits = cmd.split(" ");
			
			if(cmdBits.length == 1) {
				System.out.println("존재하지 않는 명령어입니다.");
				continue;
			}
			Controller controller = null;
				
			String controllername = cmdBits[0];
			String actionMethodName = cmdBits[1];
			
			
			if(controllername.equals("article")) {
				controller = articleController;
			}
			else if(controllername.equals("member")) {
				controller = memberController;
			}
			else {
				System.out.println("존재하지 않는 명령어입니다.");
				continue;
			}
			controller.doAction(cmd, actionMethodName);
		}

		sc.close();

		System.out.println("== 프로그램 끝 ==");
	}


	private void makeTestData() {
		Article article1 = new Article(1, Util.getNowDateStr(), "제목1", "내용1", 10);
		Article article2 = new Article(2, Util.getNowDateStr(), "제목2", "내용2", 20);
		Article article3 = new Article(3, Util.getNowDateStr(), "제목3", "내용3", 30);
		articles.add(article1);
		articles.add(article2);
		articles.add(article3);
		System.out.println("테스트를 위한 데이터를 생성합니다.");
	}

}
