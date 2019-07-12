package com.fh.util;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 下载文件
 * 创建人：FH Q313596790
 * 创建时间：2014年12月23日
 * @version
 */
public class FileDownload {

	/**
	 * @param response 
	 * @param filePath		//文件完整路径(包括文件名和扩展名)
	 * @param fileName		//下载后看到的文件名
	 * @return  文件名
	 */
	public static void fileDownload(final HttpServletResponse response, String filePath, String fileName) throws Exception{  
		   
		byte[] data = FileUtil.toByteArray2(filePath);  
	    fileName = URLEncoder.encode(fileName, "UTF-8");  
	    response.reset();  
	    response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");  
	    response.addHeader("Content-Length", "" + data.length);  
	    response.setContentType("application/octet-stream;charset=UTF-8");  
	    OutputStream outputStream = new BufferedOutputStream(response.getOutputStream());  
	    outputStream.write(data);  
	    outputStream.flush();  
	    outputStream.close();
	    response.flushBuffer();
	    
	}


	/**
	 *
	 * @notes:文件下载
	 *
	 * @author：herny
	 *
	 * @param:paramMap{
	 * 					realFileName:文件的真实名称
	 * 					contextPath:本地服务器路径}
	 *
	 * 2014-11-19 下午4:17:10
	 */
	public static void downLoadFile(String urlString, HttpServletRequest request, HttpServletResponse response) throws IOException {
		BufferedInputStream dis = null;
		BufferedOutputStream fos = null;
		String fileName = urlString.substring(urlString.lastIndexOf('/') + 1);
		try {
			URL url = new URL(urlString);
			response.setContentType("application/x-msdownload;");
			response.setHeader("Content-disposition", "attachment; filename=" + new String(fileName.getBytes("utf-8"), "ISO8859-1"));
			response.setHeader("Content-Length", String.valueOf(url.openConnection().getContentLength()));

			dis = new BufferedInputStream(url.openStream());
			fos = new BufferedOutputStream(response.getOutputStream());

			byte[] buff = new byte[2048];
			int bytesRead;
			while (-1 != (bytesRead = dis.read(buff, 0, buff.length))) {
				fos.write(buff, 0, bytesRead);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (dis != null)
				dis.close();
			if (fos != null)
				fos.close();

		}
	}
}
