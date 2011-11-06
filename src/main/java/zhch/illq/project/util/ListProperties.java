package zhch.illq.project.util;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;

public class ListProperties extends Properties {
	private static final long serialVersionUID = 1L;
	public List<String> keyList = null;

	public void initKeyList(File file) throws IOException {
		keyList = new ArrayList<String>();
		List<String> lines = FileUtils.readLines(file);
		for (String line : lines) {
			if (line.indexOf("=") > 0 && !line.startsWith("#")) {
				keyList.add(line.split("=")[0]);
			}
		}
	}

	public String getProperty(String key) {
		if (super.getProperty(key) != null) {
			return super.getProperty(key).trim();
		}
		return null;
	}
}
