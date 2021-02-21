
--스프링평가 (jblog)

create user jblog identified by jblog;
--jblog 계정생성 , 비밀번호 jblog
grant resource, connect to jblog;
--접속권한 부여--> 실행성공해야 부여됨

--users 테이블 생성 , id --> unigue (중복되지 않는 유일한 값) , primary key만 지정
create table users(
    userNo number,
    id varchar2(50) unique not null,
    userName varchar2(100) not null,
    password varchar2(50) not null,
    joinDate date not null,
    primary key(userNo)
);

--user 테이블 생성 확인
select userNo 회원식별번호,
       id 아이디,
       userName 회원이름,
       password 패스워드,
       joinDate 가입일
from users;

--users 시퀀스 생성
create sequence seq_users_no 
increment by 1 
start with 1
nocache;

--users insert
insert into users values(seq_users_no.nextval, 
                        'id',
                        'name',
                        '1234', 
                        sysdate);
                        
commit;                        
                        

--blog 설정 , pk , fk 모두 지정
create table blog(
    id varchar2(50),
    blogTitle varchar2(100) not null,
    logoFile varchar2(100) not null,
    primary key(id),
    constraint blog_fk foreign key(id)
    references users(id)
);

--blog 테이블 생성 확인
select id 식별번호,
       blogTitle 블로그제목,
       logoFile 블로그이미지경로
from blog;

--blog 생성 (회원가입시 생성)
insert into blog
values ('id','blogTitle','logoFile');

--blog (개인 블로그 정보 불러오기)
select id,
       blogTitle,
       logoFile
from blog
where id = '아이디';

--블로그 기본설정 update
update blog
set blogTitle = '블로그타이틀',
    logoFile = '로고파일'
where id = '아이디';

--category 설정 , pk , fk 모두 지정
create table category(
    cateNo number,
    id varchar2(10),
    cateName varchar2(200) not null,
    description varchar2(500),
    regDate date not null,
    primary key(cateNo),
    constraint category_fk foreign key(id)
    references blog(id)
);

--category 테이블 생성 확인
select cateNo 식별번호PK,
       id 회원번호FK,
       cateName 카테고리명,
       description 카테고리설명,
       regDate 등록일
from category;

--category 시퀀스 생성
create sequence seq_category_no 
increment by 1 
start with 1
nocache;

insert into category
values (seq_category_no.nextval ,'555','카테고리명3','카테고리설명3' ,sysdate);

select cateNo,
       id,
       cateName,
       description,
       to_char(regDate, 'YYYY-MM-DD HH:MI:SS') regDate
from category
where id = '555'
order by regDate desc;

commit;


--post 설정 , pk , fk 모두 지정
create table post(
    postNo number,
    cateNo number,
    postTitle varchar2(300) not null,
    postContent varchar2(4000),
    regDate date not null,
    primary key(postNo),
    constraint post_fk foreign key(cateNo)
    references category(cateNo)
);

--post 시퀀스 생성
create sequence seq_post_no 
increment by 1 
start with 1
nocache;

insert into post
values (1,1,'글제목','글내용' ,sysdate);

--post 테이블 생성 확인
select postNo 식별번호,
       cateNo 카테고리번호,
       postTitle 글제목,
       postContent 글내용,
       regDate 등록일
from post;

--개인 블로그 category 조회시 post갯수

--post 데이터 갯수 확인
select count(*) from post;

-- '555' 의 카테고리 테이터 확인
select cateNo,
       id,
       cateName,
       description,
       to_char(regDate, 'YYYY-MM-DD HH:MI:SS') regDate
from category
where id = '555'
order by regDate desc;

-- post 총 갯수확인
select count(*) 
from post , category
where category.cateNo = post.cateNo
and category.id = '555';

-- 기존 select문에서 postCount추가
select  c.cateNo,
        c.id,
		c.cateName,
        c.description,
		count(p.cateNo) postCount
from category c, post p
where c.cateNo = p.cateNo(+)
and c.id = '555'
group by c.cateNo, 
		  c.id,
          c.cateName,
		  c.description
order by c.cateNo desc;


--comments 설정 , pk , fk 모두 지정
create table comments(
    cmtNo number,
    postNo number,
    userNo number,
    cmtContent varchar2(1000) not null,
    regDate date not null,
    primary key(cmtNo),
    constraint comments1_fk foreign key(userNo)
    references users(userNo),
    constraint comments2_fk foreign key(postNo)
    references post(postNo)
);

--comments 테이블 생성 확인
select cmtNo 식별번호,
       postNo 글번호,
       userNo 회원번호,
       cmtContent 댓글내용,
       regDate 등록일
from comments;

--comments 시퀀스 생성
create sequence seq_comments_no 
increment by 1 
start with 1
nocache;










