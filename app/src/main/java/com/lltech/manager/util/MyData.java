package com.lltech.manager.util;

import com.lcx.mysdk.utils.AppData;
import com.lcx.mysdk.utils.CommonUtil;
import com.lcx.mysdk.utils.JsonUtils;
import com.lcx.mysdk.utils.Shp;
import com.lltech.manager.entity.project.Project;

import java.util.List;

/**
 * @ClassName(类名) : MyData
 * @Description(描述) : app缓存数据
 * @author(作者) ：liuchunxu
 * @date (开发日期)      ：2016年05月05日 11:24
 */
public class MyData extends AppData {
    /**
     * 项目列表
     */
    public static final String PROJECTS = "projects";
    /**
     * 选择项目
     */
    public static final String PROJECT = "project";

    /**
     * 设置项目列表
     *
     * @param list
     */
    public static void setProjects(String list) {
        Shp.putSharePre(application, PROJECTS, PROJECTS, list);
    }

    /**
     * 获取项目列表
     *
     * @return
     */
    public static List<Project> getProjects() {
        String json = Shp.getSharePreStr(application, PROJECTS, PROJECTS);
        if (CommonUtil.isNotEmpty(json)) {
            List<Project> list = JsonUtils.jsonToEntityList(json, Project.class);
            return list;
        }
        return null;
    }

    /**
     * 设置选择项目
     *
     * @param json
     */
    public static void setProject(String json) {
        Shp.putSharePre(application, PROJECT, PROJECT, json);
    }

    /**
     * 获取选择项目
     *
     * @return
     */
    public static Project getProject() {
        String json = Shp.getSharePreStr(application, PROJECT, PROJECT);
        if (CommonUtil.isNotEmpty(json)) {
            Project project = JsonUtils.jsonToEntity(json, Project.class);
            return project;
        }
        return null;
    }
}
