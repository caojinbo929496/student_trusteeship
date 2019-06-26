package com.meng.student.trusteeship.dao.car;

import com.meng.student.trusteeship.entity.vehicle.Car;
import com.meng.student.trusteeship.entity.vehicle.po.CarAllInfoPO;
import com.meng.student.trusteeship.entity.vehicle.po.CarPO;
import com.meng.student.trusteeship.entity.vehicle.po.ManegerCarAcceptPO;
import com.meng.student.trusteeship.entity.vehicle.po.ToBeCompeteledCarPO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * @author caojinbo
 * @since 2018.3.14
 * 车辆查询
 */
@Mapper
public interface CarMapper {
    /**
     * 插入一个车对象
     *
     * @param carPO 车对象
     */
    void insertCar(CarPO carPO);

    /**
     * 通过车Id查询车辆信息
     *
     * @param id 车牌号
     * @return 车牌号对应的Id
     */
    CarPO getCarById(String id);

    /**
     * 通过车牌查询车辆信息
     *
     * @param carNumber 车牌号
     * @return 车牌号对应的Id
     */
    CarPO getCarByCarNumber(String carNumber);

    /**
     * 根据查询条件查询所需的车辆基本信息
     *
     * @param map 查询条件
     * @return 返回所需车辆信息
     */
    List<CarPO> listCarByMap(Map<String, Object> map);

    /**
     * 根据查询条件查询所需的车辆基本信息数量
     *
     * @param map 查询条件
     * @return 返回所需车辆数量
     */
    int countCarByMap(Map<String, Object> map);

    /**
     * 获取当前车辆总数
     *
     * @return 车辆总数
     */
    int conutCar();

    /**
     * 更新车辆价格信息
     *
     * @param car 车辆基本信息
     */
    void updateVehiclePrice(Car car);

    /**
     * 更新车辆信息
     *
     * @param carPO 车辆po对象
     */
    void updateCar(CarPO carPO);

    /**
     * 更改制定的车牌号的年检状态
     *
     * @param map 车牌号和年检状态
     */
    void changeYearCheckStatus(Map map);

    /**
     * 获得车辆的所有基本信息（保险到期时间，当前车辆行驶里程， 当前车辆油卡， 当前车辆的违章数）
     *
     * @return 车辆信息集合
     */
    List<CarAllInfoPO> listCarAllInfo(Map map);

    /**
     * 获得车辆的所有基本信息个数
     *
     * @param map 查询条件
     * @return 车辆信息个数
     */
    Integer countCarAllInfo(Map map);

    List<CarPO> listCarAllInsurance(String carNumber);

    /**
     * 更具城市，仓库来进行准确查询，司机驾驶证号，车牌号来进行模糊查询
     *
     * @param map 返回所需的对象
     */
    List<ManegerCarAcceptPO> listWarehouseCarInfo(Map map);

    /**
     * 查询所有的车辆信息
     *
     * @return 返回所有的车辆信息
     */
    List<CarPO> listCar();

    /**
     * 查询所有需要补全信息的车辆信息
     *
     * @return 返回所有需要补全信息的车辆信息列表
     */
    List<ToBeCompeteledCarPO> listPartCar();

    /**
     * 查询所有需要补全信息的车辆数量
     *
     * @return 返回所有需要补全信息的车辆数量
     */
    Integer countPartCar();

    /**
     * 根据车辆uuid插入车辆价格
     *
     * @param carId        车辆id
     * @param vehiclePrice 车辆价格
     * @return
     */
    void insertVehiclePriceByCarId(@Param("carId") String carId, @Param("vehiclePrice") BigDecimal vehiclePrice);

    /**
     * 根据查询条件查询需要补全信息的车辆基本信息
     *
     * @param map 查询条件
     * @return 返回所需车辆信息
     */
    List<CarPO> listPartCarByMap(Map<String, Object> map);

    /**
     * 根据查询条件查询需要补全信息的车辆基本信息数量
     *
     * @return 返回所需车辆数量
     */
    int countPartCarByMap(Map<String, Object> map);

    /**
     * 根据查询条件查询需要补全信息的行驶证数量
     *
     * @param map 查询条件
     * @return 行驶证数量
     */
    int countDriverLicense(Map<String, Object> map);


    /**
     * 根据查询条件查询需要补全信息的车辆基本信息
     *
     * @param map 查询条件
     * @return 返回所需行驶证信息
     */
    List<CarPO> listDriverLicense(Map map);
}