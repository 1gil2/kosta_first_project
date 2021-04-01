package model;

public class CommentVO {
	private int comment_no;
	private String comment_writer;
	private int comment_post;
	private String comment_text;
	
	public CommentVO() {
		super();
	}
	public CommentVO(int comment_no, String comment_writer, int comment_post, String comment_text) {
		super();
		this.comment_no = comment_no;
		this.comment_writer = comment_writer;
		this.comment_post = comment_post;
		this.comment_text = comment_text;
	}
	
	public int getComment_no() {
		return comment_no;
	}
	public void setComment_no(int comment_no) {
		this.comment_no = comment_no;
	}
	public String getComment_writer() {
		return comment_writer;
	}
	public void setComment_writer(String comment_writer) {
		this.comment_writer = comment_writer;
	}
	public int getComment_post() {
		return comment_post;
	}
	public void setComment_post(int comment_post) {
		this.comment_post = comment_post;
	}
	public String getComment_text() {
		return comment_text;
	}
	public void setComment_text(String comment_text) {
		this.comment_text = comment_text;
	}
	
	@Override
	public String toString() {
		return "CommentVO [comment_no=" + comment_no + ", comment_writer=" + comment_writer + ", comment_post="
				+ comment_post + ", comment_text=" + comment_text + "]";
	}
}
