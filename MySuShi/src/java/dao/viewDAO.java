/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import context.DBContext;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author Admin
 */
public class viewDAO {
    /*
    function getView: hien thi so luot view
    */
    public int getView() throws Exception{
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        DBContext db = new DBContext();
        int count = 0;
        try{
            // viet cau lenh query
            String query = "SELECT * FROM [view]";
            // mo ket noi sql server
            conn = db.getConnection();
            // nem cau query sang sql server
            ps = conn.prepareStatement(query);
            // execute query va nhan ket qua tra ve
            rs = ps.executeQuery();
            //lay du lieu tu rs nem sang count
            if(rs.next()){
                count = rs.getInt(1);
            }
            return  count;
        }catch(Exception e){
            throw e;
        }
}
    /*
    function updateView(): edit lai so luot view
    */
    public void updateView(){
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        DBContext db = new DBContext();
        try{
            // viet query
            String query = "UPDATE [view] SET viewed = viewed + 1";
            // ket noi voi sql server
            conn = db.getConnection();
            // nem cau lenh query sang sql server
            ps = conn.prepareStatement(query);
            ps.executeUpdate();
        }catch(Exception e){
            
        }
    }
}
