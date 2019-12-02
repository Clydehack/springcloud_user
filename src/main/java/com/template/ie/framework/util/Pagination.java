package com.template.ie.framework.util;

import java.io.Serializable;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

/**
 * 公用分页实体类
 * 项目名称：mpims  
 * 创建人：xizhida 
 * 创建时间：2017年10月18日 上午10:13:38  
 */
public class Pagination implements Serializable, Pageable{

	private static final long serialVersionUID = 7833269951887222015L;
	/**
	 * 当前页
	 */
    private Integer pagenumber ;
    /**
     * 当前页面条数
     */
    private Integer pagesize;
    /**
     *  排序条件
     */
    private Sort sort;
    
	public Integer getPagenumber() {
		return pagenumber;
	}

	public void setPagenumber(Integer pagenumber) {
		this.pagenumber = pagenumber;
	}

	public Integer getPagesize() {
		return pagesize;
	}

	public void setPagesize(Integer pagesize) {
		this.pagesize = pagesize;
	}

	public void setSort(Sort sort) {
		this.sort = sort;
	}

	@Override
	public int getPageNumber() {
		// TODO Auto-generated method stub
		return getPagenumber();
	}

	@Override
	public int getPageSize() {
		// TODO Auto-generated method stub
		return getPagesize();
	}
	@Override
	public int getOffset() {
		// TODO Auto-generated method stub
		return (getPagenumber() - 1) * getPagesize();
	}
	@Override
	public Sort getSort() {
		// TODO Auto-generated method stub
		return  sort;
	}

	@Override
	public Pageable next() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Pageable previousOrFirst() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Pageable first() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public boolean hasPrevious() {
		// TODO Auto-generated method stub
		return false;
	}
}