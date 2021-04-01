package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import util.DBUtil;

public class BlogDAO {
	//1. ȸ�� �� ��ȸ(id, pw�� ��ȸ)
	public MemberVO selectMemberByIdPw(String memberId, String pw) {
		MemberVO member = null;
		Connection conn = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		String sql = " select * from member where member_id = ? and member_pw = ? ";
		
		conn = DBUtil.getConnection();
		
		try {
			st = conn.prepareStatement(sql);
			st.setString(1, memberId);
			st.setString(2, pw);
			rs = st.executeQuery();
			while(rs.next()){
				member = makeMember(rs);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.dbClose(rs, st, conn);
		}
		
		return member;
	}
	
	//2. �Խñ� ��ü ��ȸ
	public void selectPostAll(){
		Connection conn = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		String sql = " select * from post ";
		conn = DBUtil.getConnection();
		
		try {
			st = conn.prepareStatement(sql);
			rs = st.executeQuery();
			while(rs.next()) {
				PostVO post = makePost(rs);
				System.out.println("no : " + post.getPost_no() +", title : " + post.getPost_title() + ", contents : " + post.getPost_contents());
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.dbClose(rs, st, conn);
		}
	}
	//3. �Խñ� �� ��ȸ
	public PostVO selectPostByNo(int postNo) {
		PostVO post = null;
		Connection conn = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		String sql = " select * from post where post_no = ? ";
		String sql2 = " update post set post_view_count = post_view_count+1 where post_no = ? ";
		
		conn = DBUtil.getConnection();
		
		try {
			st = conn.prepareStatement(sql);
			st.setInt(1, postNo);
			rs = st.executeQuery();
			while(rs.next()) {
				post = makePost(rs);
				st = conn.prepareStatement(sql2);
				st.setInt(1, postNo);
				int result = st.executeUpdate();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.dbClose(rs, st, conn);
		}
		
		return post;
	}
	//4. ��� ��ü ��ȸ(�ش� �Խñ�)
	public List<CommentVO> selectCommentAll(int no){
		List<CommentVO> clist = new ArrayList<CommentVO>();
		Connection conn = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		String sql = " select * from comments where comment_post = ? ";
		conn = DBUtil.getConnection();
		
		try {
			st = conn.prepareStatement(sql);
			st.setInt(1, no);
			rs = st.executeQuery();
			while(rs.next()) {
				CommentVO comment = makeComment(rs);
				clist.add(comment);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.dbClose(rs, st, conn);
		}
		
		return clist;
	}
	
	//5. ȸ�� �Է�(ȸ������)
	public int memberInsert(MemberVO member) {
		int result = 0;
		Connection conn = null;
		PreparedStatement st = null;
		String sql = " insert into member values (member_seq.nextval, ?, ?, ?) ";
		
		conn = DBUtil.getConnection();
		
		try {
			st = conn.prepareStatement(sql);
			st.setString(1, member.getMember_id());
			st.setString(2, member.getMember_pw());
			st.setString(3, member.getMember_name());
			result = st.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.dbClose(null, st, conn);
		}
		
		return result;
	}
	//6. �Խñ� �Է�
	public int postInsert(PostVO post) {
		int result = 0;
		Connection conn = null;
		PreparedStatement st = null;
		String sql = " insert into post values (post_seq.nextval, ?, ?, ?, sysdate, sysdate, 0) ";
		
		conn = DBUtil.getConnection();
		
		try {
			st = conn.prepareStatement(sql);
			st.setString(1, post.getPost_writer());
			st.setString(2, post.getPost_title());
			st.setString(3, post.getPost_contents());
			result = st.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.dbClose(null, st, conn);
		}
		
		return result;
	}
	//7. ��� �Է�
	public int commentInsert(CommentVO comment) {
		int result = 0;
		Connection conn = null;
		PreparedStatement st = null;
		String sql = " insert into comments values (comment_seq.nextval, ?, ?, ?) ";
		
		conn = DBUtil.getConnection();
		
		try {
			st = conn.prepareStatement(sql);
			st.setString(1, comment.getComment_writer());
			st.setInt(2, comment.getComment_post());
			st.setString(3, comment.getComment_text());
			result = st.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.dbClose(null, st, conn);
		}
		
		return result;
	}
	
	//8. ȸ�� ����(ȸ�� ���� ����, id�� ã�Ƽ� pw, name ����)
	public int memberUpdate(String id, String pw, String name) {
		int result = 0;
		Connection conn = null;
		PreparedStatement st = null;
		
		String sql =
				" update member "+
				" set member_pw = ?, member_name = ? "+
				" where member_id = ? ";
		conn = DBUtil.getConnection();
		
		try {
			st = conn.prepareStatement(sql);
			st.setString(1, pw);
			st.setString(2, name);
			st.setString(3, id);
			result = st.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.dbClose(null, st, conn);
		}
		
		return result;
	}
	//9. ȸ�� ����(ȸ�� ���� ����, id pwȮ�� �� ����)
	public int memberDelete(String id, String pw) {
		int result = 0;
		Connection conn = null;
		PreparedStatement st = null;
		String sql =
				" delete from member "+
				" where member_id = ? and member_pw = ? ";
		
		conn = DBUtil.getConnection();
		
		try {
			st = conn.prepareStatement(sql);
			st.setString(1, id);
			st.setString(2, pw);
			result = st.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.dbClose(null, st, conn);
		}
		
		return result;
	}
	//10. �Խñ� ����(no�� ã�Ƽ� title, contents, update_date ����)
	public int postUpdate(int no, String title, String contents, String id) {
		int result = 0;
		Connection conn = null;
		PreparedStatement st = null;
		
		String sql =
				" update post "+
				" set post_title = ?, post_contents = ?, post_update_date = sysdate "+
				" where post_no = ? and post_writer = ?";
		conn = DBUtil.getConnection();
		
		try {
			st = conn.prepareStatement(sql);
			st.setString(1, title);
			st.setString(2, contents);
			st.setInt(3, no);
			st.setString(4, id);
			result = st.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.dbClose(null, st, conn);
		}
		
		return result;
	}
	//11. �Խñ� ����(�α��ε� ���̵� �ۼ��ڶ�� ����)
	public int postDelete(String id, int no) {
		int result = 0;
		Connection conn = null;
		PreparedStatement st = null;
		String sql =
				" delete from post "+
				" where post_writer = ? and post_no = ?";
		
		conn = DBUtil.getConnection();
		
		try {
			st = conn.prepareStatement(sql);
			st.setString(1, id);
			st.setInt(2, no);
			result = st.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.dbClose(null, st, conn);
		}
		
		return result;
	}
	//12. ��� ����(no�� ã�Ƽ� text ����)
	public int commentUpdate(int no, String text) {
		int result = 0;
		Connection conn = null;
		PreparedStatement st = null;
		
		String sql =
				" update comments "+
				" set comment_text = ? "+
				" where comment_no = ? ";
		conn = DBUtil.getConnection();
		
		try {
			st = conn.prepareStatement(sql);
			st.setString(1, text);
			st.setInt(2, no);
			result = st.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.dbClose(null, st, conn);
		}
		
		return result;
	}
	//13. ��� ����(�α��ε� ���̵� �ۼ��ڶ�� ����)
	public int commentDelete(int no, String id) {
		int result = 0;
		Connection conn = null;
		PreparedStatement st = null;
		String sql =
				" delete from comments "+
				" where comment_writer = ? and comment_no = ?";
		
		conn = DBUtil.getConnection();
		
		try {
			st = conn.prepareStatement(sql);
			st.setString(1, id);
			st.setInt(2, no);
			result = st.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.dbClose(null, st, conn);
		}
		
		return result;
	}
	
	//14. ���ƿ� ������(������ ������ check ����, ������ ������ ����)
	public int postLikeClicked(String id, int no) {
		int result = 0;
		Connection conn = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		int cnt = 0;
		String check = null;
		String sql = " select * from post_like where post_like_post = ? and post_like_member = ? ";
		String sql2 = " insert into post_like values (post_like_seq.nextval, ?, ?, '1')";
		String sql3 = " update post_like set post_like_check = ? where post_like_post = ? and post_like_member = ?";
		
		conn = DBUtil.getConnection();
		
		try {
			st = conn.prepareStatement(sql);
			st.setInt(1, no);
			st.setString(2, id);
			rs = st.executeQuery();
			while(rs.next()){
				cnt++;
				check = rs.getString("post_like_check");
			}
			
			if(cnt == 0) {
				st = conn.prepareStatement(sql2);
				st.setInt(1, no);
				st.setString(2, id);
				result = st.executeUpdate();
			}
			else {
				if(check.equals("1")) check = "0";
				else check = "1";
				st = conn.prepareStatement(sql3);
				st.setString(1, check);
				st.setInt(2, no);
				st.setString(3, id);
				result = st.executeUpdate();
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.dbClose(rs, st, conn);
		}
		
		
		return result;
	}
	
	//15. ���ƿ� ���� ǥ��(�Խñ� ��ȣ�� ��ȸ)
	public int postLikeCount(int no) {
		int postLikeCnt = 0;
		Connection conn = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		String sql = " select count(*) from post_like where post_like_post = ? and POST_LIKE_CHECK = '1' ";
	
		conn = DBUtil.getConnection();
		
		try {
			st = conn.prepareStatement(sql);
			st.setInt(1, no);
			rs = st.executeQuery();
			while(rs.next()) {
				postLikeCnt = rs.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.dbClose(rs, st, conn);
		}
		
		return postLikeCnt;
	}
	
	private PostVO makePost(ResultSet rs) throws SQLException{
		PostVO post = new PostVO();
		post.setPost_no(rs.getInt("Post_no"));
		post.setPost_writer(rs.getString("Post_writer"));
		post.setPost_title(rs.getString("Post_title"));
		post.setPost_contents(rs.getString("Post_contents"));
		post.setPost_published_date(rs.getDate("Post_published_date"));
		post.setPost_update_date(rs.getDate("Post_update_date"));
		post.setPost_view_count(rs.getInt("Post_view_count"));

		return post;
	}
	
	private MemberVO makeMember(ResultSet rs) throws SQLException{
		MemberVO member = new MemberVO();
		member.setMember_no(rs.getInt("Member_no"));
		member.setMember_id(rs.getString("Member_id"));
		member.setMember_pw(rs.getString("Member_pw"));
		member.setMember_name(rs.getString("Member_name"));
		
		return member;
		
	}
	
	private CommentVO makeComment(ResultSet rs) throws SQLException{
		CommentVO comment = new CommentVO();
		comment.setComment_no(rs.getInt("Comment_no"));
		comment.setComment_writer(rs.getString("Comment_writer"));
		comment.setComment_post(rs.getInt("Comment_post"));
		comment.setComment_text(rs.getString("Comment_text"));

		return comment;
	}

}
