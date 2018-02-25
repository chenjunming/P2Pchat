/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package bean;

import java.util.Map;
import javax.swing.AbstractListModel;

/**
 *
 * @author cjm
 */
public class DataModel extends AbstractListModel{
    private MyVector userList;
    public DataModel(MyVector userList){
        super();
        this.userList = userList;
    }
    @Override
    public int getSize() {
        return userList.size();
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getElementAt(int index) {
        Map.Entry<String, String> entry = userList.get(index);
        
        return entry.getKey()+"IP地址为"+entry.getValue();
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
