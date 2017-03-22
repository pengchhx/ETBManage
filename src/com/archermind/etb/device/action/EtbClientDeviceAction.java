package com.archermind.etb.device.action;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.ServletContext;

import net.sf.jxls.exception.ParsePropertyException;

import org.apache.log4j.Logger;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.archermind.etb.common.BaseAction;
import com.archermind.etb.device.bean.DeviceBean;
import com.archermind.etb.device.domain.EtbAreaCode;
import com.archermind.etb.device.domain.EtbClientDevice;
import com.archermind.etb.device.domain.EtbClientDeviceProperty;
import com.archermind.etb.device.domain.EtbClientDeviceTime;
import com.archermind.etb.device.domain.EtbClientDeviceVolume;
import com.archermind.etb.device.service.EtbAreaCodeService;
import com.archermind.etb.device.service.EtbClientDevicePropertyService;
import com.archermind.etb.device.service.EtbClientDeviceService;
import com.archermind.etb.device.service.EtbClientDeviceTimeService;
import com.archermind.etb.device.service.EtbClientDeviceVolumeService;
import com.archermind.etb.redis.service.RedisService;
import com.archermind.etb.user.domain.UserInfo;
import com.archermind.etb.util.Constant;
import com.archermind.etb.util.JsonHelper;
import com.archermind.etb.util.Md5Util;
import com.archermind.etb.util.PushConstant;
import com.archermind.etb.util.rwexcel.AbstractExcelRow;
import com.archermind.etb.util.rwexcel.ExcelOperator;
import com.opensymphony.xwork2.ActionContext;

/**
 * 终端设备管理ACTION
 * 
 * @author 001667
 * @version 1.0 2013-07-11
 * @see com.archermind.etb.common.BaseAction
 * @since 1.0
 * 
 * 
 */
@Controller
@Scope("prototype")
@Namespace("/device")
@Action(value = "etbClientDeviceAction", results = {
		@Result(name = "listDeviceInfo", location = "listDeviceInfo.jsp"),
		@Result(name = "addDeviceInfo", location = "addDeviceInfo.jsp"),
		@Result(name = "updateDeviceInfo", location = "updateDeviceInfo.jsp"),
		@Result(name = "importExcel", location = "importExcel.jsp"),
		@Result(name = "listMonitorDeviceInfo", location = "deviceMonitor.jsp"),
		
//------------------------------------------------------------------------------
		@Result(name = "gotoDeviceList", location = "equipmentLogin.jsp"),
		@Result(name = "gotoMore", location = "more.jsp"),
		@Result(name = "gotoMore_Base", location = "more_base.jsp"),
		@Result(name = "gotoSetting", location = "setting.jsp"),

		@Result(name = "gotoDeviceListAction", type="redirectAction", location ="etbClientDeviceAction!gotoDeviceList.do"),

})
public class EtbClientDeviceAction extends BaseAction {
	/**
	 * 日志
	 */
	private static final Logger LOGGER = Logger
			.getLogger(EtbClientDeviceAction.class);
	private List<EtbClientDevice> list;

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 实体
	 */
	private EtbClientDevice etbClientDevice;

	private EtbClientDeviceProperty deviceProperty;
	
	
	/**
	 * 实体list
	 */
	private List<EtbClientDevice> listResult;
	/**
	 * 设备在线状态集合
	 */
	private List<DeviceBean> deviceBeanList;
	/**
	 * service层
	 */
	@Resource(name = "com.archermind.etb.device.service.EtbClientDeviceService")
	private EtbClientDeviceService etbClientDeviceService;

	/**
	 * excel处理 clientUserInfoService
	 */
	@Resource(name = "com.archermind.etb.device.service.ClientDeviceExcelRowService")
	private AbstractExcelRow clientDeviceExcelRow;

	/**
	 * 终端设备监控接口
	 */
	@Resource(name = "com.archermind.etb.redis.service.RedisService")
	private RedisService redisService;
	
	/**
	 * 声明EtbAreaCodeService
	 */
	@Resource(name = "com.archermind.etb.device.service.EtbAreaCodeService")
	private EtbAreaCodeService etbAreaCodeService;
	
	/**
	 * 
	 */
	@Resource(name = "com.archermind.etb.device.service.EtbClientDevicePropertyService")
	private EtbClientDevicePropertyService etbClientDevicePropertyService;

	
	/**
	 * 
	 */
	@Resource(name = "com.archermind.etb.device.service.EtbClientDeviceTimeService")
	private EtbClientDeviceTimeService etbClientDeviceTimeService;
	
