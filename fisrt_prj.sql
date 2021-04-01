select * from all_all_tables where owner = 'HR';

drop table member cascade constraints;
drop table post cascade constraints;
drop table comments cascade constraints;
drop table post_like cascade constraints;

drop sequence member_seq;
drop sequence post_seq;
drop sequence comment_seq;
drop sequence post_like_seq;

create table member(
    member_no number(3) constraint member_member_no_pk primary key,
    member_id varchar2(20) unique,
    member_pw varchar2(20) not null,
    member_name varchar2(20) not null
);

create table post(
    post_no number(3) constraint post_post_no_pk primary key,
    post_writer varchar2(20) constraint post_writer_fk references member(member_id) on delete cascade,
    post_title varchar2(40) not null,
    post_contents varchar2(4000),
    post_published_date date default sysdate,
    post_update_date date default sysdate,
    post_view_count number(3) default 0
);

create table comments(
    comment_no number(3) constraint comment_no_pk primary key,
    comment_writer varchar2(20) constraint comment_writer_fk references member(member_id) on delete cascade ,
    comment_post number(3) constraint comment_post_fk references post(post_no) on delete cascade,
    comment_text varchar2(100) not null
);

create table post_like(
    post_like_no number(3) constraint post_like_no_pk primary key,
    post_like_post number(3) constraint post_like_post_fk references post(post_no) on delete cascade,
    post_like_member varchar2(20) constraint post_like_member_fk references member(member_id) on delete cascade,
    post_like_check char(1) constraint post_like_check check(post_like_check = '0' or post_like_check = '1')
);

create sequence member_seq;
create sequence post_seq;
create sequence comment_seq;
create sequence post_like_seq;

insert into member values (member_seq.nextval, 'id1', 'pw1', 'name1');
insert into member values (member_seq.nextval, 'id2', 'pw2', 'name2');
insert into member values (member_seq.nextval, 'id3', 'pw3', 'name3');

insert into post values (post_seq.nextval, 'id1', 'title1', 'contents1', sysdate, sysdate, 0);
insert into post values (post_seq.nextval, 'id2', 'title2', 'contents2', sysdate, sysdate, 0);
insert into post values (post_seq.nextval, 'id3', 'title3', 'contents3', sysdate, sysdate, 0);
insert into post values (post_seq.nextval, 'id1', 'title4', 'contents4', sysdate, sysdate, 0);
insert into post values (post_seq.nextval, 'id2', 'title5', 'contents5', sysdate, sysdate, 0);
insert into post values (post_seq.nextval, 'id3', 'title6', 'contents6', sysdate, sysdate, 0);
insert into post values (post_seq.nextval, 'id1', 'title7', 'contents7', sysdate, sysdate, 0);

insert into comments values (comment_seq.nextval, 'id1', 1, 'comments1');
insert into comments values (comment_seq.nextval, 'id1', 2, 'comments2');
insert into comments values (comment_seq.nextval, 'id1', 3, 'comments3');
insert into comments values (comment_seq.nextval, 'id2', 1, 'comments4');
insert into comments values (comment_seq.nextval, 'id2', 2, 'comments5');

insert into post_like values (post_like_seq.nextval, 1, 'id1', '1');
insert into post_like values (post_like_seq.nextval, 1, 'id2', '1');
insert into post_like values (post_like_seq.nextval, 1, 'id3', '1');
insert into post_like values (post_like_seq.nextval, 2, 'id2', '1');
insert into post_like values (post_like_seq.nextval, 2, 'id3', '1');

commit;