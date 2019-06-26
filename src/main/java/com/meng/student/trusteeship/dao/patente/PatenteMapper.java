package com.meng.student.trusteeship.dao.patente;

import com.meng.student.trusteeship.entity.patente.PatentePO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author fengqigui
 * @date 2018/03/13 11:18
 * 驾照mapper
 */
@Mapper
public interface PatenteMapper {

    /**
     * 根据主键删除 patente
     *
     * @param uuid 主键
     * @return 影响行数
     */
    int deleteByPrimaryKey(String uuid);

    /**
     * 新添加 Patente 不做任何字段非空判断
     *
     * @param record 实体
     * @return 影响的行数
     */
    int insert(PatentePO record);

    /**
     * 新添加 Patente 做非空判断
     *
     * @param record 实体
     * @return 影响的行数
     */
    int insertSelective(PatentePO record);

    /**
     * 根据主键查询 Patente
     *
     * @param uuid 主键
     * @return 实体对象
     */
    PatentePO getByPrimaryKey(String uuid);

    /**
     * 更新 Ptente ,做字段的非空判断
     *
     * @param record 实体对象
     * @return 影响的行数
     */
    int updateByPrimaryKeySelective(PatentePO record);

    /**
     * 更新 操作不做字段的非空判断
     *
     * @param record 待更新的对象
     * @return 影响的行数
     */
    int updateByPrimaryKey(PatentePO record);

    /**
     * 获得所有的 Patente
     *
     * @return 驾照集合
     */
    List<PatentePO> listPatentes();

    /**
     * 分页查询
     *
     * @param map 条件
     * @return 驾照集合
     */
    List<PatentePO> listPagePatente(Map<String, Object> map);

    /**
     * 求总条数
     *
     * @param map 条件
     * @return 条数
     */
    int countsPatente(Map<String, Object> map);

    /**
     * 根据姓名进行模糊插叙
     *
     * @param name 驾驶人名字
     * @return 人名字的集合
     */
    List<String> listLikeByName(@Param(value = "name") String name);

    /**
     * 根据城市进行模糊查询
     *
     * @param city 城市
     * @return 城市的集合
     */
    List<PatentePO> listLikeByCity(@Param(value = "city") String city);

    /**
     * 根据仓库进行模糊查询
     *
     * @param repository 仓库名字
     * @return 仓库的集合
     */
    List<PatentePO> listLikeByRepository(@Param(value = "repository") String repository);

    /**
     * 根据城市和仓库查询改城市仓库下面的驾驶证
     *
     * @param map
     * @return
     */
    List<PatentePO> listByCityAndRepository(Map<String, String> map);

    /**
     * 根据驾照编号进行查询
     *
     * @param number
     * @return
     */
    PatentePO getByNumber(String number);

    /**
     * 定时检查驾驶证是否过期
     *
     * @param patentePOS1
     */
    void updateBatchPatenteById(List<PatentePO> patentePOS1);

}