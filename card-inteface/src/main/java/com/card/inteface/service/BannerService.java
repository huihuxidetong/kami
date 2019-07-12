package com.card.inteface.service;

import com.card.inteface.entity.Banner;
import com.card.inteface.vo.BannerVo;

import java.util.List;

/**
 * @notes:首页bannerService接口
 *
 * @author zzh
 *
 * 2018-09-26 13:05:40		Email:azhangzhihengi@163.com
 */
public interface BannerService  {

    /**
     * @notes 查询首页banner信息
     * @return
     */
    public List<BannerVo> findIndexBannerVo() throws Exception;
}
