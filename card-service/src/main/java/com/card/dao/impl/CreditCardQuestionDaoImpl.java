package com.card.dao.impl;

import com.card.dao.DaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.card.inteface.entity.CreditCardQuestion;  
import com.card.inteface.dao.CreditCardQuestionDao;

import java.util.List;

/**
 * @notes:信用卡问题Dao实现类
 *
 * @author zzh
 *
 * 2018-09-26 13:07:44		Email:azhangzhihengi@163.com
 */
@Repository
public class CreditCardQuestionDaoImpl  implements CreditCardQuestionDao {

    @Autowired
    private DaoSupport daoSupport;

    /**
     * @通过信用卡id查询常见问题
     * @param creditCardId
     * @return
     */
    public List<CreditCardQuestion> findcreditCardQuestionByCreditCardId(Integer creditCardId) throws Exception {
        return (List<CreditCardQuestion>) daoSupport.findForList("CreditCardQuestionMapper.findcreditCardQuestionByCreditCardId",creditCardId);
    }

}