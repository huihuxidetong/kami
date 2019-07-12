package com.card.dao.impl;

import com.card.dao.DaoSupport;
import com.card.inteface.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.card.inteface.dao.ActivityDao;  
import com.card.inteface.entity.Activity;

import java.util.List;

/**
 * @notes:活动Dao实现类
 *
 * @author zzh
 *
 * 2018-09-26 13:07:43		Email:azhangzhihengi@163.com
 */
@Repository
public class ActivityDaoImpl  implements ActivityDao {

    @Autowired
    private DaoSupport daoSupport;

    /**
     * @ntoes 查询首页活动信息信息
     * @return
     */
    public List<ActivityVo> findIndexActivityVo() throws Exception {
        return (List<ActivityVo>) daoSupport.findForList("ActivityMapper.findIndexActivityVo",null);
    }

    /**
     * @notes 查询信用卡相关活动
     * @param creditCardId
     * @return
     */
    public List<CreditDetailActivityVo> findActivityVoListByCreditCardId(Integer creditCardId) throws Exception {
        return (List<CreditDetailActivityVo>) daoSupport.findForList("ActivityMapper.findActivityVoListByCreditCardId",creditCardId);
    }

    /**
     * @ntos 查询活动列表
     * @return
     */
    public List<ActivityVo> findActivityList() throws Exception {
        return (List<ActivityVo>) daoSupport.findForList("ActivityMapper.findActivityList",null);
    }

    /**
     * @ntoes
     * @param activityId
     * @return
     */
    public List<String> findActivityImageUrl(Integer activityId) throws Exception {
        return (List<String>) daoSupport.findForList("ActivityFileMapper.findActivityImageUrl",activityId);
    }

    /**
     * @ntoes 查询活动详情
     * @param
     * @return
     */
    public ActivityDetailVo findActivityDetailVo(QueryActivityVo queryActivityVo) throws Exception {
        return (ActivityDetailVo) daoSupport.findForObject("ActivityMapper.findActivityDetailVo",queryActivityVo);
    }

    /**
     * @param activityId
     * @return
     * @ntoes 相关信用卡
     */
    public List<ActivityCreditCardVo> findActivityCreditCardVo(Integer activityId) throws Exception {
        return (List<ActivityCreditCardVo>) daoSupport.findForList("ActivityMapper.findActivityCreditCardVo",activityId);
    }

    /**
     * @param activityId
     * @return
     * @ntoes 相关信用卡
     */
    public List<ActivityCreditCardVo> findActivityCreditCardPage(Integer activityId) throws Exception{
        return (List<ActivityCreditCardVo>) daoSupport.findForList("ActivityMapper.findActivityCreditCardPage",activityId);
    }

    /**
     * @notse 查询信用卡
     * @param id
     * @return
     */
    public Activity findActivity(Integer id) throws Exception {
        return (Activity) daoSupport.findForObject("ActivityMapper.selectByPrimaryKey",id);
    }

    /**
     * @ntoes 更新活动
     * @param activity
     */
    public void updateActivity(Activity activity) throws Exception {
        daoSupport.update("ActivityMapper.updateActivityLookNumber",activity);
    }
}