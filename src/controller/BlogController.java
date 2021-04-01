package controller;

import java.util.List;
import java.util.Scanner;

import model.BlogDAO;
import model.CommentVO;
import model.MemberVO;
import model.PostVO;
import view.BlogView;

public class BlogController {

	public static void main(String[] args) {
		
		main_method();
	
	}

	private static void main_method() {
		
		Scanner sc = new Scanner(System.in);
		MemberVO member = null;
		PostVO post = null;
		CommentVO comment = null;
		List<CommentVO> clist = null;
		BlogDAO blogDAO = new BlogDAO();
		boolean login = false;
		String id = null;
		String pw = null;
		int result = 0;
		int cmd = 0;
		int no = 0;

		while(true) {
			if(login) {
				System.out.println("-----�۾� ����(�α���O)-----");
				System.out.println("1. �α׾ƿ�, 2. ȸ�� ���� ����, 3. Ż��, 4. �Խñ� ��ȸ, 9. ����");
				cmd = sc.nextInt();
				switch (cmd) {
				case 1:
					//�α׾ƿ�
					id = null;
					pw = null;
					login = false;
					break;
				case 2:
					//ȸ�� ���� ����
					BlogView.display(blogDAO.selectMemberByIdPw(id, pw));
					System.out.print("������ pw >> ");
					String new_pw = sc.next();
					pw = new_pw;
					System.out.print("������ Name >> ");
					String new_name = sc.next();
					result = blogDAO.memberUpdate(id, new_pw, new_name);
					BlogView.display(result>0?"ȸ��������������":"ȸ��������������");
					BlogView.display(blogDAO.selectMemberByIdPw(id, pw));
					break;
				case 3:
					//Ż��
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
					break;
				case 4:
					//�Խñ� ��ü ��ȸ
					boolean flag2 = true;
					while(flag2) {
						blogDAO.selectPostAll();
						System.out.println();
						System.out.println("=====�۾� ����(�Խñ� ��ü ��ȸ��)=====");
						System.out.println("1. �Խñ� �� ��ȸ, 2. �Խñ� �ۼ�, 9. ����, 0. ��������");
						cmd = sc.nextInt();
						switch (cmd) {
						case 1:
							//�Խñ� �� ��ȸ
							System.out.print("postNo >> ");
							no = sc.nextInt();
							BlogView.display(blogDAO.selectPostByNo(no));
							System.out.println(no + "�Խñ� ���");
							BlogView.display(blogDAO.selectCommentAll(no));
							BlogView.display(no + "�Խñ� ���ƿ� �� : " + blogDAO.postLikeCount(no));
							
							boolean flag3 = true;
							while(flag3) {
								System.out.println();
								System.out.println("*****�۾� ����(�Խñ� �� ��ȸ��)*****");
								System.out.println("1. �Խñ� ����, 2. ��� �ۼ�, 3. ��� ����, 4. ��� ����, 5. ���ƿ� ��ư Ŭ��, 0. �۾� ���");
								cmd = sc.nextInt();
								switch (cmd) {
								case 1:
									//�Խñ� ����
									System.out.print("new title >> ");
									String new_title = sc.next();
									System.out.print("new contents >> ");
									String new_contents = sc.next();
									result = blogDAO.postUpdate(no, new_title, new_contents, id);
									BlogView.display(result>0?"�Խñۼ�������":"�Խñۼ�������");
									break;
								case 11:
									result = blogDAO.postDelete(id, no);
									BlogView.display(result>0?"�Խñۻ�������":"�Խñۻ�������");
									break;
								case 2:
									//��� �ۼ�
									comment = new CommentVO();
									comment.setComment_post(no);
									comment.setComment_writer(id);
									System.out.print("text >> ");
									comment.setComment_text(sc.next());
									result = blogDAO.commentInsert(comment);
									BlogView.display(result>0?"����ۼ�����":"����ۼ�����");
									break;
								case 3:
									//��� ����
									System.out.println("comment no >> ");
									int comment_no = sc.nextInt();
									System.out.println("new text >> ");
									String new_text = sc.next();
									result = blogDAO.commentUpdate(comment_no, new_text);
									BlogView.display(result>0?"��ۼ�������":"��ۼ�������");
									break;
								case 4:
									//��� ����
									System.out.println("comment no >> ");
									int comment_no2 = sc.nextInt();
									result = blogDAO.commentDelete(comment_no2, id);
									BlogView.display(result>0?"��ۻ�������":"��ۻ�������");
									break;
								case 5:
									//���ƿ� ������
									result = blogDAO.postLikeClicked(id, no);
									BlogView.display(result>0?"���ƿ�Ŭ������":"���ƿ�Ŭ������");
								case 0:
									flag3 = false;
									break;
								}
							}
							break;
						case 2:
							//�Խñ� �ۼ�
							post = new PostVO();
							post.setPost_writer(id);
							System.out.print("title >> ");
							post.setPost_title(sc.next());
							System.out.print("contents >> ");
							post.setPost_contents(sc.next());
							result = blogDAO.postInsert(post);
							BlogView.display(result>0?"�Խñ��ۼ�����":"�Խñ��ۼ�����");
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
					break;
				case 9:
					System.out.println("���α׷� ����");
					System.exit(0);
				}
			}
			else {
				System.out.println("-----�۾� ����(�α���X)-----");
				System.out.println("1. ȸ������, 2. �α���, 3. �Խñ� ��ȸ, 9. ����");
				cmd = sc.nextInt();
				switch (cmd) {
				case 1:
					//ȸ������
					member = new MemberVO();
					System.out.print("ID >> ");
					member.setMember_id(sc.next());
					System.out.print("PW >> ");
					member.setMember_pw(sc.next());
					System.out.print("Name >> ");
					member.setMember_name(sc.next());
					result = blogDAO.memberInsert(member);
					BlogView.display(result>0?"ȸ�����Լ���":"ȸ�����Խ���");
					break;
				case 2:
					//�α���
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
					break;
				case 3:
					//�Խñ� ��ü ��ȸ
					boolean flag2 = true;
					while(flag2) {
						blogDAO.selectPostAll();
						System.out.println();
						System.out.println("=====�۾� ����(�Խñ� ��ü ��ȸ��)=====");
						System.out.println("1. �Խñ� �� ��ȸ, 9. ����, 0. ��������");
						cmd = sc.nextInt();
						switch (cmd) {
						case 1:
							//�Խñ� �� ��ȸ
							System.out.print("postNo >> ");
							no = sc.nextInt();
							BlogView.display(blogDAO.selectPostByNo(no));
							System.out.println(no + "�Խñ� ���");
							BlogView.display(blogDAO.selectCommentAll(no));
							BlogView.display(no + "�Խñ� ���ƿ� �� : " + blogDAO.postLikeCount(no));
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
					break;
				case 9:
					System.out.println("���α׷� ����");
					System.exit(0);
				}
			}
		}
		
	}
}
