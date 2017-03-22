package com.archermind.etb.util;

/**
 * 
 * 常量类
 * 
 * @author 000578 梁伟
 * @version v1.0, 2013.07.05
 * @since v1.0
 */

public class Constant {
	/** 分页显示的记录条数 */
	public static final Integer PAGESIZE = 10;

	/** 目标类型 */
	public static final String TARGET_TYPE_NAVTAB = "navTab";
	public static final String TARGET_TYPE_DIALOG = "dialog";

	/** 输入下拉列表中，每页显示信息条数 */
	public static final Integer DEVICE_SHOW_COLUMN = 6;

	/** 页标数字多少个 */
	public static final int PAGENUM_SHOWN = 10;

	/** 初始化页数 */
	public static final int INIT_PAGENUM = 1;

	/** 新增，修改，删除记录的提示信息 */
	public static final String SAVE_SUCCESS = "保存成功";
	public static final String SAVE_FAILED = "保存失败";
	public static final String UPDATE_SUCCESS = "修改成功";
	public static final String UPDATE_FAILED = "修改失败";
	public static final String DELETE_SUCCESS = "删除成功";
	public static final String DELETE_FAILED = "删除失败";
	public static final String CHECKED_SUCCESS = "审核成功";
	public static final String CHECKED_FAILED = "审核失败";
	public static final String CHECKED_FAILED_NOPACK = "提交失败,该版本下没有完整升级包";
	public static final String UNCHECKED_SUCCESS = "审核驳回成功";
	public static final String UNCHECKED_FAILED = "审核驳回失败";
	public static final String PUBLISH_SUCCESS = "上线成功";
	public static final String PUBLISH_FAILED = "上线失败";
	public static final String PUBLISH_ADVERTISE_SUCCESS = "发布成功";
	public static final String PUBLISH_ADVERTISE_FAILED = "发布失败";
	public static final String UNPUBLISH_SUCCESS = "下线成功";
	public static final String UNPUBLISH_FAILED = "下线失败";
	public static final String DELETE_MENU_FAILED = "存在子菜单，不能删除";
	public static final String ADD_MENU_FAILED = "顶级菜单不能新增同级";
	public static final String UPLOAD_SUCCESS = "上传成功";
	public static final String UPLOAD_FAILED = "上传失败";
	public static final String UPLOAD_FAILED_ERR_SUFFIX = "上传失败,只允许上传ZIP格式文件";

	public static final String HAVE_APPS = "应用分类下已有相关应用，不可删除";
	public static final String HAVE_PUBLISHED = "应用已上线，不可删除";

	public static final String HAVE_PACK = "该版本下已有升级包，不可删除";

	public static final String HAVE_AUDIT_RELEASE = "该版本已审核通过或者已发布，不允许再做修改";
	public static final String HAVE_COMPLETE_PACK = "该版本已存在完整包，不允许再次添加";
	public static final String HAVE_FOUR_PACK = "该版本已向下差分了四个版本，不允许再次添加";
	public static final String MIN_PACK = "该版本是最低版本,不存在差分升级包";
	public static final String HAVE_OTA_NOT_RELEASE = "还有版本未发布，不允许添加新版本";

	public static final String NO_DEVICE = "该终端设备不存在";

	public static final String CLIENT_USERD = "终端设备被使用，不能删除";

	public static final String AD_PACKAGE_ADD_FINISH = "新增完成";

	public static final String ROLE_USERD = "该角色已与平台用户关联，不能删除";

	public static final String AD_PACKAGE_CHECKED = "资料已审核通过，不可修改";
	public static final String AD_PACKAGE_PUBLISHED = "资料已发布，不可修改";
	public static final String AD_PACKAGE_COMMITTED = "资料已提交，不可修改";
	
	/**
	 * 项目名称
	 */
	public static final String PROJECT_TITLE="东慧E通宝平台管理系统";

	/**
	 * 文件上传
	 */
	public static final String FILE_UPLOAD_FALSE = "文件解析失败，文件类型错误或文件出现特殊格式。";
	public static final String FILE_UPLOAD_TRUE = "文件导入成功";
	/**
	 * 消息推送
	 */
	public static final String EXCUTEDATE_FAIL = "过期时间小于当前系统时间，请填写正确的过期时间";

