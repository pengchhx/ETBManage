package com.archermind.etb.common;

import java.io.Serializable;

public class ChartLable  implements Serializable{

	private static final long serialVersionUID = 1L;

    public String label;

    public ChartLable(){};
    
    public ChartLable(String label){
    	this.label=label;
    };
	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	
    
    
}
