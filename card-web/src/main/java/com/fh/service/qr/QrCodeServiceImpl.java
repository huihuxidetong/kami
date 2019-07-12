package com.fh.service.qr;

import com.alibaba.fastjson.JSONObject;
import com.fh.util.PropertiesUtil;
import com.fh.util.UuidUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.*;

@Service
public class QrCodeServiceImpl implements QrCodeService {

    private static Logger logger = LogManager.getLogger(QrCodeServiceImpl.class);

    @Autowired
    private PropertiesUtil propertiesUtil;
    public String getQrCodeUrl(String pageIndex, String appid, String secret, String grantType) throws Exception {
        String qrCodeUrl = "";
        //生成二维码
        qrCodeUrl = this.getQrCodeUrl1(pageIndex, appid, secret, grantType);
        logger.info("===========生成并上传的二维码路径为qrCodeUrl："+qrCodeUrl);
        return qrCodeUrl;
    }

    // getToken
    public String getToken(String appid, String secret, String grantType) {
        RestTemplate rt = new RestTemplate();

        //getWxCardQrCdoeUrl     zh: getWxCardTokenUrl
        StringBuffer sb = new StringBuffer(propertiesUtil.getWxTokenUrl());
        sb.append("?appid=").append(appid);
        sb.append("&secret=").append(secret);
        sb.append("&grant_type=").append(grantType);
        ResponseEntity<String> forEntity = rt.getForEntity(sb.toString(), String.class);
        logger.info("获取token，结果" + forEntity.getBody());
        if (HttpStatus.OK.equals(forEntity.getStatusCode())) {
            JSONObject json = JSONObject.parseObject(forEntity.getBody());
            String token = String.valueOf(json.get("access_token"));
            return token;
        }
        return "";
    }

    // getQrCodeUrl
    public String getQrCodeUrl1(String pageIndex, String appid, String secret, String grantType) throws IOException {
        logger.info("================生成二维码开始参数22222relatedId,pageIndex,appid,secret,grantType:"+pageIndex +","+pageIndex+","+appid+","+secret+","+grantType);
        InputStream inputStream = null;
        OutputStream outputStream = null;
        RestTemplate rt = new RestTemplate();
        String filePath = "";
        File file = null;
        try {
            String token = getToken(appid, secret, grantType);
            if (StringUtils.isBlank(token)) {
                return "";
            } else {
                Gson gson = new GsonBuilder().disableHtmlEscaping().create();
                QrCode qrCode = new QrCode();
                qrCode.setPath(java.net.URLDecoder.decode(pageIndex));
                qrCode.setWith(430);

                //getWxCardQrCdoeUrl   getWxCardQrCdoeUrl
                    ResponseEntity< byte[]> forEntity = rt.postForEntity(propertiesUtil.getWxQrCdoeUrl()+"?access_token=" + token,  gson.toJson(qrCode),byte[].class);
                if (HttpStatus.OK.equals(forEntity.getStatusCode())) {
                    byte[] result = forEntity.getBody();
                    inputStream = new ByteArrayInputStream(result);
                    file = new File(propertiesUtil.getFilePath()+File.separator+UuidUtil.get32UUID()+".jpg");
                    if (!file.exists()) {
                        file.createNewFile();
                    }
                    outputStream = new FileOutputStream(file);
                    int len = 0;
                    byte[] buf = new byte[1024];
                    while ((len = inputStream.read(buf, 0, 1024)) != -1) {
                        outputStream.write(buf, 0, len);
                    }
                    outputStream.flush();
                    filePath = getFilePath(file);

                }
            }
        }
        catch (Exception e) {
            logger.error("==========获取二维码异常"+e.getLocalizedMessage(),e);
        } finally {
            if(inputStream != null){
                try {
                    inputStream.close();
                } catch (IOException e) {
                    logger.error("==========获取二维码异常"+e.getLocalizedMessage(),e);
                }
            }
            if(outputStream != null){
                try {
                    outputStream.close();
                } catch (IOException e) {
                    logger.error("==========获取二维码异常"+e.getLocalizedMessage(),e);
                }
            }
            if(null != file && file.exists()){
                file.delete();
            }
        }
        return filePath;
    }

    public String getFilePath(File f) throws IOException {
        HttpClientBuilder httpBuilder = HttpClientBuilder.create();
        CloseableHttpClient httpclient = httpBuilder.build();
        String filePath = "";
        try {
            HttpPost httppost = new HttpPost(propertiesUtil.getFileUploadUrl());
            FileBody bin = new FileBody(f);
            HttpEntity reqEntity = MultipartEntityBuilder.create().addPart("file", bin).build();
            httppost.setEntity(reqEntity);
            CloseableHttpResponse response = httpclient.execute(httppost);
            HttpEntity resEntity = response.getEntity();
            if (resEntity != null) {
                resEntity.getContent();
                String body = EntityUtils.toString(resEntity);
                if(StringUtils.isNotBlank(body)){
                    JSONObject jsonObject = JSONObject.parseObject(body);
                    if("0".equalsIgnoreCase(String.valueOf(jsonObject.get("code")))){
                        filePath = String.valueOf(jsonObject.get("data"));
                    }
                }
            }
            EntityUtils.consume(resEntity);
        } finally {
            if(f.exists()){
                f.delete();
            }
            httpclient.close();
        }
        return filePath;
    }

 }
