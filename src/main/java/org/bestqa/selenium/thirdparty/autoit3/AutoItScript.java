package org.bestqa.selenium.thirdparty.autoit3;

import java.io.File;
import java.io.IOException;

import org.bestqa.selenium.common.ConfigurationSettings;

/**
 * Call AutoIt function by way of executing AutoIt scripts
 *
 */
public class AutoItScript {
	private static String autoItExecutablePath;
	private static String autoItUploadScriptPath;
	
	static {
		checkExecutablePath();
		checkUploadScriptPath();
	}
	
	private static void checkExecutablePath() {
		String autoItPath = System.getProperty("user.dir") + File.separator + ConfigurationSettings.THIRDPARTY_AUTOIT3_EXE;
		File autoItExecutable = new File(autoItPath);
		if (!autoItExecutable.isFile()) {
			throw new RuntimeException(String.format("Check autoit executable failed, cannot find file: %s", autoItPath));
		}
		
		autoItExecutablePath = autoItPath;
	}
	
	private static void checkUploadScriptPath() {
		String path = System.getProperty("user.dir") + File.separator + ConfigurationSettings.THIRDPARTY_AUTOIT3_SCRIPT_UPLOADFILE;
		File temp = new File(path);
		if (!temp.isFile()) {
			throw new RuntimeException(String.format("Check upload script failed, cannot find file: %s", path));
		}
		
		autoItUploadScriptPath = path;
	}
	
	/**
	 * Start AutoIt scripts, and wait for the upload file dialog box appears, and upload files
	 * 
	 * @param filepath Absolute path of the uploaded files
	 */
	public void waitToUploadFile(String filepath) {
		try {
			String[] scriptcmd = new String[] { autoItExecutablePath, autoItUploadScriptPath, filepath };
			Runtime.getRuntime().exec(scriptcmd);
			// Waiting for AutoIt script started
			Thread.sleep(500);
		} catch (IOException e1) {
			// nothing
		} catch (InterruptedException e) {
			// nothing
		}
	}
}
