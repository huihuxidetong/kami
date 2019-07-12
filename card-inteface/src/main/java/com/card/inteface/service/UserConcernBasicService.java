package com.card.inteface.service;

import com.card.inteface.entity.UserConcernBasic;
import com.card.inteface.utils.PageBean;
import com.card.inteface.vo.ActivityIndexVo;
import com.card.inteface.vo.CreditCardInfoHotVo;

/**
 * @notes:用户关注基本信息Service接口
 *
 * @author zzh
 *
 * 2018-09-26 13:05:40		Email:azhangzhihengi@163.com
 */
public interface UserConcernBasicService  {

    /**
     * @notes 查询关注的信用卡列表分页
     * @param pageBean
     * @return
     */
    public PageBean<CreditCardInfoHotVo> findUserConcernBasic4CreditCardPage(PageBean<UserConcernBasic> pageBean) throws Exception;

    /**
     * @notes 查询关注的信用卡列表分页
     * @param pageBean
     * @return
     */
    public PageBean<ActivityIndexVo> findUserConcernBasic4ActivityPage(PageBean<UserConcernBasic> pageBean) throws Exception;

    /**
     * @notes 保存用户关注信息
     * @param userConcernBasic
     */
    public void saveUserConcernBasic(UserConcernBasic userConcernBasic) throws Exception;

    /**
     * @notes 用户取消关注信息
     * @param userConcernBasic
     */
    public void updateUserConcernBasic(UserConcernBasic userConcernBasic) throws Exception;

    /**
     * @notes 查询关注信息
     * @param userConcernBasic
     * @return
     */
    public UserConcernBasic findUserConcernBasicByEntity(UserConcernBasic userConcernBasic) throws Exception;

}
