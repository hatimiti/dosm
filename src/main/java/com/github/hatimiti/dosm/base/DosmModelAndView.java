package com.github.hatimiti.dosm.base;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

public class DosmModelAndView extends ModelAndView {
    private DosmModelAndView(String view) {
        this(view, null);
    }

    private DosmModelAndView(String view, Form form) {
        super(view);
        addObject("form", form);
    }

    public static DosmModelAndView view(String path, Form form) {
        return view("", path, form);
    }

    public static DosmModelAndView view(String base, String path) {
        return new DosmModelAndView(base + path);
    }

    public static DosmModelAndView view(String base, String path, Form form) {
        return new DosmModelAndView(base + path, form);
    }

    public static DosmModelAndView redirect(String to, RedirectAttributes ra) {
        return new DosmModelAndView("redirect:" + to);
    }

    public static DosmModelAndView forward(String to) {
        return new DosmModelAndView("forward:" + to);
    }

    public static DosmModelAndView download(Form form) {
        return new DosmModelAndView(null, form);
    }
}
