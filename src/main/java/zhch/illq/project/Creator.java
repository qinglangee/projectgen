package zhch.illq.project;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import zhch.illq.project.config.Configer;
import zhch.illq.project.config.SpringConfiger;
import zhch.illq.project.config.Struts2Configer;
import zhch.illq.project.util.C;
import zhch.illq.project.util.CommandUtil;
import zhch.illq.project.util.ListProperties;
import zhch.illq.project.util.PropUtil;

public class Creator {
	private static Log log = LogFactory.getLog(Creator.class);
	public static ListProperties props;
	public static File root;
	public static File webRoot;
	private static Configer[] configers;

	public static void create() throws IOException {
		init();
		createDirs();
		setConfig();
		copyLib();
	}

	private static void init() {
		props = PropUtil.readProperties("src/default.properties");
		root = new File(props.getProperty("genRoot") + C.FS + props.getProperty("projectName"));
		webRoot = new File(root, props.getProperty("dir.WebRoot"));
		configers = new Configer[] { new SpringConfiger(), new Struts2Configer() };
	}

	private static void createDirs() throws IOException {

		// // 重新创建根目录
		if (root.exists()) {
			FileUtils.cleanDirectory(root);
		} else {
			root.mkdirs();
		}

		// 构建各级目录
		Map<String, Boolean> result = new HashMap<String, Boolean>();
		for (Object keyObj : props.keySet()) {
			String key = keyObj.toString();
			if (key.startsWith("dir.")) {
				String dir = props.getProperty(key);
				result.put(dir, new File(root, dir).mkdir());
			}
		}

		// 文件复制
		FileUtils.copyFileToDirectory(new File("plugins/basic/web.xml"), new File(root, props
				.getProperty("dir.WEB-INF")));
		FileUtils.copyFileToDirectory(new File("plugins/basic/pom.xml"), root);

		// 文件目录构建结果列表
		for (String key : result.keySet()) {
			log.info(result.get(key) + "==" + key);
		}
	}

	private static void setConfig() throws IOException {
		for (Configer configer : configers) {
			configer.config();
		}
	}

	private static void copyLib() throws IOException {
		if (!C.Y.equals(props.getProperty("action.copyLib"))) {
			return;
		}
		String[] cmdArray = new String[] { "cmd.exe", "/C", "mvn dependency:copy-dependencies" };
		CommandUtil.exec(cmdArray, null, root);
		FileUtils.moveDirectory(new File(root, "target/dependency"), new File(webRoot, "WEB-INF/lib"));
	}

	public static void main(String[] args) {
		try {
			Creator.create();
		} catch (IOException e) {
			e.printStackTrace();
		}
		log.info("over................................");
	}

}
