package com.zcl.service.impl;

import com.zcl.dao.RoomTypeMapper;
import com.zcl.entity.RoomType;
import com.zcl.entity.vo.RoomTypeVo;
import com.zcl.service.RoomTypeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * 项目名称：ssm_hotel
 * 描述：
 *
 * @author zhong
 * @date 2022-05-19 21:37
 */
@Service
@Transactional
public class RoomTypeServiceImpl implements RoomTypeService {

    @Resource
    RoomTypeMapper RoomTypeMapper;

    /**
     * 编写房间类型数据
     * @param roomType
     * @return
     */
    public int updateRoomType(RoomType roomType) {
        // 已入住人数
        roomType.setLivednum(0);
        // 已预定人数
        roomType.setAvilablenum(0);
        // 可用房间数
        roomType.setReservednum(roomType.getRoomnum());
        return RoomTypeMapper.updateRoomType(roomType);
    }

    /**
     * 添加房间信息
     * @param roomType
     * @return
     */
    public int addRoomType(RoomType roomType) {
        // 设置默认的两个初始值
        // 已入住人数
        roomType.setLivednum(0);
        // 已预定人数
        roomType.setAvilablenum(0);
        // 可用房间数
        roomType.setReservednum(roomType.getRoomnum());
        return RoomTypeMapper.addRoomType(roomType);
    }

    /**
     * 查询房间类型数据
     * @param roomTypeVo
     * @return
     */
    public List<RoomType> roomList(RoomTypeVo roomTypeVo) {
        return RoomTypeMapper.roomList(roomTypeVo);
    }
}
