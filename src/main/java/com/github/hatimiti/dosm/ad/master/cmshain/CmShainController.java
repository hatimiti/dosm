package com.github.hatimiti.dosm.ad.master.cmshain;

import com.github.hatimiti.dosm.ad.master.Mode;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import static com.github.hatimiti.dosm.base.DosmModelAndView.redirect;
import static com.github.hatimiti.dosm.base.DosmModelAndView.view;
import static org.springframework.beans.BeanUtils.copyProperties;

/**
 * sample
 * @author hatimiti
 */
@Controller
@SessionAttributes(types = CmShainForm.class, names = "form")
@RequestMapping(CmShainController.URI)
public class CmShainController {

	public static final String URI = "/ad/master/cmShain/";

	@Autowired
    private final CmShainService cmShainService;

	public CmShainController(final CmShainService cmShainService) {
		this.cmShainService = cmShainService;
	}

	// 一覧

//	@RequestMapping
//	public ModelAndView index(CmShainListForm form) {
//		copy(new CmShainListForm(), form);
//		form.compresses.off();
//		return view(URI, "index.html", form);
//	}

//	@DoValidation(v = { ValidateList.class }, to = URI + "index.html")
//	@RequestMapping("search")
//	public ModelAndView search(CmShainListForm form) {
//		this.cmShainService.search(form);
//		return view(URI, "index.html", form);
//	}

	// CSVダウンロード

//	@DoValidation(v = { ValidateDownload.class }, to = URI + "index.html")
//	@RequestMapping(path = "search", params = "download")
//	public ModelAndView downloadShainCsv(CmShainListForm form) throws Exception {
//		if (form.compresses.isOn()) {
//			Downloads.downloadZipCsv(
//					"shain.csv", form, UTF8, cmShainService::outputCsvBySearchCondition);
//		} else {
//			Downloads.downloadPlainCsv(
//					"shain.csv", form, UTF8, cmShainService::outputCsvBySearchCondition);
//		}
//		return download(form);
//	}

	// CSVアップロード

//	@DoValidation(v = { ValidateCsvUpload.class }, to = URI + "index.html")
//	@RequestMapping(params = "upload", method = POST)
//	public ModelAndView upload(CmShainListForm form) {
//		this.cmShainService.inputCsv(form);
//		addMessage(new AppMessage(INFO, "completes.upload"));
//		return view(URI, "index.html", form);
//	}

	// 登録

//	@Token(SET)
	@RequestMapping(params = "prepareRegister")
	public ModelAndView prepareRegister(final CmShainForm form) {
		init(form, Mode.Register);
		return view(URI, "edit.html", form);
	}

//	@DoValidation(v = { Validate4Register.class }, to = "backToPrepare", transition = FORWORD)
	@RequestMapping(params = "confirmRegister")
	public ModelAndView confirmRegister(
			final @Validated CmShainForm form,
			final BindingResult bind) {
		return bind.hasErrors()
				? backToPrepare(form)
				: view(URI, "confirm.html", form);
	}

//	@Token(CHECK)
//	@DoValidation(v = { Validate4Register.class }, to = "backToList", transition = FORWORD)
	@RequestMapping(params = "register")
	public ModelAndView register(final CmShainForm form, final RedirectAttributes ra) {
		val shain = this.cmShainService.register(form);
//		saveRegisterMessage(shain.getCmShainId());
		return redirect("complete", ra);
	}

	// 更新

//	@Token(SET)
//	@DoValidation(v = { ValidId.class }, to = "backToList", transition = FORWORD)
//	@RequestMapping(params = "prepareUpdate")
//	public ModelAndView prepareUpdate(CmShainForm form) {
//		init(form, Mode.Update);
//		this.cmShainService.prepareUpdate(form);
//
//		return view(URI, "edit.html", form);
//	}
//
////	@DoValidation(v = { ValidId.class, Validate4Update.class }, to = "backToPrepare", transition = FORWORD)
//	@RequestMapping(params = "confirmUpdate")
//	public ModelAndView confirmUpdate(CmShainForm form) {
//		return view(URI, "confirm.html", form);
//	}
//
////	@Token(CHECK)
////	@DoValidation(v = { ValidId.class, Validate4Update.class }, to = "backToList", transition = FORWORD)
//	@RequestMapping(params = "update")
//	public ModelAndView update(CmShainForm form, RedirectAttributes ra) {
//		CmShain shain = this.cmShainService.update(form);
//		saveUpdateMessage(shain.getCmShainId());
//		return redirect("complete", ra);
//	}
//
//	// 削除
//
////	@Token(SET)
////	@DoValidation(v = { ValidId.class }, to = "backToList", transition = FORWORD)
//	@RequestMapping(params = "confirmDelete")
//	public DoxModelAndView confirmDelete(CmShainForm form) {
//		init(form, Mode.Delete);
//		this.cmShainService.confirmDelete(form);
//		return view(URI, "confirm.html", form);
//	}
//
////	@Token(CHECK)
////	@DoValidation(v = { ValidId.class }, to = "backToList", transition = FORWORD)
//	@RequestMapping(params = "delete")
//	public ModelAndView delete(CmShainForm form, RedirectAttributes ra) {
//		CmShain shain = this.cmShainService.delete(form);
//		saveDeleteMessage(shain.getCmShainId());
//		return redirect("complete", ra);
//	}

	// 共通

	@RequestMapping("complete")
	public ModelAndView complete(final CmShainForm form) {
		return view(URI, "complete.html", form);
	}

	@RequestMapping(params = "backToList")
	public ModelAndView backToList(final CmShainForm form, RedirectAttributes ra) {
		return redirect("search", ra);
	}

	@RequestMapping(params = "backToPrepare")
	public ModelAndView backToPrepare(final CmShainForm form) {
		if (Mode.Delete == form.getMode()) {
			return backToList(form, null);
		}
		return view(URI, "edit.html", form);
	}

	private void init(final CmShainForm form, final Mode mode) {
		val tmpId = form.getCmShainId();
		copyProperties(new CmShainForm(), form);
		form.setCmShainId(tmpId);
		form.setMode(mode);
	}

}
