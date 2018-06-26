DROP TABLE IF EXISTS
	cm_kaisha
	CASCADE
;
CREATE TABLE cm_kaisha
(
	-- CM会社ID
	cm_kaisha_id identity NOT NULL,
	-- 会社名
	kaisha_mei varchar(50) NOT NULL,
	-- 会社名(英語)
	kaisha_mei_en varchar(100),


	-- 登録者ID
	reg_user_id varchar(10) NOT NULL,
	-- 登録日時
	reg_tm timestamp NOT NULL,
	-- 登録機能区分ID
	reg_func_cd varchar(9) NOT NULL,
	-- 更新者ID
	upd_user_id varchar(10) NOT NULL,
	-- 更新日時
	upd_tm timestamp NOT NULL,
	-- 更新機能区分
	upd_func_cd varchar(9) NOT NULL,
	-- バージョン番号
	version_no numeric(9,0) NOT NULL,

	PRIMARY KEY (cm_kaisha_id)
);

DROP TABLE IF EXISTS
	cm_kish_tesuryo
	CASCADE
;
CREATE TABLE cm_kish_tesuryo
(
	-- CM会社手数料ID

	cm_kish_tesuryo_id identity NOT NULL,
	-- CM会社ID
	cm_kaisha_id bigint NOT NULL,
	-- 適用期間(From)
	tekiyo_kikan_from_dt char(8) NOT NULL,
	-- 適用期間(To)
	tekiyo_kikan_to_dt char(8) NOT NULL,
	-- 手数料数
	tesuryo_su numeric(7, 1)  NOT NULL,
	-- 手数料区分(率、円)
	tesuryo_kb char(3) NOT NULL,


	-- 登録者ID
	reg_user_id varchar(10) NOT NULL,
	-- 登録日時
	reg_tm timestamp NOT NULL,
	-- 登録機能区分ID
	reg_func_cd varchar(9) NOT NULL,
	-- 更新者ID
	upd_user_id varchar(10) NOT NULL,
	-- 更新日時
	upd_tm timestamp NOT NULL,
	-- 更新機能区分
	upd_func_cd varchar(9) NOT NULL,
	-- バージョン番号
	version_no numeric(9,0) NOT NULL,

	PRIMARY KEY (cm_kish_tesuryo_id)
);

DROP TABLE IF EXISTS
	cm_kish_renrakusaki
	CASCADE
;
CREATE TABLE cm_kish_renrakusaki
(
	-- CM会社連絡先ID
	cm_kish_renrakusaki_id identity NOT NULL,
	-- CM会社ID
	cm_kaisha_id bigint NOT NULL,
	-- 電話番号1
	tel_no1 numeric(5, 0) ,
	-- 電話番号2
	tel_no2 numeric(5, 0) ,
	-- 電話番号3
	tel_no3 numeric(5, 0) ,
	-- メールアドレス
	mail_address varchar(253) ,
	-- 連絡先用途区分
	renrakusaki_yoto_kb char(3) NOT NULL ,


	-- 登録者ID
	reg_user_id varchar(10) NOT NULL,
	-- 登録日時
	reg_tm timestamp NOT NULL,
	-- 登録機能区分ID
	reg_func_cd varchar(9) NOT NULL,
	-- 更新者ID
	upd_user_id varchar(10) NOT NULL,
	-- 更新日時
	upd_tm timestamp NOT NULL,
	-- 更新機能区分
	upd_func_cd varchar(9) NOT NULL,
	-- バージョン番号
	version_no numeric(9, 0) NOT NULL,

	PRIMARY KEY (cm_kish_renrakusaki_id)
);

DROP TABLE IF EXISTS
	cm_tesuryo_kb
	CASCADE
