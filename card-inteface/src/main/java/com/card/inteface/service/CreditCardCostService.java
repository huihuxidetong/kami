package com.card.inteface.service;

import com.card.inteface.entity.CreditCardCost;

import java.util.List;

/**
 * @notes:信用卡费用Service接口
 *
 * @author zzh
 *
 * 2018-09-26 13:05:40		Email:azhangzhihengi@163.com
 */
public interface CreditCardCostService  {

    /**
     * @notes 通过信用卡id查询费用信息
     * @param creditCardId
     * @return
     */
    public List<CreditCardCost> findCreditCardCostByCreditCardId(Integer creditCardId) throws Exception;

}