	/**
	 * 有关状态码的字段
	 */
	/**
	 * 文件导出
	 */
	public static final String FILE_EXPORT_ERROR = "文件导出失败";
	public static final String FILE_EXPORT_SUCCESS = "文件导出成功";
	public static final String DATA_IS_NULL = "没有数据";
	public static final int EXCEL_SHEET = 10000;

	public static final int HTTP_STATUS_OK = 200;
	public static final int HTTP_STATUS_FAILED = 300;
	public static final int HTTP_STATUS_WARN = 201;

	/** 广告包状态 - 0 提交，不发布;1-已提交，发布;2-发布后，待审核；3-审核通过；4-审核不通过； */
	public static final int AD_PACKAGE_STATUS_ADD = 0;
	public static final int AD_PACKAGE_STATUS_COMMITTED = 1;
	public static final int AD_PACKAGE_STATUS_PUBLISHED = 2;
	public static final int AD_PACKAGE_STATUS_CHECK_PASSED = 3;
	public static final int AD_PACKAGE_STATUS_CHECK_UNPASSED = 4;

	/** 广告包状态 - 0 未提交;1-审核通过；2-审核不通过；3-待审核；4-已发布； */
	public static final String AD_PACKAGE_STATUS_ADD_CHINESE = "未提交";
	public static final String AD_PACKAGE_STATUS_CHECK_PASSED_CHINESE = "审核通过";
	public static final String AD_PACKAGE_STATUS_CHECK_UNPASSED_CHINESE = "审核驳回";
	public static final String AD_PACKAGE_STATUS_COMMITTED_CHINESE = "待审核";
	public static final String AD_PACKAGE_STATUS_PUBLISHED_CHINESE = "已发布";

	/**
	 * OTA版本状态 0 - 新添加未提交 1 - 提交未审核 2 - 审核通过 3 - 审核不通过 4 - 已发布
	 */
	public static final int OTA_VER_INFO_STATUS_CREATE = 0;
	public static final int OTA_VER_INFO_STATUS_SUBMIT = 1;
	public static final int OTA_VER_INFO_STATUS_CHECKED = 2;
	public static final int OTA_VER_INFO_STATUS_UPDATED = 3;
	public static final int OTA_VER_INFO_STATUS_PUBLISHED = 4;

	/**
	 * APP应用版本状态 0 - 新添加待审核 1 - 审核通过 2 - 审核不通过 3 - 草稿 4 - 已发布 5 - 已下线
	 */
	public static final int APP_INFO_STATUS_CREATE = 0;
	public static final int APP_INFO_STATUS_CHECKED = 1;
	public static final int APP_INFO_STATUS_REFUSED = 2;
	public static final int APP_INFO_STATUS_MANUSCRIPT = 3;
	public static final int APP_INFO_STATUS_PUBLISHED = 4;
	public static final int APP_INFO_STATUS_UNPUBLISHED = 5;
	public static final String APP_INFO_PACK_HAVED = "已存在相同包名与版本号的文件";
	
	
	/**
	 * APPTpes应用分类操作 0-新增   1-修改  2-删除
	 */
	public static final int APPTYPES_ACTION_ADD = 0;
	public static final int APPTYPES_ACTION_DEL = 2;
	public static final int APPTYPES_ACTION_UPDATE = 1;
	
	
	public static final int APPTYPES_IMG_WIDTH = 120;
	public static final int APPTYPES_IMG_HEIGHT = 120;

	/** 数据状态 - 0 有效;1-失效（删除） */
	public static final int DATA_STAT_ON = 0;
	public static final int DATA_STAT_OFF = 1;

	/** 菜单常量信息 */
	public static final int MENU_ROOT_ID = -1;

	/**
	 * 菜单级别 0 根目录 1 模块 2 目录或菜单
	 */
	public static final int MENU_LEVEL_ROOT = 0;
	public static final int MENU_LEVEL_MODULE = 1;
	public static final int MENU_LEVEL_CATEGORYORMENU = 2;

	/**
	 * 屏幕位置 0:单屏; 1:双屏上屏； 2:双屏下屏
	 */
	public static final String AD_RESOURCE_POSITION_SIGN_SINGLE = "0";
	public static final String AD_RESOURCE_POSITION_SIGN_DOUBLE_UPPER = "1";
	public static final String AD_RESOURCE_POSITION_SIGN_DOUBLE_LOWER = "2";