;
CREATE TABLE cm_tesuryo_kb
(
	-- 区分値
	kb_val char(3) NOT NULL,
	-- 区分名
	kb_mei varchar(50) NOT NULL,
	-- 区分名(英語)
	kb_mei_en varchar(100),
	-- 備考
	kb_rem varchar(15) NOT NULL,

	-- 登録者ID
	reg_user_id varchar(10) NOT NULL,
	-- 登録日時
	reg_tm timestamp NOT NULL,
	-- 登録機能区分ID
	reg_func_cd varchar(9) NOT NULL,
	-- 更新者ID
	upd_user_id varchar(10) NOT NULL,
	-- 更新日時
	upd_tm timestamp NOT NULL,
	-- 更新機能区分
	upd_func_cd varchar(9) NOT NULL,
	-- バージョン番号
	version_no numeric(9, 0) NOT NULL,

	PRIMARY KEY (kb_val)
);

DROP TABLE IF EXISTS
	cm_renrakusaki_yoto_kb
	CASCADE
;
CREATE TABLE cm_renrakusaki_yoto_kb
(
	-- 区分値
	kb_val char(3) NOT NULL,
	-- 区分名
	kb_mei varchar(50) NOT NULL,
	-- 区分名(英語)
	kb_mei_en varchar(100),
	-- 備考
	kb_rem varchar(15) NOT NULL,


	-- 登録者ID
	reg_user_id varchar(10) NOT NULL,
	-- 登録日時
	reg_tm timestamp NOT NULL,
	-- 登録機能区分ID
	reg_func_cd varchar(9) NOT NULL,
	-- 更新者ID
	upd_user_id varchar(10) NOT NULL,
	-- 更新日時
	upd_tm timestamp NOT NULL,
	-- 更新機能区分
	upd_func_cd varchar(9) NOT NULL,
	-- バージョン番号
	version_no numeric(9, 0) NOT NULL,

	PRIMARY KEY (kb_val)
);

DROP TABLE IF EXISTS
	cm_shain
	CASCADE
;
CREATE TABLE cm_shain
(
	-- CM社員ID
	cm_shain_id identity NOT NULL,
	-- CM会社ID(FK)
	cm_kaisha_id bigint NOT NULL,
	-- 社員名(姓)
	shain_sei varchar(50) NOT NULL,
	-- 社員名(名)
	shain_mei varchar(50) NOT NULL,

	-- 社員名(姓)(英語)
	shain_sei_en varchar(100),
	-- 社員名(名)(英語)
	shain_mei_en varchar(100),

	-- ログインCD
	login_cd varchar(50) NOT NULL,
	-- パスワード
	password varchar(100) NOT NULL,

	-- 登録者ID
	reg_user_id varchar(10) NOT NULL,
	-- 登録日時
	reg_tm timestamp NOT NULL,
	-- 登録機能区分ID
	reg_func_cd varchar(9) NOT NULL,
	-- 更新者ID
	upd_user_id varchar(10) NOT NULL,
	-- 更新日時
	upd_tm timestamp NOT NULL,
	-- 更新機能区分
	upd_func_cd varchar(9) NOT NULL,
	-- バージョン番号
	version_no numeric(9,0) NOT NULL,

	PRIMARY KEY (cm_shain_id)
);

DROP TABLE IF EXISTS
	sm_shohin
	CASCADE
;
CREATE TABLE sm_shohin
(
	-- SM商品ID
	sm_shohin_id identity NOT NULL,
	-- 商品番号
	shohin_no varchar(20) NOT NULL,
	-- 商品名
	shohin_mei varchar(50) NOT NULL,
	-- 商品名(英語)
	shohin_mei_en varchar(100),

	-- 商品価格
	shohin_price numeric(9, 0) NOT NULL,

	-- 登録者ID
	reg_user_id varchar(10) NOT NULL,
	-- 登録日時
	reg_tm timestamp NOT NULL,
	-- 登録機能区分ID
	reg_func_cd varchar(9) NOT NULL,
	-- 更新者ID
	upd_user_id varchar(10) NOT NULL,
	-- 更新日時
	upd_tm timestamp NOT NULL,
	-- 更新機能区分
	upd_func_cd varchar(9) NOT NULL,
	-- バージョン番号
	version_no numeric(9,0) NOT NULL,

	PRIMARY KEY (sm_shohin_id)
);

