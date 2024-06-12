open module com.octo.captcha {
	requires java.base;
	requires java.desktop;
	requires org.apache.commons.logging;
	requires jakarta.servlet;
	requires jakarta.servlet.jsp;
	requires org.junit.jupiter.api;
	exports com.octo.captcha;
	exports com.octo.captcha.component.word;
}