package com.card.dao.impl;

import com.card.dao.DaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.card.inteface.entity.ChannelData;  
import com.card.inteface.dao.ChannelDataDao;  

/**
 * @notes:渠道数据Dao实现类
 *
 * @author zzh
 *
 * 2018-09-26 13:07:44		Email:azhangzhihengi@163.com
 */
@Repository
public class ChannelDataDaoImpl  implements ChannelDataDao {

    @Autowired
    private DaoSupport daoSupport;

    /**
     * @notes 保存渠道信息
     * @param channelData
     */
    public void saveChannelData(ChannelData channelData) throws Exception {
        daoSupport.save("ChannelDataMapper.insertSelective",channelData);
    }
}