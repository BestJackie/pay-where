/**
 * FileName: UserService
 * Author:   haichaoyang3
 * Date:     2019/7/2 11:48
 * Description: user service
 * History:
 */
package com.example.paywhere.service.impl;


import com.jc.demo.springbootdemo.commom.exception.MyException;
import com.jc.demo.springbootdemo.commom.model.ResponseCode;
import com.jc.demo.springbootdemo.commom.model.ServerResponse;
import com.jc.demo.springbootdemo.dao.mapper.LoginLogMapper;
import com.jc.demo.springbootdemo.dao.mapper.SysPermMapper;
import com.jc.demo.springbootdemo.dao.mapper.SysRoleMapper;
import com.jc.demo.springbootdemo.dao.mapper.SysUserMapper;
import com.jc.demo.springbootdemo.dao.model.*;
import com.jc.demo.springbootdemo.dao.po.SysUserPo;
import com.jc.demo.springbootdemo.service.service.UserService;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 * Description:〈user service〉
 *
 * @author haichaoyang3
 * @since 1.0.0
 */
@Service
public class UserServiceImpl implements UserService {
    private static final Logger log = org.slf4j.LoggerFactory.getLogger(UserServiceImpl.class);
    @Autowired
    private SysUserMapper userMapper;
    @Autowired
    private SysRoleMapper roleMapper;
    @Autowired
    private SysPermMapper permMapper;
    @Autowired
    private LoginLogMapper loginLogMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public SysUser getOne(Long id, String userName) {
        return userMapper.selectByPrimaryKey(id, userName);
    }

    @Override
    public void add(SysUser user) {
        userMapper.insert(user);
    }

    @Override
    public void update(SysUser user) {
        userMapper.updateByPrimaryKeySelective(user);
    }

    @Override
    public SysUser getByUsernameAndPassword(String username, String password) {
        SysUserExample example = new SysUserExample();
        example.createCriteria().andUserNameEqualTo(username).andPassWordEqualTo(password);
        List<SysUser> sysUsers = userMapper.selectByExample(example);
        return CollectionUtils.isEmpty(sysUsers) ? null : sysUsers.get(0);
    }


    @Override
    public SysUser getByUsername(String username) {
        SysUserExample example = new SysUserExample();
        example.createCriteria().andUserNameEqualTo(username);
        List<SysUser> sysUsers = userMapper.selectByExample(example);
        return CollectionUtils.isEmpty(sysUsers) ? null : sysUsers.get(0);
    }


    @Override
    public ServerResponse login(String username, String password) {
        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
            return ServerResponse.error(ResponseCode.USERNAME_OR_PASSWORD_EMPTY.getMsg());
        }

        SysUser user = getByUsernameAndPassword(username, password);
        if (null == user) {
            return ServerResponse.error(ResponseCode.USERNAME_OR_PASSWORD_WRONG.getMsg());
        }
        return ServerResponse.success();
    }

    @Override
    public Set<SysRole> getRoleByUserId(Long userId) {
        return roleMapper.selectByUserId(userId);
    }

    @Override
    public Set<SysPerm> getPermByUserId(Long userId) {
        return permMapper.selectByUserId(userId);
    }

    @Override
    public void loginLog(HttpServletRequest request, String userName) {
        LoginLog loginLog = new LoginLog();
        loginLog.setModifyTime(new Date());
        loginLog.setCreateTime(new Date());
        loginLog.setType("1");//登录
        loginLog.setUserId(getByUsername(userName).getId());
        loginLog.setLoginIp(request.getRemoteAddr());
        loginLogMapper.insertSelective(loginLog);

    }

    @Override
    public void registUser(SysUserPo sysUserPo) {
        SysUser user = getByUsername(sysUserPo.getUserName());
        if (Objects.nonNull(user)) {
            throw new MyException("用户名已存在");
        }
        final SysUser sysUser = new SysUser();
        sysUser.setUserName(sysUserPo.getUserName());
        sysUser.setIsLock(false);
        sysUser.setIsExpiration(false);
        sysUser.setCreateTime(new Date());
        sysUser.setModifyPwTime(new Date());
        sysUser.setModifyTime(new Date());
        sysUser.seteMail(sysUserPo.geteMail());
        sysUser.setTelePhone(sysUserPo.getTelePhone());
        sysUser.setPassWord(passwordEncoder.encode(sysUserPo.getPassWord()));
        userMapper.insertSelective(sysUser);
    }

    @Override
    public SysUser getInfo(String userName) {
        SysUser user = getByUsername(userName);
        if (Objects.isNull(user))
            throw new MyException("用户名不存在");
        Set<SysRole> roles = getRoleByUserId(user.getId());
        if (Objects.nonNull(roles))
            user.setRoles(roles);
        Set<SysPerm> perms = getPermByUserId(user.getId());
        if (Objects.nonNull(perms))
            user.setPerms(perms);
        return user;
    }
}