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
	 * ����û�����
	 */
	@Column(name = "password", length = 50, nullable = false)
	public String getPassword()
	{
		return this.password;
	}

	/**
	 * ����û���
	 */
	@Column(name = "LOGIN_ID", length = 20, nullable = false)
	public String getLoginId()
	{
		return this.loginId;
	}

	/**
	 * ��ȡ�û�������
	 */
	@Column(name = "DISPLAY_NAME", length = 100, nullable = false)
	public String getDisplayName()
	{
		return displayName;
	}

	/**
	 * �����û�������
	 */
	public void setDisplayName(String displayName)
	{
		this.displayName = displayName;
	}

	/**
	 * ��ȡ�û�ְ��
	 */
	@Column(name = "DUTY", length = 100, nullable = true)
	public String getDuty()
	{
		return duty;
	}

	/**
	 * �����û�ְ��
	 */
	public void setDuty(String duty)
	{
		this.duty = duty;
	}

	/**
	 * ��ȡ�б�ҳ��ÿҳ��ʾ��
	 */
	@Column(name = "DISPLAY_NUM", length = 3, nullable = true)
	public Integer getDisplayNum()
	{
		return displayNum;
	}

	/**
	 * �����б�ҳ��ÿҳ��ʾ��
	 */
	public void setDisplayNum(Integer displayNum)
	{
		this.displayNum = displayNum;
	}

	/**
	 * ��ȡҳ��CSS����
	 */
	@Column(name = "CSS_ID", length = 50, nullable = true)
	public String getCssId()
	{
		return cssId;
	}

	/**
	 * ����ҳ��CSS����
	 */
	public void setCssId(String cssId)
	{
		this.cssId = cssId;
	}

	/**
	 * ��ȡ�û��绰
	 */
	@Column(name = "TEL", length = 50, nullable = true)
	public String getTel()
	{
		return tel;
	}

	/**
	 * �����û��绰
	 */
	public void setTel(String tel)
	{
		this.tel = tel;
	}

	/**
	 * ��ȡ�û��ֻ���
	 */
	@Column(name = "MOBILE", length = 11, nullable = true)
	public String getMobile()
	{
		return mobile;
	}

	/**
	 * �����û��ֻ���
	 */
	public void setMobile(String mobile)
	{
		this.mobile = mobile;
	}

	/**
	 * ��ȡ��������
	 */
	@Column(name = "EMAIL", length = 100, nullable = true)
	public String getEmail()
	{
		return email;
	}

	/**
	 * ���õ�������
	 */
	public void setEmail(String email)
	{
		this.email = email;
	}

	/**
	 * ��ȡͬ������
	 */
	@Column(name = "SORT_SQ", length = 10, nullable = true)
	public Integer getSortSq()
	{
		return sortSq;
	}

	/**
	 * ����ͬ������
	 */
	public void setSortSq(Integer sortSq)
	{
		this.sortSq = sortSq;
	}

	/**
	 * ��ȡ��¼ʱ��
	 */
	@Transient
	public Date getLogTime()
	{
		return logTime;
	}

	/**
	 * ���õ�¼ʱ��
	 */
	public void setLogTime(Date logTime)
	{
		this.logTime = logTime;
	}

	/**
	 * ��ȡɾ��״̬
	 */
	@Column(name = "DEL_FLAG", length = 1, nullable = false)
	public Integer getDelFlag()
	{
		return delFlag;
	}

	/**
	 * ����ɾ��״̬
	 */
	public void setDelFlag(Integer delFlag)
	{
		this.delFlag = delFlag;
	}

	/**
	 * �����û�����
	 */
	public void setPassword(String password)
	{
		this.password = password;
	}

	/**
	 * �����û���
	 */
	public void setLoginId(String loginId)
	{
		this.loginId = loginId;
	}

	/** ��ȡ�Ƿ��ǳ����û��ֶ� */
	@Transient
	public Boolean getIsSuperAdmin()
	{
		return isSuperAdmin;
	}

	/** �����Ƿ��ǳ����û��ֶ� */
	public void setIsSuperAdmin(Boolean isSuperAdmin)
	{
		this.isSuperAdmin = isSuperAdmin;
	}
}
