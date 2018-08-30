CREATE TABLE menu
(
  menuid    INT AUTO_INCREMENT
    PRIMARY KEY,
  type      VARCHAR(50)  NOT NULL,
  name      VARCHAR(100) NOT NULL,
  `key`     VARCHAR(50)  NULL,
  url       VARCHAR(200) NULL,
  media_id  VARCHAR(100) NULL,
  appid     VARCHAR(100) NULL,
  pagepath  VARCHAR(200) NULL,
  firstmenu INT          NULL
)
  ENGINE = InnoDB;


--用户表
create table ngp_wx_user(
	user_id int auto_increment not null primary key,
	name varchar(20),
	nick_name varchar(50) default '云海',
	tel_num char(11),
	passwd varchar(50) not null,
	sex char(1) default '1',
	sign_date timestamp default CURRENT_TIMESTAMP,
	up_date timestamp,
	remark1 varchar(50),
	remark2 varchar(50)
);

insert into ngp_wx_user(name,tel_num,passwd) values("ty","18900000001","123321")
update ngp_wx_user set name="rty"


--微信网页授权access_token刷新表
create table ngp_wx_access_token(
	openid varchar(50) not null primary key,
	userid varchar(50),
	access_token varchar(200) not null,
	refresh_token varchar(200) not null,
	inser_date timestamp default CURRENT_TIMESTAMP,
	access_up_date timestamp default CURRENT_TIMESTAMP,
	scope varchar(50) not null,
	remark1 varchar(50),
	remark2 varchar(50)
);
