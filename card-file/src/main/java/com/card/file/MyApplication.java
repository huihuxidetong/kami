package com.card.file;

import javax.ws.rs.ApplicationPath;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.mvc.jsp.JspMvcFeature;

/**
 * qq: 845885222@qq.com
 *
 * @author Administrator
 */
@ApplicationPath("/")
public class MyApplication extends ResourceConfig {

    public MyApplication() {
	packages("com.card.file.control");
	register(JspMvcFeature.class);
	register(JacksonFeature.class);
	register(MultiPartFeature.class);
    }
}
