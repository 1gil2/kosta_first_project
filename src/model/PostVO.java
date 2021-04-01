package model;

import java.sql.Date;

public class PostVO {
	private int post_no;
	private String post_writer;
	private String post_title;
	private String post_contents;
	private Date post_published_date;
	private Date post_update_date;
	private int post_view_count;
	
	public PostVO() {
		super();
	}
	public PostVO(int post_no, String post_writer, String post_title, String post_contents, Date post_published_date,
			Date post_update_date, int post_view_count) {
		super();
		this.post_no = post_no;
		this.post_writer = post_writer;
		this.post_title = post_title;
		this.post_contents = post_contents;
		this.post_published_date = post_published_date;
		this.post_update_date = post_update_date;
		this.post_view_count = post_view_count;
	}
	
	public int getPost_no() {
		return post_no;
	}
	public void setPost_no(int post_no) {
		this.post_no = post_no;
	}
	public String getPost_writer() {
		return post_writer;
	}
	public void setPost_writer(String post_writer) {
		this.post_writer = post_writer;
	}
	public String getPost_title() {
		return post_title;
	}
	public void setPost_title(String post_title) {
		this.post_title = post_title;
	}
	public String getPost_contents() {
		return post_contents;
	}
	public void setPost_contents(String post_contents) {
		this.post_contents = post_contents;
	}
	public Date getPost_published_date() {
		return post_published_date;
	}
	public void setPost_published_date(Date post_published_date) {
		this.post_published_date = post_published_date;
	}
	public Date getPost_update_date() {
		return post_update_date;
	}
	public void setPost_update_date(Date post_update_date) {
		this.post_update_date = post_update_date;
	}
	public int getPost_view_count() {
		return post_view_count;
	}
	public void setPost_view_count(int post_view_count) {
		this.post_view_count = post_view_count;
	}
	
	@Override
	public String toString() {
		return "PostVO [post_no=" + post_no + ", post_writer=" + post_writer + ", post_title=" + post_title
				+ ", post_contents=" + post_contents + ", post_published_date=" + post_published_date
				+ ", post_update_date=" + post_update_date + ", post_view_count=" + post_view_count + "]";
	}
}
