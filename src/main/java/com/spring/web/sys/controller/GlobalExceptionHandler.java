package com.spring.web.sys.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.util.NestedServletException;

import com.spring.config.Layout;
import com.spring.domain.sys.ErrorInfo;
import com.spring.domain.sys.MyException;

@ControllerAdvice
@Layout(Layout.NONE)
public class GlobalExceptionHandler {

	public static final String DEFAULT_ERROR_VIEW = "error";
	public static final String NOT_FOUND = "404";
	public static final String INTERNAL_SERVER_ERROR = "500";

	
	
	@ExceptionHandler(value = NoHandlerFoundException.class)
	public ModelAndView notFoundErrorHandler(HttpServletRequest req, Exception e) throws Exception {
		ModelAndView mav = new ModelAndView();
		mav.addObject("exception", e);
		mav.addObject("url", req.getRequestURL());
		mav.setViewName(NOT_FOUND);
		return mav;
	}
	
	@ExceptionHandler(value = NestedServletException.class)
	public ModelAndView servletExceptionHandler(HttpServletRequest req, Exception e) throws Exception {
		ModelAndView mav = new ModelAndView();
		mav.addObject("exception", e);
		mav.addObject("url", req.getRequestURL());
		mav.setViewName(INTERNAL_SERVER_ERROR);
		return mav;
	}
	
	
	@ExceptionHandler(value = MyException.class)
    @ResponseBody
    public ErrorInfo<String> jsonErrorHandler(HttpServletRequest req, MyException e) throws Exception {
        ErrorInfo<String> r = new ErrorInfo<>();
        r.setMessage(e.getMessage());
        r.setCode(ErrorInfo.ERROR);
        r.setData("Some Data");
        r.setUrl(req.getRequestURL().toString());
        return r;
    }
	
	@ExceptionHandler(value = Exception.class)
	public ModelAndView defaultErrorHandler(HttpServletRequest req, Exception e) throws Exception {
		ModelAndView mav = new ModelAndView();
		mav.addObject("exception", e);
		mav.addObject("url", req.getRequestURL());
		mav.setViewName(DEFAULT_ERROR_VIEW);
		return mav;
	}
}
