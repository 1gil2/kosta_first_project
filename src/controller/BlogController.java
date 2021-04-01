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
				System.out.println("-----작업 선택(로그인O)-----");
				System.out.println("1. 로그아웃, 2. 회원 정보 수정, 3. 탈퇴, 4. 게시글 조회, 9. 종료");
				cmd = sc.nextInt();
				switch (cmd) {
				case 1:
					//로그아웃
					id = null;
					pw = null;
					login = false;
					break;
				case 2:
					//회원 정보 수정
					BlogView.display(blogDAO.selectMemberByIdPw(id, pw));
					System.out.print("수정할 pw >> ");
					String new_pw = sc.next();
					pw = new_pw;
					System.out.print("수정할 Name >> ");
					String new_name = sc.next();
					result = blogDAO.memberUpdate(id, new_pw, new_name);
					BlogView.display(result>0?"회원정보수정성공":"회원정보수정실패");
					BlogView.display(blogDAO.selectMemberByIdPw(id, pw));
					break;
				case 3:
					//탈퇴
					System.out.print("ID 확인 >> ");
					String check_id = sc.next();
					System.out.print("PW 확인 >> ");
					String check_pw = sc.next();
					
					if(id.equals(check_id) && pw.equals(check_pw)) {
						result = blogDAO.memberDelete(check_id, check_pw);
						BlogView.display(result>0?"회원탈퇴성공":"회원탈퇴실패");
						login = false;
					}
					else {
						System.out.println("id, pw 잘못입력함");
					}
					break;
				case 4:
					//게시글 전체 조회
					boolean flag2 = true;
					while(flag2) {
						blogDAO.selectPostAll();
						System.out.println();
						System.out.println("=====작업 선택(게시글 전체 조회중)=====");
						System.out.println("1. 게시글 상세 조회, 2. 게시글 작성, 9. 종료, 0. 이전으로");
						cmd = sc.nextInt();
						switch (cmd) {
						case 1:
							//게시글 상세 조회
							System.out.print("postNo >> ");
							no = sc.nextInt();
							BlogView.display(blogDAO.selectPostByNo(no));
							System.out.println(no + "게시글 댓글");
							BlogView.display(blogDAO.selectCommentAll(no));
							BlogView.display(no + "게시글 좋아요 수 : " + blogDAO.postLikeCount(no));
							
							boolean flag3 = true;
							while(flag3) {
								System.out.println();
								System.out.println("*****작업 선택(게시글 상세 조회중)*****");
								System.out.println("1. 게시글 수정, 2. 댓글 작성, 3. 댓글 수정, 4. 댓글 삭제, 5. 좋아요 버튼 클릭, 0. 작업 취소");
								cmd = sc.nextInt();
								switch (cmd) {
								case 1:
									//게시글 수정
									System.out.print("new title >> ");
									String new_title = sc.next();
									System.out.print("new contents >> ");
									String new_contents = sc.next();
									result = blogDAO.postUpdate(no, new_title, new_contents, id);
									BlogView.display(result>0?"게시글수정성공":"게시글수정실패");
									break;
								case 11:
									result = blogDAO.postDelete(id, no);
									BlogView.display(result>0?"게시글삭제성공":"게시글삭제실패");
									break;
								case 2:
									//댓글 작성
									comment = new CommentVO();
									comment.setComment_post(no);
									comment.setComment_writer(id);
									System.out.print("text >> ");
									comment.setComment_text(sc.next());
									result = blogDAO.commentInsert(comment);
									BlogView.display(result>0?"댓글작성성공":"댓글작성실패");
									break;
								case 3:
									//댓글 수정
									System.out.println("comment no >> ");
									int comment_no = sc.nextInt();
									System.out.println("new text >> ");
									String new_text = sc.next();
									result = blogDAO.commentUpdate(comment_no, new_text);
									BlogView.display(result>0?"댓글수정성공":"댓글수정실패");
									break;
								case 4:
									//댓글 삭제
									System.out.println("comment no >> ");
									int comment_no2 = sc.nextInt();
									result = blogDAO.commentDelete(comment_no2, id);
									BlogView.display(result>0?"댓글삭제성공":"댓글삭제실패");
									break;
								case 5:
									//좋아요 누르기
									result = blogDAO.postLikeClicked(id, no);
									BlogView.display(result>0?"좋아요클릭성공":"좋아요클릭실패");
								case 0:
									flag3 = false;
									break;
								}
							}
							break;
						case 2:
							//게시글 작성
							post = new PostVO();
							post.setPost_writer(id);
							System.out.print("title >> ");
							post.setPost_title(sc.next());
							System.out.print("contents >> ");
							post.setPost_contents(sc.next());
							result = blogDAO.postInsert(post);
							BlogView.display(result>0?"게시글작성성공":"게시글작성실패");
							break;
						case 9:
							System.out.println("프로그램 종료");
							System.exit(0);
							break;
						case 0:
							flag2 = false;
							break;
						}
					}
					break;
				case 9:
					System.out.println("프로그램 종료");
					System.exit(0);
				}
			}
			else {
				System.out.println("-----작업 선택(로그인X)-----");
				System.out.println("1. 회원가입, 2. 로그인, 3. 게시글 조회, 9. 종료");
				cmd = sc.nextInt();
				switch (cmd) {
				case 1:
					//회원가입
					member = new MemberVO();
					System.out.print("ID >> ");
					member.setMember_id(sc.next());
					System.out.print("PW >> ");
					member.setMember_pw(sc.next());
					System.out.print("Name >> ");
					member.setMember_name(sc.next());
					result = blogDAO.memberInsert(member);
					BlogView.display(result>0?"회원가입성공":"회원가입실패");
					break;
				case 2:
					//로그인
					System.out.print("ID >> ");
					id = sc.next();
					System.out.print("PW >> ");
					pw = sc.next();
					member = blogDAO.selectMemberByIdPw(id, pw);
					if(member != null) {
						login = true;
						System.out.println("로그인 성공");
					}
					else {
						System.out.println("로그인 실패");
						id = null;
						pw = null;
					}
					break;
				case 3:
					//게시글 전체 조회
					boolean flag2 = true;
					while(flag2) {
						blogDAO.selectPostAll();
						System.out.println();
						System.out.println("=====작업 선택(게시글 전체 조회중)=====");
						System.out.println("1. 게시글 상세 조회, 9. 종료, 0. 이전으로");
						cmd = sc.nextInt();
						switch (cmd) {
						case 1:
							//게시글 상세 조회
							System.out.print("postNo >> ");
							no = sc.nextInt();
							BlogView.display(blogDAO.selectPostByNo(no));
							System.out.println(no + "게시글 댓글");
							BlogView.display(blogDAO.selectCommentAll(no));
							BlogView.display(no + "게시글 좋아요 수 : " + blogDAO.postLikeCount(no));
							break;
						case 9:
							System.out.println("프로그램 종료");
							System.exit(0);
							break;
						case 0:
							flag2 = false;
							break;
						}
					}
					break;
				case 9:
					System.out.println("프로그램 종료");
					System.exit(0);
				}
			}
		}
		
	}
}