	/**
	 * 声明EtbAreaCodeService
	 */
	@Resource(name = "com.archermind.etb.device.service.EtbClientDeviceVolumeService")
	private EtbClientDeviceVolumeService etbClientDeviceVolumeService;
	
	/**
	 * Excel File
	 */
	private File clientDeviceExcelFile;

	/**
	 * Excel模板文件templateFileName;
	 */
	private static final String templateFileName = "client_device_template.xls";

	/**
	 * Excel数据文件
	 */
	private String clientFileName = "终端设备列表.xls";

	/**
	 * 终端监控redis缓存KEY值
	 */
	private final static String monitorKey = "Online_10.52.11.222";
	
	/**
	 * 所有省份
	 */
	private List<EtbAreaCode> provinces;
	private List<EtbAreaCode> citys;
	private List<EtbAreaCode> countys;
	private EtbAreaCode device_city;
//	private EtbAreaCode device_county;

	private String areaCode;
	
	
	
	/**
	 * 当前页数
	 */
	private int page;
	
	private String imei;
	
	private String resolution;
	
	private List<EtbClientDeviceTime> deviceDayTimeList=new ArrayList<EtbClientDeviceTime>();
	
	private List<EtbClientDeviceTime> deviceWeekTimeList=new ArrayList<EtbClientDeviceTime>();
	
	private List<EtbClientDeviceVolume> deviceVolumeList=new ArrayList<EtbClientDeviceVolume>();
	
	//用来判断是 开机时间 是每日设置 还是每周设置
	private String every;
	
	/**
	 * 终端设备列表
	 * 
	 * @return String
	 * @see com.archermind.etb.device.service.EtbClientDeviceService
	 */
	public String listDeviceInfo() {
		String imeiString = "";
		if (etbClientDevice == null) {
			etbClientDevice = new EtbClientDevice();
		} else {
			if (etbClientDevice.getImei() != null) {
				imeiString = etbClientDevice.getImei().trim();
			}
		}
		this.totalCount = etbClientDeviceService.getDeviceInfoCount(imeiString);
		this.listResult = etbClientDeviceService.listUserInfoByPage(imeiString,
				this.numPerPage, this.getPageNum());
		return "listDeviceInfo";
	}

	/**
	 * @return String 增加设备
	 */
	public String addDeviceInfo() {
		provinces = etbAreaCodeService.getAllProvince();
		citys = etbAreaCodeService.getCityByAreaCode(provinces.get(0).getAreaCode());
		if(null != citys && citys.size() > 0){
			countys = etbAreaCodeService.getCountyByAreaCode(citys.get(0).getAreaCode());
		}
		
		return "addDeviceInfo";
	}

	/**
	 * 通过imei号查找设备
	 * 
	 * @return
	 */
	public String findDeviceInfoById() {
		etbClientDevice = etbClientDeviceService
				.findDeviceInfoById(etbClientDevice.getImei());
		if (etbClientDevice == null) {
			this.writeResultToResponse("true");
		} else {
			if (etbClientDevice.getStat() == Constant.DATA_STAT_OFF) {
				this.writeResultToResponse("true");
			} else {
				this.writeResultToResponse("false");
			}
		}
		return null;
	}

	/**
	 * 更新设备
	 * 
	 * @return String
	 * @see com.archermind.etb.device.service.EtbClientDeviceService
	 */
//	public String updateDeviceInfo() {
//		etbClientDevice = etbClientDeviceService.updateDeviceInfo(etbClientDevice.getImei());
//		
//		provinces = etbAreaCodeService.getAllProvince();
//		if(null != etbClientDevice.getAreaId() && etbClientDevice.getAreaId() > 0){
//			EtbAreaCode device_county = etbAreaCodeService.getAreaById(etbClientDevice.getAreaId());
//			device_city = etbAreaCodeService.getAreaByParentCode(device_county.getAreaParentCode());
//			EtbAreaCode area_province = etbAreaCodeService.getAreaByParentCode(device_city.getAreaParentCode());
//			
//			citys = etbAreaCodeService.getCityByAreaCode(area_province.getAreaCode());
//			countys = etbAreaCodeService.getCountyByAreaCode(device_city.getAreaCode());
//		}else{
//			citys = etbAreaCodeService.getCityByAreaCode(provinces.get(0).getAreaCode());
//			if(null != citys && citys.size() > 0){
//				countys = etbAreaCodeService.getCountyByAreaCode(citys.get(0).getAreaCode());
//			}
//		}
//		
//		return "updateDeviceInfo";
//	}

