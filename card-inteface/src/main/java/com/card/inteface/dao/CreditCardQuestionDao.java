package com.card.inteface.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DaoSupport;
import org.springframework.stereotype.Component;

import com.card.inteface.entity.CreditCardQuestion;

import java.util.List;

/**
 * @notes:信用卡问题Dao接口
 *
 * @author zzh
 *
 * 2018-09-26 13:07:44		Email:azhangzhihengi@163.com
 */
@Component
public interface CreditCardQuestionDao {

    /**
     * @通过信用卡id查询常见问题
     * @param creditCardId
     * @return
     */
    public List<CreditCardQuestion> findcreditCardQuestionByCreditCardId(Integer creditCardId) throws Exception;
}
