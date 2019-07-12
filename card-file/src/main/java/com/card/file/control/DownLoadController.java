package com.card.file.control;

import com.card.file.util.FilePathConfig;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;
import java.io.File;
import java.io.IOException;

@Path("down")
public class DownLoadController {

	private final Logger logger = LoggerFactory.getLogger(DownLoadController.class);


	/**
	 * 原始版本文件浏览
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@GET
	@Path("/{year}/{month}/{day}/{file}")
	public void showImgs(@PathParam("year")String year,@PathParam("month")String month ,@PathParam("day")String day, @PathParam("file")String file,@Context HttpServletRequest request, @Context HttpServletResponse response) throws IOException {
		String filePath = FilePathConfig.ROOT_FILE_PATH + File.separator + year +  File.separator + month + File.separator + day + File.separator + file;
		try{
			FileUtils.copyFile(new File(filePath), response.getOutputStream());
		}catch(Exception e){
			logger.error("404 文件未找到:" + FilePathConfig.ROOT_FILE_PATH + filePath +e.getMessage(), e);
		}
	}
	
}
