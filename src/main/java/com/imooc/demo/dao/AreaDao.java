package com.imooc.demo.dao;

import com.imooc.demo.entity.Area;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


public interface AreaDao {
    /**
     * 列出区域列表
     */
    List<Area> queryArea();

    /**
     * 根据id列出具体区域
     * @param areaId
     * @return
     */
    Area queryAreaById(int areaId);

    /**
     * 插入区域信息
     * @param area
     * @return
     */
    int insertArea(Area area);

    /**
     * 更新区域信息
     * @param area
     * @return
     */
    int updateArea(Area area);

    /**
     * 删除区域信息
     * @param areaId
     * @return
     */
    int deleteArea(int areaId);
}
