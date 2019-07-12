package com.card.inteface.dao;

import com.card.inteface.vo.BannerVo;
import org.springframework.stereotype.Component;

import com.card.inteface.entity.Banner;

import java.util.List;

/**
 * @notes:首页bannerDao接口
 *
 * @author zzh
 *
 * 2018-09-26 13:07:44		Email:azhangzhihengi@163.com
 */
@Component
public interface BannerDao {

    /**
     * @notes 查询首页banner信息
     * @return
     */
    public List<BannerVo> findIndexBannerVo() throws Exception;
}
