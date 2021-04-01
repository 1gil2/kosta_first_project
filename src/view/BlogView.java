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
		System.out.println("-----�Խñ� �� ��ȸ-----");
		System.out.println(post);
	}
	
	public static void display(MemberVO member) {
		System.out.println("-----ȸ�� ���� ��ȸ-----");
		System.out.println(member);
	}
	
	public static void display(String message) {
		System.out.println(message);
	}

}
