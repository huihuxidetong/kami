package com.card.api.service.impl;

import com.card.api.common.exception.BusinessException;
import com.card.api.service.ActivityService;
import com.card.api.utils.JedisUtil;
import com.card.api.vo.params.ActivityCreditCardListParam;
import com.card.api.vo.params.ActivityDetailParam;
import com.card.api.vo.params.ActivityListParam;
import com.card.api.vo.params.JoinActivityParam;
import com.card.api.vo.responses.ActivityCreditCardListResponse;
import com.card.api.vo.responses.ActivityDetailResponse;
import com.card.api.vo.responses.ActivityListResponse;
import com.card.api.vo.responses.JoinActivityResponse;
import com.card.api.vo.vo.ActivityIndexVo;
import com.card.inteface.entity.ActivityApply;
import com.card.inteface.entity.User;
import com.card.inteface.service.ActivityApplyService;
import com.card.inteface.service.UserService;
import com.card.inteface.utils.PageBean;
import com.card.inteface.vo.ActivityCreditCardVo;
import com.card.inteface.vo.ActivityDetailVo;
import com.card.inteface.vo.ActivityVo;
import com.card.inteface.vo.QueryActivityVo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @notes:用户信息Service实现类
 *
 * @author zzh
 *
 * 2018-08-21 14:56:12		Email:azhangzhihengi@163.com
 */
@Service
public class ActivityServiceImpl implements ActivityService {

	@Autowired
	private com.card.inteface.service.ActivityService activityService;

	@Autowired
	private UserService userService;

	@Autowired
	private ActivityApplyService activityApplyService;

	/**
	 * @notes 查询列表
	 * @param activityListParam
	 * @return
	 */
	public ActivityListResponse findActivityList(ActivityListParam activityListParam) throws Exception {
		ActivityListResponse activityListResponse = new ActivityListResponse();
		List<ActivityVo> activityIndexVoList = activityService.findActivityList();
		List<ActivityIndexVo> activityVoList4Bank = new ArrayList<>();
		List<ActivityIndexVo> activityVoList4Platform = new ArrayList<>();
		if(null != activityIndexVoList && activityIndexVoList.size()>0){
			for(ActivityVo activityVo : activityIndexVoList){
				Integer activity_type = activityVo.getActivityType();
				ActivityIndexVo activityIndexVo = new ActivityIndexVo();
				BeanUtils.copyProperties(activityVo,activityIndexVo);
				if(1== activity_type){
					activityVoList4Platform.add(activityIndexVo);
				}
				if(2== activity_type){
					activityVoList4Bank.add(activityIndexVo);
				}
			}
		}
		activityListResponse.setActivityVoList4Bank(activityVoList4Bank);
		activityListResponse.setActivityVoList4Platform(activityVoList4Platform);
		return activityListResponse;
	}

	/**
	 * @notes 查询活动详情
	 * @param activityDetailParam
	 * @return
	 */
	public ActivityDetailResponse findActivityDetail(ActivityDetailParam activityDetailParam) throws Exception {
		if (activityDetailParam == null) {
			throw new BusinessException(1, "获取信息失败");
		}
		if (org.apache.commons.lang3.StringUtils.isBlank(activityDetailParam.getBusinessKey())) {
			throw new BusinessException(1, "获取信息失败");
		}
		Map<String, Object> keyMap = JedisUtil.getKeyMap(activityDetailParam.getBusinessKey());
		if (keyMap == null || keyMap.isEmpty()) {
			throw new BusinessException(1, "获取信息失败");
		}
		//用户信息
		String openId = String.valueOf(keyMap.get("openId"));
		User user = userService.findUserByOpenId(openId);
		QueryActivityVo queryCreditCardVo = new QueryActivityVo();
		if(null == user){
			queryCreditCardVo.setUserId(-999);
		}else{
			queryCreditCardVo.setUserId(user.getId());
		}
		queryCreditCardVo.setId(activityDetailParam.getActivityId());
		ActivityDetailResponse activityDetailResponse = new ActivityDetailResponse();
		//活动图片
		List<String> activityUmageUrl = activityService.findActivityImageUrl(activityDetailParam.getActivityId());
		ActivityDetailVo activityDetailVo = activityService.findActivityDetailVo(queryCreditCardVo);
		BeanUtils.copyProperties(activityDetailVo,activityDetailResponse);
		activityDetailResponse.setActivityUmageUrl(activityUmageUrl);
		//相关信用卡
		List<ActivityCreditCardVo> activityCreditCardVoList = activityService.findActivityCreditCardVo(activityDetailParam.getActivityId());
		List<com.card.api.vo.vo.ActivityCreditCardVo> activityCreditCardVoList1 = new ArrayList<>();
		if(null != activityCreditCardVoList && activityCreditCardVoList.size()>0){
			for(ActivityCreditCardVo activityCreditCardVo :activityCreditCardVoList ){
				com.card.api.vo.vo.ActivityCreditCardVo activityCreditCardVo1 = new com.card.api.vo.vo.ActivityCreditCardVo();
				BeanUtils.copyProperties(activityCreditCardVo,activityCreditCardVo1);
				activityCreditCardVoList1.add(activityCreditCardVo1);
			}
			activityDetailResponse.setActivityCreditCardVoList(activityCreditCardVoList1);
		}

		return activityDetailResponse;
	}

