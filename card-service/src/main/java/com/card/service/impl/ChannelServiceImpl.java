package com.card.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.card.inteface.service.ChannelService;  
import com.card.inteface.entity.Channel;  
import com.card.inteface.dao.ChannelDao;

/**
 * @notes:渠道Service实现类
 *
 * @author zzh
 *
 * 2018-09-26 13:05:40		Email:azhangzhihengi@163.com
 */
@Service
public class ChannelServiceImpl implements ChannelService {

	@Autowired
	private ChannelDao channelDao;

}
