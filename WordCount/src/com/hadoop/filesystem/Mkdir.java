package com.hadoop.filesystem;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
 

public class Mkdir {
	static final String PATH = "hdfs://192.168.230.147:9000/";
	static final String DIR = "/dl";
	public static void main(String[] args) throws IOException, URISyntaxException {
		FileSystem fileSystem = getFileSystem();
		//fileSystem.mkdirs();
		//mkdir
		//mkdirs(fileSystem);
		//putFile(fileSystem);
		//getFile(fileSystem);
		readFolder(fileSystem);
	}
	private static FileSystem getFileSystem() throws IOException, URISyntaxException{
		//return FileSystem.get(new URL(PATH), new Configuration());
		return FileSystem.get(new URI(PATH), new Configuration());
	}
	private static void mkdirs(FileSystem fs) throws IllegalArgumentException, IOException{
		fs.mkdirs(new Path(DIR));
	}
	private static void putFile(FileSystem fs) throws IllegalArgumentException, IOException{
		String filePath = "/opt/helloworld.txt";
		String inputPathString = "/test/hello2";
		FSDataOutputStream outputStream = fs.create(new Path(inputPathString));
		FileInputStream inputStream = new FileInputStream(filePath);
		IOUtils.copyBytes(inputStream, outputStream, 1024,true);
		
	}
	private static void getFile(FileSystem fsFileSystem) throws IllegalArgumentException, IOException{
		FSDataInputStream in = fsFileSystem.open(new Path("/test/hello2"));
		IOUtils.copyBytes(in, System.out, 1024, true);
	}
	
	private static void readFolder(FileSystem fs) throws FileNotFoundException, IllegalArgumentException, IOException{
		FileStatus[] listStatus = fs.listStatus(new Path("/test/"));
		for (FileStatus fileStatus:listStatus) {
			String isDirString = fileStatus.isDir()? "folder":"file";
			String permission = fileStatus.getPermission().toString();
			short replication = fileStatus.getReplication();
			long length = fileStatus.getLen();
			String itPath = fileStatus.getPath().toString();
			System.out.println(isDirString + "\t" + permission + "\t" + replication + "\t" + length + "\t" + itPath);
		}
	}
	
	
	
	
	
}
