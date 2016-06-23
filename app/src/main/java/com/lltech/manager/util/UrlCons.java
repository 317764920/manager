package com.lltech.manager.util;

/**
 * @ClassName(类名) : UrlCons
 * @Description(描述) : 请求路径
 * @author(作者) ：liuchunxu
 * @date (开发日期)      ：2016年04月07日 17:55
 */
public class UrlCons {
    public static final String DEFUALT_IP = "172.18.51.249";
    public static final String DEFUALT_PORT = "8020";
    //    public static final String DEFUALT_IP = "222.180.195.171";
//    public static final String DEFUALT_PORT = "81";
    public static final String SERVER = "/AppServers/";
    public static final String FILE = "/UploadFiles/";
    public static final String UPLOAD_FILE = "UploadFile.ashx";
    public static final String UPLOAD_VOICE = "VoiceFileUpload.ashx";

    /**
     * @param reqUrl 请求地址
     * @return String
     * @throws :
     * @Description(功能描述) : 拼装请求地址
     * @author(作者) ： liuchunxu
     * @date (开发日期) : 2015年8月26日 下午3:58:23
     */
    public static String url(String reqUrl) {
        StringBuffer sb = new StringBuffer();
        sb.append("http://");
        sb.append(MyData.getUrl());
        sb.append(UrlCons.SERVER);
        sb.append(reqUrl);
        return sb.toString();
    }

    /**
     * @ClassName(类名) : GetCodeService
     * @Description(描述) : 密钥
     * @author(作者) ：liuchunxu
     * @date (开发日期)      ：2016年2月24日 下午6:08:14
     */
    public class GetCodeService {
        /**
         * 返回随机码
         */
        public static final String GET = "GetCodeService.asmx/GetPassWord";
    }

    /**
     * @ClassName(类名) : AppVerHistory
     * @Description(描述) : 版本更新
     * @author(作者) ：liuchunxu
     * @date (开发日期)      ：2016年2月29日 上午10:38:17
     */
    public class AppVerHistory {
        /**
         * 查询APP最新版本
         */
        public static final String GET_NEW = "AppVerHistory.asmx/GetAppLatestVersion";
    }

    /**
     * @ClassName(类名) : AutomaticNoService
     * @Description(描述) : 获取单号
     * @author(作者) ：liuchunxu
     * @date (开发日期)      ：2016年2月24日 下午6:08:14
     */
    public class AutomaticNoService {
        /**
         * 返回随机码
         */
        public static final String GET = "AutomaticNoService.asmx/GetAutomaticNo";
    }

    /**
     * @ClassName(类名) : ScheduleService
     * @Description(描述) : 我的日程
     * @author(作者) ：liuchunxu
     * @date (开发日期)      ：2016年2月29日 上午10:38:17
     */
    public class ScheduleService {
        /**
         * 通过查询条件获取列表
         */
        public static final String GET_LIST = "ScheduleService.asmx/GetScheduleList";
    }

    /**
     * @ClassName(类名) : LoginService
     * @Description(描述) : 登录
     * @author(作者) ：liuchunxu
     * @date (开发日期)      ：2016年2月24日 下午5:55:54
     */
    public class LoginService {
        /**
         * 通过LoginID获取单个用户信息
         */
        public static final String GET_USER = "LoginService.asmx/GetEntityByLogId";
        /**
         * 通过查询条件获取用户列表
         */
        public static final String GET_USERLIST = "LoginService.asmx/GetList";
        /**
         * 修改账户密码
         */
        public static final String UPDATE_PWD = "LoginService.asmx/UpdateUserInfo";
        /**
         * 验证用户登录
         */
        public static final String LOGIN = "LoginService.asmx/VerificationLogin";
    }

    /**
     * @ClassName(类名) : ProjectService
     * @Description(描述) : 项目
     * @author(作者) ：liuchunxu
     * @date (开发日期)      ：2016年2月24日 下午6:08:29
     */
    public class ProjectService {
        /**
         * 通过用户名获取项目列表
         */
        public static final String GET_LIST = "ProjectService.asmx/GetList";
    }

