package com.example.springrestcontroller.singelon;

import java.util.HashMap;
import java.util.Map;

public class SingelonHashMap {
    private Map<String, Integer> map = new HashMap<>();
    private static SingelonHashMap getUni;
    private SingelonHashMap(){}
    public static SingelonHashMap getIns(){
        if(getUni == null){
            getUni = new SingelonHashMap();
        }
        return getUni;
    }
    public Map<String, Integer> getSingelonMap(){
        return this.map;
    }
}
