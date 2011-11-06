package zhch.illq.project.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class CommandUtil {
	private static Log log = LogFactory.getLog(CommandUtil.class);

	public static void exec(String[] cmdArray, String[] envp, File dir) throws IOException {
		Runtime rt = Runtime.getRuntime();

		Process p = rt.exec(cmdArray, null, dir);
		BufferedReader in = null;
		try {
			in = new BufferedReader(new InputStreamReader(p.getInputStream()));
			String str = null;
			while ((str = in.readLine()) != null) {
				log.info(str);
			}
			in = new BufferedReader(new InputStreamReader(p.getErrorStream()));
			while ((str = in.readLine()) != null) {
				log.error(str);
			}
			p.waitFor();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			in.close();
		}
	}
}
