package com.zcl.service;

import com.zcl.entity.RoomType;
import com.zcl.entity.vo.RoomTypeVo;

import java.util.List;

/**
 * 项目名称：ssm_hotel
 * 描述：房间类型业务层
 *
 * @author zhong
 * @date 2022-05-19 21:37
 */
public interface RoomTypeService {

    /**
     * 编辑房间类型信息
     * @param roomType
     * @return
     */
    int updateRoomType(RoomType roomType);

    /**
     * 添加房型数据
     * @param roomType
     * @return
     */
    int addRoomType(RoomType roomType);

    /**
     * 查询房间类型列表数据
     * @param roomTypeVo
     * @return
     */
    List<RoomType> roomList(RoomTypeVo roomTypeVo);
}
