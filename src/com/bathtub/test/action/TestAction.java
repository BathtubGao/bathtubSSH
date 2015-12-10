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
package com.bathtub.test.action;

import org.springframework.beans.factory.annotation.Autowired;

import com.bathtub.core.base.action.BaseAction;
import com.bathtub.test.entity.User;
import com.bathtub.test.service.UserService;

public class TestAction extends BaseAction 
{
	@Autowired  
    private UserService userService;   
      
    private String username;    
    private String password;  

	
	public String execute() throws Exception   
    {    
		System.out.println("username:"+username+" password:"+password);  
        if(userService.login(username,password))    
        {  
        	this.setRequestAttribute("message", "登陆成功！");
            return SUCCESS;    
        }  
        else
        {
        	return ERROR;	
        }
            
    }

	public String save()
	{
		User user = new User();
		user.setUsername(username);
		user.setPwd(password);
		userService.save(user);
		this.setRequestAttribute("message", "保存成功！");
		return SUCCESS;
	}

	public String getUsername()
	{
		return username;
	}


	public void setUsername(String username)
	{
		this.username = username;
	}


	public String getPassword()
	{
		return password;
	}


	public void setPassword(String password)
	{
		this.password = password;
	}   
	
	
}
