package zhch.illq.project.util;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.taskdefs.Replace;

public class StringUtil {
	public static void insertBefore(File file, String token, File valueFile) {
		replace(file, token, getContent(valueFile) + token);

	}

	public static void insertAfter(File file, String token, File valueFile) {
		replace(file, token, token + C.LS + getContent(valueFile));
	}

	public static void replace(File file, String token, File valueFile) {
		replace(file, token, getContent(valueFile));
	}

	public static void replace(File file, String token, String value) {
		replace(file, token, value, "utf-8");
	}

	public static void replace(File file, String token, String value, String encoding) {
		Project project = new Project();

		Replace r = new Replace();
		r.setProject(project);
		r.setEncoding(encoding);
		r.setFile(file);
		r.setToken(token);
		r.setValue(value);
		r.execute();
	}

	private static String getContent(File file) {
		return getContent(file, "utf-8");
	}

	private static String getContent(File file, String encoding) {
		String content = null;
		try {
			content = FileUtils.readFileToString(file, encoding);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return content;
	}

}
