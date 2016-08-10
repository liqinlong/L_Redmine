package liql.util;

import java.io.File;
import java.io.FileFilter;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

public class JarUtil {
	public static void readJarLib(String path) throws MalformedURLException, Exception {
		L_LOG.OUT_Nece("start load lib [" + System.getProperty("user.dir") + "]");
		JarLoader jarLoader = new JarLoader((URLClassLoader) ClassLoader.getSystemClassLoader());
		loadjar(jarLoader, System.getProperty("user.dir") + "/" + path);
		L_LOG.OUT_Nece("end load lib");
	}

	private static class JarLoader {
		private URLClassLoader urlClassLoader;

		public JarLoader(URLClassLoader urlClassLoader) {
			this.urlClassLoader = urlClassLoader;
		}

		public void loadJar(URL url) throws Exception {
			L_LOG.OUT_Nece("add jar library path ==>" + url.getPath());
			Method addURL = URLClassLoader.class.getDeclaredMethod("addURL", URL.class);
			addURL.setAccessible(true);
			addURL.invoke(urlClassLoader, url);
		}
	}

	private static void loadjar(JarLoader jarLoader, String path) throws MalformedURLException, Exception {
		File libdir = new File(path);
		if (libdir != null && libdir.isDirectory()) {
			File[] listFiles = libdir.listFiles(new FileFilter() {
				public boolean accept(File file) {
					return file.exists() && file.isFile() && file.getName().endsWith(".jar");
				}
			});
			for (File file : listFiles) {
				jarLoader.loadJar(file.toURI().toURL());
			}
		} else {
			L_LOG.OUT_Nece("[Console Message] Directory [" + path + "] does not exsit, please check it");
			System.exit(0);
		}
	}
}
