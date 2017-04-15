package com.samplecrud.constant;

import java.util.LinkedHashMap;
import java.util.Map;

public class Variables {
	public static final int WITHDRAW_COUNT = 4;
	
	
	public static final Map<Integer, Double> aMap=new LinkedHashMap<Integer, Double>();
	
    static {
       
        aMap.put(10, 1.0);
       aMap.put(50, 2.5);
       aMap.put(100, 5.0);
       aMap.put(200, 7.5);
       aMap.put(500, 10.0);
       System.out.println(aMap);
       
        //aMap = Collections.unmodifiableMap(aMap);
        
    }
	

}
