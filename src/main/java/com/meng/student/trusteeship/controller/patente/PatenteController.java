package com.meng.student.trusteeship.controller.patente;

import com.meng.student.trusteeship.entity.patente.PatenteVO;
import com.meng.student.trusteeship.entity.result.BaseResult;
import com.meng.student.trusteeship.service.patente.PatenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author fengqigui
 * @description 驾照 controller
 * @date 2018/03/13 11:18
 */
@RestController
public class PatenteController {

    @Autowired
    PatenteService patenteService;

    /**
     * 废弃驾照
     *
     * @param id 驾照的ID
     */
    @RequestMapping(value = "/discard/{id}", method = RequestMethod.PUT)
    public BaseResult discardPatente(@PathVariable String id) {

        Assert.hasLength(id, "驾照Id不为空");
        patenteService.updataPatenteStateById(id);
        return BaseResult.getResult();

    }

    /**
     * 分页查询
     *
     * @param currentPage 当前页
     * @param pageSize    页面尺寸
     */
    @RequestMapping(value = "/pagePatent/{currentPage}/{pageSize}/{patenteName}/{city}/{repository}/{state}", method = RequestMethod.GET)
    public BaseResult pagePatente(@PathVariable Integer currentPage, @PathVariable Integer pageSize, @PathVariable String patenteName, @PathVariable String city, @PathVariable String repository, @PathVariable Integer state) {

        Assert.notNull(currentPage, "当前页不为空");
        Assert.notNull(pageSize, "页面尺寸不为空");
        Map<String, Object> map = patenteService.listPagePatent(currentPage, pageSize, patenteName, city, repository, state);
        return BaseResult.getResult(map);

    }

    @RequestMapping(value = "/likeName/{name}", method = RequestMethod.GET)
    public BaseResult likeName(@PathVariable String name) {

        if (null == name) {
            name = "";
        }
        List<String> listVOs = patenteService.likeName(name);
        return BaseResult.getResult(listVOs);

    }

    @RequestMapping(value = "/likeCity/{city}", method = RequestMethod.GET)
    public BaseResult likeCity(@PathVariable String city) {

        if (null == city || "null".equals(city)) {
            city = "";
        }
        List<PatenteVO> listVOs = patenteService.likeCity(city);
        return BaseResult.getResult(listVOs);

    }

    @RequestMapping(value = "/likeRepository/{repository}", method = RequestMethod.GET)
    public BaseResult likeRepository(@PathVariable String repository) {

        if (null == repository) {
            repository = "";
        }
        List<PatenteVO> listVOs = patenteService.likeRepository(repository);
        return BaseResult.getResult(listVOs);

    }

    /**
     * @param patenteNumber 驾驶证编号
     * @return 过期时间  Date enddate
     */
    @RequestMapping(value = "/remoteQueryByPatenteNumber/{patenteNumber}", method = RequestMethod.GET)
    public Long getByPatenteNumber(@PathVariable String patenteNumber) {
        if (null == patenteNumber) {
            throw new IllegalArgumentException("驾驶证编号不为空！");
        }
        return patenteService.getByPatenteNumebr(patenteNumber);

    }

    /**
     * 根据城市和仓库查找下面的所有的驾驶员
     *
     * @param city       城市
     * @param repository 仓库
     * @return 返回制定城市下面的所有司机的信息 List<PatenteVO>
     */
    @RequestMapping(value = "/remoteListByCityAndRepository/{city}/{repository}", method = RequestMethod.GET)
    public List<PatenteVO> listByCityAndRepository(@PathVariable String city, @PathVariable String repository) {

        List<PatenteVO> patenteVOS = patenteService.listCityAndRepository(city, repository);
        return patenteVOS;

    }


}
