package com.card.dao.impl;

import com.card.dao.DaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.card.inteface.dao.ActivityApplyDao;  
import com.card.inteface.entity.ActivityApply;  

/**
 * @notes:活动申请Dao实现类
 *
 * @author zzh
 *
 * 2018-09-26 13:07:44		Email:azhangzhihengi@163.com
 */
@Repository
public class ActivityApplyDaoImpl  implements ActivityApplyDao {

    @Autowired
    private DaoSupport daoSupport;

    /**
     * @notes 保存活动申请
     * @param activityApply
     */
    public void saveActivityApply(ActivityApply activityApply) throws Exception {
        daoSupport.save("ActivityApplyMapper.insertSelective",activityApply) ;
    }
}