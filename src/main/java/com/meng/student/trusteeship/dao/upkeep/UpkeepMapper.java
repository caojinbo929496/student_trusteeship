package com.meng.student.trusteeship.dao.upkeep;

import com.meng.student.trusteeship.entity.QueryCondition;
import com.meng.student.trusteeship.entity.upkeep.ImageUpkeepFreePO;
import com.meng.student.trusteeship.entity.upkeep.ImageUpkeepPO;
import com.meng.student.trusteeship.entity.upkeep.UpkeepPO;

import java.util.List;
import java.util.Map;

import com.meng.student.trusteeship.entity.upkeep.query.UpkeepQueryCondition;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author fengqigui
 */
@Mapper
public interface UpkeepMapper {


    /**
     * 通过车辆的牌照查找保养记录
     *
     * @param carNumber
     * @return
     */
    List<UpkeepPO> getByCarNumber(String carNumber);

    /**
     * 根据主键删除 ID
     *
     * @param uuid 主键
     * @return 影响的行数
     */
    int deleteByPrimaryKey(String uuid);

    /**
     * 添加
     *
     * @param record 实体对象
     * @return 影响的行数
     */
    int insert(UpkeepPO record);

    /**
     * 根据主键筛选
     *
     * @param uuid 主键
     * @return 对象
     */
    UpkeepPO getByPrimaryKey(String uuid);

    /**
     * 根据主键进行字段非空更新
     *
     * @param record 待更新的对象
     * @return 影响的行数
     */
    int updateByPrimaryKeySelective(UpkeepPO record);

    /**
     * 主键更新操作
     *
     * @param record 待更新的对象
     * @return 影响的行数
     */
    int updateByPrimaryKey(UpkeepPO record);

    /**
     * 分页查询
     *
     * @param map 分页的字段
     * @return 对象的集合
     */
    List<UpkeepPO> listPage(Map<String, Integer> map);

    /**
     * 获得总的数量
     *
     * @return 影响的行数
     */
    int getCount();

    /**
     * 条件查询
     *
     * @param queryCondition 查询条件
     * @return 实体类对象的集合
     */
    List<UpkeepPO> queryKeyWords(QueryCondition queryCondition);

    /**
     * 条件查询查询数量
     *
     * @param queryCondition 查询条件
     * @return 影响的行数
     */
    int getCounts(QueryCondition queryCondition);

    /**
     * 批量插入保养的图片
     *
     * @param image 图片的集合
     */
    void insertUpkeepImageBatch(List<ImageUpkeepPO> image);

    /**
     * 插入图片
     *
     * @param image 图片的集合
     */
    void insertUpkeepImageFreeBatch(List<ImageUpkeepFreePO> image);

    /**
     * 通过城市和仓库 查找 该仓库下的保养记录
     *
     * @param queryCondition 保养的查询条件
     * @return
     */
    List<UpkeepPO> listUpkeepByCarNumberAndPatenteNumber(UpkeepQueryCondition queryCondition);

}