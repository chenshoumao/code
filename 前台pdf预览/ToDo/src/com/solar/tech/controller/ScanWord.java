package com.solar.tech.controller;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.ConnectException;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.artofsolving.jodconverter.DocumentConverter;
import com.artofsolving.jodconverter.openoffice.connection.OpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.connection.SocketOpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.converter.OpenOfficeDocumentConverter;

@Controller
@RequestMapping("/scan")
public class ScanWord {
	@RequestMapping(value = "showWord")
    public String showWord(HttpServletRequest request, ModelMap model,
            String singleID) throws UnsupportedEncodingException {

        // 获取FTP路径
        String ftpUrlAddr = "";
        
        String pdfFile = "期末总结.pdf";
        
        pdfFile = URLEncoder.encode(pdfFile);
        
        
        String path = "C:/Users/csm/Desktop/" + pdfFile;
        
        String resourceFile ="C:/Users/csm/Desktop/期末总结.docx";
        
        
        File fileForPdf = new File(path);
        
        File fileForWord = new File(resourceFile);
        
        
        OpenOfficeConnection connection = new SocketOpenOfficeConnection(8100);
        File pathFile = new File(path);
        File resourceFiles = new File(resourceFile);
        System.out.println(resourceFiles.exists());
        try {
            connection.connect();
            DocumentConverter converter = new    OpenOfficeDocumentConverter(connection);
        converter.convert(resourceFiles, pathFile);
        connection.disconnect();
        } catch (ConnectException e) {
            e.printStackTrace();
            System.out.println("OpenOffice服务未启动");
        }
        if (fileForPdf.exists()) {
            model.put("pdfFile", pdfFile);
            return "documentView";
        } 
        
        return "documentView";
	 
	}
}
