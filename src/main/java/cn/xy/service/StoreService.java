package cn.xy.service;

import cn.xy.bean.Store;

/**
 * @Description 业务层接口实现页面
 * @Author  pzg
 *
 **/
public interface StoreService {
    /**
     * @Description 登录方法,通过账号密码登录
     * @Param username 用户名
     * @Param password 用户密码
     **/
    Store login(String sName, String sPassword);

    /**
     * @Description 登录方法,通过手机号和验证码登录
     * @Param phone 用户手机号
     * @Param code  验证码
     **/
    Store loginByPhone(String sPhone,String code);
    /**
     * @Description 注册方法,通过账号密码进行注册
     * @Param username 用户名
     * @Param password 用户密码
     * @Param image 用户头像
     * @Param phone 用户手机号
     * @Param ucdCard 用户身份证
     **/
    int register(String sName,String sPassword,String sImage,String sPhone,int state,String cdCard);
    /**
     * @Description 注册方法,通过账号密码进行注册
     * @Param username 用户名
     * @Param password 用户密码
     * @Param image 用户头像
     * @Param phone 用户手机号
     * @Param ucdCard 用户身份证
     **/
    int registerByPhone(String sName,String sPhone, String sPassword,String cdCard,int state, String code);

}
