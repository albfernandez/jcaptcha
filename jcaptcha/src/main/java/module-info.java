module com.octo.captcha {
	
	requires java.base;
	requires transitive jakarta.servlet;
	requires jakarta.servlet.jsp;
	requires transitive org.apache.commons.logging;
	requires filters;
	
	exports com.octo.captcha;
	exports com.octo.captcha.engine;
	exports com.octo.captcha.service;
	exports com.octo.captcha.service.captchastore;
	exports com.octo.captcha.module.servlet.image;
	exports com.octo.captcha.helper;
	
}