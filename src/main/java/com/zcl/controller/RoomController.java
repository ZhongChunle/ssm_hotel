package com.zcl.controller;

import com.zcl.entity.Room;
import com.zcl.entity.RoomType;
import com.zcl.entity.vo.RoomVo;
import com.zcl.service.RoomService;
import com.zcl.service.RoomTypeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.List;

/**
 * 项目名称：ssm_hotel
 * 描述：C端房间管理控制器
 *
 * @author zhong
 * @date 2022-05-22 9:02
 */
@Controller
@RequestMapping("/room")
public class RoomController {

    /**
     * 注入房间业务层
     */
    @Resource
    RoomService roomService;

    /**
     * 注入查看房型列表
     */
    @Resource
    RoomTypeService roomTypeService;

    /**
     * 根据房型id查询房间列表数据
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("/roomList/{id}.html")
    public String roomList(@PathVariable Integer id, Model model){
        // 查询所有的房间类型
        List<RoomType> roomTypeList = roomTypeService.roomList(null);
        // 创建查询条件的vo类
        RoomVo roomVo = new RoomVo();
        roomVo.setRoomtypeid(id);
        // 查询所有的房间列表数据
        List<Room> roomList = roomService.findRoomList(roomVo);
        model.addAttribute("roomTypeList",roomTypeList);
        model.addAttribute("roomList",roomList);
        // 将当前选中的id值回显页面判断是否选中当前的筛选值，高亮显示
        model.addAttribute("typeid",id);
        return "hotelList";
    }

    /**
     * 查看所有房间列表
     * @return
     */
    @RequestMapping("/roomList")
    public String roomList(Model model){
        // 查询所有的房间类型
        List<RoomType> roomTypeList = roomTypeService.roomList(null);
        // 创建查询条件的vo类作为查询可预定的房间状态
        RoomVo roomVo = new RoomVo();
        roomVo.setStatus(3);
        // 查询所有的房间列表数据
        List<Room> roomList = roomService.findRoomList(roomVo);
        model.addAttribute("roomTypeList",roomTypeList);
        model.addAttribute("roomList",roomList);
        return "hotelList";
    }


    /**
     * 根据房间id查询房间详情
     * @param id
     * @return
     */
    @RequestMapping("/{id}.html")
    public String detail(@PathVariable Integer id, Model model){
        Room room = roomService.findById(id);
        model.addAttribute("room",room);
        // 跳转到房间详情页面
        return "detail";
    }
}
