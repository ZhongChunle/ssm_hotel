package com.zcl.dao;

import com.zcl.entity.Checkin;
import com.zcl.entity.vo.checkinVo;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 项目名称：ssm_hotel
 * 描述：入住管理接口
 *
 * @author zhong
 * @date 2022-05-23 6:49
 */
public interface CheckinMapper {
    /**
     * 修改入住信息的方法
     * @param checkin
     * @return
     */
    int updateCheckin(Checkin checkin);

    /**
     * 根据入住id查询入住信息
     * @param checkinId
     * @return
     */
    @Select("select * from t_checkin where id = #{checkinId}")
    Checkin finfById(Long checkinId);

    /**
     * 添加入住信息
     * @param checkin
     * @return
     */
    int addCheckin(Checkin checkin);

    /**
     * 分页查看
     * @param checkinVo
     * @return
     */
    List<Checkin> findCheckinList(checkinVo checkinVo);

}
