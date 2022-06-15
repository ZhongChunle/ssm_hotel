package com.zcl.dao;

import com.zcl.entity.RoomType;
import com.zcl.entity.vo.RoomTypeVo;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 项目名称：ssm_hotel
 * 描述：房间类型接口
 *
 * @author zhong
 * @date 2022-05-19 21:32
 */
public interface RoomTypeMapper {
    /**
     * 更具房型id查询房型信息
     * @param id
     * @return
     */
    @Select("select * from t_room_type where id = #{id};")
    RoomType findById(Integer id);

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
