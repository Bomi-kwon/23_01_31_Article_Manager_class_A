package com.KoreaIT.java.AM.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.KoreaIT.java.AM.dto.Member;

public class MemberController extends Controller {
	private List<Member> members;
	private Scanner sc;
	private String cmd;
	private String actionMethodName;
	private Member matched_member;

	public MemberController(Scanner sc) {
		this.sc = sc;

		members = new ArrayList<Member>();
	}

	public void doAction(String cmd, String actionMethodName) {
		this.cmd = cmd;
		this.actionMethodName = actionMethodName;

		switch (actionMethodName) {
		case "join":
			dojoin();
			break;
		case "login":
			dologin();
			break;
		case "logout":
			dologout();
			break;
		default:
			System.out.println("존재하지 않는 명령어입니다.");
			break;
		}
	}

	

	private void dojoin() {
		String ID = null;
		while (true) {
			System.out.printf("로그인 아이디 : ");
			ID = sc.nextLine();

			if (isjoinableId(ID) == false) {
				System.out.println("이미 존재하는 아이디입니다.");
				continue;
			}
			break;
		}

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

	private void dologin() {
		if (matched_member != null) {
			System.out.printf("이미 %s님이 로그인된 상태입니다.\n",matched_member.name);
			return;
		}
		
		while (true) {
			System.out.printf("로그인 아이디 : ");
			String ID = sc.nextLine();
			for (Member member : members) {
				if (member.loginID.equals(ID)) {
					matched_member = member;
				}
			}
			if (matched_member == null) {
				System.out.println("존재하지 않는 아이디입니다.");
				continue;
			}
			break;
		}
		while (true) {
			System.out.printf("로그인 비밀번호 : ");
			String PW = sc.nextLine();
			if (matched_member.loginPW.equals(PW)) {
				System.out.printf("%s님이 로그인했습니다.\n", matched_member.name);
				break;
			} else {
				System.out.println("비밀번호를 다시 확인하세요.");
				continue;
			}

		}
		
		
	}
	
	private void dologout() {
		if (matched_member == null) {
			System.out.println("로그인된 회원이 없습니다.");
		}
		else {
			System.out.printf("%s님이 로그아웃 했습니다.\n", matched_member.name);
			matched_member = null;
		}
		
	}

	private boolean isjoinableId(String iD) {
		int index = getMemberIndexbyLoginID(iD);

		if (index == -1) {
			return true;
		}
		return false;
	}

	private int getMemberIndexbyLoginID(String iD) {
		int i = 0;
		for (Member member : members) {
			if (member.loginID.equals(iD)) {
				// 근데 왜 member.loginID == iD 라고 하면 로직이 안 먹히지???
				return i;
			}
			i++;
		}
		return -1;
	}

	public void makeTestData() {

		Member member1 = new Member(1, "admin", "admin", "관리자");
		Member member2 = new Member(2, "test1", "test1", "철수");
		Member member3 = new Member(3, "test2", "test2", "짱구");
		members.add(member1);
		members.add(member2);
		members.add(member3);

		System.out.println("테스트를 위한 회원 데이터를 생성합니다.");
	}

}
