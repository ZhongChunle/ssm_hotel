package com.zcl.dao;

import com.zcl.entity.floor;
import com.zcl.entity.vo.FloorVo;

import java.util.List;

/**
 * 项目名称：ssm_hotel
 * 描述：楼层管理接口
 *
 * @author zhong
 * @date 2022-05-19 20:08
 */
public interface FloorMapper {

    /**
     * 查询楼层数据列表
     * @param floorVo
     * @return
     */
    List<floor> findFloorList(FloorVo floorVo);

    /**
     * 添加楼层数据
     * @param floor
     * @return
     */
    int addFloor(floor floor);

    /**
     * 修改楼层数据
     * @param floor
     * @return
     */
    int updataFloor(floor floor);

    /**
     * 删除楼层判断是否有房间
     * @param id
     * @return
     */
    int judgeFloor(Integer id);

    /**
     * 根据id删除楼层数据
     * @param id
     * @return
     */
    int deleteFloor(Integer id);
}
