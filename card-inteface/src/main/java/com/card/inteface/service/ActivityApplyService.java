package com.card.inteface.service;

import com.card.inteface.entity.ActivityApply;  

/**
 * @notes:活动申请Service接口
 *
 * @author zzh
 *
 * 2018-09-26 13:05:40		Email:azhangzhihengi@163.com
 */
public interface ActivityApplyService  {

    /**
     * @notes 保存活动申请
     * @param activityApply
     */
     public void saveActivityApply(ActivityApply activityApply) throws Exception;

}
