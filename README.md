# fileDownloadUpload
Download and Upload file

1.Download
  1)下载前台技术用的是超链接标签a,如：
  \\<a href="url" download="filename"\\>
  
  2)这是一个Get请求，后台我们可以从HttpServletResponse对象response使用getOutputStream()方法获取输出流OutputStream对象，
  然后将要下载的文件使用InputStream读取成字节流写入输出流中，这样前台下载的文件就可以正常输出文件内容。
  
  3)需要注意的是在IE浏览器中不是很好的支持\\<a\\>标签的download属性，再就是浏览器可以解析的文件不会下载，而是浏览器直接解析显示，
  这就需要我们在接口response设置header，使其强制下载，编码是防止中文乱码，如：
  response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode("template.txt", "UTF-8"));

  
2.Upload
  1)使用form表单，因为get请求是将内容拼接在url后，上传文件是将文件解析成二进制流处理，所以url回很长，而且不安全 ，故form表单method="post",
  而且数据类型必须是enctype="multipart/form-data"，如：
    \\<form action="/test/upload" enctype="multipart/form-data" method="post"\\>
        \\<input type="file" name = "file"\\>
        \\<br\\>\\<br\\>
        \\<input type="submit" value="Submit"\\>
    \\</form\\>
    
  2)后台接口形参@RequestParam("file") MultipartFile file获取上传的文件，注解中的file和form表单中的上传文件input标签name对应，获取文件名 file.getOriginalFilename();
  获取上传文件的字节数组 file.getBytes()
  或者获取上传文件的字节流 file.getInputStream();
  然后new FileOutputStream()对象写入到服务器路径即可实现上传。
