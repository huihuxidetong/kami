package com.card.inteface.dao;

import org.springframework.stereotype.Component;

import com.card.inteface.entity.ChannelData;  

/**
 * @notes:渠道数据Dao接口
 *
 * @author zzh
 *
 * 2018-09-26 13:07:44		Email:azhangzhihengi@163.com
 */
@Component
public interface ChannelDataDao {

    /**
     * @notes 保存渠道信息
     * @param channelData
     */
    public void saveChannelData(ChannelData channelData) throws Exception;
}
