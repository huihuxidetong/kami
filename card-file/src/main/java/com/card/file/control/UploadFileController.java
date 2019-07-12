package com.card.file.control;

import com.card.file.util.FilePathConfig;
import com.card.file.util.FileUpload;
import com.card.file.util.UuidUtil;
import com.card.file.vo.OutVo;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.io.InputStream;

/**
 * 文件上传
 *
 * @author zzh
 */
@Path("upload")
public class UploadFileController {

    private final Logger logger = LoggerFactory.getLogger(UploadFileController.class);

    /**
     * 文件保存
     *
     * @param fileInputStream
     * @return
     */
    @POST
    @Path("/file")
    @Consumes({MediaType.MULTIPART_FORM_DATA, MediaType.APPLICATION_FORM_URLENCODED})
    @Produces({MediaType.APPLICATION_JSON})
    public OutVo<?> portraitFile(@FormDataParam("file") InputStream fileInputStream, @FormDataParam("file") FormDataContentDisposition disposition, @Context HttpServletResponse response) {
        OutVo<?> out = new OutVo<Object>();
        try {
            logger.info("文件:==================================");
            String extName = disposition.getFileName();
            extName = extName.substring(extName.lastIndexOf("."), extName.length());
            String fileName = FileUpload.fileUp(fileInputStream, extName, FilePathConfig.ROOT_FILE_PATH, UuidUtil.get32UUID()); //源文件url
            //对上传的文件进行加密处理
//			CryptFileDES.encrypt(filePath, fileDes);
            out.setData(fileName);
            logger.info(fileName + "==========" + fileName);
            response.addHeader("Access-Control-Allow-Origin","*");
            return out;
        } catch (Exception e) {
            out.setCode(1);
            out.setShowMsg("上传失败，请重新上传");
            response.addHeader("Access-Control-Allow-Origin","*");
            logger.info("上传错误：" + e, e);
            return out;
        }
    }
}
