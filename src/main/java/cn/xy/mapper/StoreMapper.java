package cn.xy.mapper;


import cn.xy.bean.Store;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @Description 农户方法层，登录注册方法
 * @Author  pzg
 *
 **/
@Repository
public interface StoreMapper {
    /**
     * @Description 登录方法,通过账号密码登录
     * @Param sName 用户名
     * @Param sPassword 用户密码
     **/
    Store login(@Param("sname") String sName,@Param("spassword") String sPassword);

    /**
     * @Description 登录方法,通过手机号和验证码登录
     * @Param sPhone 用户手机号
     * @Param code  验证码
     **/
    Store loginByPhone(String sPhone,String code);
    /**
     * @Description 注册方法,通过账号密码进行注册
     * @Param username 用户名
     * @Param password 用户密码
     **/
    int register(Store store);
    /**
     * @Description 注册方法,通过账号密码进行注册
     * @Param username 用户名
     * @Param password 用户密码
     **/
    int registerByPhone(@Param("store") Store store,@Param("code") String code);

}