	/**
	 * 新增修改标记：0-新增；1-修改
	 */
	public static final String ADD_MODIFY_FLAG_ADD = "0";
	public static final String ADD_MODIFY_FLAG_MODIFY = "1";

	/**
	 * 普通广告模板 1：单屏 2：双屏 4：应用广告5：紧急广告-文字广告6：紧急广告-图片广告
	 */
	public static final String AD_TEMPLATE_COMMON_SINGLE = "1";
	public static final String AD_TEMPLATE_COMMON_DOUBLE = "2";
	public static final String AD_TEMPLATE_APP = "4";
	public static final String AD_TEMPLATE_URGENCY_WORD = "5";
	public static final String AD_TEMPLATE_URGENCY_PIC = "6";

	/**
	 * 广告批次号类型：U-紧急广告；B-普通广告；E-应用广告
	 */
	public static final String AD_PACKAGE_BATCH_B = "B";
	public static final String AD_PACKAGE_BATCH_U = "U";
	public static final String AD_PACKAGE_BATCH_E = "E";

	/**
	 * 广告模板类型：1-普通广告；2-应用广告；3-紧急广告；
	 */
	public static final int AD_TEMPLATE_TYPE_COMMON = 1;
	public static final int AD_TEMPLATE_TYPE_APPLICATION = 2;
	public static final int AD_TEMPLATE_TYPE_URGENCY = 3;

	/**
	 * 广告类型：1-横版广告；2-竖版广告；3-滚动广告；
	 */
	public static final int AD_TEMPLATE_TYPE_HENG = 1;
	public static final int AD_TEMPLATE_TYPE_SHU = 2;
	public static final int AD_TEMPLATE_TYPE_SCROLL = 3;
	
	/**
	 * 用户管理模块：session里存放的用户名
	 */
	public static final String SESSION_USER_NAME = "sessionUserName";
	public static final String SESSION_USER = "user";

	/**
	 * 日期样式
	 */
	public static final String DATE_FORMAT_YYYY_MM_DD = "yyyy-MM-dd";
	public static final String DATE_FORMAT_YYYYMMDDHHMMSS = "yyyyMMddHHmmss";
	public static final String DATE_FORMAT_YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
	public static final String DATE_MAX_HH_MM_SS = " 23:59:59";

	/**
	 * 服务器存放广告文件目录名
	 */
	public static final String AD_FILE_UPLOAD_FOLDER = "uploads";
	
	/**
	 * 服务器存放广告文件缩略图目录名
	 */
	public static final String AD_FILE_THUMBNAIL_FOLDER = "thumbnail";
	
	/**
	 * 服务器存放广告文件缩略图后缀名
	 */
	public static final String AD_FILE_THUMBNAIL_POSTFIX = "thu.jpg";
	
	/**
	 * 服务器存放广告文件缩略图后缀名
	 */
	public static final String AD_FILE_THUMBNAIL_DEFAULT = "images/down_movie_double.gif";

	/**
	 * 紧急广告类型 5：文字广告 6：图片广告
	 */
	public static final String AD_URGENCY_WORD = "5";
	public static final String AD_URGENCY_IMG = "6";

	/**
	 * chunkServer状态 1：运行 2：空闲3：暂停
	 */
	public static final String CHUNK_STATUS_RUN = "1";
	public static final String CHUNK_STATUS_FREE = "2";
	public static final String CHUNK_STATUS_STOP = "3";
	/**
	 * 图片压缩：宽高设置
	 */
	public static final float COMPRESSION_RATIO = (float) 0.5;
	public static final int AD_SIZE_WIDTH = 800;
	public static final int AD_SIZE_COMMON_SINGLE_HEIGHT = 1280;
	public static final int AD_SIZE_COMMON_DOUBLE_HEIGHT_UPPER = 640;
	public static final int AD_SIZE_COMMON_DOUBLE_HEIGHT_LOWER = 640;
	public static final int AD_SIZE_COMMON_APP_HEIGHT = 426;// 1280/3
	/**
	 * 压缩后 图片分辨率
	 */
	public static final String AD_RATIO_COMMON_SINGLE = "800*1280";
	public static final String AD_RATIO_COMMON_DOUBLE_UPPER = "800*640";
	public static final String AD_RATIO_COMMON_DOUBLE_LOWER = "800*640";
	public static final String AD_RATIO_URGENCY = "800*1280";
	public static final String AD_RATIO_APP = "800*426";// 1280/3

