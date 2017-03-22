package com.archermind.etb.common;

import java.io.Serializable;

public class ChartValue implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public String value;

	public ChartValue(){};
	
	public ChartValue(String value){
		
		this.value=value;
	};
	
	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	

}
