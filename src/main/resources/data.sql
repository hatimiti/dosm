-- cm_kaisha
INSERT INTO cm_kaisha (kaisha_mei, kaisha_mei_en, reg_user_id, reg_tm, reg_func_cd, upd_user_id, upd_tm, upd_func_cd, version_no)
  VALUES ('Gabage株式会社', 'Gabase corp.,', 'hatimiti', now(), 'MAN', 'hatimiti', now(), 'MAN', 1);

INSERT INTO cm_kaisha (kaisha_mei, kaisha_mei_en, reg_user_id, reg_tm, reg_func_cd, upd_user_id, upd_tm, upd_func_cd, version_no)
  VALUES ('Dosmソリューション株式会社', 'Dosm corp.,', 'hatimiti', now(), 'MAN', 'hatimiti', now(), 'MAN', 1);

-- cm_shain
INSERT INTO cm_shain (cm_kaisha_id, shain_sei, shain_mei, shain_sei_en, shain_mei_en, login_cd, password, reg_user_id, reg_tm, reg_func_cd, upd_user_id, upd_tm, upd_func_cd, version_no)
  VALUES (2, 'サンプル', '太郎', NULL, NULL, 'sample', 'asdf1234', 'hatimiti', now(), 'MAN', 'hatimiti', now(), 'MAN', 1);