    /**
     * @ClassName(类名) : GetDialogData
     * @Description(描述) : 获取对话框数据
     * @author(作者) ：liuchunxu
     * @date (开发日期)      ：2016年2月24日 下午6:08:14
     */
    public class GetDialogData {
        /**
         * 获取对话框数据
         */
        public static final String GET = "GetDialogData.asmx/GetList";
    }

    /**
     * @ClassName(类名) : SubSystemService
     * @Description(描述) : 系统
     * @author(作者) ：liuchunxu
     * @date (开发日期)      ：2016年2月26日 上午10:47:24
     */
    public class SubSystemService {
        /**
         * 获取项目下所有系统集成信息
         */
        public static final String GET_LIST = "SubSystemService.asmx/GetSystemList";
    }

    /**
     * @ClassName(类名) : EmployeeService
     * @Description(描述) : 获取系统用户
     * @author(作者) ：liuchunxu
     * @date (开发日期)      ：2016年2月29日 上午10:38:17
     */
    public class EmployeeService {
        /**
         * 通过查询条件获取列表
         */
        public static final String GET_LIST = "EmployeeService.asmx/GetList";
    }

    /**
     * @ClassName(类名) : EquipmentService
     * @Description(描述) : 设备
     * @author(作者) ：liuchunxu
     * @date (开发日期)      ：2016年2月26日 上午10:47:24
     */
    public class EquipmentService {
        /**
         * 根据系统编号，项目编号，设备编号等信息查询设备档案信息
         */
        public static final String GET_LIST = "EquipmentService.asmx/GetEquipmentList";

        /**
         * 根据设备编号查询设备信息
         */
        public static final String GET_ONE = "EquipmentService.asmx/GetEquipmentEntity";

        /**
         * 根据设备编号查询保养信息
         */
        public static final String GET_MaintenanceList = "EquipmentService.asmx/GetMaintenanceRecordList";

        /**
         * 根据设备编号查询维修信息
         */
        public static final String GET_RepairList = "EquipmentService.asmx/GetRepairRecordList";
    }

    /**
     * @ClassName(类名) : FaultReportService
     * @Description(描述) : 设备报修接口
     * @author(作者) ：liuchunxu
     * @date (开发日期)      ：2016年2月29日 上午10:38:17
     */
    public class FaultReportService {
        /**
         * 通过查询条件获取列表
         */
        public static final String GET_LIST = "FaultReportService.asmx/GetFaultReportList";
        /**
         * 删除
         */
        public static final String DEL_LIST = "FaultReportService.asmx/DeleteList";
        /**
         * 插入
         */
        public static final String INSERT = "FaultReportService.asmx/Insert";
        /**
         * 更新
         */
        public static final String UPDATE = "FaultReportService.asmx/Update";
        /**
         * 根据主键获取详情
         */
        public static final String GET_ENTITY = "FaultReportService.asmx/GetFaultReportEntity";
    }

    /**
     * @ClassName(类名) : RepairService
     * @Description(描述) : 设备维修接口
     * @author(作者) ：liuchunxu
     * @date (开发日期)      ：2016年2月29日 上午10:38:17
     */
    public class RepairService {
        /**
         * 通过查询条件获取列表
         */
        public static final String GET_LIST = "RepairService.asmx/GetRepairList";
        /**
         * 删除
         */
        public static final String DEL_LIST = "RepairService.asmx/DeleteList";
        /**
         * 插入
         */
        public static final String INSERT = "RepairService.asmx/Insert";
        /**
         * 更新
         */
        public static final String UPDATE = "RepairService.asmx/Update";
        /**
         * 根据主键获取详情
         */
        public static final String GET_ENTITY = "RepairService.asmx/GetRepairEntity";
    }

    /**
     * @ClassName(类名) : RepairRecordService
     * @Description(描述) : 报修存档接口
     * @author(作者) ：liuchunxu
     * @date (开发日期)      ：2016年2月29日 上午10:38:17
     */
    public class RepairRecordService {
        /**
         * 报修存档
         */
        public static final String SAVE = "RepairRecordService.asmx/InArchives";
    }

