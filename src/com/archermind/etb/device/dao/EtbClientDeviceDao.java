package com.archermind.etb.device.dao;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.xwork.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.archermind.etb.common.BaseDao;
import com.archermind.etb.device.domain.EtbClientDevice;
import com.archermind.etb.util.Constant;
import com.archermind.etb.util.Md5Util;

/**
 * 终端设备service类
 * 
 * @author 001667 梁伟
 * @version 1.0 2013-07-11
 * @see com.archermind.etb.common.BaseDao
 * @since 1.0
 */
@Repository("com.archermind.etb.device.dao.EtbClientDeviceDao")
public class EtbClientDeviceDao extends BaseDao<EtbClientDevice> {
	private static final Logger LOGGER = Logger
			.getLogger(EtbClientDeviceDao.class);

	/**
	 * 获取设备信息Count
	 * 
	 * @param name
	 * @return
	 */
	public int getDeviceInfoCount(String imei) {
		StringBuffer sbhql = new StringBuffer(256);
		sbhql.append(" from EtbClientDevice where 1=1 ");
		List<Object> params = new ArrayList<Object>();
		setQueryCondition(imei, sbhql, params);
		return this.getRecordNumber(sbhql.toString(), params);
	}

	/**
	 * 设置查询条件
	 * 
	 * @param name
	 * @param sbhql
	 * @param params
	 */
	private void setQueryCondition(String imei, StringBuffer sbhql,
			List<Object> params) {
		sbhql.append(" and stat = ? ");
		params.add(Constant.DATA_STAT_ON);
		if (!StringUtils.isEmpty(imei)) {
			sbhql.append(" and imei like '%' || ? || '%' ");
			params.add(imei);
		}
	}

	/**
	 * 分页查询设备信息
	 * 
	 * @param name
	 * @param pageSize
	 * @param pageNum
	 * @return
	 */
	public List<EtbClientDevice> listDeviceInfoByPage(String imei,
			int pageSize, int pageNum) {

		StringBuffer sbHQL = new StringBuffer(256);
		sbHQL.append(" from EtbClientDevice where 1 = 1 ");
		List<Object> params = new ArrayList<Object>();
		setQueryCondition(imei, sbHQL, params);
		sbHQL.append(" order by ct desc");
		return this.findListByHql(sbHQL.toString(), params, pageNum, pageSize);
	}

	/**
	 * 终端设备监控
	 */
	public List<EtbClientDevice> listMonitorDeviceInfo(String imei,
			int pageSize, int pageNum) {
		StringBuffer sbHQL = new StringBuffer(256);
		sbHQL.append(" from EtbClientDevice where 1 = 1 ");
		List<Object> params = new ArrayList<Object>();
		setQueryCondition(imei, sbHQL, params);
		sbHQL.append(" order by imei desc");
		return this.findListByHql(sbHQL.toString(), params, pageNum, pageSize);
	}

	/**
	 * 查找有效设备
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<EtbClientDevice> exportDeviceInfo() {
		StringBuffer sbHQL = new StringBuffer(256);
		sbHQL.append(" from EtbClientDevice where stat =  "
				+ Constant.DATA_STAT_ON);
		sbHQL.append(" order by imei desc");
		return (List<EtbClientDevice>) this.find(sbHQL.toString());
	}

	/**
	 * 批量导入终端设备
	 * 
	 * @param list
	 */
	public String[] saveImportDeviceInfo(List<Object> list) {
		String[] resultArray = new String[2];
		int countSuccess = 0;
		int countfail = 0;
		int size = list.size();
		if (size == 0) {
			resultArray[0] = "";
			resultArray[1] = "模板无数据，请完善。";
		} else {
			StringBuilder sb = new StringBuilder();
			Session session = getHibernateTemplate().getSessionFactory()
					.openSession();
			session.beginTransaction();
			for (int i = 0; i < size; i++) {
				EtbClientDevice etbClientDevice = (EtbClientDevice) list.get(i);

				// 验证数据的合法性
				boolean validate = true;
				if (StringUtils.isEmpty(etbClientDevice.getImei())) {
					sb.append("第" + etbClientDevice.getIndex()
							+ "行设备编号为空，导入失败。<br/>");
					validate = false;
				} else if (!etbClientDevice.getImei().matches("\\w*")) {
					sb.append("第" + etbClientDevice.getIndex()
							+ "行设备编号不合法，导入失败。<br/>");
					validate = false;
				}

				if (StringUtils.isEmpty(etbClientDevice.getName())) {
					sb.append("第" + etbClientDevice.getIndex()
							+ "行终端设备型号为空，导入失败。<br/>");
					validate = false;
				}

				if (StringUtils.isEmpty(etbClientDevice.getAddr())) {
					sb.append("第" + etbClientDevice.getIndex()
							+ "行终端设备物理地址为空，导入失败。<br/>");
					validate = false;
				} else if (!etbClientDevice
						.getAddr()
						.matches(
								"[0-9A-F]{2}-[0-9A-F]{2}-[0-9A-F]{2}-[0-9A-F]{2}-[0-9A-F]{2}-[0-9A-F]{2}")) {
					sb.append("第" + etbClientDevice.getIndex()
							+ "行终端设备物理地址不合法，导入失败。<br/>");
					validate = false;
				}
				if (StringUtils.isNotEmpty(etbClientDevice.getIp())
						&& !etbClientDevice.getIp().matches(
								"\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}")) {
					sb.append("第" + etbClientDevice.getIndex()
							+ "行终端设备IP地址不合法，导入失败。<br/>");
					validate = false;
				}

				EtbClientDevice ecd = (EtbClientDevice) session.get(
						EtbClientDevice.class, etbClientDevice.getImei());
				if (ecd != null) {
					if (ecd.getStat() == 0) {
						sb.append("第" + etbClientDevice.getIndex() + "行设备编号："
								+ etbClientDevice.getImei() + "重复，导入失败。<br/>");
						validate = false;
					} else {
						session.delete(ecd);
					}
				}

				try {
					etbClientDevice.setToken(Md5Util.Md5(etbClientDevice
							.getImei()));
				} catch (NoSuchAlgorithmException e) {
					LOGGER.error("通过设备编号加密生成TOKEN异常", e);
					sb.append("第" + etbClientDevice.getIndex()
							+ "行Token生成失败，导入失败。<br/>");
					validate = false;
				}
				if (!validate) {
					countfail++;
					continue;
				}
				session.save(etbClientDevice);
				countSuccess++;
				if (i % 50 == 0) {
					session.flush();
					session.clear();
				}
			}
			session.getTransaction().commit();
			session.close();
			resultArray[0] = "成功导入数据" + countSuccess + "条，失败" + countfail + "条。<br/><br/>";
			resultArray[1] = sb.toString();
		}
		return resultArray;
	}
	
