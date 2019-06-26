package com.meng.student.trusteeship.service.vehicle;

import com.meng.student.trusteeship.entity.vehicle.Car;
import com.meng.student.trusteeship.entity.vehicle.dto.ManagerCarSendDTO;
import com.meng.student.trusteeship.entity.vehicle.dto.ManegerCarAcceptDTO;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

/**
 * @author caojinbo
 * @since 2018.3.14
 * 车辆查询
 */
public interface CarService {
    /**
     * 增加车辆信息
     *
     * @param car 车辆对象
     */
    void saveCar(Car car);

    /**
     * 根据唯一主键获得车辆信息
     *
     * @param id 主键
     * @return 返回单一车辆信息
     */
    Car getCarById(String id);

    /**
     * 根据车牌号获得车辆信息
     *
     * @param carNumber 车牌号
     * @return 返回单一车辆信息
     */
    Car getCarByCarNumber(String carNumber);

    /**
     * 列举所有车辆信息
     *
     * @return 所需车辆信息
     */
    Map<String, Object> listCar(Map<String, Object> map);

    /**
     * 列举所有车辆信息
     *
     * @return 所需车辆信息
     */
    Map<String, Object> listDriverLicense(Map<String, Object> map);

    /**
     * 更新车辆价格信息
     *
     * @param car 车辆基本信息
     */
    void updateVehiclePrice(Car car);

    /**
     * 查询所有记录
     *
     * @return 返回所有车的记录
     */
    List<Car> listAll();

    /**
     * 返回有所的车辆信息（保险到期时间，当前车辆行驶里程， 当前车辆油卡， 当前车辆的违章数）
     *
     * @return 返回有所的车辆信息
     */
    Map listCarAllInfo(Map map);

    /**
     * 更具车牌号返回车辆的所有基本信息
     *
     * @param carNumber 车牌号
     * @return 车辆的所有基本信息
     */
    Map getCarAllInfo(String carNumber);


    /**
     * 更具城市，仓库，车牌号，司机驾驶证号来查询所需的车辆信息
     *
     * @param managerCarSendDTO APP端传过来的条件
     * @return App所需要的车辆信息集合
     */
    List<ManegerCarAcceptDTO> listWarehouseInfo(ManagerCarSendDTO managerCarSendDTO);

    /**
     * 查询所有未补全信息的车辆信息，并以excel文件的字节流返回;保险信息、车辆价格任意一项为空则说明需要补全,保险信息过期也需要补全
     *
     * @return 所有未补全信息的车辆信息包装成excel文件的字节流
     * @
     */
    ResponseEntity<byte[]> responseVehicleTemplate();

    /**
     * 批量处理车辆补全信息
     *
     * @param excelInputStream excel文件转成的输入流
     * @return 第一位为0表示导入完全正常，1表示创建Excel错误、2表示文件格式错误，3表示数据格式错误
     */
    List<Object> uploadSeveralVehicleInformation(InputStream excelInputStream, HttpSession session) throws IOException, InvalidFormatException;
}