	/**
	 * 保存设备
	 * 
	 * @return null
	 */
	public String saveDeviceInfo() {
		try {
			etbClientDevice.setToken(Md5Util.Md5(etbClientDevice.getImei()));
			etbClientDevice.setStat(Constant.DATA_STAT_ON);
			etbClientDevice.setCt(new Date());
			etbClientDeviceService.saveDeviceInfo(etbClientDevice);
			this.writeDwzResultToResponse(Constant.SAVE_SUCCESS,
					Constant.HTTP_STATUS_OK, "deviceid", null, "closeCurrent",
					null);
		} catch (Exception e) {
			LOGGER.error("设备保存失败", e);
			this.writeDwzResultToResponse(Constant.SAVE_FAILED,
					Constant.HTTP_STATUS_FAILED, "deviceid", null,
					"closeCurrent", null);
		}
		return null;
	}

	/**
	 * 删除设备
	 * 
	 * @return null
	 */
	public String deleteDeviceInfo() {
		if (etbClientDeviceService.findDeviceInfoByIdForUser(etbClientDevice
				.getImei()) > 0) {
			this.writeDwzResultToResponse(Constant.CLIENT_USERD,
					Constant.HTTP_STATUS_FAILED, "deviceid", null,
					"closeCurrent", null);
			return null;
		}
		etbClientDevice = etbClientDeviceService
				.findDeviceInfoById(etbClientDevice.getImei());
		etbClientDevice.setStat(Constant.DATA_STAT_OFF);
		etbClientDeviceService.saveDeviceInfo(etbClientDevice);
		this.writeDwzResultToResponse(Constant.DELETE_SUCCESS,
				Constant.HTTP_STATUS_OK, "deviceid", null, null, null);
		return null;
	}

	/**
	 * 导出设备入口
	 * 
	 * @return
	 */
	public String importDeviceInfo() {
		return "importExcel";
	}

	/**
	 * 导入设备保存
	 * 
	 * @return
	 */
	public String saveImportDeviceInfo() {
		List<Object> list = new ArrayList<Object>();
		try {
			list = ExcelOperator.getList(
					clientDeviceExcelFile.getAbsolutePath(),
					clientDeviceExcelRow);
			String[] resultArray = etbClientDeviceService
					.saveImportDeviceInfo(list);
			if ("".equals(resultArray[1])) {
				this.writeTextResultToResponse(Constant.FILE_UPLOAD_TRUE,
						Constant.HTTP_STATUS_OK, "deviceid", null,
						"closeCurrent", null);
			} else {
				this.writeTextResultToResponse(resultArray[0] + resultArray[1],
						Constant.HTTP_STATUS_WARN, "deviceid", null,
						"closeCurrent", null);
			}

		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			this.writeTextResultToResponse(Constant.FILE_UPLOAD_FALSE,
					Constant.HTTP_STATUS_FAILED, "deviceid", null,
					"closeCurrent", null);
		}
		return null;
	}

	/**
	 * 导出设备
	 * 
	 * @throws IOException
	 * @throws InvalidFormatException
	 * @throws ParsePropertyException
	 */
	public void exportDeviceInfo() {
		list = etbClientDeviceService.exportDeviceInfo();
		if (list.size() == 0) {
			this.writeTextResultToResponse(Constant.DATA_IS_NULL,
					Constant.HTTP_STATUS_FAILED, "deviceid", null,
					"closeCurrent", null);
		} else {
			String filePath;
			try {
				ServletContext servletContext = (ServletContext) ActionContext
						.getContext().get(ServletActionContext.SERVLET_CONTEXT);
				String path = servletContext.getRealPath(File.separator);
				filePath = ExcelOperator.generateExcel(path, list,
						templateFileName, clientFileName);
				File dataFile = new File(filePath);
				if (!dataFile.exists()) {
					this.writeTextToResponse("no");
				} else {
					this.writeTextToResponse("yes");
				}
			} catch (Exception e) {
				LOGGER.error(e.getMessage(), e);
				this.writeTextToResponse("no");
			}
		}
	}
	
	/**
	 * 下载文件
	 */
	public void downloadFile(){
		ServletContext servletContext = (ServletContext) ActionContext.getContext().get(ServletActionContext.SERVLET_CONTEXT);
		String filePath = servletContext.getRealPath(File.separator);
		int length = filePath.length();
		if (filePath.charAt(length - 1) == File.separatorChar) {
			filePath = filePath.substring(0, length - 1);
		}
		filePath += File.separator + Constant.DIR_ROOT + File.separator + Constant.DIR_DATA + File.separator + clientFileName;
		try {
			this.downloadFile(filePath, clientFileName,"APPLICATION/msexcel;charset=UTF-8");
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}finally{
			new File(filePath).delete();
		}
		
	}

