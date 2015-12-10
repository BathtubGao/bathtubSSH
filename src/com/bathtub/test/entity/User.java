/**************************************************************************/
/*                                                                        */
/* Copyright (c) 2012-2013 Jiangsu Hongxin System Integration Co., Ltd.   */
/*                                                                        */
/* PROPRIETARY RIGHTS of Jiangsu Hongxin are involved in the ������������ */
/* subject matter of this material.  All manufacturing, reproduction, use,*/
/* and sales rights pertaining to this subject matter are governed by the */
/* license agreement. The recipient of this software implicitly accepts   */
/* the terms of the license.                                              */
/* ������ĵ������ǽ��պ��Ź�˾���ʲ�,�κ���ʿ�Ķ���ʹ�ñ����ϱ�����    */
/* ��Ӧ��������Ȩ,�е��������κͽ�����Ӧ�ķ���Լ��.                       */
/*                                                                        */
/**************************************************************************/
package com.bathtub.test.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.bathtub.core.base.entity.BaseModel;

@SuppressWarnings("serial")
@Entity
@Table(name="T_TEST_USER")
public class User extends BaseModel
{

	private String username;

	private String pwd;

	@Column(name="PWD")
	public String getPwd()
	{
		return pwd;
	}

	public void setPwd(String pwd)
	{
		this.pwd = pwd;
	}

	@Column(name="USERNAME")
	public String getUsername()
	{
		return username;
	}

	public void setUsername(String username)
	{
		this.username = username;
	}

	
}
