package com.meng.student.trusteeship.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @Author: 吴宸煊
 * @Description: 获得用户输入的url
 * @Date: Created in 10:20 2018/3/14
 */
@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/login")
                .excludePathPatterns("/loginIn")
                .excludePathPatterns("/static/**")
                /**
                 * 通过驾驶证号查询司机违章信息 get
                 *
                 * @param patenteNumber 驾驶证号
                 * @return 违章信息 List<Violation>
                 */
                .excludePathPatterns("/violation/query/{patenteNumber}")
                /**
                 * 通过驾驶证编号返回驾驶证失效日期 get
                 * @param patenteNumber 驾驶证编号
                 * @return 过期时间  Long enddate
                 */
                .excludePathPatterns("/remoteQueryByPatenteNumber/{patenteNumber}")
                /**
                 * 通过车牌号返回年检有效期和保险到期时间 get
                 *
                 * @param carNumber 车牌号
                 * @return 车牌号，保险到期时间， 年检有效期 的 map
                 */
                .excludePathPatterns("/vehicle/info/{carNumber}")
                /**
                 * 更具车牌号返回 维修信息  post
                 * @param UpkeepQueryCondition 车牌号和驾驶证号
                 * @return List<UpkeepVO>
                 */
                .excludePathPatterns("/remoteQueryUpkeepByCarNumberAndPatentedNumber")
                /**
                 * 通过车牌号返回保养信息  post
                 * @param MaintainQueryCondition 车牌号和保养人驾驶证
                 * @return List<MaintainVO>
                 */
                .excludePathPatterns("/remoteQueryMaintainByCarNumberAndPatenteNumber")
                /**
                 * 通过 城市，仓库 准备查询，车牌号和驾驶证号进行模糊查询车辆信息  post
                 *
                 * @param managerCarDto 查询条件 ManagerCarSendDTO
                 * @return 车辆信息 List<ManegerCarAcceptDTO>
                 */
                .excludePathPatterns("/vehicle/manager/query")
                /**
                 * 根据条件查询城市仓库下的所有车辆信息 post 前端APP调用接口
                 *
                 * @param vehicleConditionQueryVo 查询条件
                 * @return List<CarAllInfo>
                 */
                .excludePathPatterns("/vehicle/listCarAllInfo")
                /**
                 * 根据城市和仓库查找下面的所有的驾驶员  get
                 *
                 * @param city       城市
                 * @param repository 仓库
                 * @return 返回制定城市下面的所有司机的信息 List<PatenteVO>
                 */
                .excludePathPatterns("/remoteListByCityAndRepository/{city}/{repository}")
                /**
                 * 根据违章的查询条件筛选所需记录 post
                 *
                 * @param violationQueryVo 查询条件  包括城市仓库 违章人  驾驶证号
                 * @return List<ViolationUnity>
                 */
                .excludePathPatterns("/violation/listViolation")
                /**
                 * 通过绑定的车牌号查询特定加油记录给车辆监控模块
                 *
                 * @param bundlePlateNumber 车牌号
                 * @return 放回该车牌号的所有加油记录 List<RefuelingRecordForForms>
                 */
                .excludePathPatterns("/fuelCardManager/getRefuelingRecordForFormsList/{bundlePlateNumber}")
                /**
                 * 给安卓端返回最新一次加油记录加油卡交易记录界面  get
                 * @param carNumber 车牌号
                 * @return RefuelingRecordDTO
                 */
                .excludePathPatterns("/androidRefuelingInterface/getrefuelingrecordbyPlate/{carNumber}")
                /**
                 *通过城市和仓库为参数查询特定加油记录给安卓端
                 *
                 * @param city      城市
                 * @param warehouse 仓库
                 * @return 返回给安卓端的加油记录列表
                 */
                .excludePathPatterns("/androidRefuelingInterface/getRefuelingRecordForAndroidWithCityAndWarehouse/{city}/{warehouse}");
    }

    /**
     * 访问静态资源
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        registry.addResourceHandler("/static").addResourceLocations("/static");
        super.addResourceHandlers(registry);
    }

}
