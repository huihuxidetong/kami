package com.card.inteface.dao;

import com.card.inteface.vo.*;
import org.springframework.stereotype.Component;

import com.card.inteface.entity.Activity;

import java.util.List;

/**
 * @notes:活动Dao接口
 *
 * @author zzh
 *
 * 2018-09-26 13:07:43		Email:azhangzhihengi@163.com
 */
@Component
public interface ActivityDao {

    /**
     * @ntoes 查询首页活动信息信息
     * @return
     */
    public List<ActivityVo> findIndexActivityVo() throws Exception;

    /**
     * @notes 查询信用卡相关活动
     * @param creditCardId
     * @return
     */
    public List<CreditDetailActivityVo> findActivityVoListByCreditCardId(Integer creditCardId) throws Exception;

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
     * @param queryActivityVo
     * @return
     */
    public ActivityDetailVo findActivityDetailVo(QueryActivityVo queryActivityVo) throws Exception;

    /**
     * @param activityId
     * @return
     * @ntoes 相关信用卡
     */
    public List<ActivityCreditCardVo> findActivityCreditCardVo(Integer activityId) throws Exception;

    /**
     * @param activityId
     * @return
     * @ntoes 相关信用卡
     */
    public List<ActivityCreditCardVo> findActivityCreditCardPage(Integer activityId) throws Exception;

    /**
     * @notse 查询信用卡
     * @param id
     * @return
     */
    public Activity findActivity(Integer id) throws Exception;

    /**
     * @ntoes 更新活动
     * @param activity
     */
    public void updateActivity(Activity activity) throws Exception;
}