    /**
     * @ClassName(类名) : DistributionService
     * @Description(描述) : 派工接口
     * @author(作者) ：liuchunxu
     * @date (开发日期)      ：2016年2月29日 上午10:38:17
     */
    public class DistributionService {
        /**
         * 通过查询条件获取列表
         * 维修派工
         */
        public static final String GET_LIST = "DistributionService.asmx/GetDistributionList";
        /**
         * 通过查询条件获取巡检派工列表
         */
        public static final String GET_INSPECTION_LIST = "DistributionService.asmx/GetInspectionDistributionList";
        /**
         * 通过查询条件获取保养派工列表
         */
        public static final String GET_PLAN_LIST = "DistributionService.asmx/GetPlanDistributionList ";
        /**
         * 通过查询条件获取已派工或者已领单维修派工列表
         */
        public static final String GET_REPAIR_LIST = "DistributionService.asmx/GetRepairDistributionList ";
        /**
         * 保养计划派工
         */
        public static final String GET_PLAN = "DistributionService.asmx/MaintenancePlanDistribution ";
        /**
         * 派工领取
         */
        public static final String GET_RECEIVE = "DistributionService.asmx/ReceiveDistribution ";
        /**
         * 维修派工
         */
        public static final String GET_REPAIR = "DistributionService.asmx/RepairDistribution ";
        /**
         * 删除
         */
        public static final String DEL_LIST = "DistributionService.asmx/DeleteList";
        /**
         * 插入
         */
        public static final String INSERT = "DistributionService.asmx/Insert";
        /**
         * 更新
         */
        public static final String UPDATE = "DistributionService.asmx/Update";
        /**
         * 根据主键获取详情
         * 保养计划派工
         */
        public static final String GET_ENTITY = "DistributionService.asmx/GetDistributionOfPlanEntity";
        /**
         * 派工审核
         */
        public static final String GET_REVIEW = "DistributionService.asmx/ReviewedDistribution";
    }

    /**
     * @ClassName(类名) : DistributionApplyService
     * @Description(描述) : 退单申请接口
     * @author(作者) ：liuchunxu
     * @date (开发日期)      ：2016年2月29日 上午10:38:17
     */
    public class DistributionApplyService {
        /**
         * 插入派工撤回申请信息
         */
        public static final String APPLY = "DistributionApplyService.asmx/Apply";
    }

    /**
     * @ClassName(类名) : WorkCheckService
     * @Description(描述) : 签到签退接口
     * @author(作者) ：liuchunxu
     * @date (开发日期)      ：2016年2月29日 上午10:38:17
     */
    public class WorkCheckService {
        /**
         * 通过查询条件获取签到签退列表
         */
        public static final String GET_LIST = "WorkCheckService.asmx/GetCheckList";
        /**
         * 删除
         */
        public static final String DEL_LIST = "WorkCheckService.asmx/DeleteList";
        /**
         * 签到
         */
        public static final String CHECK_IN = "WorkCheckService.asmx/CheckIn";
        /**
         * 签退
         */
        public static final String CHECK_OUT = "WorkCheckService.asmx/CheckOut";
    }

    /**
     * @ClassName(类名) : SystemInfo
     * @Description(描述) : 消息接口
     * @author(作者) ：liuchunxu
     * @date (开发日期)      ：2016年2月29日 上午10:38:17
     */
    public class SystemInfo {
        /**
         * 联合数据查询 内部消息
         */
        public static final String GET_LIST = "SystemInfo.asmx/GetMessageList";
        /**
         * 通过查询条件获取 单号
         */
        public static final String GET_CODE = "SystemInfo.asmx/GetOrderCode";
        /**
         * 查询所有信息的未处理条数
         */
        public static final String GET_COUNT = "SystemInfo.asmx/GetSystemCount";
        /**
         * 通过查询条件获取系统信息(关于，帮助)
         */
        public static final String GET_INFO = "SystemInfo.asmx/GetSystemInfo";
        /**
         * 通过查询条件获取 系统消息(个人消息，内部消息)
         */
        public static final String GET_MESSAGES = "SystemInfo.asmx/GetSystemMessages";
        /**
         * 处理接收消息
         */
        public static final String UPDATE = "SystemInfo.asmx/UpdateRecipient";
        /**
         * 插入站内消息信息(指定部分个人发消息)
         */
        public static final String INSERT = "SystemInfo.asmx/InsertSystemMessages";
        /**
         * 给所有人(除开自己) 发送站内消息
         */
        public static final String SAVE = "SystemInfo.asmx/SaveSystemMessagesByAll ";
    }
}
