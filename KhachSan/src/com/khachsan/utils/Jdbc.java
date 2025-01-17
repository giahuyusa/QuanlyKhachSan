/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.khachsan.utils;

import java.sql.*;

/**
 *
 * @author nguye
 */
public class Jdbc {
    private static final String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    private static String dburl="jdbc:sqlserver://localhost;database=KhachSan7;encrypt=false";
    private static final String user   = "sa";
    private static final String pass   = "858685";
    static{
        try{
            Class.forName(driver);
        }catch(Exception e){
            throw new RuntimeException(e);
        }
    }
    // nap driver
    public static PreparedStatement getStmt(String sql, Object ... args) throws SQLException{
        Connection conn = DriverManager.getConnection(dburl,user, pass);
        PreparedStatement pre = null;
        if(sql.trim().startsWith("{")){
            pre = conn.prepareCall(sql);
        } else {
            pre = conn.prepareStatement(sql);
        }
        // duyet qua danh sach cac tham so
        for (int i = 0; i < args.length; i++){
            pre.setObject(i+1,args[i]);
        }
        return pre;
    }
    // update
        public static int update(String sql, Object ... args){
            try{
                PreparedStatement stmt = getStmt(sql,args);
                try{
                    return stmt.executeUpdate();
                } finally {
                    stmt.getConnection().close();
                }
            }catch(SQLException e){
                throw new RuntimeException(e);
            }
        }
    // thuc hien cau truy van la select hoac thu tuc luu tru
        public static ResultSet query (String sql, Object ... args){
            try{
                PreparedStatement stmt = getStmt(sql, args);
                return stmt.executeQuery();
            }catch(SQLException e){
                throw new RuntimeException(e);
            }
        }
    public static Object value(String sql, Object ... args){
        try{
            ResultSet rs = query(sql, args);
            if(rs.next()){
                return rs.getObject(0);
            }
            rs.getStatement().getConnection().close();
        }catch(Exception e){
            throw new RuntimeException(e);
        }
        return null;
    }
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(dburl, user, pass);
    }
    
}
