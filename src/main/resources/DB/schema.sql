/*用户基本信息表*/
CREATE TABLE t_user(
	user_id bigint NOT NULL AUTO_INCREMENT COMMENT '用户id',
	name varchar(32)  COMMENT '用户昵称',
	head_image varchar(50)  COMMENT '用户头像路径',
	intro varchar(100)  COMMENT '简介',
	phone bigint NOT NULL COMMENT '手机号码',
	mail varchar(50)  COMMENT '邮箱',
	password varchar(20) NOT NULL COMMENT '密码',
	sex varchar(2)  COMMENT '性别',
	create_time timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '注册时间',
	state tinyint NOT NULL DEFAULT 1 COMMENT '状态 ， 1：有效  -1：无效  2:订购了短信服务',
	last_time timestamp DEFAULT CURRENT_TIMESTAMP COMMENT '最后登录时间',
	
	PRIMARY KEY (user_id),
	KEY idx_name(name),
	KEY idx_phone(phone),
	KEY idx_mail(mail),
	KEY idx_register_time(create_time),
	KEY idx_last_time(last_time)
)ENGINE = InnoDB AUTO_INCREMENT = 1000 COMMENT = '用户基本信息表';


	
	

/**
 * 用户详细信息 --这张表设置了多个索引方便查询，所以尽量减少对齐数据的增删改。
 */
CREATE TABLE t_user_detail(
	user_id bigint NOT NULL COMMENT '用户id',
	sms_create_time timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '短信服务开始时间',
	sms_end_time timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '短信服务结束时间',
	money decimal(5,2) NOT NULL DEFAULT 0 COMMENT '消费总金额',
	birthdate timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '出生日期',
	personality tinyint COMMENT '性格 1-5 内向到外向',
	height smallint COMMENT '身高 cm',
	weight tinyint COMMENT '体重 斤',
	color varchar(10) COMMENT '喜欢的颜色',
	/*constellation varchar(6) COMMENT '星座',
	property varchar(4) COMMENT '生肖',
	age tinyint COMMENT '年龄',*/
	province varchar(20) COMMENT '省',
	city varchar(20) COMMENT '市',
	district varchar(20) COMMENT '区',
	
	PRIMARY KEY(user_id),
	KEY idx_sms_create_time(sms_create_time),
	KEY idx_sms_end_time(sms_end_time),
	KEY idx_money(money),
	KEY idx_birthdate(birthdate),
	KEY idx_character(personality),
	KEY idx_height(height),
	KEY idx_weight(weight),
	KEY idx_color(color),
	/*KEY idx_constellation(constellation),
	KEY idx_property(property),
	KEY idx_age(age),*/
	KEY idx_province(province),
	KEY idx_city(city),
	KEY idx_district(district)
)ENGINE = InnoDB  COMMENT='用户详细信息表';



/**
 * 用户密码修改表
 
CREATE TABLE t_user_password(
	password_id bigint NOT NULL AUTO_INCREMENT COMMENT '密码修改表id',
	user_id bigint NOT NULL COMMENT '用户id',
	create_time timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
	password_new varchar(20) NOT NULL COMMENT '新密码',
	password_old varchar(20) NOT NULL COMMENT '旧密码',
	
	PRIMARY KEY(password_id),
	KEY idx_user_id(user_id)
)ENGINE = InnoDB AUTO_INCREMENT = 1000 COMMENT="用户密码修改表";*/


/**
 * 用户登录记录表
 */
CREATE TABLE t_user_login(
	login_id bigint NOT NULL AUTO_INCREMENT COMMENT '登录记录id',
	user_id bigint NOT NULL COMMENT '用户id',
	create_time timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '登录时间',
	ip varchar(64) NOT NULL COMMENT '登录ip',
	
	PRIMARY KEY(login_id),
	KEY idx_user_id(user_id),
	KEY idx_create_time(create_time)
)ENGINE = InnoDB AUTO_INCREMENT = 1000 COMMENT='用户登录记录表';

/**
 * 用户消费记录表
 */
