package com.github.hatimiti.dosm.base;

import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Arrays;

public class DosmModelAndView extends ModelAndView {

    private DosmModelAndView(String view) {
        this(view, null, null);
    }

    private DosmModelAndView(String view, Form form, BindingResult bind) {
        super(view);
        addObject("form", form);
        addObject(BindingResult.MODEL_KEY_PREFIX + "form", bind);
    }

    public static DosmModelAndView view(String path, Form form) {
        return view("", path, form);
    }

    public static DosmModelAndView view(String base, String path) {
        return view(base, path, null);
    }

    public static DosmModelAndView view(String base, String path, Form form) {
        return view(base, path, form, null);
    }

    public static DosmModelAndView view(String base, String path, Form form, BindingResult bind) {
        return new DosmModelAndView(base + path, form, bind);
    }

    public static DosmModelAndView redirect(String to, RedirectAttributes ra) {
        return redirect(to, ra, new FlashAttribute[0]);
    }

    public static DosmModelAndView redirect(
            final String to,
            final RedirectAttributes ra,
            final FlashAttribute... flashAttrs) {

        Arrays.asList(flashAttrs)
                .forEach(a -> ra.addFlashAttribute(a.getName(), a.getValue()));
        return new DosmModelAndView("redirect:" + to);
    }

    public static DosmModelAndView forward(String to) {
        return new DosmModelAndView("forward:" + to);
    }

    public static DosmModelAndView download(Form form) {
        return new DosmModelAndView(null, form, null);
    }

}
