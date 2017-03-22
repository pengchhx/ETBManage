package com.archermind.etb.common;
/**
 * 
 * ETB系统异常
 * 
 * @author  000578   wei.liang
 * @version v1.0, 2013.07.22
 * @see java.lang.RuntimeException
 * @since v1.0
 * 
 */
public class SystemException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
    public SystemException(){
    	super();
    }
	
	public SystemException(String msg){
		  super(msg);
	}
	
	public SystemException(String msg, Throwable ex){
		 super(msg,ex);
	}

}
