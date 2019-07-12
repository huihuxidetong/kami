package com.card.api.vo.params;

import io.swagger.annotations.ApiModel;

/**
 * 首页信息
 */
@ApiModel(value = "首页信息", discriminator = "IndexParam", subTypes = {IndexParam.class})
public class IndexParam {

    private static final long serialVersionUID = 1L;

}
