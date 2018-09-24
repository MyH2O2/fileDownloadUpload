package com.imooc.download.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class FileUtils {
	public static void uploadFile(byte[] file, String path, String fileName) throws IOException {
		File targetFile = new File(path);
		if (!targetFile.exists()) {
			targetFile.mkdirs();
		}
		OutputStream out = new FileOutputStream(path + File.separator + fileName);
		out.write(file);
		out.flush();
		out.close();
	}
}
