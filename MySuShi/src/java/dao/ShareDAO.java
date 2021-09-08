/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import context.DBContext;
import entity.Share;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
public class ShareDAO {
    /*
    function getShare: suat ra cac duong lien he cho nguoi dung
    */
    public List<Share> getShare() throws Exception {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        DBContext db = new DBContext();
        ArrayList<Share> list = new ArrayList<>();
        try {
            // query ra tat cac duong lien he
            String query = "select * from Share";
            // mo ket noi sql server
            conn = db.getConnection();
            //nem query sang sql server
            ps = conn.prepareStatement(query);
            // execute query va nhan ket qua tra ve
            rs = ps.executeQuery();
            // lay du lieu tu rs chuyen sang list
            while (rs.next()) {
                Share share = new Share(rs.getString("icon"),
                        rs.getString("socialNetwork"),
                        rs.getString("URL"));
                list.add(share);
            }
            return list;
        } catch (Exception e) {
           throw e;
        } finally {
            db.closeConnection(rs, ps, conn);
        }
    }
}
