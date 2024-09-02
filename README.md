# JCaptcha

JCaptcha stands for Java Completely Automated Public Test to tell Computers and Humans Apart 

# License

JCaptcha is LGPL [https://www.gnu.org/licenses/lgpl-3.0.txt](https://www.gnu.org/licenses/lgpl-3.0.txt)

Package com.octo.jhlabs contains a small subset of [https://gitlab.com/axet/jhlabs](https://gitlab.com/axet/jhlabs) and has Apache License [http://www.apache.org/licenses/LICENSE-2.0](http://www.apache.org/licenses/LICENSE-2.0)

# Requeriments

JCaptcha 3.0 requires:
 * Java 11.
 * Jakarta EE9 (if you use servlet related classes)

# Usage (Simple Servlet Integration)

This integration module provides the shortest way to integrate JCaptcha to your web application.
All you need to do is add the jcaptcha jar to your project, make a reference to the SimpleImageCaptchaServlet in your web.xml and then use the servlet session id to validate what the user submits on your form against the captcha service.

Files to add (version is ommited)
- jcaptcha.jar

And if you don't have it already:
- lib/commons-logging.jar


Put the reference in your web.xml (checking that the url-pattern path matches up with what you put in your html fragment above):

```xml
<servlet>
  <servlet-name>jcaptcha</servlet-name>
  <servlet-class>com.octo.captcha.module.servlet.image.SimpleImageCaptchaServlet</servlet-class>
</servlet>
<servlet-mapping>
  <servlet-name>jcaptcha</servlet-name>
  <url-pattern>/jcaptcha.jpg</url-pattern>
</servlet-mapping>
```

Add the folowing image tag to the form you want to protect, this call will asks the SimpleImageCaptchaServlet to generate a fresh new captcha

```html

<form action="submit.action">
    <img src="jcaptcha.jpg" /> 
    <input type="text" name="jcaptcha" value="" />
    <input type="submit" />
</form>

```

In your code that manages the submit action add the following code fragment to validate the user input

```java

String userCaptchaResponse = request.getParameter("jcaptcha");
boolean captchaPassed = SimpleImageCaptchaServlet.validateResponse(request, userCaptchaResponse);
if (captchaPassed) {
    // proceed to submit action
} else {
    // return error to user
}
```

And that's it!



# Building from sources

Clone the repository or download de tar file from releases page on github, then run the Maven command:

    git clone https://github.com/albfernandez/jcaptcha.git
    git checkout tags/v3.0.0
    cd jcaptcha
    mvn clean package 
    mvn assembly:single -Dmaven.test.skip=true


The result file is ``target/jcaptcha-3.0.0-dist.zip``

# Running sample

Clone the repository or download de tar file from releases page on github, then run the Maven command:

    git clone https://github.com/albfernandez/jcaptcha.git
    git checkout tags/v3.0.0
    cd jcaptcha
    cd samples/simple-servlet-image-sample/    
    mvn clean package


Ensure your 8080 localhost port is not in use

    mvn jetty:run


You can browse to http://localhost:8080/


# Changes from 2.0 to 3.0

* Upgraded Java requeriment to 11.
* Upgrade to Jakarta EE 9.
* Remove dependency on commons-collections.
* Remove dependency on slf4j.
* Remove extension-sound-freetts extension and all sound related code.
* Remove buffered-engine extension.
* Remove jboss-cache extension.
* Integrate api, jcaptcha and servlet-integration in an unique jar.
* Integrate only used filters from filters library.



