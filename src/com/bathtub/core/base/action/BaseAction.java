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
package com.bathtub.core.base.action;

import com.opensymphony.xwork2.ActionSupport;

public class BaseAction extends ActionSupport
{

	private static final long serialVersionUID = -8278362090314253360L;

	protected static final String RELOAD = "reload";

	protected static final String VIEW = "view";

	protected static final String EDIT = "edit";

	protected static final String LIST = "list";

	protected static final String JSON_ERROR = "Json Converted Error";
}
