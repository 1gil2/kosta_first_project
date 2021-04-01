package controller;

import java.util.List;
import java.util.Scanner;

import model.BlogDAO;
import model.CommentVO;
import model.MemberVO;
import model.PostVO;
import view.BlogView;

public class NewBlogController {

	Scanner sc = new Scanner(System.in);
	MemberVO member = null;
	PostVO post = null;
	CommentVO comment = null;
	List<CommentVO> clist = null;
	BlogDAO blogDAO = new BlogDAO();
	boolean login = false;
	boolean flag2 = false;
	boolean flag3 = false;
	String id = null;
	String pw = null;
	int result = 0;
	int cmd = 0;
	int no = 0;

	protected void main_method() {
		while(true) {
			if(login)
				logined_true();
			else
				logined_false();
		}	
	}

	//��Ȳ ����
	private void logined_false() {
		System.out.println("-----�۾� ����(�α���X)-----");
		System.out.println("1. ȸ������, 2. �α���, 3. �Խñ� ��ȸ, 9. ����");
		cmd = sc.nextInt();
		switch (cmd) {
		case 1:
			//ȸ������
			member_create();
			break;
		case 2:
			//�α���
			login();
			break;
		case 3:
			//�Խñ� ��ü ��ȸ
			flag2 = true;
			while(flag2) {
				action_login_false();
			}
			break;
		case 9:
			System.out.println("���α׷� ����");
			System.exit(0);
		}

	}
	private void logined_true() {
		System.out.println("-----�۾� ����(�α���O)-----");
		System.out.println("1. �α׾ƿ�, 2. ȸ�� ���� ����, 3. Ż��, 4. �Խñ� ��ȸ, 9. ����");
		cmd = sc.nextInt();
		switch (cmd) {
		case 1:
			//�α׾ƿ�
			logout();
			break;
		case 2:
			//ȸ�� ���� ����
			member_update();
			break;
		case 3:
			//Ż��
			member_delete();
			break;
		case 4:
			//�Խñ� ��ü ��ȸ
			flag2 = true;
			while(flag2) {
				action_login_true();
			}
			break;
		case 9:
			System.out.println("���α׷� ����");
			System.exit(0);
		}	
	}
	private void action_login_true() {
		blogDAO.selectPostAll();
		System.out.println();
		System.out.println("=====�۾� ����(�Խñ� ��ü ��ȸ��)=====");
		System.out.println("1. �Խñ� �� ��ȸ, 2. �Խñ� �ۼ�, 9. ����, 0. ��������");
		cmd = sc.nextInt();
		switch (cmd) {
		case 1:
			//�Խñ� �� ��ȸ
			post_read_one();

			flag3 = true;
			while(flag3) {
				action_post_detail();
			}
			break;
		case 2:
			//�Խñ� �ۼ�
			create_post();
			break;
		case 9:
			System.out.println("���α׷� ����");
			System.exit(0);
			break;
		case 0:
			flag2 = false;
			break;
		}

	}
	private void action_post_detail() {
		System.out.println();
		System.out.println("*****�۾� ����(�Խñ� �� ��ȸ��)*****");
		System.out.println("1. �Խñ� ����, 2. �Խñ� ����, 3. ��� �ۼ�, 4. ��� ����, 5. ��� ����, 6. ���ƿ� ��ư Ŭ��, 0. �۾� ���");
		cmd = sc.nextInt();
		switch (cmd) {
		case 1:
			//�Խñ� ����
			post_update();
			break;
		case 2:
			//�Խñ� ����
			post_delete();
			flag3 = false;
			break;
		case 3:
			//��� �ۼ�
			comment_create();
			break;
		case 4:
			//��� ����
			comment_update();
			break;
		case 5:
			//��� ����
			comment_delete();
			break;
		case 6:
			//���ƿ� ������
			post_like_action();
		case 0:
			flag3 = false;
			break;
		}

	}
	private void action_login_false() {
		System.out.println();
		blogDAO.selectPostAll();
		System.out.println();
		System.out.println("=====�۾� ����(�Խñ� ��ü ��ȸ��)=====");
		System.out.println("1. �Խñ� �� ��ȸ, 9. ����, 0. ��������");
		cmd = sc.nextInt();
		switch (cmd) {
		case 1:
			//�Խñ� �� ��ȸ
			post_read_one();
			break;
		case 9:
			System.out.println("���α׷� ����");
			System.exit(0);
			break;
		case 0:
			flag2 = false;
			break;
		}
	}
	//CRUD��� ����
	private void post_delete() {
		result = blogDAO.postDelete(id, no);
		BlogView.display(result>0?"�Խñۻ�������":"�Խñۻ�������");

	}
	private void member_delete() {
		System.out.print("ID Ȯ�� >> ");
		String check_id = sc.next();
		System.out.print("PW Ȯ�� >> ");
		String check_pw = sc.next();

		if(id.equals(check_id) && pw.equals(check_pw)) {
			result = blogDAO.memberDelete(check_id, check_pw);
			BlogView.display(result>0?"ȸ��Ż�𼺰�":"ȸ��Ż�����");
			login = false;
		}
		else {
			System.out.println("id, pw �߸��Է���");
		}	
	}
	private void member_update() {
		BlogView.display(blogDAO.selectMemberByIdPw(id, pw));
		System.out.print("������ pw >> ");
		String new_pw = sc.next();
		pw = new_pw;
		System.out.print("������ Name >> ");
		String new_name = sc.next();
		result = blogDAO.memberUpdate(id, new_pw, new_name);
		BlogView.display(result>0?"ȸ��������������":"ȸ��������������");
		BlogView.display(blogDAO.selectMemberByIdPw(id, pw));
	}
	private void logout() {
		id = null;
		pw = null;
		login = false;
	}
	private void post_like_action() {
		result = blogDAO.postLikeClicked(id, no);
		BlogView.display(result>0?"���ƿ�Ŭ������":"���ƿ�Ŭ������");
	}
	private void post_read_one() {
		System.out.print("postNo >> ");
		no = sc.nextInt();
		BlogView.display(blogDAO.selectPostByNo(no));
		System.out.println(no + "�Խñ� ���");
		BlogView.display(blogDAO.selectCommentAll(no));
		BlogView.display(no + "�Խñ� ���ƿ� �� : " + blogDAO.postLikeCount(no));
	}
	private void comment_delete() {
		System.out.println("comment no >> ");
		int comment_no2 = sc.nextInt();
		result = blogDAO.commentDelete(comment_no2, id);
		BlogView.display(result>0?"��ۻ�������":"��ۻ�������");
	}
	private void comment_update() {
		System.out.println("comment no >> ");
		int comment_no = sc.nextInt();
		System.out.println("new text >> ");
		String new_text = sc.next();
		result = blogDAO.commentUpdate(comment_no, new_text);
		BlogView.display(result>0?"��ۼ�������":"��ۼ�������");
	}
	private void comment_create() {
		comment = new CommentVO();
		comment.setComment_post(no);
		comment.setComment_writer(id);
		System.out.print("text >> ");
		comment.setComment_text(sc.next());
		result = blogDAO.commentInsert(comment);
		BlogView.display(result>0?"����ۼ�����":"����ۼ�����");
	}
	private void post_update() {
		System.out.print("new title >> ");
		String new_title = sc.next();
		System.out.print("new contents >> ");
		String new_contents = sc.next();
		result = blogDAO.postUpdate(no, new_title, new_contents, id);
		BlogView.display(result>0?"�Խñۼ�������":"�Խñۼ�������");
	}
	private void create_post() {
		post = new PostVO();
		post.setPost_writer(id);
		System.out.print("title >> ");
		post.setPost_title(sc.next());
		System.out.print("contents >> ");
		post.setPost_contents(sc.next());
		result = blogDAO.postInsert(post);
		BlogView.display(result>0?"�Խñ��ۼ�����":"�Խñ��ۼ�����");
	}
	private void login() {
		System.out.print("ID >> ");
		id = sc.next();
		System.out.print("PW >> ");
		pw = sc.next();
		member = blogDAO.selectMemberByIdPw(id, pw);
		if(member != null) {
			login = true;
			System.out.println("�α��� ����");
		}
		else {
			System.out.println("�α��� ����");
			id = null;
			pw = null;
		}	
	}
	private void member_create() {
		member = new MemberVO();
		System.out.print("ID >> ");
		member.setMember_id(sc.next());
		System.out.print("PW >> ");
		member.setMember_pw(sc.next());
		System.out.print("Name >> ");
		member.setMember_name(sc.next());
		result = blogDAO.memberInsert(member);
		BlogView.display(result>0?"ȸ�����Լ���":"ȸ�����Խ���");
	}
}
