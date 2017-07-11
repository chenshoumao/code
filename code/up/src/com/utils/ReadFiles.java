package com.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
//import java.io.UnsupportedEncodingException;
//import java.net.URLEncoder;
//import java.util.HashMap;
//import java.util.Map;

import com.service.FindImg;

public class ReadFiles {
	static int totalRecord;
	/**
	 * 
	 * @Description: ��ȡ����������ͼƬ
	 * @param @param path   ʵ��·��
	 * @param @param urlList·���ļ���
	 * @param @param pageNum�ڼ�ҳ
	 * @param @param pageSizeÿҳ��ʾ������
	 * @param @return   
	 * @return List<Img>  
	 * @throws
	 * @author houhuayu
	 * @date 2016-6-27
	 */
	public static Pager<Img>  photo(List<String> path,List<String> urlList,int pageNum,int pageSize){
		
		//��ȡ��ҳ��
		int totalPage = totalRecord / pageSize;
		if (totalRecord % pageSize != 0) {
			totalPage++;
		}
		Pager<Img> result = null;
		List<Img> imgList = ReadFiles.photos(path, urlList, pageNum, pageSize);

		// //�ܼ�¼��
		result = new Pager<Img>(pageSize, pageNum, totalRecord, totalPage, imgList);
		return result;
	}
	
	/**
	 * 
	 * @Description: ����б�
	 * @param @param path
	 * @param @param urlList
	 * @param @param pageNum
	 * @param @param pageSize
	 * @param @return   
	 * @return List<Img>  
	 * @throws
	 * @author houhuayu
	 * @date 2016-6-27
	 */
	public static List<Img> photos(List<String> path,List<String> urlList,int pageNum,int pageSize){
		  List<Img> imgList = new ArrayList<Img>();
		  List<Img> imgListNew = new ArrayList<Img>();
		  String url ="";
		for(int k=0;k<path.size();k++){
		  File file=new File(path.get(k));
		  File[] tempList = file.listFiles();
		  url = urlList.get(k);
		  for (int i = 0; i < tempList.length; i++) { //ѭ�����
		    System.out.println("�ļ��У�"+tempList[i]);
		    File imgFile= tempList[i];
		    String photoname = imgFile.getName();
		    File[] tempListImg = imgFile.listFiles();
		    String imgname = tempListImg[0].getName();
		    Img img = new Img();
		    img.setImgName(imgname);
		    img.setPhoto(photoname);
		    img.setImgUrl(url+"/"+photoname+"/"+imgname);
		    img.setSaveFile(url);
		    imgList.add(img);
		   }
		}
		totalRecord = imgList.size();  
	    int fromIndex	= pageSize * (pageNum-1);
	    int sizeAll = imgList.size();
	    int indexAll = fromIndex+pageSize;
	    if(indexAll>sizeAll){
	    	indexAll = sizeAll;
	    }
	    for(int m=fromIndex;m<indexAll;m++){
	    	imgListNew.add(imgList.get(m));
	    }
		  return imgListNew;	
	}



}