	/**
	 * 终端设备监控管理
	 */
	public String listMonitorDeviceInfo() {
		String imeiString = "";
		if (etbClientDevice == null) {
			etbClientDevice = new EtbClientDevice();
		} else {
			if (etbClientDevice.getImei() != null) {
				imeiString = etbClientDevice.getImei().trim();
			}
		}
		this.totalCount = etbClientDeviceService.getDeviceInfoCount(imeiString);
		this.listResult = etbClientDeviceService.listUserInfoByPage(imeiString,
				this.numPerPage, this.getPageNum());
		deviceBeanList = new ArrayList<DeviceBean>();
		for(EtbClientDevice ecd : listResult){
			DeviceBean deviceBean = new DeviceBean();
			deviceBean.setImei(ecd.getImei());
			deviceBean.setServerIp("10.52.11.222");
			if("".equals(redisService.getToken(PushConstant.TOKEN_KEY_PREFIX + ecd.getImei()))){
				deviceBean.setDeviceStatus(0);//未激活
			}else{
				Set<String>  deviceset = redisService.getOnlineDevices(monitorKey);				
				if(deviceset != null && deviceset.contains(ecd.getImei())){
						deviceBean.setDeviceStatus(1);//在线
				}else{
						deviceBean.setDeviceStatus(2);//离线
				}
			}
			deviceBeanList.add(deviceBean);
		}
		return "listMonitorDeviceInfo";
	}

	
	
	//------------------------------------------------------------------------------------------
	/**
	 * 进入设备管理页面
	 */
	public String gotoDeviceList(){
		
		this.provinces = etbAreaCodeService.getAllProvince();
		

		
		return "gotoDeviceList";
	}
	
	
	
	/**
	 * 获取设备列表 ajax
	 */
	
	public void getDeviceList(){

		
		
		Subject subject = SecurityUtils.getSubject();
		//取登录者信息
		UserInfo user= (UserInfo) subject.getSession().getAttribute(Constant.SESSION_USER);
		
		
		this.totalCount=etbClientDeviceService.findClientDeviceCountByCompanyId( user.getCompanyInfo().getCompanyInfoId(), 
				this.resolution,
				etbClientDevice.getAreaId().intValue(),0);
		this.listResult=etbClientDeviceService.findClientDeviceByCompanyIdByPage( user.getCompanyInfo().getCompanyInfoId(), 
				this.resolution,
				etbClientDevice.getAreaId().intValue(),0, this.numPerPage,this.getPageNum(this.page));
		
		Map map=new HashMap<>();
		map.put("list", this.listResult);
		map.put("totalPage", (int) Math.ceil((double)this.totalCount/this.numPerPage));
		
		this.writeResultToResponse(JsonHelper.getGson(JsonHelper.TYPE_EXPOSE).toJson(map));
	}
	
	/**
	 * 进入设备管理页面
	 */
	public String gotoMore(){

		return "gotoMore";
	}
	
	
	/**
	 * 进入设备管理页面
	 */
	public String gotoMore_Base(){

		this.etbClientDevice=etbClientDeviceService.findDeviceInfoById(this.imei);
		
		if(this.etbClientDevice.getAreaId()!=null){
			EtbAreaCode country=etbAreaCodeService.getAreaById(this.etbClientDevice.getAreaId());
			EtbAreaCode city=etbAreaCodeService.getAreaByParentCode(country.getAreaParentCode());
			EtbAreaCode province=etbAreaCodeService.getAreaByParentCode(city.getAreaParentCode());

			this.etbClientDevice.setCity(city.getAreaName());
			this.etbClientDevice.setProvince(province.getAreaName());
			this.etbClientDevice.setCountry(country.getAreaName());
			
		}

		return "gotoMore_Base";
	}
	
	
	
