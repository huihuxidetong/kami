package com.card.inteface.dao;

import com.card.inteface.vo.CreditCardConcernVo;
import org.springframework.stereotype.Component;

import com.card.inteface.entity.CreditCardConcern;

import java.util.List;

/**
 * @notes:信用卡关注Dao接口
 *
 * @author zzh
 *
 * 2018-09-26 13:07:44		Email:azhangzhihengi@163.com
 */
@Component
public interface CreditCardConcernDao {

    /**
     * @notes 查询关注者头像
     * @param creditCardId
     * @return
     */
    public List<String> findCreditCardConcernPortraitLimit(Integer creditCardId) throws Exception;

    /**
     * @notes 查询关注者统计
     * @param creditCardId
     * @return
     */
    public Integer findCreditCardConcernCount(Integer creditCardId) throws Exception;

    /**
     * @ntoes 查询关注者列表
     * @param creditCardId
     * @return
     */
    public List<CreditCardConcernVo> findCreditCardConcernPage(Integer creditCardId) throws Exception;


}
