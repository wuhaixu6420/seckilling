package com.zq.fin.seckill.common;

public interface BaseGroup {
	
	/** 项目创建组验证*/
	public static interface GroupCreate{};
	/** 项目删除组验证*/
	public static interface GroupDelete{}
	/** 项目更新组验证*/
	public static interface GroupUpdate{};
	/** 项目审核组验证*/
	public static interface GroupVerify{};
	/** 项目搜索组验证*/
	public static interface GroupSearch{};
	/** 图片验证*/
	public static interface GroupImage{};
	/** AJAX验证*/
	public static interface GroupAjax{};
	/** 证券登录*/
	public static interface GroupGlscLogin{};

}
