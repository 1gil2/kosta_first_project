package view;

import java.util.List;

import model.CommentVO;
import model.MemberVO;
import model.PostVO;

public class BlogView {
	
	public static void display(List<CommentVO> clist) {
		for(CommentVO comment : clist) {
			System.out.println(comment);
		}
	}
	
	public static void display(PostVO post) {
		System.out.println("-----게시글 상세 조회-----");
		System.out.println(post);
	}
	
	public static void display(MemberVO member) {
		System.out.println("-----회원 정보 조회-----");
		System.out.println(member);
	}
	
	public static void display(String message) {
		System.out.println(message);
	}

}
