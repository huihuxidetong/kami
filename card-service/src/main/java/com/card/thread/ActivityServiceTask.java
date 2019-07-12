package com.card.thread;

import com.card.dao.DaoSupport;
import com.card.inteface.entity.Activity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * 创建人：zzh
 * 创建时间：2018-09-09
 * @version
 */
@Service("startCardServiceTask")
public class ActivityServiceTask {

	private static Logger logger = LoggerFactory.getLogger(ActivityServiceTask.class);

	@Resource(name = "daoSupport")
	private DaoSupport daoSupport;

	@Transactional(rollbackFor = Exception.class)
	public void updateActivityWait2Ing() {
		try {
			List<Activity> list = (List<Activity>) daoSupport.findForList("ActivityMapper.findWaitingActivity",null);
			if(null != list){
				for(Activity activity : list){
					activity.setStatus(2);
					daoSupport.update("ActivityMapper.updateById",activity);
				}
			}
		} catch (Exception e) {
			logger.error("==============更新待开始到进行中异常"+e.getMessage(),e);
		}
	}
	public void updateActivityIng2End() {
		try {
			List<Activity> list = (List<Activity>) daoSupport.findForList("ActivityMapper.findIngActivity",null);
			if(null != list){
				for(Activity activity : list){
					activity.setStatus(3);
					daoSupport.update("ActivityMapper.updateById",activity);
				}
			}
		} catch (Exception e) {
			logger.error("============================更新待进行到结束中异常"+e.getMessage(),e);
		}
	}
}