package model;

public class PostLikeVO {
	private int post_like_no;
	private int post_like_post;
	private int post_like_member;
	private String post_like_check;
	
	public PostLikeVO() {
		super();
	}
	public PostLikeVO(int post_like_no, int post_like_post, int post_like_member, String post_like_check) {
		super();
		this.post_like_no = post_like_no;
		this.post_like_post = post_like_post;
		this.post_like_member = post_like_member;
		this.post_like_check = post_like_check;
	}
	
	public int getPost_like_no() {
		return post_like_no;
	}
	public void setPost_like_no(int post_like_no) {
		this.post_like_no = post_like_no;
	}
	public int getPost_like_post() {
		return post_like_post;
	}
	public void setPost_like_post(int post_like_post) {
		this.post_like_post = post_like_post;
	}
	public int getPost_like_member() {
		return post_like_member;
	}
	public void setPost_like_member(int post_like_member) {
		this.post_like_member = post_like_member;
	}
	public String getPost_like_check() {
		return post_like_check;
	}
	public void setPost_like_check(String post_like_check) {
		this.post_like_check = post_like_check;
	}
	
	@Override
	public String toString() {
		return "PostLikeVO [post_like_no=" + post_like_no + ", post_like_post=" + post_like_post + ", post_like_member="
				+ post_like_member + ", post_like_check=" + post_like_check + "]";
	}
	
}
