package com.github.hatimiti.dosm.ad.master.cmshain;

import com.github.hatimiti.dosm.repository.CmShainRepository;
import com.github.hatimiti.dosm.repository.entity.CmShain;
import lombok.val;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Optional;


@Transactional
@Service
public class CmShainService {

	@Resource
    private final CmShainRepository cmShainRepository;

	public CmShainService(CmShainRepository cmShainRepository) {
		this.cmShainRepository = cmShainRepository;
	}

	/*
	 * 一覧検索
	 */

//	public void search(
//			final CmShainListForm form) {
//
//		form.shainList = this.cmShainBhv.selectPageForMaster(form);
//		form.setupPage(form.shainList);
//	}

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
//					this.cmShainBhv.update(line.copyTo(cmShain));
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

	public CmShain register(
			final CmShainForm form) {

		val shain = new CmShain();

		shain.copyFrom(form);
		shain.setPassword(form.getEncryptedPassword());

		this.cmShainRepository.save(shain);

		return shain;
	}

	/*
	 * 更新
	 */

//	public void prepareUpdate(final CmShainForm form) {
//		setCmShainWithRel(form);
//	}
//
//	public CmShain update(
//			final CmShainForm form) {
//
//		val shain = this.cmShainBhv.selectByPk4Update(form.cmShainId);
//
//		// Stores the current password temporary.
//		String nowPassword = shain.getPassword();
//		shain.copyFrom(form);
//
//		// If the new password is inputed, sets it encrypted.
//		shain.setPassword(form.getPassword().isEmpty()
//				? nowPassword
//				: form.getEncryptedPassword());
//
//		this.cmShainBhv.update(shain);
//
//		return shain;
//	}
//
//	/*
//	 * 削除
//	 */
//
//	public void confirmDelete(final CmShainForm form) {
//		setCmShainWithRel(form);
//	}
//
//	public CmShain delete(final CmShainForm form) {
//
//		// 行ロック
//		this.cmShainBhv.selectByPk4Update(form.cmShainId);
//
//		val shain = selectByPkWithRel(form.cmShainId);
//
//		this.cmShainBhv.delete(shain);
//		return shain;
//	}

	/*
	 * 共通
	 */

	protected void setCmShainWithRel(final CmShainForm form) {

		val cmShain = selectByPkWithRel(form.getCmShainId());
		form.copyFrom(cmShain);

	}

	protected CmShain selectByPkWithRel(final Long cmShainId) {
		var cmShain = Optional.ofNullable(this.cmShainRepository.selectByPkWithRel(cmShainId));
//		if (cmShain)) {
//			throw new AppMessagesException(
//					new AppMessage(ERROR, "valid.exists", prop("shain")));
//		}
        return cmShain.map(s -> {s.setPassword(""); return s;}).get();
	}

}
