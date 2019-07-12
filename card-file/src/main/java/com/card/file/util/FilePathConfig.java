package com.card.file.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * createUser：lizq
 * createDate：2016年4月25日
 * version：1.0
 * note：
 *
 **/

public class FilePathConfig {

	private static Logger log = LoggerFactory.getLogger(FilePathConfig.class);

	public static String ROOT_FILE_PATH;

	public static String TEMP_FILE_PATH;

	public static Integer POST_IMG_WIDTH ;
	
	static{
		Properties prop =  new  Properties();    
        InputStream in = FilePathConfig.class.getClassLoader().getResourceAsStream( "/system.properties" );
        try  {
            prop.load(in);
			ROOT_FILE_PATH = prop.getProperty("root_file_path");
            TEMP_FILE_PATH = prop.getProperty("temp_file_path");
            POST_IMG_WIDTH = Integer.valueOf(prop.getProperty("post_img_width"));
        }  catch  (IOException e) {    
            e.printStackTrace();    
            log.info("---------- 文件保存地址加载错误=---------------------------");
            log.info("" + e);
        }
	}
	
}
