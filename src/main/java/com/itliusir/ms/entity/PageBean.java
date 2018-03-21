package com.itliusir.ms.entity;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public class PageBean implements Pageable {
	/**
	 * 当前页
	 */
	private Integer pagenumber = 1;

	/**
	 * 当前页面条数
	 */
	private Integer pagesize = 2;

	/**
	 * 排序条件
	 */
	private Sort sort;

	public PageBean(Integer pagenumber, Integer pagesize, Sort sort) {
		super();
		this.pagenumber = pagenumber;
		this.pagesize = pagesize;
		this.sort = sort;
	}

	public void setSort(Sort sort) {
		this.sort = sort;
	}

	/**
	 * 当前页面
	 */
	@Override
	public int getPageNumber() {
		return getPagenumber();
	}

	/**
	 * 每一页显示的条数
	 */
	@Override
	public int getPageSize() {
		return getPagesize();
	}

	/**
	 * 第二页所需要增加的数量
	 */
	@Override
	public int getOffset() {
		return (getPagenumber() - 1) * getPagesize();
	}

	@Override
	public Sort getSort() {
		return sort;
	}

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

	@Override
	public Pageable next() {
		return null;
	}

	@Override
	public Pageable previousOrFirst() {
		return null;
	}

	@Override
	public Pageable first() {
		return null;
	}

	@Override
	public boolean hasPrevious() {
		return false;
	}
}
