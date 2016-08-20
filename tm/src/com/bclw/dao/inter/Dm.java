package com.bclw.dao.inter;

public interface Dm<T> {
	public boolean add(T t);//增加
	public boolean delete(T t);//删除
	public boolean update(T t);//修改
}
