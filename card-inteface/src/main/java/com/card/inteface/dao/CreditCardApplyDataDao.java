package com.card.inteface.dao;

import org.springframework.stereotype.Component;

import com.card.inteface.entity.CreditCardApplyData;  

/**
 * @notes:信用卡数据Dao接口
 *
 * @author zzh
 *
 * 2018-10-12 11:20:54		Email:azhangzhihengi@163.com
 */
@Component
public interface CreditCardApplyDataDao {

    /**
     * @ntoes 信用卡申请
     * @param creditCardApplyData
     */
    public void addCreditCardApplyData(CreditCardApplyData creditCardApplyData) throws Exception;

    /**
     * @notes 查询
     * @param creditCardApplyData
     * @return
     */
    public CreditCardApplyData findCreditCardApplyData(CreditCardApplyData creditCardApplyData) throws Exception;
}
