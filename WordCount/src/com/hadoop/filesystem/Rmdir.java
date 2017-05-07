package com.hadoop.filesystem;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
 

public class Rmdir {
	static final String PATH = "hdfs://192.168.230.147:9000/";
	static final String DIR = "/dl";
	public static void main(String[] args) throws IOException, URISyntaxException {
		FileSystem fileSystem = getFileSystem(); 
		//fileSystem.delete(new Path(DIR),true);
		
	}
	private static FileSystem getFileSystem() throws IOException, URISyntaxException{
		//return FileSystem.get(new URL(PATH), new Configuration());
		return FileSystem.get(new URI(PATH), new Configuration());
	}
	 
}
