package com.xxx;

import java.io.IOException;
import java.net.URI;

import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;

public class TestHDFS {
	public static String hdfsUrl = "hdfs://192.168.230.147:9000";
	public static void main(String[] args) throws Exception {
        Configuration conf = new Configuration();
        FileSystem fs = FileSystem.get(URI.create(hdfsUrl), conf);
        //hadoop fs -lsr / 
//        FileSystem fs = FileSystem.get(conf);
//        Path path = new Path("/tes1122t");
//        fs.mkdirs(path);
        //hadoop fs -cat /tes1122t/b.txt
//        Path path = new Path("/tes1122t/b.txt");
//        FSDataOutputStream out = fs.create(path);
//        out.write("hello hadoop".getBytes());
        
      //  Path path = new Path("/test/a.txt");
        //rename a file
//        Path newpath = new Path("/tes1122t/a.txt");
//        System.out.println(fs.rename(path, newpath));
        
        //upload a local file to HDFS
        Path src = new Path("/home/csm/Downloads/sort_in/file1");
        Path dst = new Path("/tes1122t");
        fs.copyFromLocalFile(src, dst);
}
}
