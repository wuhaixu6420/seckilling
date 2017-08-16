package com.zq.fin.seckill.entity.xw;

import java.io.Serializable;
import java.sql.Timestamp;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 
 * 描述：新闻
 * @author wuhaixu
 * @created 2017年3月23日 下午3:35:05
 * @since
 * @version 0.1		没有图片的设计模式
 */
public class Journalism implements Serializable{

	private static final long serialVersionUID = 1L;
	
	/**
	 * 新闻id
	 */
	private Long id;
	
	/**
	 * 标题
	 */
	private String title;
	
	/**
	 * 新闻内容
	 */
	private String journalismContent;
	
	/**
	 * 新闻分类
	 */
	private String classification;
	
	/**
	 * 创建者Id
	 */
	private Long createId;
	
	/**
	 * 创建时间
	 */
	private Timestamp createTime;
	
	/**
	 * 更新者Id
	 */
	private Long updateId;
	
	/**
	 * 更新时间
	 */
	private Timestamp updateTime;

	/**
	 * 新闻id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * 新闻id
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * 标题
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * 标题
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * 新闻内容
	 */
	public String getJournalismContent() {
		return journalismContent;
	}

	/**
	 * 新闻内容
	 */
	public void setJournalismContent(String journalismContent) {
		this.journalismContent = journalismContent;
	}

	/**
	 * 新闻分类
	 */
	public String getClassification() {
		return classification;
	}

	/**
	 * 新闻分类
	 */
	public void setClassification(String classification) {
		this.classification = classification;
	}

	/**
	 * 创建者Id
	 */
	public Long getCreateId() {
		return createId;
	}

	/**
	 * 创建者Id
	 */
	public void setCreateId(Long createId) {
		this.createId = createId;
	}

	/**
	 * 创建时间
	 */
	public Timestamp getCreateTime() {
		return createTime;
	}

	/**
	 * 创建时间
	 */
	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	/**
	 * 更新者Id
	 */
	public Long getUpdateId() {
		return updateId;
	}

	/**
	 * 更新者Id
	 */
	public void setUpdateId(Long updateId) {
		this.updateId = updateId;
	}

	/**
	 * 更新时间
	 */
	public Timestamp getUpdateTime() {
		return updateTime;
	}

	/**
	 * 更新时间
	 */
	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
