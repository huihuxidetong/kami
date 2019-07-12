package com.fh.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class PropertiesUtil {

    private @Value("${file.path}")String filePath;//文件存放路径

    private @Value("${file.upload.url}")String fileUploadUrl;//文件上传路径

    private @Value("${file.download.url}")String fileDownloadUrl;//文件下载路径

    private @Value("${wx_token_url}")String wxTokenUrl;

    private @Value("${wx_qr_cdoe_url}")String wxQrCdoeUrl;

    private @Value("${appid}")String appid;

    private @Value("${secret}")String secret;

    private @Value("${page_index}")String page_index;

    private @Value("${grant_type}")String grant_type;

    private @Value("${default_img_url}")String default_img_url;


    public String getFilePath() {
        return filePath;
    }
    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
    public String getFileDownloadUrl() {
        return fileDownloadUrl;
    }

    public void setFileDownloadUrl(String fileDownloadUrl) {
        this.fileDownloadUrl = fileDownloadUrl;
    }

    public String getFileUploadUrl() {
        return fileUploadUrl;
    }

    public void setFileUploadUrl(String fileUploadUrl) {
        this.fileUploadUrl = fileUploadUrl;
    }

    public String getWxTokenUrl() {
        return wxTokenUrl;
    }

    public void setWxTokenUrl(String wxTokenUrl) {
        this.wxTokenUrl = wxTokenUrl;
    }

    public String getWxQrCdoeUrl() {
        return wxQrCdoeUrl;
    }

    public void setWxQrCdoeUrl(String wxQrCdoeUrl) {
        this.wxQrCdoeUrl = wxQrCdoeUrl;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getPage_index() {
        return page_index;
    }

    public void setPage_index(String page_index) {
        this.page_index = page_index;
    }

    public String getGrant_type() {
        return grant_type;
    }

    public void setGrant_type(String grant_type) {
        this.grant_type = grant_type;
    }

    public String getDefault_img_url() {
        return default_img_url;
    }

    public void setDefault_img_url(String default_img_url) {
        this.default_img_url = default_img_url;
    }


}

