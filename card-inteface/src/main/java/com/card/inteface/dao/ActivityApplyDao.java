package com.card.inteface.dao;

import org.springframework.stereotype.Component;

import com.card.inteface.entity.ActivityApply;  

/**
 * @notes:活动申请Dao接口
 *
 * @author zzh
 *
 * 2018-09-26 13:07:44		Email:azhangzhihengi@163.com
 */
@Component
public interface ActivityApplyDao {

    /**
     * @notes 保存活动申请
     * @param activityApply
     */
    public void saveActivityApply(ActivityApply activityApply) throws Exception;
}
