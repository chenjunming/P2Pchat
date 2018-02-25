/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package bean;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

/**
 *
 * @author cjm
 */
public class MyVector extends Vector<Map.Entry<String, String>>{
    // 管理用户的生命
    private Map<String,Long> liveMap;

    public MyVector() {
        super();
    }
    
    public MyVector(Collection c){
        super(c);
        liveMap = new HashMap<>();
    }

    public Map<String, Long> getLiveMap() {
        return liveMap;
    }

    public void setLiveMap(Map<String, Long> liveMap) {
        this.liveMap = liveMap;
    }
    
    @Override
    public synchronized boolean add(Map.Entry<String, String> e) {
        
        boolean returnFlag = super.add(e); 
        liveMap.put(e.getKey(), 0l);//初始化用户的生命
        return returnFlag;
    }
    public synchronized void removeUser(String username){
        ArrayList<Map.Entry<String,String>> list = new ArrayList<>();
        for(Map.Entry<String,String> entry:this){
            if(entry.getKey().equals(username)){
                list.add(entry);
            }
        }
        for(Map.Entry<String,String> entry:list){
            this.remove(entry);
        }
    }

    public Vector<String> getListVector(){
        Vector<String> vector = new Vector<>();
        for(Map.Entry<String,String> entry:this){
            vector.add(entry.getKey()+"           IP:"+entry.getValue());
        }
        return vector;
    }
    
}
