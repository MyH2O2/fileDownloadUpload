package com.imooc.download.model;

public class ResultBean<T> {
	
	private T data;
	
	public ResultBean() {
		
	}
	
	public ResultBean(T data) {
		this.data = data;
	}

	/**
	 * @return the data
	 */
	public T getData() {
		return data;
	}

	/**
	 * @param data the data to set
	 */
	public void setData(T data) {
		this.data = data;
	}
}
