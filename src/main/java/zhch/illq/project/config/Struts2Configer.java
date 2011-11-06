package zhch.illq.project.config;

import java.io.File;

import zhch.illq.project.Creator;
import zhch.illq.project.util.C;
import zhch.illq.project.util.StringUtil;

public class Struts2Configer extends Configer {
	public Struts2Configer() {
		modle = "struts2";
		init();
	}

	protected void setExampleConfig() {
		File valueFile = new File(exampleDir + C.FS + "struts.xml");
		if (valueFile.exists()) {
			File file = new File(Creator.root, Creator.props.getProperty("dir.src") + C.FS + "struts.xml");
			String token = "</struts>";
			StringUtil.insertBefore(file, token, valueFile);
		}
	}
}
