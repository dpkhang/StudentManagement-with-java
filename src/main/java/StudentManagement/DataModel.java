/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package StudentManagement;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;

/**
 *
 * @author khang
 */
public class DataModel {
    private ConnectDB conn;
    
    public DataModel () {
        this.conn = new ConnectDB();
    }
    
    public String[] getUserByUsername(String username, String password) {
        String[] user;
        user = new String[5];
        try {
            conn.openDB();
            String query = "Select * from giangvien where magv = ? and password = ?";
            PreparedStatement ps;
            ps = conn.conn.prepareStatement(query);
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                user[0] = rs.getString("magv");
                user[1] = rs.getString("ten");
                user[2] = rs.getString("email");
                user[3] = rs.getString("phone");
                user[4] = rs.getString("gioitinh");
            }
            conn.closeDB();
        }catch(Exception ex) {
            ex.printStackTrace();
        }
        return user;
    }
    
    public ArrayList<String[]> getStudents(String mamon) {
        ArrayList<String[]> result = new ArrayList<>();
        try {
            conn.openDB();
            String query = "Select ten, hoc.mssv, email, phone, tenlop from hoc join sinhvien on hoc.mssv = sinhvien.mssv join lop on sinhvien.malop = lop.malop where mamon = ?";
            PreparedStatement ps;
            ps = conn.conn.prepareStatement(query);
            ps.setString(1, mamon);
            ResultSet rs = ps.executeQuery();
            ResultSetMetaData rsMeta = ps.getMetaData();
            int col = rsMeta.getColumnCount();
            result = new ArrayList<>();
            String[] colData;
            while(rs.next()){
                colData = new String[col];
                colData[0] = rs.getString("mssv");
                colData[1] = rs.getString("ten");
                colData[2] = rs.getString("email");
                colData[3] = rs.getString("phone");
                colData[4] = rs.getString("tenlop");
                result.add(colData);
            }
            conn.closeDB();
        }
        catch(Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }
    
    public ArrayList<String[]> getAllStudents() {
        ArrayList<String[]> result = new ArrayList<>();
        try {
            conn.openDB();
            String query = "Select ten, mssv, email, phone, tenlop from sinhvien join lop on sinhvien.malop = lop.malop";
            PreparedStatement ps;
            ps = conn.conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            ResultSetMetaData rsMeta = ps.getMetaData();
            int col = rsMeta.getColumnCount();
            result = new ArrayList<>();
            String[] colData;
            while(rs.next()){
                colData = new String[col];
                colData[0] = rs.getString("mssv");
                colData[1] = rs.getString("ten");
                colData[2] = rs.getString("email");
                colData[3] = rs.getString("phone");
                colData[4] = rs.getString("tenlop");
                result.add(colData);
            }
            conn.closeDB();
        }
        catch(Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }
        
    public ArrayList<String[]> getAllClass() {
        ArrayList<String[]> result = new ArrayList<>();
        try {
            conn.openDB();
            String query = "select * from lop";
            PreparedStatement ps = conn.conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            ResultSetMetaData rsMeta = rs.getMetaData();
            int col = rsMeta.getColumnCount();
            String[] data;
            while(rs.next()){
                data = new String[col];
                data[0] = rs.getString("malop");
                data[1] = rs.getString("tenlop");
                result.add(data);
            }
            
            conn.closeDB();
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return result;
    }
    
    public ArrayList<String[]> getCourses() {
        ArrayList<String[]> result = new ArrayList<>();
        try {
            conn.openDB();
            String query = "select * from monhoc";
            PreparedStatement ps = conn.conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            ResultSetMetaData rsMeta = rs.getMetaData();
            int col = rsMeta.getColumnCount();
            String[] data;
            while(rs.next()){
                data = new String[col];
                data[0] = rs.getString("mamon");
                data[1] = rs.getString("tenmon");
                data[2] = rs.getString("tinchi");
                result.add(data);
            }
            conn.closeDB();
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return result;
    }
    
    public ArrayList<String[]> getCouresOfUser(String magv) {
        ArrayList<String[]> result = new ArrayList<>();
        try {
            conn.openDB();
            String query = "Select * from giangvien join giangday on giangvien.magv = giangday.magv join monhoc on monhoc.mamon = giangday.mamon where giangvien.magv = ?";
            PreparedStatement ps = conn.conn.prepareStatement(query);
            ps.setString(1, magv);
            ResultSet rs = ps.executeQuery();
            ResultSetMetaData rsMeta = rs.getMetaData();
            int col = rsMeta.getColumnCount();
            String[] colData;
            while(rs.next()){
                colData = new String[col];
                colData[0] = rs.getString("mamon");
                colData[1] = rs.getString("tenmon");
                colData[2] = rs.getString("tinchi");
                result.add(colData);
            }
            conn.closeDB();
        }
        catch(Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }
    
    public ArrayList<String[]> getScore(String mamon) {
        ArrayList<String[]> score = new ArrayList<>();
        try{
            conn.openDB();
            String query  = "Select sinhvien.mssv, ten, email, diem from hoc join sinhvien on hoc.mssv = sinhvien.mssv join monhoc on monhoc.mamon = hoc.mamon where hoc.mamon = ?";
            PreparedStatement ps = conn.conn.prepareStatement(query);
            ps.setString(1, mamon);
            ResultSet rs = ps.executeQuery();
            ResultSetMetaData rsData = rs.getMetaData();
            int col = rsData.getColumnCount();
            String[] colData;
            while (rs.next()) {
                colData = new String[col];
                colData[0] = rs.getString("mssv");
                colData[1] = rs.getString("ten");
                colData[2] = rs.getString("email");
                colData[3] = rs.getString("diem");
                score.add(colData);
            }
            conn.closeDB();
        }catch(Exception ex) {
            ex.printStackTrace();
        }
        return score;
    }
    
    public int updateUser(String ten, String email, String phone, String gioitinh, String magv ){
        int bool = 0;
        try {
            conn.openDB();
            String query = "update giangvien set ten = ?, email = ?, phone = ?, gioitinh = ? where magv = ?";
            PreparedStatement ps = conn.conn.prepareStatement(query);
            ps.setString(1, ten);
            ps.setString(2, email);
            ps.setString(3, phone);
            ps.setString(4, gioitinh);
            ps.setString(5, magv);
            bool = ps.executeUpdate();
            conn.closeDB();
        }catch(Exception ex) {
            ex.printStackTrace();
        }
        
        return bool;
    }
    
    public int updateStudent(String type, String mssv, String ten, String email, String phone, String lop ){
        int bool = 0;
        try {
            conn.openDB();
            String query = "";
            PreparedStatement ps; 
            if(type == "insert") {
                query = "insert into sinhvien values (? ,? ,? ,? ,?)";
                ps = conn.conn.prepareStatement(query);
                ps.setString(1, mssv);
                ps.setString(2, ten);
                ps.setString(3, email);
                ps.setString(4, phone);
                ps.setString(5, lop);
            }else{
                System.out.println (ten + " " + email + " " + phone + " " + lop + " " + mssv );
                query = "update sinhvien set ten = ?, email = ?, phone = ?, malop = ? where mssv = ?";
                ps = conn.conn.prepareStatement(query);
                ps.setString(1, ten);
                ps.setString(2, email);
                ps.setString(3, phone);
                ps.setString(4, lop);
                ps.setString(5, mssv);
            }
            bool = ps.executeUpdate();
            conn.closeDB();
        }catch(Exception ex) {
            ex.printStackTrace();
        }
        
        return bool;
    }
    
    public int updateCourse(String maMon, String magv) {
        int bool = 0;
        try {
            conn.openDB();
            PreparedStatement ps;
            String query;            
            query = "Insert into giangday values (?, ?)";
            ps = conn.conn.prepareStatement(query);
            ps.setString(1, magv);
            ps.setString(2, maMon);
            bool = ps.executeUpdate();
            conn.closeDB();
        }catch(Exception ex) {
            ex.printStackTrace();
        }
        return bool;
    }   
    
    public int updateScore(String mamon, String mssv, String diem) {
        int bool = 0;
        try {
            conn.openDB();
            PreparedStatement ps;
            String query;
            query = "Update hoc set diem = ? where mssv = ? and mamon = ?;";
            ps = conn.conn.prepareStatement(query);
            ps.setString(1, diem);
            ps.setString(2, mssv);
            ps.setString(3, mamon);
            bool = ps.executeUpdate();
            conn.closeDB();
        }catch(Exception ex) {
            ex.printStackTrace();
        }
        return bool;
    }
    
    public int deleteStudent(String mssv) {
        int bool = 0;
        try {
            conn.openDB();
            PreparedStatement ps;
            String query = "delete from hoc where mssv = ?";
            ps = conn.conn.prepareStatement(query);
            ps.setString(1, mssv);
            int executeUpdate = ps.executeUpdate();
            PreparedStatement ps2;
            String query2 = "delete from sinhvien where mssv = ?";
            ps2 = conn.conn.prepareStatement(query2);
            ps2.setString(1, mssv);
            bool = ps2.executeUpdate();
        }catch(Exception ex) {
            ex.printStackTrace();
        }
        return bool;
    }  
    
    public int deleteCourse(String maMon, String magv) {
        int bool = 0;
        try {
            conn.openDB();
            PreparedStatement ps;
            String query = "delete from giangday where maMon = ? and magv = ?";
            ps = conn.conn.prepareStatement(query);
            ps.setString(1, maMon);
            ps.setString(2, magv);
            bool = ps.executeUpdate();
        }catch(Exception ex) {
            ex.printStackTrace();
        }
        return bool;
    }
}
