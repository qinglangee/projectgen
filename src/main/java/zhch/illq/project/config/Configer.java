package zhch.illq.project.config;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import zhch.illq.project.Creator;
import zhch.illq.project.util.C;
import zhch.illq.project.util.StringUtil;

public class Configer {
	private static Log log = LogFactory.getLog(Configer.class);
	protected static final String plugins = "plugins";

	protected static final String example = "example";
	protected static final String src = "src";
	protected static final String webContent = "WebContent";

	/** 大模块名称 struts2 spring */
	protected String modle = null;
	/** 小模块名称 struts2.core */
	protected String innerModle = null;
	/** 模块名称前缀 */
	protected String modlePre = null;

	protected String pluginDir = plugins;
	protected String exampleDir = plugins;

	protected void init() {
		modlePre = "modle." + modle + ".";
	}

	public void config() throws IOException {
		for (String key : Creator.props.keyList) {
			String value = Creator.props.getProperty(key);
			log.debug("property:===" + key);
			// 选中,则进行配置设置
			if (key.startsWith(modlePre) && C.Y.equals(value)) {
				pluginDir = plugins + C.FS + modle + C.FS + key.substring(modlePre.length());
				innerModle = modle + "." + key.substring(modlePre.length());
				setConfig();

				// 是否设置例子
				String exampleKey = key.replace("modle", "example");
				String exampleValue = Creator.props.getProperty(exampleKey);
				if (exampleValue != null && C.Y.equals(exampleValue)) {
					exampleDir = pluginDir + C.FS + example;
					setExample();
				}
			}
		}
	}

	protected void setConfig() throws IOException {
		copyFiles();
		mergePom(pluginDir);
		mergeWeb(pluginDir);
		copyConfigFile();
	}

	private void copyFiles() throws IOException {
		File srcDir = new File(pluginDir + C.FS + "copy");
		if (srcDir.exists()) {
			FileUtils.copyDirectory(srcDir, Creator.root);
		}
	}

	private void mergePom(String dirPath) {
		File valueFile = new File(dirPath + C.FS + "pom.xml");
		if (valueFile.exists()) {
			File file = new File(Creator.root, "pom.xml");
			String token = "    </dependencies>";
			StringUtil.insertBefore(file, token, valueFile);
			log.info("merged pom.xml for [" + innerModle + "]");
		}
	}

	private void mergeWeb(String dirPath) {
		File valueFile = new File(dirPath + C.FS + "web.xml");
		if (valueFile.exists()) {
			File file = new File(Creator.root, Creator.props.getProperty("dir.WEB-INF") + C.FS + "web.xml");
			String token = "</web-app>";
			StringUtil.insertBefore(file, token, valueFile);
			log.info("merged web.xml for [" + innerModle + "]");
		}
	}

	protected void copyConfigFile() throws IOException {
		log.info("Configer.copyConfigFile()......" + modle);
	}

	protected void setExample() throws IOException {
		mergePom(exampleDir);
		mergeWeb(exampleDir);
		File examples = new File(exampleDir + C.FS + "copy");
		if (examples.exists()) {
			FileUtils.copyDirectory(examples, Creator.root);
		}
		setExampleConfig();
	}

	protected void setExampleConfig() {
		log.info("Configer.setExampleConfig()......");
	}
}