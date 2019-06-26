package com.meng.student.trusteeship.dao.maintain;

import com.meng.student.trusteeship.entity.QueryCondition;
import com.meng.student.trusteeship.entity.maintain.ImageMaintainFaultPO;
import com.meng.student.trusteeship.entity.maintain.ImageMaintainFreePO;
import com.meng.student.trusteeship.entity.maintain.ImageMaintainPO;
import com.meng.student.trusteeship.entity.maintain.MaintainPO;

import java.util.List;

import com.meng.student.trusteeship.entity.maintain.query.MaintainQueryCondition;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author 冯其贵
 * 车辆维修
 */
@Mapper
public interface MaintainMapper {


    /**
     * 根据 UUID 进行删除
     *
     * @param uuid 主键
     * @return 对行影响的结果
     */
    int deleteByPrimaryKey(String uuid);

    /**
     * 新增加维修记录
     *
     * @param record MaintainPO实体类
     * @return 对行影响结果
     */
    int insert(MaintainPO record);

    /**
     * 按条件筛选维修记录
     *
     * @param record 实体类
     * @return 对行影响的结果
     */
    int insertSelective(MaintainPO record);

    /**
     * 根据主键进行查询
     *
     * @param uuid 主键
     * @return MaintainPO实体类
     */
    MaintainPO selectByPrimaryKey(String uuid);

    /**
     * 根据主键进行非空判断进行跟新
     *
     * @param record 实体类
     * @return 行影响结果
     */
    int updateByPrimaryKeySelective(MaintainPO record);

    /**
     * 根据主键进行更新
     *
     * @param record record 实体类
     * @return 行影响结果
     */
    int updateByPrimaryKey(MaintainPO record);

    /**
     * 首页查询，分页
     *
     * @param queryCondition 查询条件
     * @return list集合
     */
    List<MaintainPO> listMaintain(QueryCondition queryCondition);


    /**
     * 统计总条数
     *
     * @return 条数
     */
    int getCount();


    /**
     * 根据车的牌照查找维修记录
     *
     * @param carNumebr
     * @return
     */
    List<MaintainPO> getByCarNumber(String carNumebr);


    /**
     * 模糊查询
     *
     * @param queryCondition 条件
     * @return list集合
     */
    List<MaintainPO> queryKeyWords(QueryCondition queryCondition);

    /**
     * 条件查询统计数量
     *
     * @param queryCondition 条件
     * @return 数量
     */
    int getCounts(QueryCondition queryCondition);

    /**
     * 插入维修清单的图片
     *
     * @param imageMaintainPOS 插入ImageMaintainPO图片
     */
    void insertMaintainImageBatch(List<ImageMaintainPO> imageMaintainPOS);

    /**
     * 插入维修的故障的照片
     *
     * @param imageMaintainFaultPOS 插入维修的故障的照片
     */
    void insertMaintainFaultImageBatch(List<ImageMaintainFaultPO> imageMaintainFaultPOS);

    /**
     * 插入维修费用的照片
     *
     * @param imageMaintainFreePOS 插入维修费用的照片
     */
    void insertMaintainFreeImageBatch(List<ImageMaintainFreePO> imageMaintainFreePOS);

    /**
     * 通过牌照或者维修人的驾照编号查找下面的维修记录
     *
     * @param maintainQueryCondition 查询条件
     * @return 相关条件查询的维修记录
     */
    List<MaintainPO> listMaintainByCarNumberAndPatenteNumber(MaintainQueryCondition maintainQueryCondition);


}