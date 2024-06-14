/**
 * Module com.octo.captcha
 */
module com.octo.captcha {
	
	requires java.base;
	requires jakarta.servlet;
	requires jakarta.servlet.jsp;
	requires org.apache.commons.logging;
	
	exports com.octo.captcha;
	exports com.octo.captcha.engine;
	exports com.octo.captcha.service;
	exports com.octo.captcha.service.captchastore;
	exports com.octo.captcha.module.servlet.image;
	
}