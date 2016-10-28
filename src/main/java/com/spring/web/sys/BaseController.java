package com.spring.web.sys;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.spring.domain.BaseModel;
import com.spring.enums.ResponseCode;

/**
 * 
 */
public abstract class BaseController {
	protected static final Logger log = LoggerFactory.getLogger(BaseController.class);
	
	protected HttpServletRequest request;
	protected HttpServletResponse response;
	protected static final ObjectMapper jsonMapper = new ObjectMapper();
	protected static final ResponseCode SUCCESS = ResponseCode.SUCCESS;
	protected static final ResponseCode ERROR = ResponseCode.ERROR;
	protected static final String JSON_WITH_UTF8 = "application/json;charset=utf-8";
	protected static final String SESSION_CAPTCHA = "captcha_session";
	public static final String SESSION_USER = "user_session";
	protected static final String PAGE_SIZE = "10";
	protected static final String PAGE_NO = "1";

	protected String responseJson(Object obj) {
		return responseJson(SUCCESS, obj);
	}

	protected String responseJson(ResponseCode code) {
		return jsonMapper.createObjectNode().put("code", code.getCode()).put("message", code.getMsg()).toString();
	}

	protected String responseJson(ResponseCode code, Object obj) {
		if (obj == null) {
			return responseJson(ERROR);
		}

		if (obj instanceof ArrayNode) {
			return jsonMapper.createObjectNode().put("code", SUCCESS.getCode()).put("message", SUCCESS.getMsg())
					.set("data", (ArrayNode) obj).toString();
		}

		return jsonMapper.createObjectNode().put("code", SUCCESS.getCode()).put("message", SUCCESS.getMsg())
				.set("data", jsonMapper.createObjectNode().set("obj", jsonMapper.valueToTree(obj))).toString();
	}

	protected String responseJson(List<? extends BaseModel> dataList) {
		if (dataList == null || dataList.size() == 0) {
			return responseJson(ERROR);
		}

		return jsonMapper.createObjectNode().put("code", SUCCESS.getCode()).put("message", SUCCESS.getMsg())
				.set("data", jsonMapper.createObjectNode().set("list", jsonMapper.valueToTree(dataList))).toString();
	}

	protected String responseJson(List<? extends BaseModel> dataList, int count, int currentPage) {
		if (dataList == null || dataList.size() == 0) {
			return responseJson(ERROR);
		}

		return jsonMapper.createObjectNode().put("code", SUCCESS.getCode()).put("message", SUCCESS.getMsg())
				.set("data",
						// TODO
						jsonMapper.createObjectNode().put("total", count).put("currentPage", currentPage).set("list",
								jsonMapper.valueToTree(dataList)))
				.toString();
	}

	@ResponseBody
	@ExceptionHandler
	protected String exp(HttpServletRequest request, Exception ex) {
		request.setAttribute("ex", ex);
		return responseJson(ResponseCode.ERROR);
	}

	@ModelAttribute
	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}

	@ModelAttribute
	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	protected void putRequestContext(String key, Object value) {
		this.request.setAttribute(key, value);
	}

	protected void putSessionContext(String key, Object value) {
		this.request.getSession().setAttribute(key, value);
	}

	protected HttpSession getSession() {
		return request.getSession();
	}

}