DROP TABLE IF EXISTS
	st_shohin_konyu
	CASCADE
;
CREATE TABLE st_shohin_konyu
(
	-- ST商品購入ID
	st_shohin_konyu_id identity NOT NULL,
	-- 購入CM社員ID
	konyu_cm_shain_id numeric(12, 0) NOT NULL,
	-- 購入年月日
	konyu_ymd char(8),
	-- 購入時間
	konyu_tm char(4),
	-- 購入価格(税抜)
	konyu_price numeric(9, 0) NOT NULL,
	-- 購入税
	konyu_tax numeric(9, 0) NOT NULL,

	-- 登録者ID
	reg_user_id varchar(10) NOT NULL,
	-- 登録日時
	reg_tm timestamp NOT NULL,
	-- 登録機能区分ID
	reg_func_cd varchar(9) NOT NULL,
	-- 更新者ID
	upd_user_id varchar(10) NOT NULL,
	-- 更新日時
	upd_tm timestamp NOT NULL,
	-- 更新機能区分
	upd_func_cd varchar(9) NOT NULL,
	-- バージョン番号
	version_no numeric(9,0) NOT NULL,

	PRIMARY KEY (st_shohin_konyu_id)
);

DROP TABLE IF EXISTS
	st_shhn_kny_ms
	CASCADE
;
CREATE TABLE st_shhn_kny_ms
(
	-- ST商品購入明細ID
	st_shhn_kny_ms_id identity NOT NULL,
	-- ST商品購入ID
	st_shohin_konyu_id numeric(12, 0) NOT NULL,
	-- ST商品ID
	sm_shohin_id numeric(12, 0) NOT NULL,
	-- 購入数
	konyu_su numeric(4, 0) NOT NULL,
	-- 購入単価(税抜)
	konyu_unit_price numeric(9, 0) NOT NULL,
	-- 購入税
	konyu_tax numeric(9, 0) NOT NULL,

	-- 登録者ID
	reg_user_id varchar(10) NOT NULL,
	-- 登録日時
	reg_tm timestamp NOT NULL,
	-- 登録機能区分ID
	reg_func_cd varchar(9) NOT NULL,
	-- 更新者ID
	upd_user_id varchar(10) NOT NULL,
	-- 更新日時
	upd_tm timestamp NOT NULL,
	-- 更新機能区分
	upd_func_cd varchar(9) NOT NULL,
	-- バージョン番号
	version_no numeric(9,0) NOT NULL,

	PRIMARY KEY (st_shhn_kny_ms_id)
);

DROP TABLE IF EXISTS
	TCM_SAMPLE
	CASCADE
;
CREATE TABLE TCM_SAMPLE (
	TCM_SAMPLE_ID IDENTITY PRIMARY KEY,
	SAMPLE_NAME    VARCHAR(50),
	SAMPLE_NAME_EN VARCHAR(50)
)
;
--CREATE SEQUENCE SCM_SAMPLE_ID START WITH 1 INCREMENT BY 1 MAXVALUE 999999999999 NOCYCLE NOCACHE

/* Create Indexes */
CREATE UNIQUE INDEX UX01_CM_KAISHA ON cm_kaisha (kaisha_mei);
CREATE UNIQUE INDEX UX01_CM_KISH_TESURYO ON cm_kish_tesuryo (cm_kaisha_id, tekiyo_kikan_from_dt);
CREATE UNIQUE INDEX UX01_SM_SHOHIN ON sm_shohin (shohin_no);
CREATE UNIQUE INDEX UX01_ST_SHHN_KNY_MS ON st_shhn_kny_ms (st_shohin_konyu_id, sm_shohin_id);