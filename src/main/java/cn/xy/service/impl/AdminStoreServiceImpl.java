package cn.xy.service.impl;

import cn.xy.bean.Administrator;
import cn.xy.bean.Store;
import cn.xy.mapper.AdminStoreMapper;
import cn.xy.service.AdminStoreService;
import lombok.Setter;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Setter
@Service(value = "adminStoreServiceImpl")
public class AdminStoreServiceImpl implements AdminStoreService {
    @Resource
    private AdminStoreMapper adminStoreMapper;

    private Administrator administrator;
    /**
     * 查询所有农户
     * @return
     */
    @Override
    public List<Store> findAllStore() {
        return adminStoreMapper.findStore(null);
    }

    /**
     * 通过用户状态查询农户
     * @param state
     * @return
     */
    @Override
    public List<Store> findStoreByState(Integer state) {
        return adminStoreMapper.findStore(new Store().setState(state));
    }

    /**
     * 只有4超级管理员和2拥有账号管理权限的管理员才能修改农户状态
     * @param id
     * @param state
     */
    @Override
    public String updateStoreStateById(Integer id, Integer state) {
            if (administrator.getRight()==0|| administrator.getRight()==2) {
                int num = adminStoreMapper.updateStoreState(id, state);
                if (num > 0) {
                    return "修改成功";
                }
            }
        return "修改失败";
    }
}