CREATE TABLE t_user_consumption(
	consumption_id bigint NOT NULL AUTO_INCREMENT COMMENT '消费id',
	user_id bigint NOT NULL COMMENT '用户id',
	state tinyint NOT NULL COMMENT '状态， 0：其他 1：赞助 2：短信服务',
	create_time timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '消费时间',
	detail varchar(100) COMMENT '详情',
	
	PRIMARY KEY(consumption_id),
	KEY idx_user_id(user_id),
	KEY idx_state(state)
)ENGINE = InnoDB AUTO_INCREMENT = 1000 COMMENT='用户消费记录表';


/**
 * 用户消息接收记录表
 
CREATE TABLE t_user_message(
	message_id bigint NOT NULL AUTO_INCREMENT COMMENT '消息id',
	user_id bigint NOT NULL COMMENT '用户id',
	create_time timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '消息时间',
	content varchar(200) NOT NULL COMMENT '消息内容',
	state tinyint NOT NULL DEFAULT -1 COMMENT '消息状态， -1:未读  1：已读',
	
	PRIMARY KEY(message_id),
	KEY idx_user_id(user_id),
	KEY idx_state(state)
)ENGINE = InnoDB AUTO_INCREMENT = 1000 COMMENT="用户消息接收记录表";*/

/**
 * 好友申请消息记录表
 */
CREATE TABLE t_friend_apply(
	apply_id bigint NOT NULL AUTO_INCREMENT COMMENT '好友申请id',
	user_id_a bigint NOT NULL COMMENT '请求发起人id',
	user_id_b bigint NOT NULL COMMENT '请求接受人id',
	create_time timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '消息时间',
	state tinyint NOT NULL DEFAULT -1 COMMENT '消息状态， -1：未读  1：已读  2 ：接受  -2：拒绝',

	PRIMARY KEY(apply_id),
	KEY idx_user_id_a(user_id_a),
	KEY idx_user_id_b(user_id_b),
	KEY idx_state(state)
)ENGINE = InnoDB AUTO_INCREMENT = 1000 COMMENT='好友申请消息记录表';

/**
 * 好友关系表
 */
CREATE TABLE t_friend(
	friend_id bigint NOT NULL AUTO_INCREMENT COMMENT '好友关系id',
	user_id_a bigint NOT NULL COMMENT '用户id1',
	user_id_b bigint NOT NULL COMMENT '用户id2',
	state tinyint NOT NULL DEFAULT 1 COMMENT '状态  是否有消息未读  -1：未读   1：已读',
	
	PRIMARY KEY(friend_id),
	KEY idx_user_id_a(user_id_a),
	KEY idx_user_id_b(user_id_b)
)ENGINE = InnoDB AUTO_INCREMENT = 1000 COMMENT='好友关系表';

/**
 * 聊天记录表
 */
CREATE TABLE t_chat_record(
	chat_id bigint NOT NULL AUTO_INCREMENT COMMENT '聊天记录id',
	user_id_a bigint NOT NULL COMMENT '用户id1 消息发起人',
	user_id_b bigint NOT NULL COMMENT '用户id2 消息接受人',
	content varchar(100) NOT NULL COMMENT '消息内容',
	create_time timestamp NOT NULL COMMENT '发送时间',
	
	
	PRIMARY KEY(chat_id),	
	KEY idx_user_id_a(user_id_a),
	KEY idx_user_id_b(user_id_b)
)ENGINE = InnoDB  AUTO_INCREMENT = 1000 COMMENT='聊天记录表';

/**
 * 友情赞助记录表
 */
CREATE TABLE t_sponsor(
	sponsor_id bigint NOT NULL AUTO_INCREMENT COMMENT '赞助id',
	user_id bigint NOT NULL COMMENT '用户id',
	money decimal(5,2) NOT NULL COMMENT '赞助金额',
	content varchar(1000) COMMENT '赞助宣言',
	create_time timestamp DEFAULT CURRENT_TIMESTAMP COMMENT '赞助时间',
	
	PRIMARY KEY(sponsor_id),
	KEY idx_user_id(user_id)
)ENGINE = InnoDB AUTO_INCREMENT = 1000 COMMENT='友情赞助记录表';

/**
 * 网站消息推送记录
 */
CREATE TABLE t_message_record(
	message_id bigint NOT NULL AUTO_INCREMENT COMMENT '消息id',
	user_id bigint NOT NULL DEFAULT 0 COMMENT '用户id',
	create_time timestamp DEFAULT CURRENT_TIMESTAMP COMMENT '消息时间',
	content varchar(1000) NOT NULL COMMENT '推送内容',
	state tinyint DEFAULT -1 COMMENT '状态  -1：未读   1：已读',

	PRIMARY KEY(message_id),
	KEY idx_user_id(user_id),
	KEY idx_create_time(create_time),
	KEY idx_state(state)
)ENGINE = InnoDB AUTO_INCREMENT = 1000 COMMENT='网站消息推送记录';

