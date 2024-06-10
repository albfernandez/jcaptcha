open module com.octo.captcha {
	requires java.base;
	requires java.desktop;
	requires transitive org.apache.commons.logging;
	requires transitive jakarta.servlet;
	requires jakarta.servlet.jsp;
	requires filters;
	requires org.junit.jupiter.api;
	exports com.octo.captcha;
	exports com.octo.captcha.component.word;
}