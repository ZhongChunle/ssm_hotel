package com.zcl.controller.admin;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zcl.entity.RoomType;
import com.zcl.entity.vo.RoomTypeVo;
import com.zcl.service.RoomTypeService;
import com.zcl.utils.DataGridViewResult;
import com.zcl.utils.SystemConstant;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

/**
 * 项目名称：ssm_hotel
 * 描述：房间类型控制器
 *
 * @author zhong
 * @date 2022-05-19 21:38
 */
@RestController
@RequestMapping("/admin/roomType")
public class RoomTypeController {

    @Resource
    RoomTypeService roomTypeService;

    /**
     * 查询所有的下拉列表
     * @return
     */
    @RequestMapping("/findAll")
    public String findAll(){
        List<RoomType> roomTypes = roomTypeService.roomList(null);
        return JSON.toJSONString(roomTypes);
    }

    /**
     * 编辑房型数据
     * @param roomType
     * @return
     */
    @RequestMapping("/updateRoomType")
    public String updateRoomType(RoomType roomType){
        HashMap<String, Object> map = new HashMap<String, Object>();
        if(roomTypeService.updateRoomType(roomType) > 0){
            map.put(SystemConstant.SUCCESS,true);
            map.put(SystemConstant.MESSAGES,"房间类型信息编辑成功");
        }else{
            map.put(SystemConstant.SUCCESS,false);
            map.put(SystemConstant.MESSAGES,"房间类型信息编辑失败");
        }
        return JSON.toJSONString(map);
    }

    /**
     * 添加房型数据
     * @param roomType
     * @return
     */
    @RequestMapping("/addRoomType")
    public String addRoomType(RoomType roomType){
        HashMap<String, Object> map = new HashMap<String, Object>();
        if(roomTypeService.addRoomType(roomType) > 0){
            map.put(SystemConstant.SUCCESS,true);
            map.put(SystemConstant.MESSAGES,"房间类型信息添加成功");
        }else{
            map.put(SystemConstant.SUCCESS,false);
            map.put(SystemConstant.MESSAGES,"房间类型信息添加失败");
        }
        return JSON.toJSONString(map);
    }

    /**
     * 查询房间列表控制器
     * @param roomTypeVo
     * @return
     */
    @RequestMapping("/roomTypeList")
    public DataGridViewResult roomTypeList(RoomTypeVo roomTypeVo){
        // 设置分页
        PageHelper.startPage(roomTypeVo.getPage(),roomTypeVo.getLimit());
        // 调用查询方法
        List<RoomType> roomTypes = roomTypeService.roomList(roomTypeVo);
        // 封装分页数据
        PageInfo<RoomType> page = new PageInfo<RoomType>(roomTypes);
        return new DataGridViewResult(page.getTotal(),page.getList());
    }
}
