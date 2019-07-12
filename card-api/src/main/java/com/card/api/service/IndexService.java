package com.card.api.service;

import com.card.api.vo.params.IndexParam;
import com.card.api.vo.params.SaveUserParam;
import com.card.api.vo.responses.IndexResponse;
import com.card.api.vo.responses.SaveUserResponse;

/**
 * @notes:用户信息Service接口
 *
 * @author zzh
 *
 * 2018-08-21 14:56:12		Email:azhangzhihengi@163.com
 */
public interface IndexService {

    /**
     * @notes 查查询首页基本信息
     * @param indexParam
     * @return
     */
    public IndexResponse findIndexInfo(IndexParam indexParam) throws Exception;
}
