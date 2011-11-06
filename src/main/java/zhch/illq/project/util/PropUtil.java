package zhch.illq.project.util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

public class PropUtil {
	public static ListProperties readProperties(String filePath) {
		ListProperties props = new ListProperties();
		try {
			InputStream in = new BufferedInputStream(new FileInputStream(filePath));
			props.load(in);
			props.initKeyList(new File(filePath));
			return props;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
