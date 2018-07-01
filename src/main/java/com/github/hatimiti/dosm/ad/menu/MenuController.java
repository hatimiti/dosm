package com.github.hatimiti.dosm.ad.menu;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import static com.github.hatimiti.dosm.base.DosmModelAndView.view;

/**
 * sample
 * @author hatimiti
 */
@Controller
@RequestMapping(MenuController.URI)
public class MenuController {

	public static final String URI = "/ad/menu";

	// 一覧

	@RequestMapping
	public ModelAndView index() {
		return view(URI, "/menu.html", null);
	}

}
