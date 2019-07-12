package com.card.inteface.service;

import com.card.inteface.utils.PageBean;
import com.card.inteface.vo.*;

import java.util.List;

/**
 * @notes:活动Service接口
 *
 * @author zzh
 *
 * 2018-09-26 13:05:40		Email:azhangzhihengi@163.com
 */
public interface ActivityService  {

    /**
     * @ntoes 查询首页活动信息信息
     * @return
     */
    public List<ActivityVo> findIndexActivityVo() throws Exception;

    /**
     * @notes 查询信用卡相关活动
     * @param creditCardid
     * @return
     */
    public List<CreditDetailActivityVo> findActivityVoListByCreditCardId(Integer creditCardid) throws Exception;

    /**
     * @ntos 查询活动列表
     * @return
     */
    public List<ActivityVo> findActivityList() throws Exception;

    /**
     * @ntoes
     * @param activityId
     * @return
     */
    public List<String> findActivityImageUrl(Integer activityId) throws Exception;

    /**
     * @ntoes 查询活动详情
     * @param activityId
     * @return
     */
    public ActivityDetailVo findActivityDetailVo(QueryActivityVo queryActivityVo) throws Exception;

    /**
     * @ntoes 相关信用卡
     * @param activityId
     * @return
     */
    public List<ActivityCreditCardVo> findActivityCreditCardVo(Integer activityId) throws Exception;

    /**
     * @ntoe 查询活动相关信用卡列表
     * @param pageBean
     * @return
     */
    public PageBean<ActivityCreditCardVo> findActivityCreditCardPage(PageBean<ActivityCreditCardVo> pageBean) throws Exception;
}
