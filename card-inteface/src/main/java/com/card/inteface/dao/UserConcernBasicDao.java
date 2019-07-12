package com.card.inteface.dao;

import com.card.inteface.vo.ActivityIndexVo;
import com.card.inteface.vo.CreditCardInfoHotVo;
import org.springframework.stereotype.Component;

import com.card.inteface.entity.UserConcernBasic;

import java.util.List;

/**
 * @notes:用户关注基本信息Dao接口
 *
 * @author zzh
 *
 * 2018-09-26 13:07:44		Email:azhangzhihengi@163.com
 */
@Component
public interface UserConcernBasicDao {

    /**
     * @notes 查询用户关注信用卡
     * @param userConcernBasic
     * @return
     */
    public List<CreditCardInfoHotVo> findUserConcernBasic4CreditCardPage(UserConcernBasic userConcernBasic) throws Exception;

    /**
     * @notes 查询用户关注信用卡
     * @param userConcernBasic
     * @return
     */
    public List<ActivityIndexVo> findUserConcernBasic4ActivityPage(UserConcernBasic userConcernBasic) throws Exception;

    /**
     * @notes 保存用户关注信息
     * @param userConcernBasic
     */
    public void saveUserConcernBasic(UserConcernBasic userConcernBasic) throws Exception;

    /**
     * @notes 查询实体
     * @param userConcernBasic
     * @return
     */
    public UserConcernBasic findUserConcernBasicByEntity(UserConcernBasic userConcernBasic) throws Exception;

    /**
     * @ntoes 更新关注
     * @param userConcernBasic
     */
    public Object updateUserConcernBasic(UserConcernBasic userConcernBasic) throws Exception;
}
