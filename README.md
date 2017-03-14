#JCaptcha

JCaptcha stands for Java Completely Automated Public Test to tell Computers and Humans Apart 

#License

JCaptcha is LGPL

#Usage (Simple Servlet Integration)
This integration module provides the shortest way to integrate JCaptcha to your web application.
All you need to do is add the jcaptcha jar to your project, make a reference to the SimpleImageCaptchaServlet in your web.xml and then use the servlet session id to validate what the user submits on your form against the captcha service.

Files to add (version is ommited)
- jcaptcha.jar
- jcaptcha-api.jar
- integrations/jcaptcha-integration-simple-servlet.jar
- lib/filters.jar

And if you don't have them already:

- lib/commons-logging.jar
- lib/commons-collections.jar

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

    <img src="jcaptcha.jpg" /> <input type="text" name="jcaptcha" value="" />

</form>

```

In your code that manages the submit action add the following code fragment to validate the user input

```java

String userCaptchaResponse = request.getParameter("jcaptcha");
boolean captchaPassed = SimpleImageCaptchaServlet.validateResponse(request, userCaptchaResponse);
if(captchaPassed){
// proceed to submit action
}else{
// return error to user
}
```

And that's it!



#Building from sources

Clone the repository or download de tar file from releases page on github, then run the Maven command:

    git clone https://github.com/albfernandez/jcaptcha.git
    git checkout tags/v.2.0.0
    cd jcaptcha
    mvn clean package verify 
    mvn assembly:single -Dmaven.test.skip=true


The result file is ``target/jcaptcha-2.0.0-dist.zip``
