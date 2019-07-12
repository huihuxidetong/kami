package com.card.file.util;

import org.apache.commons.io.FileUtils;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * 上传文件
 * 创建人：zzh
 * 创建时间：2014年12月23日
 * @version
 */
public class FileUpload {

	public static String getFilePath(){
		String year = DateUtil.getYear();
		String month = DateUtil.getMonth();
		String day = DateUtil.getNowDay();
		String fileDir = year + File.separatorChar + month + File.separatorChar + day + File.separatorChar;
		return fileDir;
	}

	/**上传文件
	 * @param filePath		//上传路径
	 * @param fileName		//文件名
	 * @return  文件名
	 */
	public static String fileUp(InputStream in,String extName, String filePath,String fileName){
		String fileDir = getFilePath();
		try {
			/**
			 * 创建yyyy/MM/dd的文件夹
			 */
			copyFile(in,filePath + File.separatorChar + fileDir, fileName+extName).replaceAll("-", "");
		} catch (IOException e) {
			return "";
		}
		return fileDir+fileName+extName;
	}
	
	/**
	 * 写文件到当前目录的upload目录中
	 * @param in
	 * @param realName
	 * @throws IOException
	 */
	public static String copyFile(InputStream in, String dir, String realName)
			throws IOException {
		File file = mkdirsmy(dir,realName);
		FileUtils.copyInputStreamToFile(in, file);
		return realName;
	}
	
	
	/**判断路径是否存在，否：创建此路径
	 * @param dir  文件路径
	 * @param realName  文件名
	 * @throws IOException 
	 */
	public static File mkdirsmy(String dir, String realName) throws IOException{
		File file = new File(dir, realName);
		if (!file.exists()) {
			if (!file.getParentFile().exists()) {
				file.getParentFile().mkdirs();
			}
			file.createNewFile();
		}
		return file;
	}
	
	
	/**下载网络图片上传到服务器上
	 * @param httpUrl 图片网络地址
	 * @param filePath	图片保存路径
	 * @param myFileName  图片文件名(null时用网络图片原名)
	 * @return	返回图片名称
	 */
	public static String getHtmlPicture(String httpUrl, String filePath , String myFileName) {
		
		URL url;						//定义URL对象url
		BufferedInputStream in;			//定义输入字节缓冲流对象in
		FileOutputStream file;			//定义文件输出流对象file
		try {
			String fileName = null == myFileName?httpUrl.substring(httpUrl.lastIndexOf("/")).replace("/", ""):myFileName; //图片文件名(null时用网络图片原名)
			url = new URL(httpUrl);		//初始化url对象
			in = new BufferedInputStream(url.openStream());									//初始化in对象，也就是获得url字节流
			//file = new FileOutputStream(new File(filePath +"\\"+ fileName));
			file = new FileOutputStream(mkdirsmy(filePath,fileName));
			int t;
			while ((t = in.read()) != -1) {
				file.write(t);
			}
			file.close();
			in.close();
			return fileName;
		} catch (MalformedURLException e) {
			e.printStackTrace();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
		
	}

	public static void main(String[] args) {
		String year = DateUtil.getYear();
		String month = DateUtil.getMonth();
		String day = DateUtil.getNowDay();
		String fileDir = year + File.separatorChar + month + File.separatorChar + day + File.separatorChar;
		System.out.println(fileDir);
	}
}
