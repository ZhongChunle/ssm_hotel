package com.zcl.service.impl;

import com.zcl.dao.FloorMapper;
import com.zcl.entity.floor;
import com.zcl.entity.vo.FloorVo;
import com.zcl.service.FloorService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * 项目名称：ssm_hotel
 * 描述：
 *
 * @author zhong
 * @date 2022-05-19 20:15
 */
@Service
@Transactional
public class FloorServiceImpl implements FloorService {
    @Resource
    FloorMapper floorMapper;


    /**
     * 模糊分页查询楼层列表
     * @param floorVo
     * @return
     */
    public List<floor> findFloorList(FloorVo floorVo) {
        return floorMapper.findFloorList(floorVo);
    }

    /**
     * 添加楼层
     * @param floor
     * @return
     */
    public int addFloor(floor floor) {
        return floorMapper.addFloor(floor);
    }

    /**
     * 修改楼层
     * @param floor
     * @return
     */
    public int updataFloor(floor floor) {
        return floorMapper.updataFloor(floor);
    }

    /**
     * 判断楼层
     * @param id
     * @return
     */
    public int judgeFloor(Integer id) {
        return floorMapper.judgeFloor(id);
    }

    /**
     * 删除楼层
     * @param id
     * @return
     */
    public int deleteFloor(Integer id) {
        return floorMapper.deleteFloor(id);
    }

}
