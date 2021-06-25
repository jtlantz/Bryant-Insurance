package org.muic.ssc;

public class Main{
	public static void main(String[] args){
		Tomcat tomcat = new Tomcat();
		tomcat.setPort(22700);

		File doceBase = new File("src/main/webapp/");
		doceBase.mkdirs();

		try {
			Context ctx = tomcat.addWebapp("", doceBase.getAbsolutePath());

			ServletRouter servletRouter = new ServletRouter();
			servletRouter.init(ctx);

			tomcat.start();
			tomcat.getServer().await();
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (LifecycleException e) {
			e.printStackTrace();
		}

	}
	}
}
