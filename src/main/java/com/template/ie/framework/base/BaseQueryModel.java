package com.template.ie.framework.base;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@ApiModel("查询请求基类")
public class BaseQueryModel implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "当前页", hidden = false)
    private Integer pageNum;

    @ApiModelProperty(value = "每页的数量", hidden = false)
    private Integer pageSize;
}
