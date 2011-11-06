package zhch.illq.project.config;

import java.io.File;
import java.io.IOException;

import zhch.illq.project.Creator;
import zhch.illq.project.util.C;
import zhch.illq.project.util.StringUtil;

public class SpringConfiger extends Configer {

	private static final String ns = "    xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"";
	private static final String location = "        http://www.springframework.org/schema/beans/spring-beans-2.5.xsd";

	public SpringConfiger() {
		modle = "spring";
		init();
	}

	protected void copyConfigFile() throws IOException {
		mergeApplicationContect();
	}

	private void mergeApplicationContect() {
		File namespace = new File(pluginDir + C.FS + "namespace.xml");
		File schemaLocation = new File(pluginDir + C.FS + "schemaLocation.xml");
		File application_merge = new File(pluginDir + C.FS + "merge_application.xml");
		File application = new File(Creator.root, Creator.props.getProperty("dir.src") + C.FS
				+ "applicationContext.xml");
		if (namespace.exists()) {
			StringUtil.insertAfter(application, ns, namespace);
		}
		if (schemaLocation.exists()) {
			StringUtil.insertAfter(application, location, schemaLocation);
		}
		if (application_merge.exists()) {
			StringUtil.insertBefore(application, "</beans>", application_merge);
		}
	}
}