/**
 * 邮箱发送记录表
 */
CREATE TABLE t_mail(
	mail_id bigint NOT NULL AUTO_INCREMENT COMMENT '邮箱发送id',
	user_id bigint NOT NULL COMMENT '用户id',
	mail varchar(50) NOT NULL COMMENT '邮箱',
	state tinyint NOT NULL DEFAULT 1 COMMENT '发送状态， 1：成功 ； 0：失败',
	content varchar(500) NOT NULL COMMENT '发送内容',
	create_time timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '发送时间',
	
	PRIMARY KEY(mail_id),
	KEY idx_user_id(user_id),
	KEY idx_state(state),
	KEY idx_mail(mail),
	KEY idx_create_time(create_time)
)ENGINE = InnoDB AUTO_INCREMENT = 1000 COMMENT='邮箱发送记录表';

/**
 * 弹幕发送记录表
 */
CREATE TABLE t_barrage(
	barrage_id bigint NOT NULL AUTO_INCREMENT COMMENT '弹幕id',
	user_id bigint NOT NULL DEFAULT 0 COMMENT '发送用户id,默认（未登录）0',
	create_time timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '发送时间',
	content varchar(200) NOT NULL COMMENT '发送内容',
	
	PRIMARY KEY(barrage_id),
	KEY idx_user_id(user_id),
	KEY idx_create_time(create_time)
)ENGINE = InnoDB AUTO_INCREMENT =1000 COMMENT='弹幕发送记录表';

/**
 * 字典表
 */
CREATE TABLE t_dict(
	dict_id bigint NOT NULL AUTO_INCREMENT COMMENT '字典id',
	dict_type varchar(20) NOT NULL COMMENT '字典类型',
	dict_key varchar(20) NOT NULL COMMENT '字典key',
	dict_value varchar(100) NOT NULL COMMENT '字典value',
	
	PRIMARY KEY(dict_id),
	KEY idx_dict_type(dict_type),
	KEY idx_dict_key(dict_key)
)ENGINE = InnoDB AUTO_INCREMENT =1000 COMMENT='字典表';

