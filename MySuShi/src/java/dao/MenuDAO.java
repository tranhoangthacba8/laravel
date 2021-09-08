/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import context.DBContext;
import entity.Menu;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
public class MenuDAO {
    /*
    function getCountMenu: dem so ban ghi trong menu
    */
    public int getCountMenu() throws Exception {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        DBContext db = new DBContext();
        int count = 0;
        try {
            // query so ban ghi trong menu
            String query = "SELECT COUNT(Id) FROM Menu";
            // mo ket noi sql server
            conn = db.getConnection();
            // nem cau lenh query sang sql server
            ps = conn.prepareStatement(query);
            // execute query va nhan ket qua tra ve
            rs = ps.executeQuery();
            // lay ket qua rs sang count
            while (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (Exception e) {
             throw e;
        }finally{
            db.closeConnection(rs, ps, conn);
        }
        return count;
     }
    /*
    function getListMenu: hien thi ra cac danh menu theo so trang
    */
     public List<Menu> getListMenu(int pageIndex, int pageSize) throws SQLException, Exception {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        DBContext db = new DBContext();
        List<Menu> list = new ArrayList<>();
        try {
            // query cau lenh
            String query = "SELECT *FROM("
                + "SELECT ROW_NUMBER() over (order by ID ASC) as rn, *\n"
                + "FROM Menu\n"
                + ")as x\n"
                + "WHERE rn BETWEEN (?-1)*?+1"
                + "and ?*?";
            // mo ket noi sql server
            conn = db.getConnection();
            // nem cau lenh sang sql server
            ps = conn.prepareStatement(query);
            ps.setInt(1, pageIndex);
            ps.setInt(2, pageSize);
            ps.setInt(3, pageIndex);
            ps.setInt(4, pageSize);
            // execute query va nhan ket qua tra ve
            rs = ps.executeQuery();
            // lay ket tu rs chuyen sang list
            while (rs.next()) {
                Menu menu = new Menu(rs.getInt("id"), 
                        rs.getString("name"), 
                        rs.getString("price"), 
                        rs.getString("shortDes"), 
                        rs.getString("detailDes"));
                list.add(menu);
            }
            return list;
        } catch (Exception e) {
            throw e;
        } finally {
            db.closeConnection(rs, ps, conn);
        }
    }
}
