package com.card.inteface.service;

import com.card.inteface.utils.PageBean;
import com.card.inteface.vo.CreditCardConcernVo;

import java.util.List;

/**
 * @notes:信用卡关注Service接口
 *
 * @author zzh
 *
 * 2018-09-26 13:05:40		Email:azhangzhihengi@163.com
 */
public interface CreditCardConcernService  {

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
     * @notse 查询关注列表
     * @param pageBean
     * @return
     */
    public PageBean<CreditCardConcernVo> findCreditCardConcernPage(PageBean<CreditCardConcernVo> pageBean) throws Exception;
}