INSERT INTO  t_user(name,head_image,intro,phone,mail,password,sex) VALUES
	('zx','noLogin.jpg','别人笑我忒疯癫，我笑他人看不穿',17826824998,'97389745@qq.com','123456','男'),
	('zzx','noLogin.jpg','别人笑我忒疯癫，我笑他人看不穿',17826824997,'97389745@qq.com','123456','男'),
	('zzzzx','noLogin.jpg','别人笑我忒疯癫，我笑他人看不穿',17826824996,'97389745@qq.com','123456','男'),
	('aa','noLogin.jpg','别人笑我忒疯癫，我笑他人看不穿',17826824995,'97389745@qq.com','123456','男'),
	('bbb','noLogin.jpg','别人笑我忒疯癫，我笑他人看不穿',17826824994,'97389745@qq.com','123456','男'),
	('bbc','noLogin.jpg','别人笑我忒疯癫，我笑他人看不穿',17826824993,'97389745@qq.com','123456','男'),
	('bbdd','noLogin.jpg','别人笑我忒疯癫，我笑他人看不穿',17826824992,'97389745@qq.com','123456','男'),
	('ssss','noLogin.jpg','别人笑我忒疯癫，我笑他人看不穿',17826824991,'97389745@qq.com','123456','男'),
	('xxx','noLogin.jpg','别人笑我忒疯癫，我笑他人看不穿',17826824990,'97389745@qq.com','123456','男'),
	('xxxxxxxs','noLogin.jpg','别人笑我忒疯癫，我笑他人看不穿',17826824989,'97389745@qq.com','123456','男'),
	('wwewewee','noLogin.jpg','别人笑我忒疯癫，我笑他人看不穿',17826824987,'97389745@qq.com','123456','男'),
	('wtggfg','noLogin.jpg','别人笑我忒疯癫，我笑他人看不穿',17826824986,'97389745@qq.com','123456','男'),
	('jghjhj','noLogin.jpg','别人笑我忒疯癫，我笑他人看不穿',17826824985,'97389745@qq.com','123456','男'),
	('dgdret','noLogin.jpg','别人笑我忒疯癫，我笑他人看不穿',17826824984,'97389745@qq.com','123456','男'),
	('rrrtrt','noLogin.jpg','别人笑我忒疯癫，我笑他人看不穿',17826824983,'97389745@qq.com','123456','男'),
	('mnvchf','noLogin.jpg','别人笑我忒疯癫，我笑他人看不穿',17826824982,'97389745@qq.com','123456','男'),
	('weqqg','noLogin.jpg','别人笑我忒疯癫，我笑他人看不穿',17826824981,'97389745@qq.com','123456','男'),
	('fdgjyjtu','noLogin.jpg','别人笑我忒疯癫，我笑他人看不穿',17826824980,'97389745@qq.com','123456','男'),
	('uiyui','noLogin.jpg','别人笑我忒疯癫，我笑他人看不穿',17826824979,'97389745@qq.com','123456','男'),
	('yutyj','noLogin.jpg','别人笑我忒疯癫，我笑他人看不穿',17826824978,'97389745@qq.com','123456','男'),
	('hjnbvjdg','noLogin.jpg','别人笑我忒疯癫，我笑他人看不穿',17826824977,'97389745@qq.com','123456','男'),
	('hlil','noLogin.jpg','别人笑我忒疯癫，我笑他人看不穿',17826824976,'97389745@qq.com','123456','男'),
	('addcz','noLogin.jpg','别人笑我忒疯癫，我笑他人看不穿',17826824975,'97389745@qq.com','123456','男'),
	('gdrh','noLogin.jpg','别人笑我忒疯癫，我笑他人看不穿',17826824974,'97389745@qq.com','123456','男'),
	('nbncg','noLogin.jpg','别人笑我忒疯癫，我笑他人看不穿',17826824973,'97389745@qq.com','123456','男'),
	('xfgmhj','noLogin.jpg','别人笑我忒疯癫，我笑他人看不穿',17826824972,'97389745@qq.com','123456','男'),
	('fhfyk','noLogin.jpg','别人笑我忒疯癫，我笑他人看不穿',17826824971,'97389745@qq.com','123456','男'),
	('sfgsbx','noLogin.jpg','别人笑我忒疯癫，我笑他人看不穿',17826824970,'97389745@qq.com','123456','男'),
	('gzvzdvn','noLogin.jpg','别人笑我忒疯癫，我笑他人看不穿',17826824969,'97389745@qq.com','123456','男'),
	('bvncjfj','noLogin.jpg','别人笑我忒疯癫，我笑他人看不穿',17826824968,'97389745@qq.com','123456','男'),
	('sdgdgae','noLogin.jpg','别人笑我忒疯癫，我笑他人看不穿',17826824967,'97389745@qq.com','123456','男'),
	('afsfbhfyk','noLogin.jpg','别人笑我忒疯癫，我笑他人看不穿',17826824966,'97389745@qq.com','123456','男'),
	('popyi','noLogin.jpg','别人笑我忒疯癫，我笑他人看不穿',17826824965,'97389745@qq.com','123456','男'),
	('fnvcn','noLogin.jpg','别人笑我忒疯癫，我笑他人看不穿',17826824964,'97389745@qq.com','123456','男'),
	('sgse','noLogin.jpg','别人笑我忒疯癫，我笑他人看不穿',17826824963,'97389745@qq.com','123456','男'),
	('kgu','noLogin.jpg','别人笑我忒疯癫，我笑他人看不穿',17826824962,'97389745@qq.com','123456','男'),
	('fhjdu','noLogin.jpg','别人笑我忒疯癫，我笑他人看不穿',17826824961,'97389745@qq.com','123456','男'),
	('jhfjr','noLogin.jpg','别人笑我忒疯癫，我笑他人看不穿',17826824960,'97389745@qq.com','123456','男'),
	('qewefsfvbcb','noLogin.jpg','别人笑我忒疯癫，我笑他人看不穿',17826824959,'97389745@qq.com','123456','男'),
	('dbggsfgdth','noLogin.jpg','别人笑我忒疯癫，我笑他人看不穿',17826824958,'97389745@qq.com','123456','男'),
	('fgdghdg','noLogin.jpg','别人笑我忒疯癫，我笑他人看不穿',17826824957,'97389745@qq.com','123456','男'),
	('fxhbdg','noLogin.jpg','别人笑我忒疯癫，我笑他人看不穿',17826824956,'97389745@qq.com','123456','男'),
	('dgdgd','noLogin.jpg','别人笑我忒疯癫，我笑他人看不穿',17826824955,'97389745@qq.com','123456','男'),
	('bcbcb','noLogin.jpg','别人笑我忒疯癫，我笑他人看不穿',17826824954,'97389745@qq.com','123456','男'),
	('xxbxvjghj','noLogin.jpg','别人笑我忒疯癫，我笑他人看不穿',17826824953,'97389745@qq.com','123456','男'),
	('hfjfhj','noLogin.jpg','别人笑我忒疯癫，我笑他人看不穿',17826824952,'97389745@qq.com','123456','男'),
	('zxrytyj','noLogin.jpg','别人笑我忒疯癫，我笑他人看不穿',17826824951,'97389745@qq.com','123456','男'),
	('kghj','noLogin.jpg','别人笑我忒疯癫，我笑他人看不穿',17826824950,'97389745@qq.com','123456','男'),
	('sryuu','noLogin.jpg','别人笑我忒疯癫，我笑他人看不穿',17826824949,'97389745@qq.com','123456','男'),
	('oop','noLogin.jpg','别人笑我忒疯癫，我笑他人看不穿',17826824948,'97389745@qq.com','123456','男'),
	('dvbb','noLogin.jpg','别人笑我忒疯癫，我笑他人看不穿',17826824947,'97389745@qq.com','123456','男'),
	('nmgn','noLogin.jpg','别人笑我忒疯癫，我笑他人看不穿',17826824946,'97389745@qq.com','123456','男'),
	('mdghd','noLogin.jpg','别人笑我忒疯癫，我笑他人看不穿',17826824945,'97389745@qq.com','123456','男'),
	('zaear','noLogin.jpg','别人笑我忒疯癫，我笑他人看不穿',17826824944,'97389745@qq.com','123456','男'),
	('zdzvg','noLogin.jpg','别人笑我忒疯癫，我笑他人看不穿',17826824943,'97389745@qq.com','123456','男'),
	('wqr','noLogin.jpg','别人笑我忒疯癫，我笑他人看不穿',17826824942,'97389745@qq.com','123456','男'),
	('aaaads','noLogin.jpg','别人笑我忒疯癫，我笑他人看不穿',17826824941,'97389745@qq.com','123456','男'),
	('hhdfgdg','noLogin.jpg','别人笑我忒疯癫，我笑他人看不穿',17826824940,'97389745@qq.com','123456','男'),
	('dfsgcctu','noLogin.jpg','别人笑我忒疯癫，我笑他人看不穿',17826824939,'97389745@qq.com','123456','男'),
	('xvv','noLogin.jpg','别人笑我忒疯癫，我笑他人看不穿',17826824938,'97389745@qq.com','123456','男');
	
	INSERT INTO t_user_detail(user_id)
	VALUES
		(1000),(1001),(1002),(1003),(1004),(1005),(1006),(1007),(1008),(1009),(1010),
		(1011),(1012),(1013),(1014),(1015),(1016),(1017),(1018),(1019),(1020),(1021),
		(1022),(1023),(1024),(1025),(1026),(1027),(1028),(1029),(1030),(1031),(1032),
		(1033),(1034),(1035),(1036),(1037),(1038),(1039),(1040),(1041),(1042),(1043),
		(1044),(1045),(1046),(1047),(1048),(1049),(1050),(1051),(1052),(1053),(1054),
		(1055),(1056),(1057),(1058),(1059);

