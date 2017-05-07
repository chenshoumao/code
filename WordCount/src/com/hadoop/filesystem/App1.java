package com.hadoop.filesystem;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.hadoop.fs.FsUrlStreamHandlerFactory;
import org.apache.hadoop.io.IOUtils;

public class App1 {
	static final String PATH = "hdfs://192.168.230.147:9000/test/hello";
	public static void main(String[] args) throws IOException {
		URL.setURLStreamHandlerFactory(new FsUrlStreamHandlerFactory());
		final URL url = new URL(PATH);
		final InputStream in = url.openStream();
		
		IOUtils.copyBytes(in, System.out, 1024,true);
		
	}
}