	/**
	 * 进入设备设置页面
	 */
	public String gotoSetting(){
		this.provinces = etbAreaCodeService.getAllProvince();
		this.etbClientDevice=etbClientDeviceService.findDeviceInfoById(this.imei);
		
		
		if(this.etbClientDevice.getAreaId()!=null){
			EtbAreaCode country=etbAreaCodeService.getAreaById(this.etbClientDevice.getAreaId());
			EtbAreaCode city=	etbAreaCodeService.getAreaByParentCode(country.getAreaParentCode());
			EtbAreaCode province=	etbAreaCodeService.getAreaByParentCode(city.getAreaParentCode());
			this.etbClientDevice.setCityCode(city.getAreaCode());
			this.etbClientDevice.setProvince(province.getAreaName());
		}
		return "gotoSetting";
	}
	

	
	/**
	 * 省 市 级联查询
	 * 
	 * @return
	 */

	public void getCityByProvinceCode(){

		List<EtbAreaCode> cityList = etbAreaCodeService.getCityByAreaCode(areaCode);

		Map map=new HashMap<>();
		map.put("cityList", cityList);
		
		this.writeResultToResponse(JsonHelper.getGson(JsonHelper.TYPE_EXPOSE).toJson(map));
	}
	
	
	
	
	/**
	 * 修改设备信息
	 * 
	 * @return
	 */

	public String editEtbClientDevice(){
		
		//查出这个对象缘由的属性
		EtbClientDevice ecd=etbClientDeviceService.findDeviceInfoById(this.etbClientDevice.getImei());
		
		//设备属性
		etbClientDevicePropertyService.saveOrUpdate(this.deviceProperty);
		System.out.println(this.deviceProperty.getId());
		this.etbClientDevice.setDeviceProperty(this.deviceProperty);
	
		
		//设备与开关机时间的关系
		
		if("day".equals(this.every)){
			for(EtbClientDeviceTime deviceTime:this.deviceDayTimeList){
				if(deviceTime!=null){
					deviceTime.setClientDevice(this.etbClientDevice);
				}	
			}
		}else{
			for(EtbClientDeviceTime deviceTime:this.deviceWeekTimeList){
				if(deviceTime!=null){
					deviceTime.setClientDevice(this.etbClientDevice);
				}	
			}
			
		}

		//设备与音量的关系
		for(EtbClientDeviceVolume deviceVolume:this.deviceVolumeList){
			if(deviceVolume!=null){
				deviceVolume.setClientDeviceByVolume(this.etbClientDevice);;
			}	
		}

		
		
		
		//删除原有的时间设置	
		if(ecd.getDeviceTimeList()!=null){
			etbClientDeviceTimeService.deleteDeviceTimeList(ecd.getDeviceTimeList());
		}
		
		//删除原有的音量设置
		if(ecd.getDeviceVolumeList()!=null){
			etbClientDeviceVolumeService.deleteDeviceVolumeList(ecd.getDeviceVolumeList());
		}
		
		
		//开机时间设置
		if("day".equals(this.every)){
			etbClientDevice.setDeviceTimeList(new HashSet<EtbClientDeviceTime>(this.deviceDayTimeList));
		}else{
			etbClientDevice.setDeviceTimeList(new HashSet<EtbClientDeviceTime>(this.deviceWeekTimeList));
		}
		
		
		//音量设置
		etbClientDevice.setDeviceVolumeList(new HashSet<EtbClientDeviceVolume>(this.deviceVolumeList));
		
		//修改时间
		etbClientDevice.setUt(new Date());
		
		//修改设备
		etbClientDeviceService.saveDeviceInfo(this.etbClientDevice);
	
		return "gotoDeviceListAction";

	}
	
	
	
	
	
	
	/**
	 * 市 区 级联查询
	 * 
	 * @return
	 */

	public void getCountryByCityCode(){

		List<EtbAreaCode> cityList = etbAreaCodeService.getCountyByAreaCode(areaCode);

		Map map=new HashMap<>();
		map.put("cityList", cityList);
		
		this.writeResultToResponse(JsonHelper.getGson(JsonHelper.TYPE_EXPOSE).toJson(map));
	}
	
	
	

	
	/**
	 * 
	 * @return
	 * @see com.archermind.etb.device.domain.EtbClientDevice
	 */
	public EtbClientDevice getEtbClientDevice() {
		return etbClientDevice;
	}

	/**
	 * 
	 * @param etbClientDevice
	 * @see com.archermind.etb.device.domain.EtbClientDevice
	 */
	public void setEtbClientDevice(EtbClientDevice etbClientDevice) {
		this.etbClientDevice = etbClientDevice;
	}

	/**
	 * 
	 * @return
	 */
	public List<EtbClientDevice> getListResult() {
		return listResult;
	}

	/**
	 * 
	 * @param listResult
	 */
	public void setListResult(List<EtbClientDevice> listResult) {
		this.listResult = listResult;
	}

