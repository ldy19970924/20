package cn.xy.service.impl;

import cn.xy.bean.Store;
import cn.xy.mapper.StoreMapper;
import cn.xy.service.StoreService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Data
@Service
public class StoreServiceImpl implements StoreService {
    @Autowired
    private StoreMapper storeMapper;

    @Override
    public Store login(String sName, String sPassword) {
        return storeMapper.login(sName,sPassword);
    }

    @Override
    public Store loginByPhone(String sPhone, String code) {
        return storeMapper.loginByPhone(sPhone, code);
    }

    @Override
    public int register(String sName, String sPassword, String sImage, String sPhone,int state,String cdCard) {
        int k=0;
        if(sName.equals("") || sName == null || sPassword.equals("") || sPassword == null){
            return k;
        }
        k=storeMapper.register(new Store().setSname(sName).setSpassword(sPassword).setSimage(sImage).setSphone(sPhone).setState(state).setCdcard(cdCard));
        return k;
    }

    @Override
    public int registerByPhone(String sName,String sPhone, String sPassword,String cdCard,int state, String code) {
        int k=storeMapper.registerByPhone(new Store().setSname(sName).setSphone(sPhone).setSpassword(sPassword).setCdcard(cdCard).setState(state),code);
        return k;
    }
}
