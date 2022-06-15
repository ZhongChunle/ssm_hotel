package com.zcl.dao;

import com.zcl.entity.Room;
import com.zcl.entity.vo.RoomVo;

import java.util.List;

/**
 * 项目名称：ssm_hotel
 * 描述：房间管理接口
 *
 * @author zhong
 * @date 2022-05-21 10:12
 */
public interface RoomMapper {
    /**
     * 根据房间id查询房间详情信息
     * @param id
     * @return
     */
    Room findById(Integer id);

    /**
     * 查询房间列表
     * @return
     */
    List<Room> findRoomList2();

    /**
     * 删除房间数据
     * @param roomId
     * @return
     */
    int deleteRoom(Integer roomId);

    /**
     * 修改房间数据
     * @param room
     * @return
     */
    int updateRoom(Room room);

    /**
     * 添加房间数据
     * @param room
     * @return
     */
    int addRoom(Room room);

    /**
     * 查询所有房间列表数据
     * @param roomVo
     * @return
     */
    List<Room> findRoomList(RoomVo roomVo);
}
