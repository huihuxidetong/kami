package com.card.dao.impl;

import com.card.dao.DaoSupport;
import com.card.inteface.vo.BannerVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.card.inteface.entity.Banner;  
import com.card.inteface.dao.BannerDao;

import java.util.List;

/**
 * @notes:首页bannerDao实现类
 *
 * @author zzh
 *
 * 2018-09-26 13:07:44		Email:azhangzhihengi@163.com
 */
@Repository
public class BannerDaoImpl  implements BannerDao {

    Logger logger = LoggerFactory.getLogger(BannerDaoImpl.class);

    @Autowired
    private DaoSupport daoSupport;

    /**
     * @notes 查询首页banner信息
     * @return
     */
    public List<BannerVo> findIndexBannerVo() throws Exception {
        logger.info("==================首页进入dao");
        return (List<BannerVo>) daoSupport.findForList("BannerMapper.findIndexBannerVo",null);
    }
}