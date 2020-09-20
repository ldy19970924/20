package cn.xy.service;

import cn.xy.bean.Store;

import java.util.List;

public interface AdminStoreService {
    /**
     * 查询所有农户
     */
    List<Store> findAllStore();
    /**
     * 根据账号状态查询农户信息
     */
    List<Store> findStoreByState(Integer state);
    /**
     * 通过农户编号修改状态
     */
    String updateStoreStateById(Integer id,Integer state);

}
