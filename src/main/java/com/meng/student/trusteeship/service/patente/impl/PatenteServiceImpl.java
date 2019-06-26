package com.meng.student.trusteeship.service.patente.impl;

import com.meng.student.trusteeship.dao.patente.PatenteMapper;
import com.meng.student.trusteeship.service.patente.PatenteService;
import com.meng.student.trusteeship.entity.patente.PatentePO;
import com.meng.student.trusteeship.entity.patente.PatenteVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author fengqigui
 * @description
 * @date 2018/03/13 13:16
 */
@Service
public class PatenteServiceImpl implements PatenteService {

    @Autowired
    private PatenteMapper patenteMapper;


    @Override
    public void accretionPatented(PatenteVO patenteVO) {

        PatentePO patentePO = convertToPatentePO(patenteVO);
        patenteMapper.insert(patentePO);

    }

    @Override
    public List<PatenteVO> listPatente() {

        List<PatentePO> patentePOS = patenteMapper.listPatentes();
        staleDate(patentePOS);
        List<PatenteVO> collect = patentePOS.stream().map(p -> this.convertToPatenteVO(p)).collect(Collectors.toList());
        return collect;
    }

    @Override
    public int updataPatenteStateById(String id) {

        PatentePO patente = patenteMapper.getByPrimaryKey(id);
        patente.setState(0);
        patenteMapper.updateByPrimaryKey(patente);
        return 0;
    }

    @Override
    public Map<String, Object> listPagePatent(Integer currentPage, Integer pageSize, String patenteName, String city, String repository, Integer state) {

        Map<String, Object> map = new HashMap<>();
        patenteName = checkKeyWords(patenteName);
        city = checkKeyWords(city);
        repository = checkKeyWords(repository);
        map.put("name", patenteName);
        map.put("city", city);
        map.put("repository", repository);
        map.put("state", state);
        int countsPatente = patenteMapper.countsPatente(map);
        map.put("currentPage", (currentPage - 1) * pageSize);
        map.put("pageSize", pageSize);
        List<PatentePO> listPatentePO = patenteMapper.listPagePatente(map);
        List<PatentePO> patentePOS = volidatePatentePast(listPatentePO);
        List<PatenteVO> collect = patentePOS.stream().map(p -> this.convertToPatenteVO(p)).collect(Collectors.toList());

        Map<String, Object> mapResult = new HashMap<>();
        mapResult.put("counts", countsPatente);
        mapResult.put("listPatentes", collect);
        return mapResult;
    }


    @Override
    public List<String> likeName(String name) {

        List<String> lists = patenteMapper.listLikeByName(name);
        return lists;

    }

    @Override
    public List<PatenteVO> likeCity(String city) {

        List<PatentePO> lists = patenteMapper.listLikeByCity(city);
        return lists.stream().map(p -> this.convertToPatenteVO(p)).collect(Collectors.toList());

    }

    @Override
    public List<PatenteVO> likeRepository(String repository) {

        List<PatentePO> lists = patenteMapper.listLikeByRepository(repository);
        return lists.stream().map(p -> this.convertToPatenteVO(p)).collect(Collectors.toList());


    }

    @Override
    public List<PatenteVO> listCityAndRepository(String city, String repository) {
        city = checkKeyWords(city);
        repository = checkKeyWords(repository);
        Map<String, String> map = new HashMap<>(2);
        map.put("city", city);
        map.put("repository", repository);
        List<PatentePO> patentePOS = patenteMapper.listByCityAndRepository(map);
        List<PatenteVO> collect = patentePOS.stream().map(p -> this.convertToPatenteVO(p)).collect(Collectors.toList());
        return collect;
    }

    @Override
    public Long getByPatenteNumebr(String patentNumber) {

        PatentePO patentePO = patenteMapper.getByNumber(patentNumber);
        if (null != patentePO) {
            return patentePO.getEnddate().getTime();
        }
        return null;
    }

    /**
     * 检查关键字是否为空
     *
     * @param keyWords
     * @return
     */
    private String checkKeyWords(String keyWords) {

        if (keyWords == null || "null".equals(keyWords)) {
            return "";
        }
        return keyWords;
    }


    /**
     * 判断是否过期
     *
     * @param lists
     */
    public void staleDate(List<PatentePO> lists) {

        for (PatentePO patentePO : lists) {
            if (null != patentePO.getState() && 0 == patentePO.getState()) {
                continue;
            }
            long currentTime = System.currentTimeMillis();
            long endTime = patentePO.getEnddate().getTime();
            if ((endTime - currentTime) > 0) {
                // 此时未过期
                patentePO.setState(2);
            }
            if ((endTime - currentTime) <= 0) {
                // 已过期
                patentePO.setState(1);
            }
        }

    }

    /**
     * 和当前时间加一天的时间对比，判断是否过期
     *
     * @param patentePOS
     * @return
     */
    private List<PatentePO> volidatePatentePast(List<PatentePO> patentePOS) {

        List<PatentePO> patentes = new ArrayList<>();
        Long tempDate = System.currentTimeMillis() + 1 * 24 * 60 * 60 * 1000L;

        for (PatentePO patentePO : patentePOS) {
            if (patentePO.getState() != null && patentePO.getState() == 0) {
                patentes.add(patentePO);
                continue;
            }
            if (patentePO.getEnddate().getTime() > tempDate) {
                // 驾驶证还未过期
                patentePO.setState(2);
            } else {
                // 驾驶证今天过期
                patentePO.setState(1);
            }

            patentes.add(patentePO);
        }
        return patentes;

    }


    /**
     * VO 转 PO 的转换
     *
     * @param patenteVO
     * @return
     */
    private PatentePO convertToPatentePO(PatenteVO patenteVO) {

        String uuid = UUID.randomUUID().toString().replace("-", "");
        patenteVO.setUuid(uuid);
        long startTime = patenteVO.getStartdate();
        long endTime = patenteVO.getEnddate();
        if (endTime > startTime) {
            patenteVO.setState(2);
        }
        return new PatentePO(patenteVO);
    }

    /**
     * PO 转 VO
     *
     * @param patentePO
     * @return
     */
    private PatenteVO convertToPatenteVO(PatentePO patentePO) {

        if (null == patentePO) {
            return null;
        }
        PatenteVO patenteVO = new PatenteVO(patentePO);
        return patenteVO;
    }

}
