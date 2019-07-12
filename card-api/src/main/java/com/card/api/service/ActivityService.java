package com.card.api.service;

import com.card.api.common.exception.BusinessException;
import com.card.api.vo.params.*;
import com.card.api.vo.responses.*;

/**
 *
 * @author zzh
 *
 * 2018-08-21 14:56:12		Email:azhangzhihengi@163.com
 */
public interface ActivityService {

    /**
     * @notes 查询列表
     * @param activityListParam
     * @return
     */
    public ActivityListResponse findActivityList(ActivityListParam activityListParam) throws Exception;

    /**
     * @notes 查询活动详情
     * @param activityDetailParam
     * @return
     */
    public ActivityDetailResponse findActivityDetail(ActivityDetailParam activityDetailParam) throws Exception;

    /**
     * @nots 活动相关信用卡分页
     * @param param
     * @return
     */
    public ActivityCreditCardListResponse findActivityCreditCardPage(ActivityCreditCardListParam param) throws Exception;

    /**
     * @ntoes 参与活动
     * @param param
     * @return
     */
    public JoinActivityResponse joinActivity(JoinActivityParam param) throws Exception;

}
