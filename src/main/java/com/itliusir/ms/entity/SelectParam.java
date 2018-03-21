package com.itliusir.ms.entity;

import lombok.Data;

@Data
public class SelectParam {
    /**
	 * 参数键
	 */
	private String paramKey;
	/**
	 * 参数值
	 */
	private Object paramValue;
	
	/**
	 * 参数类型
	 */
	private ParamCondition condition;
	
}
