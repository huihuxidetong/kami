package com.card.inteface.service;

import com.card.inteface.entity.BankCreditCard;
import com.card.inteface.vo.CreditCardInfoQueryVo;

import java.util.List;

/**
 * @notes:银行关联信用卡Service接口
 *
 * @author zzh
 *
 * 2018-09-26 13:05:40		Email:azhangzhihengi@163.com
 */
public interface BankCreditCardService  {

    /**
     * @ntoes 搜索信用卡
     * @param bankCreditCard
     * @return
     */
    public List<CreditCardInfoQueryVo> queryCreditCardList(BankCreditCard bankCreditCard) throws Exception;
}
