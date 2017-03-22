package com.archermind.etb.util;

import java.net.SocketTimeoutException;
import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;
import org.apache.cxf.transport.http.HTTPConduit;
import org.apache.cxf.transport.http.HTTPException;
import org.apache.cxf.transports.http.configuration.HTTPClientPolicy;
import org.apache.log4j.Logger;
import javax.xml.stream.XMLStreamException;

/**
 * 调用web Service 工具类
 * 
 * @author 003487  王鹏博
 * @version 1.0, 2013-8-21 下午6:14:33
 * @since 1.0
 * 
 */
public class WsClientUtil {
	Logger logger = Logger.getLogger(WsClientUtil.class);

	/**
	 * 提取值
	 * 
	 * @param arg
	 *            输入参数
	 * @return
	 */
	public static Object callCXFWservice(String wsdl,String webServiceName, Object[] arg ) {

		try {
			Client client = JaxWsDynamicClientFactory.newInstance().createClient(wsdl);
			
			HTTPConduit http = (HTTPConduit) client.getConduit();
			
			HTTPClientPolicy clientPol = new HTTPClientPolicy();
			/*
			 * 连接超时
			 */
			clientPol.setConnectionTimeout(Constant.CONN_TIMEOUT);
			/*
			 * 取消块编码
			 */
			clientPol.setAllowChunking(false);
			/*
			 * 响应超时
			 */
			clientPol.setReceiveTimeout(Constant.REC_TIMEOUT);
			http.setClient(clientPol);
		
			/*
			 * 得到连接获取对应的 web service 名称 获取参数 实现调用
			 */
			Object[] objs = client.invoke(webServiceName, arg);
			/*
			 * 转换成Object数组
			 */
			return objs != null && objs.length > 0 ?  objs[0] : null;
		} catch (Exception e) {
			Throwable te = e.getCause();
			e.printStackTrace();
			if (te instanceof SocketTimeoutException) {
				return Constant.RESPONSE_TIMEOUT;
			} else if (te instanceof HTTPException) {
				return Constant.RESPONSE_404;
			} else if (te instanceof XMLStreamException) {
				return Constant.CONN_TIME_OUT;
			} else {
				return Constant.CONN_EXCEPTION;
			}

		}

	}
}
