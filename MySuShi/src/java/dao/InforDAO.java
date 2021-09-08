/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import context.DBContext;
import entity.Information;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Admin
 */
public class InforDAO {
    /*
    function getInformation xuat ra thong tin
    */
    public Information getInformation() throws SQLException, Exception {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        DBContext db = new DBContext();
        try {
            // query ra thong tin 
            String query = "SELECT * FROM information;";
            // mo ket noi sql server
            conn = db.getConnection();
            // nem cau lenh query sang sql server
            ps = conn.prepareStatement(query);
            // execute query va tra ve ket qua
            rs = ps.executeQuery();
            // lay du lieu tu rs va chuyen sang object
            while (rs.next()) {
               Information infor = new Information(rs.getInt("id"), 
                       rs.getString("address"), 
                       rs.getString("tel"), 
                       rs.getString("email"), 
                       rs.getString("openingHours") );
                return infor;
            }
        } catch (Exception e) {
            throw e;
        } finally {
            db.closeConnection(rs, ps, conn);
        }
        return null;
    }
}
