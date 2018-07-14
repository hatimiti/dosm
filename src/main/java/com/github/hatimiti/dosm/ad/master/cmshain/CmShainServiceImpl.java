package com.github.hatimiti.dosm.ad.master.cmshain;

import com.github.hatimiti.dosm.base.form.Pager;
import com.github.hatimiti.dosm.repository.CmShainRepository;
import com.github.hatimiti.dosm.repository.entity.CmShain;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Transactional
@Service
public class CmShainServiceImpl implements CmShainService {

    private final CmShainRepository cmShainRepository;

    @Autowired
	public CmShainServiceImpl(final CmShainRepository cmShainRepository) {
		this.cmShainRepository = cmShainRepository;
	}

	/*
	 * 一覧検索
	 */

	public void search(
			final CmShainListForm form) {

		form.shainList = new Pager(this.cmShainRepository
				.selectPageForMaster(form), 5 , form);
	}

	/*
	 * ダウンロード用CSV作成
	 */

//	public void outputCsvBySearchCondition(
//			final CmShainListForm form,
//			final Writer out) {
//
//		try (CsvEntityWriter<CmShainCsv> writer
//				= new CsvEntityWriter<>(new CsvWriter(out), CmShainCsv.class, true)) {
//
//			writer.write(CmShainCsv.createHader());
//
//			this.cmShainBhv.selectCursorForMaster(form, shain -> {
//				CmShainCsv csv = new CmShainCsv();
//				csv.copyFrom(shain);
//				writer.write(csv);
//			});
//
//		} catch (IOException e) {
//			throw new DoxBusinessRuntimeException(e);
//		}
//	}


	/*
	 * CSVアップロード処理
	 */

//	public void inputCsv(
//			final CmShainListForm form) {
//
//		CsvConfig conf = new CsvConfig();
//		conf.setIgnoreEmptyLines(true);
//
//		try (DoxCsvEntityReader<CmShainCsv> csv
//				= new DoxCsvEntityReader<>(form.uploadedCsvFile, conf, CmShainCsv.class)) {
//
//			csv.stream().forEach(line -> {
//				CmShainId cmShainId = CmShainId.of(line.cmShainId);
//				if (cmShainId.isEmpty()) {
//					this.cmShainBhv.insert(line.copyTo(new CmShain()));
//				} else {
//					CmShain cmShain = this.cmShainBhv.selectByPK(cmShainId.getValL()).get();
//					this.cmShainBhv._update(line.copyTo(cmShain));
//				}
//			});
//
//		} catch (IOException e) {
//			throw new DoxBusinessRuntimeException(e);
//		}
//	}

	/*
	 * 登録
	 */

	@Override
	public CmShain register(
			final CmShainForm form) {

		val cmShain = new CmShain();

		cmShain.copyFrom(form);
		cmShain.setPassword(form.getEncryptedPassword());
		cmShain.setupCommonColumnOfInsert();

		this.cmShainRepository.insert(cmShain);

		return cmShain;
	}

	/*
	 * 更新
	 */

	public void prepareUpdate(final CmShainForm form) {
		setCmShainWithRel(form);
	}

	public CmShain update(
			final CmShainForm form) {

		val cmShain = this.cmShainRepository.selectByPk4Update(form.getCmShainId());

		// Stores the current password temporarily.
		val nowPassword = cmShain.getPassword();
		cmShain.copyFrom(form);

		// If the new password is inputed, sets it encrypted.
		cmShain.setPassword(form.getPassword().isEmpty()
				? nowPassword
				: form.getEncryptedPassword());

		cmShain.setupCommonColumnOfUpdate();
		this.cmShainRepository.update(cmShain);
		return cmShain;
	}

	/*
	 * 削除
	 */

	public void confirmDelete(final CmShainForm form) {
		setCmShainWithRel(form);
	}

	public CmShain delete(final CmShainForm form) {
		// 行ロック
		this.cmShainRepository.selectByPk4Update(form.getCmShainId());

		val cmShain = selectByPkWithRel(form.getCmShainId());

		this.cmShainRepository.delete(cmShain);
		return cmShain;
	}

	/*
	 * 共通
	 */

	protected void setCmShainWithRel(final CmShainForm form) {
		form.copyFrom(selectByPkWithRel(form.getCmShainId()));
	}

	protected CmShain selectByPkWithRel(final Long cmShainId) {
		val cmShain = Optional.ofNullable(this.cmShainRepository.selectByPkWithRel(cmShainId));
//		if (cmShain)) {
//			throw new AppMessagesException(
//					new AppMessage(ERROR, "valid.exists", prop("shain")));
//		}
        return cmShain.map(s -> {s.setPassword(""); return s;}).get();
	}

}
