package com.card.api.service.impl;

import com.card.api.service.IndexService;
import com.card.api.utils.SiteConfig;
import com.card.api.vo.params.IndexParam;
import com.card.api.vo.responses.IndexResponse;
import com.card.api.vo.vo.ActivityIndexVo;
import com.card.api.vo.vo.BannerIndexVo;
import com.card.api.vo.vo.CreditCardInfoIndexVo;
import com.card.inteface.service.ActivityService;
import com.card.inteface.service.BannerService;
import com.card.inteface.service.CreditCardInfoService;
import com.card.inteface.vo.ActivityVo;
import com.card.inteface.vo.BannerVo;
import com.card.inteface.vo.CreditCardInfoVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @notes:用户信息Service实现类
 *
 * @author zzh
 *
 * 2018-08-21 14:56:12		Email:azhangzhihengi@163.com
 */
@Service
public class IndexServiceImpl implements IndexService {

    @Autowired
	private ActivityService activityService;

    @Autowired
	private BannerService bannerService;

    @Autowired
	private CreditCardInfoService creditCardInfoService;

    @Autowired
	private SiteConfig siteConfig;

	@Override
	/**
	 * @notes 查查询首页基本信息
	 * @param indexParam
	 * @return
	 */
	public IndexResponse findIndexInfo(IndexParam indexParam) throws Exception {
		IndexResponse indexResponse = new IndexResponse();
		List<BannerVo> bannerVoList = bannerService.findIndexBannerVo();
		List<ActivityVo> activityVoList = activityService.findIndexActivityVo();
        List<CreditCardInfoVo> creditCardInfoVoList = creditCardInfoService.findIndexCreditCardInfoVo();

        List<BannerIndexVo> bannerIndexVoList = new ArrayList<>();
        List<ActivityIndexVo> activityIndexVoList = new ArrayList<>();
        List<CreditCardInfoIndexVo> creditCardInfoIndexVoList = new ArrayList<>();
        if(null != bannerVoList){
        	for(BannerVo bannerVo : bannerVoList){
				BannerIndexVo bannerIndexVo = new BannerIndexVo();
				BeanUtils.copyProperties(bannerVo,bannerIndexVo);
				bannerIndexVoList.add(bannerIndexVo);
			}
		}
		if(null != activityVoList){
			for(ActivityVo activityVo : activityVoList){
				ActivityIndexVo activityIndexVo = new ActivityIndexVo();
				BeanUtils.copyProperties(activityVo,activityIndexVo);
				activityIndexVoList.add(activityIndexVo);
			}
		}
		if(null != creditCardInfoVoList){
			for(CreditCardInfoVo creditCardInfoVo : creditCardInfoVoList){
				CreditCardInfoIndexVo creditCardInfoIndexVo = new CreditCardInfoIndexVo();
				BeanUtils.copyProperties(creditCardInfoVo,creditCardInfoIndexVo);
				creditCardInfoIndexVoList.add(creditCardInfoIndexVo);
			}
		}
		indexResponse.setBannerList(bannerIndexVoList);
		indexResponse.setActivityVoList(activityIndexVoList);
		indexResponse.setCreditCardInfoVoList(creditCardInfoIndexVoList);
		indexResponse.setIsCanClickDetail(siteConfig.isCanClickDetail);
		return indexResponse;
	}
}
