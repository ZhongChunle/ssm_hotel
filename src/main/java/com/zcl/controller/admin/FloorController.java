package com.zcl.controller.admin;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sun.org.apache.bcel.internal.generic.NEW;
import com.zcl.entity.RoomType;
import com.zcl.entity.floor;
import com.zcl.entity.vo.FloorVo;
import com.zcl.service.FloorService;
import com.zcl.utils.DataGridViewResult;
import com.zcl.utils.SystemConstant;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

/**
 * 项目名称：ssm_hotel
 * 描述：楼层管理控制器
 *
 * @author zhong
 * @date 2022-05-19 20:16
 */
@RestController
@RequestMapping("/admin/floor")
public class FloorController {

    @Resource
    FloorService floorService;

    /**
     * 查询所有的下拉列表
     * @return
     */
    @RequestMapping("/findAll")
    public String findAll(){
        List<floor> roomTypes = floorService.findFloorList(null);
        return JSON.toJSONString(roomTypes);
    }

    /**
     * 查询楼层列表
     * @param floorVo
     * @return
     */
    @RequestMapping("/list")
    public DataGridViewResult floorList(FloorVo floorVo){
        // 配置分页信息
        PageHelper.startPage(floorVo.getPage(), floorVo.getLimit());
        // 调用查询列表数据
        List<floor> floorList = floorService.findFloorList(floorVo);
        // 创建分页对象
        PageInfo<floor> pageInfo = new PageInfo<floor>(floorList);
        // 设置分页
        return new DataGridViewResult(pageInfo.getTotal(),pageInfo.getList());
    }

    /**
     * 添加楼层数据
     * @param floor
     * @return
     */
    @RequestMapping("/addFloor")
    public String addFloor(floor floor){
        HashMap<String, Object> map = new HashMap<String, Object>();
        // 调用添加方法
        if(floorService.addFloor(floor) > 0){
            map.put(SystemConstant.SUCCESS,true);
            map.put(SystemConstant.MESSAGES,"楼层数据添加成功");
        }else{
            map.put(SystemConstant.SUCCESS,false);
            map.put(SystemConstant.MESSAGES,"楼层数据添加失败");
        }
        return JSON.toJSONString(map);
    }

    /**
     * 修改楼层数据
     * @param floor
     * @return
     */
    @RequestMapping("/updataFloor")
    public String updataFloor(floor floor){
        HashMap<String, Object> map = new HashMap<String, Object>();
        // 调用添加方法
        if(floorService.updataFloor(floor) > 0){
            map.put(SystemConstant.SUCCESS,true);
            map.put(SystemConstant.MESSAGES,"楼层数据修改成功");
        }else{
            map.put(SystemConstant.SUCCESS,false);
            map.put(SystemConstant.MESSAGES,"楼层数据修改失败");
        }
        return JSON.toJSONString(map);
    }

    /**
     * 查询楼层数据
     * @param id
     * @return
     */
    @RequestMapping("/judgeFloor")
    public String judgeFloor(Integer id){
        HashMap<String, Object> map = new HashMap<String, Object>();
        // 调用添加方法
        if(floorService.judgeFloor(id) > 0){
            map.put(SystemConstant.EXIST,true);
            map.put(SystemConstant.MESSAGES,"该楼层信息下面有房间不可删除");
        }
        return JSON.toJSONString(map);
    }

    /**
     * 查询楼层数据
     * @param id
     * @return
     */
    @RequestMapping("/deleteFloor")
    public String deleteFloor(Integer id){
        HashMap<String, Object> map = new HashMap<String, Object>();
        // 调用添加方法
        if(floorService.deleteFloor(id) > 0){
            map.put(SystemConstant.EXIST,true);
            map.put(SystemConstant.MESSAGES,"楼层信息删除成功");
        }else{
            map.put(SystemConstant.SUCCESS,false);
            map.put(SystemConstant.MESSAGES,"楼层数据删除失败");
        }
        return JSON.toJSONString(map);
    }
}
