package com.meng.student.trusteeship.service.patente;

import com.meng.student.trusteeship.entity.patente.PatenteVO;

import java.util.List;
import java.util.Map;

/**
 * @author fengqigui
 * @description 驾驶证service
 * @date 2018/03/13 13:04
 */
public interface PatenteService {

    /**
     * 添加新的驾驶证数据
     *
     * @param patenteVO controller 层接受前端传入的数据
     */
    void accretionPatented(PatenteVO patenteVO);

    /**
     * 获得所有的驾驶员列表
     *
     * @return
     */
    List<PatenteVO> listPatente();


    /**
     * 更新废弃的车辆为废弃的车辆
     *
     * @param id
     * @return
     */
    int updataPatenteStateById(String id);

    /**
     * /**
     * 分页查询
     *
     * @param currentPage 当前页
     * @param pageSize    页面尺寸
     * @param patenteName
     * @param city
     * @param repository
     * @param state       查询状态：1：过期；2：30天过期；3:60天过期
     * @return
     */
    Map<String, Object> listPagePatent(Integer currentPage, Integer pageSize, String patenteName, String city, String repository, Integer state);

    /**
     * 根据姓名进行模糊查询
     *
     * @param name
     * @return
     */
    List<String> likeName(String name);


    /**
     * 根据城市进行模糊查询
     *
     * @param city
     * @return
     */
    List<PatenteVO> likeCity(String city);

    /**
     * 根据仓库进行模糊查询
     *
     * @param repository
     * @return
     */
    List<PatenteVO> likeRepository(String repository);

    /**
     * 根据城市和仓库查询下面的
     *
     * @param city
     * @param repository
     * @return
     */
    List<PatenteVO> listCityAndRepository(String city, String repository);


    /**
     * 过期截止时间
     *
     * @param patentNumber 驾驶证编号
     * @return
     */
    Long getByPatenteNumebr(String patentNumber);


}
