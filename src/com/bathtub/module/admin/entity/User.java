package com.bathtub.module.admin.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.bathtub.core.base.entity.BaseModel;

@Table(name = "USERS")
@Entity
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class User extends BaseModel
{

	private static final long serialVersionUID = 8568426839204401086L;

	private String password;

	private String displayName;

	private String duty;

	private Integer displayNum;

	private String cssId;

	private String tel;

	private String mobile;

	private String email;

	private Integer sortSq;

	private Date logTime;

	private String loginId;

	private Integer delFlag;

	private Boolean isSuperAdmin = false;

	/**
	 * 获得用户密码
	 */
	@Column(name = "password", length = 50, nullable = false)
	public String getPassword()
	{
		return this.password;
	}

	/**
	 * 获得用户名
	 */
	@Column(name = "LOGIN_ID", length = 20, nullable = false)
	public String getLoginId()
	{
		return this.loginId;
	}

	/**
	 * 获取用户的姓名
	 */
	@Column(name = "DISPLAY_NAME", length = 100, nullable = false)
	public String getDisplayName()
	{
		return displayName;
	}

	/**
	 * 设置用户的姓名
	 */
	public void setDisplayName(String displayName)
	{
		this.displayName = displayName;
	}

	/**
	 * 获取用户职务
	 */
	@Column(name = "DUTY", length = 100, nullable = true)
	public String getDuty()
	{
		return duty;
	}

	/**
	 * 设置用户职务
	 */
	public void setDuty(String duty)
	{
		this.duty = duty;
	}

	/**
	 * 获取列表页面每页显示数
	 */
	@Column(name = "DISPLAY_NUM", length = 3, nullable = true)
	public Integer getDisplayNum()
	{
		return displayNum;
	}

	/**
	 * 设置列表页面每页显示数
	 */
	public void setDisplayNum(Integer displayNum)
	{
		this.displayNum = displayNum;
	}

	/**
	 * 获取页面CSS代码
	 */
	@Column(name = "CSS_ID", length = 50, nullable = true)
	public String getCssId()
	{
		return cssId;
	}

	/**
	 * 设置页面CSS代码
	 */
	public void setCssId(String cssId)
	{
		this.cssId = cssId;
	}

	/**
	 * 获取用户电话
	 */
	@Column(name = "TEL", length = 50, nullable = true)
	public String getTel()
	{
		return tel;
	}

	/**
	 * 设置用户电话
	 */
	public void setTel(String tel)
	{
		this.tel = tel;
	}

	/**
	 * 获取用户手机号
	 */
	@Column(name = "MOBILE", length = 11, nullable = true)
	public String getMobile()
	{
		return mobile;
	}

	/**
	 * 设置用户手机号
	 */
	public void setMobile(String mobile)
	{
		this.mobile = mobile;
	}

	/**
	 * 获取电子邮箱
	 */
	@Column(name = "EMAIL", length = 100, nullable = true)
	public String getEmail()
	{
		return email;
	}

	/**
	 * 设置电子邮箱
	 */
	public void setEmail(String email)
	{
		this.email = email;
	}

	/**
	 * 获取同级排序
	 */
	@Column(name = "SORT_SQ", length = 10, nullable = true)
	public Integer getSortSq()
	{
		return sortSq;
	}

	/**
	 * 设置同级排序
	 */
	public void setSortSq(Integer sortSq)
	{
		this.sortSq = sortSq;
	}

	/**
	 * 获取登录时间
	 */
	@Transient
	public Date getLogTime()
	{
		return logTime;
	}

	/**
	 * 设置登录时间
	 */
	public void setLogTime(Date logTime)
	{
		this.logTime = logTime;
	}

	/**
	 * 获取删除状态
	 */
	@Column(name = "DEL_FLAG", length = 1, nullable = false)
	public Integer getDelFlag()
	{
		return delFlag;
	}

	/**
	 * 设置删除状态
	 */
	public void setDelFlag(Integer delFlag)
	{
		this.delFlag = delFlag;
	}

	/**
	 * 设置用户密码
	 */
	public void setPassword(String password)
	{
		this.password = password;
	}

	/**
	 * 设置用户名
	 */
	public void setLoginId(String loginId)
	{
		this.loginId = loginId;
	}

	/** 获取是否是超级用户字段 */
	@Transient
	public Boolean getIsSuperAdmin()
	{
		return isSuperAdmin;
	}

	/** 设置是否是超级用户字段 */
	public void setIsSuperAdmin(Boolean isSuperAdmin)
	{
		this.isSuperAdmin = isSuperAdmin;
	}
}