	/**
	 * 缴费采集信息：缴费类型
	 */
	public static final String PAYMENT_TYPE_1 = "水费";
	public static final String PAYMENT_TYPE_2 = "电费";
	public static final String PAYMENT_TYPE_3 = "煤气费";

	/**
	 * 广告XML用
	 */
	public static final String AD_PLAYAREA_LOCATION_1 = "0,0";
	public static final String AD_PLAYAREA_LOCATION_2 = "0,641";
	public static final String AD_TIPS_WIDTH = "300";
	public static final String AD_TIPS_HEIGHT = "60";

	/**
	 * 南京提供的 web service 方法名 beatChangeName 方法作用：心跳时间变更
	 * 
	 */
	public static final String BEAT_CHANGE_NAME = "beatChange";
	/**
	 * 南京提供的 web service 方法名 beatChangeName 方法作用：发送通知有新消息，将流水号和新消息存入缓存(包含执行时间)
	 * 
	 */
	public static final String NOTIFY_NEW_MESSAGE = "notifyNewMessage";
	/**
	 * 南京提供的 web service 方法名 notifyNewMessage
	 * 方法作用：发送通知有新消息，将流水号和新消息存入缓存(不包含执行时间)
	 */
	public static final String NOTIFY_NEW_MESSAGE_FOR_TIME = "notifyNewMessageForTime";

	/**
	 * 连接超时
	 */
	public static final long CONN_TIMEOUT = 5000L;

	/**
	 * 响应超时
	 */
	public static final long REC_TIMEOUT = 5000L;

	/**
	 * 请求超时 返回状态码
	 */
	public static final String RESPONSE_TIMEOUT = "1";
	/**
	 * 请求地址无效 返回状态码
	 */
	public static final String RESPONSE_404 = "2";
	/**
	 * 连接超时 返回状态码
	 */
	public static final String CONN_TIME_OUT = "3";
	/**
	 * 连接异常 返回状态码
	 */
	public static final String CONN_EXCEPTION = "4";

	/**
	 * 资源类型
	 */
	public static final String AD_RESOURCE_IMG = "1";
	public static final String AD_RESOURCE_VEDIO = "2";

	/**
	 * 推送成功
	 */
	public static final String PUSH_SUCCESS = "推送成功";
	/**
	 * 推送Type：4 - 广告模块
	 */
	public static final int CLIENT_TYPE_AD = 4;

	/**
	 * 推送Action 标识新增广告批次: 1 - 新增
	 */
	public static final int CLIENT_ACTION_AD_ADD = 1;

	/**
	 * HTTP头
	 */
	public static final String HTTP = "http://";

	/**
	 * app操作 1-安装 2-卸载
	 */
	public static final int APP_INSTALL = 1;
	public static final int APP_UNINSTALL = 2;

	/**
	 * 标识APP升级类型
	 */
	public static final int APP_TYPE = 3;
	/**
	 * 标识APP分类升级类型
	 */
	public static final int APPTYPS_TYPE = 6;

	/**
	 * 标识升级通知批次
	 */
	public static final int APP_ACTION = 0;
	/**
	 * 标识OTA升级类型
	 */
	public static final int OTA_TYPE = 2;

	/**
	 * 标识OTA升级通知批次
	 */
	public static final int OTA_ACTION = 1;

	/**
	 * 异常信息采集类型 1 表示 广告异常 2 表示 应用异常 3 表示 OTA异常 4 表示 缴费异常 5 表示 彩票异常
	 */
	public static final String EXCEPTION_01 = "广告";
	public static final String EXCEPTION_02 = "应用";
	public static final String EXCEPTION_03 = "OTA";
	public static final String EXCEPTION_04 = "缴费";
	public static final String EXCEPTION_05 = "彩票";
	public static final String EXCEPTION_06 = "应用分类";

	/**
	 * 导入EXCEL文件根目录
	 */
	public static final String DIR_ROOT = "excel";

	/**
	 * 导出EXCEL文件数据目录
	 */
	public static final String DIR_DATA = "data";
	
	/**
	 * 限定素材中图片上传的数量,2013-10-30,RanChen
	 */
	public static final int LIMIT_PIC_NUM = 20;
	
	public static final String MATERIAL_DELETED_MESSAGE = "该素材已被删除，不可修改";
}
