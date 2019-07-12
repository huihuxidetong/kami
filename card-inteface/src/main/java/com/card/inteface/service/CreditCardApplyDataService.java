package com.card.inteface.service;

import com.card.inteface.entity.CreditCardApplyData;  

/**
 * @notes:信用卡数据Service接口
 *
 * @author zzh
 *
 * 2018-10-12 11:20:54		Email:azhangzhihengi@163.com
 */
public interface CreditCardApplyDataService  {

    /**
     * @ntoes 信用卡申请
     * @param creditCardApplyData
     */
    public void addCreditCardApplyData(CreditCardApplyData creditCardApplyData) throws Exception;
}
