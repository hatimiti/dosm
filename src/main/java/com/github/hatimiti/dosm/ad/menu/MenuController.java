package com.github.hatimiti.dosm.ad.menu;

import com.github.hatimiti.dosm.base.AccessUser;
import lombok.val;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

import static com.github.hatimiti.dosm.base.DosmModelAndView.view;

/**
 * sample
 * @author hatimiti
 */
@Controller
@RequestMapping(MenuController.URI)
public class MenuController {

	private static final Logger LOG = LoggerFactory.getLogger(MenuController.class);

	public static final String URI = "/ad/menu";

	@RequestMapping
	public ModelAndView index(final Principal principal) {
		val user = (AccessUser) ((Authentication) principal).getPrincipal();
		LOG.info(String.format("Logged in %s", user));

		return view(URI, "/menu.html", null);
	}

}