	/**
	 * @nots 活动相关信用卡分页
	 * @param param
	 * @return
	 */
	public ActivityCreditCardListResponse findActivityCreditCardPage(ActivityCreditCardListParam param) throws Exception{
		ActivityCreditCardListResponse activityCreditCardListResponse = new ActivityCreditCardListResponse();
		ActivityCreditCardVo activityCreditCardVo = new ActivityCreditCardVo();
		activityCreditCardVo.setActivityId(param.getActivityId());
		PageBean<ActivityCreditCardVo> pageBean = new PageBean<ActivityCreditCardVo>();
		pageBean.setBean(activityCreditCardVo);
		pageBean.setPageNum(param.getPageNum());
		pageBean.setPageSize(param.getPageSize());
		PageBean<ActivityCreditCardVo> list = activityService.findActivityCreditCardPage(pageBean);
		activityCreditCardListResponse.setCount(list.getTotal());
		activityCreditCardListResponse.setPage(list.getPageNum());
		activityCreditCardListResponse.setPageSize(list.getPageSize());
		activityCreditCardListResponse.setTotalPage(list.getPages());
		List<ActivityCreditCardVo> activityCreditCardVoList = list.getDatas();
		List<com.card.api.vo.vo.ActivityCreditCardVo> activityCreditCardVoList1 = new ArrayList<>();
		if(null != activityCreditCardVoList && activityCreditCardVoList.size()>0){
			if(null != activityCreditCardVoList && activityCreditCardVoList.size()>0){
				for(ActivityCreditCardVo activityCreditCardVo1 :activityCreditCardVoList ){
					com.card.api.vo.vo.ActivityCreditCardVo activityCreditCardVo2 = new com.card.api.vo.vo.ActivityCreditCardVo();
					BeanUtils.copyProperties(activityCreditCardVo1,activityCreditCardVo2);
					activityCreditCardVoList1.add(activityCreditCardVo2);
				}
				activityCreditCardListResponse.setActivityCreditCardVoList(activityCreditCardVoList1);
			}

		}
		return activityCreditCardListResponse;
	}

	/**
	 * @ntoes 参与活动
	 * @param param
	 * @return
	 */
	public JoinActivityResponse joinActivity(JoinActivityParam param) throws Exception {
		JoinActivityResponse joinActivityResponse = new JoinActivityResponse();
		if (param == null) {
			throw new BusinessException(1, "获取信息失败");
		}
		if (StringUtils.isBlank(param.getBusinessKey())) {
			throw new BusinessException(1, "业务key不能为空");
		}
		Map<String, Object> keyMap = JedisUtil.getKeyMap(param.getBusinessKey());
		if (keyMap == null || keyMap.isEmpty()) {
			throw new BusinessException(1, "获取用户信息失败");
		}
		User user = userService.findUserByOpenId(keyMap.get("openId").toString());
		if(null == user){
			throw new BusinessException(1, "未查询到用户信息");
		}

		ActivityApply activityApply = new ActivityApply();
		activityApply.setActivityId(param.getActivityId());
		activityApply.setJoinUserName(param.getJoinUserName());
		activityApply.setJoinUserMobile(param.getJoinUserMobile());
		activityApply.setJoinUserCreditCard(param.getJoinUserCreditCard());
		activityApply.setCreateUserId(user.getId());
		activityApply.setCreateUserName(user.getNickName());
		activityApply.setCreateTime(new Date());
		activityApplyService.saveActivityApply(activityApply);
		return joinActivityResponse;
	}

}
