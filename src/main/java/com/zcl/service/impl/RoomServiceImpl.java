package com.zcl.service.impl;

import com.zcl.dao.RoomMapper;
import com.zcl.entity.Room;
import com.zcl.entity.vo.RoomVo;
import com.zcl.service.RoomService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * 项目名称：ssm_hotel
 * 描述：
 *
 * @author zhong
 * @date 2022-05-21 10:24
 */
@Service
@Transactional
public class RoomServiceImpl implements RoomService {
    @Resource
    RoomMapper roomMapper;

    /**
     * 根据房间id查询房间详情信息
     * @param id
     * @return
     */
    public Room findById(Integer id) {
        return roomMapper.findById(id);
    }

    /**
     * 查询房间列表
     * @return
     */
    public List<Room> findRoomList2() {
        return roomMapper.findRoomList2();
    }

    /**
     * 根据房间id删除房间信息
     * @param roomId
     * @return
     */
    public int deleteRoom(Integer roomId) {
        return roomMapper.deleteRoom(roomId);
    }

    /**
     * 修改房间数据
     * @param room
     * @return
     */
    public int updateRoom(Room room) {
        return roomMapper.updateRoom(room);
    }

    /**
     * 添加房间
     * @param room
     * @return
     */
    public int addRoom(Room room) {
        return roomMapper.addRoom(room);
    }

    /**
     * 查询所有的数据列表
     * @param roomVo
     * @return
     */
    public List<Room> findRoomList(RoomVo roomVo) {
        return roomMapper.findRoomList(roomVo);
    }
}
