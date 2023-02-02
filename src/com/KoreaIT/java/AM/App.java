package com.KoreaIT.java.AM;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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

			else if (cmd.equals("article write")) {
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

			else if (cmd.startsWith("article modify ")) {
				String[] cmdBits = cmd.split(" ");
				int searchId = Integer.parseInt(cmdBits[2]);

				Article foundArticle = getArticleById(searchId);

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
			}

			else if (cmd.startsWith("article detail ")) {

				String[] cmdBits = cmd.split(" ");
				int searchId = Integer.parseInt(cmdBits[2]);

				Article foundArticle = getArticleById(searchId);

				if (foundArticle == null) {
					System.out.printf("%d번 게시물은 존재하지 않습니다.\n", searchId);
					continue;
				} else {
					foundArticle.increasehit();
					System.out.printf("번호 : %d\n날짜 : %s\n제목 : %s" + "\n내용 : %s\n조회수 : %d회\n", foundArticle.id,
							foundArticle.regDate, foundArticle.title, foundArticle.body, foundArticle.hit);
				}
			}

			else if (cmd.startsWith("article delete ")) {
				String[] cmdBits = cmd.split(" ");
				int searchId = Integer.parseInt(cmdBits[2]);

				int foundindex = getArticleByIndex(searchId);

				if (foundindex == -1) {
					System.out.printf("%d번 게시물은 존재하지 않습니다.\n", searchId);
					continue;
				} else {
					articles.remove(foundindex);
					System.out.printf("%d번 게시물이 삭제되었습니다.\n", searchId);
				}
			}

			else if (cmd.startsWith("article list")) {
				if (articles.size() == 0) {
					System.out.println("게시글이 없습니다.");
					continue;
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
						continue;
					}

					System.out.println("번호    |    제목     |     조회수");
					for (int i = forPrintArticles.size() - 1; i >= 0; i--) {
						Article article = forPrintArticles.get(i);
						System.out.printf("%-4d   |   %4s    |    %4d\n", article.id, article.title, article.hit);
					}
				}
			}

			else if (cmd.equals("member join")) {
				System.out.printf("로그인 아이디 : ");
				String ID = sc.nextLine();
				String PW = null;

				while (true) {
					System.out.printf("로그인 비밀번호 : ");
					PW = sc.nextLine();
					System.out.printf("로그인 비밀번호 확인: ");
					String PWcheck = sc.nextLine();
					if (PW.equals(PWcheck) == false) {
						System.out.println("비밀번호가 일치하지 않습니다.");
						continue;
					}
					break;
				}

				System.out.printf("이름 : ");
				String name = sc.nextLine();
				int memberid = members.size() + 1;
				Member member = new Member(memberid, ID, PW, name);
				members.add(member);
				System.out.printf("%d번 회원이 가입했습니다.\n", memberid);
			}

			else if (cmd.equals("sign in")) {
				// break랑 continue 중에 뭘 쓸지 모르겠다!!
				System.out.printf("로그인 아이디 : ");
				String ID = sc.nextLine();
				for (Member member : members) {
					if (member.loginID == ID) {
						System.out.printf("로그인 비밀번호 : ");
						String PW = sc.nextLine();
						if (member.loginPW == PW) {
							System.out.printf("%s 회원이 로그인했습니다.\n", ID);
						} else {
							System.out.println("입력한 비밀번호가 맞지 않습니다.");
							break;
						}
					}
					System.out.println("해당 아이디가 존재하지 않습니다.");
					break;
				}

			}

			else {
				System.out.println("존재하지 않는 명령어입니다.");
			}
		}

		sc.close();

		System.out.println("== 프로그램 끝 ==");
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

	private Article getArticleById(int searchId) {
		int index = getArticleByIndex(searchId);
		if (index != -1) {
			return articles.get(index);
		}
		return null;
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
