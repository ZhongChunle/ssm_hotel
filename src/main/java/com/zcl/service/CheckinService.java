package com.zcl.service;

import com.zcl.entity.Checkin;
import com.zcl.entity.vo.checkinVo;

import java.util.List;

/**
 * 项目名称：ssm_hotel
 * 描述：入住管理业务层
 *
 * @author zhong
 * @date 2022-05-23 7:09
 */
public interface CheckinService {
    /**
     * 添加入住信息
     * @param checkin
     * @return
     */
    int addCheckin(Checkin checkin);

    /**
     * 分页查询
     * @param checkinVo
     * @return
     */
    List<Checkin> findCheckinList(checkinVo checkinVo);
}
