package com.kanasinfo.ext.mvc.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * @author J.Mars
 */
public abstract class SupportController {

    public static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd";
    private static final String BUSINESS_EXCEPTION_KEY = "bsExpCode";

    protected final Logger logger;

    public SupportController() {
        logger = LoggerFactory.getLogger(this.getClass());
    }

    protected String dateFormat = DEFAULT_DATE_FORMAT;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(this.dateFormat);
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

    /**
     * 将错误信息统一打到页面，Key为"code"
     * @param object
     * @param model
     */
    protected void addBusinessExceptionAttribute(Object object, Model model) {
        model.addAttribute(BUSINESS_EXCEPTION_KEY, object);
    }

}
