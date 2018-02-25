/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package function;

import View.IndexFrame;
import bean.MyVector;
import java.util.ArrayList;
import java.util.Map;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author cjm
 */
public class TimerClock implements Runnable{
    private MyVector userVector;
    private IndexFrame indexFrame;
    public TimerClock(MyVector userVector,IndexFrame indexFrame){
        super();
        this.indexFrame= indexFrame;
        this.userVector = userVector;
    }
    @Override
    public void run() {
        long lastTime = System.currentTimeMillis();
        while(true){
            try {
                ArrayList<String> outOfDate = new ArrayList<String>();
                Thread.sleep(100);
                for(Map.Entry<String,Long> entry:userVector.getLiveMap().entrySet()){
                    entry.setValue(entry.getValue()+System.currentTimeMillis()-lastTime);
                    if(entry.getValue() >3000){
                        outOfDate.add(entry.getKey());
                        userVector.removeUser(entry.getKey());
                    }
                }
                if(outOfDate.size()!=0){
                    
                    for(String str:outOfDate){
                        userVector.getLiveMap().remove(str);
                    }
                    outOfDate.clear();
                    indexFrame.updataMyIndex();
                }
                
                lastTime = System.currentTimeMillis();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        
    }
    
}
