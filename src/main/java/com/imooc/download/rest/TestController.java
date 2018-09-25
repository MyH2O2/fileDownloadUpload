package com.imooc.download.rest;

import com.imooc.download.model.ResultBean;
import com.imooc.download.model.UserInfo;
import com.imooc.download.utils.FileUtils;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Author: zhangxing
 * @Date: 2018/9/23 14:58
 */
@RestController
@RequestMapping("/test")
public class TestController{

	@GetMapping("/user")
    public ResultBean<UserInfo> getTest()
    {
        System.out.println("get1 ok.");
        return new ResultBean<UserInfo>(new UserInfo("zhangxing", 26));
    }
	
	@PostMapping("/user")
	public ResultBean<UserInfo> createUser(@RequestBody UserInfo user) throws IOException {
		System.out.println(user.getName()+ ","+user.getAge());
		
		return new ResultBean<UserInfo>(user);
	}
    
    @GetMapping("/download")
    public void download(HttpServletRequest req, HttpServletResponse rsp) throws Exception
    {
    	System.out.println("start to download the file.");
    	// get file name.
    	String filename = req.getParameter("filename");
    	// set response header:content-disposition
    	rsp.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode("template.txt", "UTF-8"));
    	InputStream in;
    	OutputStream out;
    	try {
    		String path = "src/main/resources/templates";
    		System.out.println(path);
			in = new FileInputStream(path + File.separator + filename);
			out = rsp.getOutputStream();
			byte[] b = new byte[1024];
			int len;
			while((len = in.read(b)) != -1)
			{
				out.write(b, 0, len);
			}
			in.close();
		} catch (Exception e) {
			System.out.println("download failed.");
			e.printStackTrace();
		}
    	System.out.println("download success.");
    }
    
    @PostMapping("/upload")
    public String upload(@RequestParam("file") MultipartFile file, HttpServletRequest req) {
    	System.out.println("start to upload file.");
    	String filename = file.getOriginalFilename();
    	try {
    		String path = "src/main/resources/uploads";
    		System.out.println(path);
			FileUtils.uploadFile(file.getBytes(), path, filename);
		} catch (IOException e) {
			System.out.println("upload failed.");
			e.printStackTrace();
		}
    	return "upload success";
    }
    
    @PostMapping("/upload2")
    public String upload2(@RequestParam("file") MultipartFile file, HttpServletRequest req) {
    	System.out.println("start to upload file.");
    	// Get file name
    	String filename = file.getOriginalFilename();
    	try {
    		String path = "src/main/resources/uploads";
    		System.out.println(path);
    		InputStream in = file.getInputStream();
    		OutputStream out = new FileOutputStream(path + File.separator + filename);
    		byte[] b = new byte[1024];
    		int len;
    		while((len = in.read(b)) != -1) {
    			out.write(b, 0, len);
    		}
    		out.flush();
    		out.close();
		} catch (IOException e) {
			System.out.println("upload failed.");
			e.printStackTrace();
		}
    	System.out.println("upload success.");
    	return "upload success";
    }
}