	/**
	 * 
	 * @return
	 */
	public File getClientDeviceExcelFile() {
		return clientDeviceExcelFile;
	}

	/**
	 * 
	 * @param clientDeviceExcelFile
	 */
	public void setClientDeviceExcelFile(File clientDeviceExcelFile) {
		this.clientDeviceExcelFile = clientDeviceExcelFile;
	}

	/**
	 * 
	 * @return
	 */
	public String getClientFileName() {
		return clientFileName;
	}

	/**
	 * 
	 * @param clientFileName
	 */
	public void setClientFileName(String clientFileName) {
		this.clientFileName = clientFileName;
	}

	public List<EtbClientDevice> getList() {
		return list;
	}

	public void setList(List<EtbClientDevice> list) {
		this.list = list;
	}

	public List<DeviceBean> getDeviceBeanList() {
		return deviceBeanList;
	}

	public void setDeviceBeanList(List<DeviceBean> deviceBeanList) {
		this.deviceBeanList = deviceBeanList;
	}

	public List<EtbAreaCode> getProvinces() {
		return provinces;
	}

	public void setProvinces(List<EtbAreaCode> provinces) {
		this.provinces = provinces;
	}

	public List<EtbAreaCode> getCitys() {
		return citys;
	}

	public void setCitys(List<EtbAreaCode> citys) {
		this.citys = citys;
	}

	public List<EtbAreaCode> getCountys() {
		return countys;
	}

	public void setCountys(List<EtbAreaCode> countys) {
		this.countys = countys;
	}

	public EtbAreaCode getDevice_city() {
		return device_city;
	}

	public void setDevice_city(EtbAreaCode device_city) {
		this.device_city = device_city;
	}

//	public EtbAreaCode getDevice_county() {
//		return device_county;
//	}
//
//	public void setDevice_county(EtbAreaCode device_county) {
//		this.device_county = device_county;
//	}
	
	public String getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}
	
	public void getCityByProvince(){
		List<EtbAreaCode> city_list = etbAreaCodeService.getCityByAreaCode(areaCode);
		List<EtbAreaCode> county_list = null;
		
		if(null != city_list && city_list.size() > 0){
			county_list = etbAreaCodeService.getCountyByAreaCode(city_list.get(0).getAreaCode());
		}
		String city_json = JsonHelper.getGson(JsonHelper.TYPE_EXPOSE).toJson(city_list);
		String county_json = JsonHelper.getGson(JsonHelper.TYPE_EXPOSE).toJson(county_list);
		
		Map<String,String> obj = new HashMap<String,String>();
		obj.put("city", city_json);
		obj.put("county", county_json);
		String area = JsonHelper.getGson().toJson(obj);
		
		this.writeResultToResponse(area);
	}

	public void getCountyByCity(){
		List<EtbAreaCode> county_list = etbAreaCodeService.getCountyByAreaCode(areaCode);
		String json = JsonHelper.getGson(JsonHelper.TYPE_EXPOSE).toJson(county_list);
		
		this.writeResultToResponse(json);
	}

	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}

	public String getImei() {
		return imei;
	}

	public void setImei(String imei) {
		this.imei = imei;
	}

	public EtbClientDeviceProperty getDeviceProperty() {
		return deviceProperty;
	}

	public void setDeviceProperty(EtbClientDeviceProperty deviceProperty) {
		this.deviceProperty = deviceProperty;
	}


	public String getResolution() {
		return resolution;
	}

	public void setResolution(String resolution) {
		this.resolution = resolution;
	}

	public List<EtbClientDeviceVolume> getDeviceVolumeList() {
		return deviceVolumeList;
	}

	public void setDeviceVolumeList(List<EtbClientDeviceVolume> deviceVolumeList) {
		this.deviceVolumeList = deviceVolumeList;
	}

	public List<EtbClientDeviceTime> getDeviceDayTimeList() {
		return deviceDayTimeList;
	}

	public void setDeviceDayTimeList(List<EtbClientDeviceTime> deviceDayTimeList) {
		this.deviceDayTimeList = deviceDayTimeList;
	}

	public List<EtbClientDeviceTime> getDeviceWeekTimeList() {
		return deviceWeekTimeList;
	}

	public void setDeviceWeekTimeList(List<EtbClientDeviceTime> deviceWeekTimeList) {
		this.deviceWeekTimeList = deviceWeekTimeList;
	}

	public String getEvery() {
		return every;
	}

	public void setEvery(String every) {
		this.every = every;
	}


	


}
