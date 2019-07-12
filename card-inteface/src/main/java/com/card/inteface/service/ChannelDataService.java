package com.card.inteface.service;

import com.card.inteface.entity.ChannelData;  

/**
 * @notes:渠道数据Service接口
 *
 * @author zzh
 *
 * 2018-09-26 13:05:40		Email:azhangzhihengi@163.com
 */
public interface ChannelDataService  {

    /**
     * @notes 保存渠道信息
     * @param channelData
     */
    public void saveChannelData(ChannelData channelData) throws Exception;

}
