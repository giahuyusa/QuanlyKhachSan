package com.khachsan.dao;

import com.khachsan.utils.Jdbc;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DoanhThuDAO {
    private List<Object[]> getListOfArray(String sql,String[] cols,Object ... args){
        try{
            List<Object[]> list = new ArrayList<>();
            // mang chua
            ResultSet rs = Jdbc.query(sql, args);

            while(rs.next()){
                Object[] vals = new Object[cols.length];
                for(int i = 0;i < cols.length;i++){
                    vals[i] = rs.getObject(cols[i]);
                }
                list.add(vals);
            }
            rs.getStatement().getConnection().close();
            return list;
        }catch(Exception e){
            throw new RuntimeException(e);
        }

    }

}
