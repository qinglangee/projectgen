package zhch.illq.project;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.taskdefs.Replace;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Project project = new Project();

		String content = "";
		try {
			content = FileUtils.readFileToString(new File("d:\\temp\\bb.txt"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		Replace r = new Replace();
		r.setProject(project);
		r.setFile(new File("d:\\temp\\aa.txt"));
		r.setToken("a.a");
		r.setValue(content);
		r.execute();
	}
}
