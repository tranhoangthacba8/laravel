/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import context.DBContext;
import entity.Sushi;
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
public class SushiDAO {
    /*
    function getOne: xuat ra san pham co id duoc chon
    */
    public Sushi getOne(int id) throws SQLException, Exception {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        DBContext db = new DBContext();
        try {
            //query de xuat ra mot san pham
            String query = "SELECT * FROM Sushi WHERE Id = ?";
            // mo ket noi sql server
            conn = db.getConnection();
            //nem cau lenh query sang sql
            ps = conn.prepareStatement(query);
            ps.setInt(1, id);
            //execute query va nhan ket qua tra ve
            rs = ps.executeQuery();
            //lay du lieu ra tu rs nem ra object
            while (rs.next()) {
                Sushi sushi = new Sushi(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("image"),
                        rs.getString("shortDes"),
                        rs.getString("detailDes"),
                        rs.getString("price"));
                return sushi;
            }
        } catch (Exception e) {
            throw e;
        } finally {
            db.closeConnection(rs, ps, conn);
        }
        return null;
    }
    //function dem so sushi trong bang du lieu
    public int getTotalSushi() throws SQLException, Exception {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        DBContext db = new DBContext();
        int count = 0;
        try {
            // qurey ra du lieu dem san pham
            String query = "SELECT COUNT(Id) FROM Sushi";
            //mo ket noi sql
            conn = db.getConnection();
            //nem cau lenh sang sql server
            ps = conn.prepareStatement(query);
            //execute query va nhan ket qua tra ve
            rs = ps.executeQuery();
            if (rs.next()) {
                count = rs.getInt(1);
            }
            return count;
        } catch (Exception e) {
            throw e;
        } finally {
            db.closeConnection(rs, ps, conn);
        }
    }
    /*
    function getListPaging: xuat ra san pham theo so trang
    */
    public List<Sushi> getListSuShiPaging(int pageIndex, int pageSize) throws SQLException, Exception {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        DBContext db = new DBContext();
        List<Sushi> list = new ArrayList<>();
        try {
            // query sau nhung san pham theo trang cua no
            String query = "SELECT *FROM("
                    + "SELECT ROW_NUMBER() over (order by ID ASC) as rn, *\n"
                    + "FROM Sushi \n"
                    + ")as x\n"
                    + "WHERE rn BETWEEN (?-1)*?+1"
                    + "and ?*?";
            // ket noi sql server
            conn = db.getConnection();
            // nem cau lenh query sang sql server
            ps = conn.prepareStatement(query);
            ps.setInt(1, pageIndex);
            ps.setInt(2, pageSize);
            ps.setInt(3, pageIndex);
            ps.setInt(4, pageSize);
            // execute query va nhan ket qua tra ve
            rs = ps.executeQuery();
            // lay du lieu tu rs chuyen sang list
            while (rs.next()) {
                Sushi sushi = new Sushi(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("image"),
                        rs.getString("shortDes"),
                        rs.getString("detailDes"),
                        rs.getString("price"));
                list.add(sushi);
            }
            return list;
        } catch (Exception e) {
            throw e;
        } finally {
            db.closeConnection(rs, ps, conn);
        }
    }
}