INSERT INTO `t_dict` VALUES ('1000', 'weight', '40', '40');
INSERT INTO `t_dict` VALUES ('1001', 'weight', '41', '41');
INSERT INTO `t_dict` VALUES ('1002', 'weight', '42', '42');
INSERT INTO `t_dict` VALUES ('1003', 'weight', '43', '43');
INSERT INTO `t_dict` VALUES ('1004', 'weight', '44', '44');
INSERT INTO `t_dict` VALUES ('1005', 'weight', '45', '45');
INSERT INTO `t_dict` VALUES ('1006', 'weight', '46', '46');
INSERT INTO `t_dict` VALUES ('1007', 'weight', '47', '47');
INSERT INTO `t_dict` VALUES ('1008', 'weight', '48', '48');
INSERT INTO `t_dict` VALUES ('1009', 'weight', '49', '49');
INSERT INTO `t_dict` VALUES ('1010', 'weight', '50', '50');
INSERT INTO `t_dict` VALUES ('1011', 'weight', '51', '51');
INSERT INTO `t_dict` VALUES ('1012', 'weight', '52', '52');
INSERT INTO `t_dict` VALUES ('1013', 'weight', '53', '53');
INSERT INTO `t_dict` VALUES ('1014', 'weight', '54', '54');
INSERT INTO `t_dict` VALUES ('1015', 'weight', '55', '55');
INSERT INTO `t_dict` VALUES ('1016', 'weight', '56', '56');
INSERT INTO `t_dict` VALUES ('1017', 'weight', '57', '57');
INSERT INTO `t_dict` VALUES ('1018', 'weight', '58', '58');
INSERT INTO `t_dict` VALUES ('1019', 'weight', '59', '59');
INSERT INTO `t_dict` VALUES ('1020', 'weight', '60', '60');
INSERT INTO `t_dict` VALUES ('1021', 'weight', '61', '61');
INSERT INTO `t_dict` VALUES ('1022', 'weight', '62', '62');
INSERT INTO `t_dict` VALUES ('1023', 'weight', '63', '63');
INSERT INTO `t_dict` VALUES ('1024', 'weight', '64', '64');
INSERT INTO `t_dict` VALUES ('1025', 'weight', '65', '65');
INSERT INTO `t_dict` VALUES ('1026', 'weight', '66', '66');
INSERT INTO `t_dict` VALUES ('1027', 'weight', '67', '67');
INSERT INTO `t_dict` VALUES ('1028', 'weight', '68', '68');
INSERT INTO `t_dict` VALUES ('1029', 'weight', '69', '69');
INSERT INTO `t_dict` VALUES ('1030', 'weight', '70', '70');
INSERT INTO `t_dict` VALUES ('1031', 'weight', '71', '71');
INSERT INTO `t_dict` VALUES ('1032', 'weight', '72', '72');
INSERT INTO `t_dict` VALUES ('1033', 'weight', '73', '73');
INSERT INTO `t_dict` VALUES ('1034', 'weight', '74', '74');
INSERT INTO `t_dict` VALUES ('1035', 'weight', '75', '75');
INSERT INTO `t_dict` VALUES ('1036', 'weight', '76', '76');
INSERT INTO `t_dict` VALUES ('1037', 'weight', '77', '77');
INSERT INTO `t_dict` VALUES ('1038', 'weight', '78', '78');
INSERT INTO `t_dict` VALUES ('1039', 'weight', '79', '79');
INSERT INTO `t_dict` VALUES ('1040', 'weight', '80', '80');
INSERT INTO `t_dict` VALUES ('1041', 'weight', '81', '81');
INSERT INTO `t_dict` VALUES ('1042', 'weight', '82', '82');
INSERT INTO `t_dict` VALUES ('1043', 'weight', '83', '83');
INSERT INTO `t_dict` VALUES ('1044', 'weight', '84', '84');
INSERT INTO `t_dict` VALUES ('1045', 'weight', '85', '85');
INSERT INTO `t_dict` VALUES ('1046', 'weight', '86', '86');
INSERT INTO `t_dict` VALUES ('1047', 'weight', '87', '87');
INSERT INTO `t_dict` VALUES ('1048', 'weight', '88', '88');
INSERT INTO `t_dict` VALUES ('1049', 'weight', '89', '89');
INSERT INTO `t_dict` VALUES ('1050', 'weight', '90', '90');
INSERT INTO `t_dict` VALUES ('1051', 'height', '140', '140');
INSERT INTO `t_dict` VALUES ('1052', 'height', '141', '141');
INSERT INTO `t_dict` VALUES ('1053', 'height', '142', '142');
INSERT INTO `t_dict` VALUES ('1054', 'height', '143', '143');
INSERT INTO `t_dict` VALUES ('1055', 'height', '144', '144');
INSERT INTO `t_dict` VALUES ('1056', 'height', '145', '145');
INSERT INTO `t_dict` VALUES ('1057', 'height', '146', '146');
INSERT INTO `t_dict` VALUES ('1058', 'height', '147', '147');
INSERT INTO `t_dict` VALUES ('1059', 'height', '148', '148');
INSERT INTO `t_dict` VALUES ('1060', 'height', '149', '149');
INSERT INTO `t_dict` VALUES ('1061', 'height', '150', '150');
INSERT INTO `t_dict` VALUES ('1062', 'height', '151', '151');
INSERT INTO `t_dict` VALUES ('1063', 'height', '152', '152');
INSERT INTO `t_dict` VALUES ('1064', 'height', '153', '153');
INSERT INTO `t_dict` VALUES ('1065', 'height', '154', '154');
INSERT INTO `t_dict` VALUES ('1066', 'height', '155', '155');
INSERT INTO `t_dict` VALUES ('1067', 'height', '156', '156');
INSERT INTO `t_dict` VALUES ('1068', 'height', '157', '157');
INSERT INTO `t_dict` VALUES ('1069', 'height', '158', '158');
INSERT INTO `t_dict` VALUES ('1070', 'height', '159', '159');
INSERT INTO `t_dict` VALUES ('1071', 'height', '160', '160');
INSERT INTO `t_dict` VALUES ('1072', 'height', '161', '161');
INSERT INTO `t_dict` VALUES ('1073', 'height', '162', '162');
INSERT INTO `t_dict` VALUES ('1074', 'height', '163', '163');
INSERT INTO `t_dict` VALUES ('1075', 'height', '164', '164');
INSERT INTO `t_dict` VALUES ('1076', 'height', '165', '165');
INSERT INTO `t_dict` VALUES ('1077', 'height', '166', '166');
INSERT INTO `t_dict` VALUES ('1078', 'height', '167', '167');
INSERT INTO `t_dict` VALUES ('1079', 'height', '168', '168');
INSERT INTO `t_dict` VALUES ('1080', 'height', '169', '169');
INSERT INTO `t_dict` VALUES ('1081', 'height', '170', '170');
INSERT INTO `t_dict` VALUES ('1082', 'height', '171', '171');
INSERT INTO `t_dict` VALUES ('1083', 'height', '172', '172');
INSERT INTO `t_dict` VALUES ('1084', 'height', '173', '173');
INSERT INTO `t_dict` VALUES ('1085', 'height', '174', '174');
INSERT INTO `t_dict` VALUES ('1086', 'height', '175', '175');
INSERT INTO `t_dict` VALUES ('1087', 'height', '176', '176');
INSERT INTO `t_dict` VALUES ('1088', 'height', '177', '177');
INSERT INTO `t_dict` VALUES ('1089', 'height', '178', '178');
INSERT INTO `t_dict` VALUES ('1090', 'height', '179', '179');
INSERT INTO `t_dict` VALUES ('1091', 'height', '180', '180');
INSERT INTO `t_dict` VALUES ('1092', 'height', '181', '181');
INSERT INTO `t_dict` VALUES ('1093', 'height', '182', '182');
INSERT INTO `t_dict` VALUES ('1094', 'height', '183', '183');
INSERT INTO `t_dict` VALUES ('1095', 'height', '184', '184');
INSERT INTO `t_dict` VALUES ('1096', 'height', '185', '185');
INSERT INTO `t_dict` VALUES ('1097', 'height', '186', '186');
INSERT INTO `t_dict` VALUES ('1098', 'height', '187', '187');
INSERT INTO `t_dict` VALUES ('1099', 'height', '188', '188');
INSERT INTO `t_dict` VALUES ('1100', 'height', '189', '189');
INSERT INTO `t_dict` VALUES ('1101', 'height', '190', '190');
INSERT INTO `t_dict` VALUES ('1102', 'color', '红色', '红色');
INSERT INTO `t_dict` VALUES ('1103', 'color', '蓝色', '蓝色');
INSERT INTO `t_dict` VALUES ('1104', 'color', '黄色', '黄色');
INSERT INTO `t_dict` VALUES ('1105', 'color', '绿色', '绿色');
INSERT INTO `t_dict` VALUES ('1106', 'color', '黑色', '黑色');
INSERT INTO `t_dict` VALUES ('1107', 'color', '白色', '白色');
INSERT INTO `t_dict` VALUES ('1108', 'color', '黄色', '黄色');