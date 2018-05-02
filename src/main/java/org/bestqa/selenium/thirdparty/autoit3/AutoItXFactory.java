package org.bestqa.selenium.thirdparty.autoit3;

import java.io.File;

import org.bestqa.selenium.common.ConfigurationSettings;
import org.bestqa.selenium.thirdparty.autoit3.AutoItX;

import com.sun.jna.Native;

/**
 * Create AutoItX object classes by JNA 
 *
 */
public class AutoItXFactory {
private static AutoItX INSTANCE;
	
	static {
		String path = System.getProperty("user.dir") + File.pathSeparator + ConfigurationSettings.THIRDPARTY_AUTOIT3_DLL;
		File file = new File(path);
		
		if (!file.isFile()) {
			throw new RuntimeException(String.format("Cannot load dll file: %s", path));
		}

		INSTANCE = (AutoItX) Native.loadLibrary(path, AutoItX.class);
	}
	
	/**
	 * Get AutoItX instance
	 */
	public static AutoItX getInstance() {
		return INSTANCE;
	}
}
