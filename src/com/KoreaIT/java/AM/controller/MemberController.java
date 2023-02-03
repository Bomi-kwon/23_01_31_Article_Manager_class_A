package com.KoreaIT.java.AM.controller;

import java.util.List;
import java.util.Scanner;

import com.KoreaIT.java.AM.dto.Member;

public class MemberController {
	private List<Member> members;
	private Scanner sc;
	
	public MemberController(List<Member> members, Scanner sc) {
		this.members = members;
		this.sc = sc;
	}

	public void dojoin() {
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
	
	private boolean isjoinableId(String iD) {
		int index = getMemberIndexbyLoginID(iD);
		
		if(index == -1) {
			return true;
		}
		return false;
	}

	private int getMemberIndexbyLoginID(String iD) {
		int i = 0;
		for (Member member : members) {
			if (member.loginID.equals(iD)) {
				//근데 왜 member.loginID == iD 라고 하면 로직이 안 먹히지???
				return i;
			}
			i++;
		}
		return -1;
	}

}
