package com.zcl.controller.admin;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zcl.dao.RoomMapper;
import com.zcl.entity.Room;
import com.zcl.entity.vo.RoomVo;
import com.zcl.utils.DataGridViewResult;
import com.zcl.utils.SystemConstant;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

/**
 * 项目名称：ssm_hotel
 * 描述：房间管理控制器
 *
 * @author zhong
 * @date 2022-05-21 10:27
 */
@RestController
@RequestMapping("/admin/room")
public class RoomAdminController {
    @Resource
    RoomMapper roomMapper;

    /**
     * 删除房间数据
     * @param roomId
     * @return
     */
    @RequestMapping("/deltetRoom")
    public String deltetRoom(Integer roomId){
        System.out.println("接收可删除的房间id："+roomId);
        HashMap<String, Object> map = new HashMap<String, Object>();
        if(roomMapper.deleteRoom(roomId) > 0){
            map.put(SystemConstant.EXIST,true);
            map.put(SystemConstant.MESSAGES,"房间信息删除成功");
        }else{
            map.put(SystemConstant.MESSAGES,"房间信息删除失败");
        }
        return JSON.toJSONString(map);
    }

    /**
     * 修改房间数据
     * @param room
     * @return
     */
    @RequestMapping("/updateRoom")
    public String updateRoom(Room room){
        HashMap<String, Object> map = new HashMap<String, Object>();
        if(roomMapper.updateRoom(room) > 0){
            map.put(SystemConstant.SUCCESS,true);
            map.put(SystemConstant.MESSAGES,"房间信息修改成功");
        }else{
            map.put(SystemConstant.SUCCESS,false);
            map.put(SystemConstant.MESSAGES,"房间信息修改失败");
        }
        return JSON.toJSONString(map);
    }

    /**
     * 添加房间数据
     * @param room
     * @return
     */
    @RequestMapping("/addRoom")
    public String addRoom(Room room){
        System.out.println("添加房间数据输出："+room);
        HashMap<String, Object> map = new HashMap<String, Object>();
        if(roomMapper.addRoom(room) > 0){
            map.put(SystemConstant.SUCCESS,true);
            map.put(SystemConstant.MESSAGES,"房间信息添加成功");
        }else{
            map.put(SystemConstant.SUCCESS,false);
            map.put(SystemConstant.MESSAGES,"房间信息添加失败");
        }
        return JSON.toJSONString(map);
    }

    /**
     * 查询房间列表控制器
     * @param roomVo
     * @return
     */
    @RequestMapping("/roomList")
    public DataGridViewResult roomList(RoomVo roomVo){
        // 设置分页
        PageHelper.startPage(roomVo.getPage(),roomVo.getLimit());
        // 调用查询方法
        List<Room> roomList = roomMapper.findRoomList(roomVo);
        // 封装分页数据
        PageInfo<Room> page = new PageInfo<Room>(roomList);
        return new DataGridViewResult(page.getTotal(),page.getList());
    }
}
