package com.zcl.controller;

import com.zcl.entity.Room;
import com.zcl.entity.RoomType;
import com.zcl.entity.floor;
import com.zcl.service.FloorService;
import com.zcl.service.RoomService;
import com.zcl.service.RoomTypeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.List;

/**
 * 项目名称：ssm_hotel
 * 描述：C端主页面控制器
 *
 * @author zhong
 * @date 2022-05-21 20:56
 */
@Controller
public class IndexController {

    /**
     * 注入房间类型业务层
     */
    @Resource
    RoomTypeService roomTypeService;

    /**
     *  注入楼层业务层
     */
    @Resource
    FloorService floorService;

    /**
     * 注入房间业务层
     */
    @Resource
    RoomService roomService;

    /**
     * 主页面请求控制器
     * @param model
     * @return
     */
    @RequestMapping(value = {"/index.html"})
    public String index(Model model){
        // 1、调用查询房间类型数据
        List<RoomType> roomTypesList = roomTypeService.roomList(null);
        // 2、调用查询所有楼层数据
        List<floor> floorList = floorService.findFloorList(null);
        //3、调用查询房间列表
        List<Room> roomList = roomService.findRoomList2();
        // 返回模型数据
        model.addAttribute("roomTypesList",roomTypesList);
        model.addAttribute("floorList",floorList);
        model.addAttribute("roomList",roomList);
        return "forward:/home.jsp";
    }
}
