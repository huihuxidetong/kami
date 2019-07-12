package com.card.inteface.service;

import com.card.inteface.entity.CreditCardQuestion;

import java.util.List;

/**
 * @notes:信用卡问题Service接口
 *
 * @author zzh
 *
 * 2018-09-26 13:05:40		Email:azhangzhihengi@163.com
 */
public interface CreditCardQuestionService  {

    /**
     * @通过信用卡id查询常见问题
     * @param creditCardId
     * @return
     */
    public List<CreditCardQuestion> findcreditCardQuestionByCreditCardId(Integer creditCardId) throws Exception;
}
