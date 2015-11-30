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
package com.bathtub.test.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bathtub.test.dao.UserDao;
import com.bathtub.test.entity.User;

@Repository(value = "userDao")  
public class UserDaoImpl implements UserDao   
{  
    @Autowired  
    private SessionFactory sessionFactory;  
      
    public User getUserByName(String name)  
    {  
        Session session = sessionFactory.getCurrentSession();  
        List<User> list = new ArrayList<User>();   
          
        //通过Hibernate的Criteria查询     
        Criteria criteria = session.createCriteria(User.class);    
        if (!(null == name && "".equals(name)))   
        {    
            criteria.add(Restrictions.eq("name", name));    
        }    
        list = criteria.list();    
          
        if(list != null && list.size() > 0)   
        {  
            return list.get(0);    
        }  
        return null;    
    }  
      
}  
