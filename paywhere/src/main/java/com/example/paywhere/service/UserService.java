package com.example.paywhere.service;


import com.jc.demo.springbootdemo.commom.model.ServerResponse;
import com.jc.demo.springbootdemo.dao.model.SysPerm;
import com.jc.demo.springbootdemo.dao.model.SysRole;
import com.jc.demo.springbootdemo.dao.model.SysUser;
import com.jc.demo.springbootdemo.dao.po.SysUserPo;

import javax.servlet.http.HttpServletRequest;
import java.util.Set;


public interface UserService {


    SysUser getOne(Long id, String userName);

    void add(SysUser user);

    void update(SysUser user);

    SysUser getByUsernameAndPassword(String username, String password);

    SysUser getByUsername(String username);

    ServerResponse login(String username, String password);

    Set<SysRole> getRoleByUserId(Long userId);

    Set<SysPerm> getPermByUserId(Long userId);

    void loginLog(HttpServletRequest request, String userName);

    void registUser(SysUserPo sysUserPo);

    SysUser getInfo(String userName);


}
