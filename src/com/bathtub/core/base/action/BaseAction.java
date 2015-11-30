/**************************************************************************/
/*                                                                        */
/* Copyright (c) 2012-2013 Jiangsu Hongxin System Integration Co., Ltd.   */
/*                                                                        */
/* PROPRIETARY RIGHTS of Jiangsu Hongxin are involved in the 　　　　　　 */
/* subject matter of this material.  All manufacturing, reproduction, use,*/
/* and sales rights pertaining to this subject matter are governed by the */
/* license agreement. The recipient of this software implicitly accepts   */
/* the terms of the license.                                              */
/* 本软件文档资料是江苏鸿信公司的资产,任何人士阅读和使用本资料必须获得    */
/* 相应的书面授权,承担保密责任和接受相应的法律约束.                       */
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
