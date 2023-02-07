package com.KoreaIT.java.AM;

import java.util.Scanner;

import com.KoreaIT.java.AM.controller.ArticleController;
import com.KoreaIT.java.AM.controller.Controller;
import com.KoreaIT.java.AM.controller.MemberController;

public class App {
	//App의 역할 : 올바른 명령어만 통과시키기, 라우팅(최적의 길로 안내)
	public App() {// static 생성자
	}

	public void start() {
		System.out.println("== 프로그램 시작 ==");
		Scanner sc = new Scanner(System.in);
		MemberController memberController = new MemberController(sc);
		ArticleController articleController = new ArticleController(sc);
		
		articleController.makeTestData();
		memberController.makeTestData();
		

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

	
	

}