	public List<EtbClientDevice> getClientDeviceByArea(int areaId){
		StringBuffer sbHQL = new StringBuffer(256);
		sbHQL.append(" from EtbClientDevice where areaId = ? and stat =  " + Constant.DATA_STAT_ON);
		
		return (List<EtbClientDevice>) this.find(sbHQL.toString(), areaId);
	}
	
	//--------------------------------------------------------------------------------------------------------------------
	public List<EtbClientDevice> getClientDeviceListByCompanyId(String imei,long companyId){
		StringBuffer sbHQL = new StringBuffer(128);

		sbHQL.append("from EtbClientDevice e inner join  fetch e.company c where c.companyInfoId = ").append(companyId);
		sbHQL.append(" and e.stat = ").append(Constant.DATA_STAT_ON);
		List<Object> params = new ArrayList<Object>();

		if (!StringUtils.isEmpty(imei)) {
			sbHQL.append(" and e.imei like '%'||?||'%'  ");
			params.add(imei);
		}
		sbHQL.append(" order by e.ct desc");
		return this.find(sbHQL.toString(), params.toArray());
	}
	
	public List<EtbClientDevice> getClientDeviceListByCompanyIdByPage(long companyId,String resolution,int areaId,int status, int pageSize,int pageNum){
		StringBuffer sbHQL = new StringBuffer(128);

		sbHQL.append("from EtbClientDevice e left join fetch e.company c left join fetch e.deviceProperty p where c.companyInfoId = ").append(companyId);
		sbHQL.append(" and e.stat = ").append(Constant.DATA_STAT_ON);
		List<Object> params = new ArrayList<Object>();

		if (!StringUtils.isEmpty(resolution)) {
			sbHQL.append(" and p.resolution = ? ");
			params.add(resolution);
		}
		if(areaId!=0){
			sbHQL.append(" and e.areaId = ").append(areaId);

		}
		if(status!=0){
			sbHQL.append(" and p.status = ");
			params.add(status);
		}
		
		return this.findListByHql(sbHQL.toString(), params, pageNum, pageSize);
	}
	
	
	public int getClientDeviceCountByCompanyId(long companyId,String resolution,int areaId,int status){
		StringBuffer sbHQL = new StringBuffer(128);
	
		sbHQL.append("from EtbClientDevice e left join  fetch e.company c left join fetch e.deviceProperty p where c.companyInfoId = ").append(companyId);
		sbHQL.append(" and e.stat = ").append(Constant.DATA_STAT_ON);
		List<Object> params = new ArrayList<Object>();

		if (!StringUtils.isEmpty(resolution)) {
			sbHQL.append(" and p.resolution = ? ");
			params.add(resolution);
		}
		
		if(areaId!=0){
			sbHQL.append(" and e.areaId = ").append(areaId);

		}
		if(status!=0){
			sbHQL.append(" and p.status = ");
			params.add(status);
		}
		
		System.out.println(sbHQL.toString());
		if (params != null && params.size() > 0) {
			return this.getHibernateTemplate().find(sbHQL.toString(),params.toArray()).size();
		} else {
			return this.getHibernateTemplate().find(sbHQL.toString()).size();
		}
	
	}
	
	@SuppressWarnings("unchecked")
	public List<Object> findDeviceBySomeAndPage(int areaId, String resolution, int pageSize, int pageNum){
		StringBuffer sbHQL = new StringBuffer(256);
		List<Object> params = new ArrayList<Object>();
		sbHQL.append(" from EtbClientDevice e left join e.deviceProperty p where e.stat =  " + Constant.DATA_STAT_ON);
		
		if (null != resolution && resolution.length() > 0) {
			sbHQL.append(" and p.resolution = ? ");
			params.add(resolution);
		}
		
		if(areaId > 0){
			sbHQL.append(" and e.areaId = ? ");
			params.add(areaId);
		}
		
		return (List<Object>) this.getHibernateTemplate().find(sbHQL.toString(), params.toArray());
	}
	
}
