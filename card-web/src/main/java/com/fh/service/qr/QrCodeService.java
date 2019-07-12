package com.fh.service.qr;

public interface QrCodeService {

    /**
     * @notes 获取二维码
     * @param pageIndex
     * @return
     * @throws Exception
     */
    public String getQrCodeUrl(String pageIndex, String appid, String secret, String grantType) throws Exception;
}
