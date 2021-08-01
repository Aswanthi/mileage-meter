/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fuelcalc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JFrame;

/**
 *
 * @author Aswanthi
 */
public class DbOperations {
    
    DbOperations(){
        try{  
            Class.forName("com.mysql.jdbc.Driver");  
            }catch(Exception e){
                    
            }
    }
            
     Connection con;
     public Connection getConnection(FuelCalc obj) throws Exception
     {
          con=DriverManager.getConnection("jdbc:mysql://localhost:3306/fuel","root","");  
          return con;
     }
     
     public  boolean insertinitialReadings(String date,String initialReading,String fuelUsed,FuelCalc obj) 
        {
            try{
                con= getConnection(obj);

                String sql = "INSERT INTO `bikemileagemaintenance`(`Date`, `InitialReading`, `Nooflitresoffuel`) "
                    + "VALUES ('" + date + "','" + initialReading + "','"+fuelUsed+"')";
                Statement st = con.createStatement();
                if(st.execute(sql))
                {
                 obj.alert("Failed to insert", "Alert");

                }else{
                   obj.alert("Data Inserted Successfully", "Alert");
                }
                con.close();
            }catch(Exception e){
                obj.alert("SQL Exception", "Alert");
            }
            return true;
        }
    public boolean insertfinalReadings(String date,String finalReading, FuelCalc obj)
    {
        try
        { 
           con= getConnection(obj);
           String sql = "UPDATE `bikemileagemaintenance` SET FinalReading='" + finalReading + "'WHERE date='" + date + "'";
            Statement st = con.createStatement();
               if(st.execute(sql))
                {
                 obj.alert("Failed to update", "Alert");

                }else{
                   obj.alert("Data Updated Successfully", "Alert");
                }
            
        }catch(Exception e){
                obj.alert("SQL Exception", "Alert");
            }
        
    return true;
    }
    
    public void fetchResults(FuelCalc obj,JFrame f){
        int kilometrestraveled=0,totalfuel=0;
        try
        {
          con= getConnection(obj);
          String query = "select * from bikemileagemaintenance";
            Statement stmt = con.createStatement();
            ResultSet rs=stmt.executeQuery(query);  
            while(rs.next()){
                int date = rs.getInt("date");
                int initialReading  = rs.getInt("InitialReading");
                int fuel = rs.getInt("Nooflitresoffuel");
                int finalReading = rs.getInt("FinalReading");
               
                if(finalReading >0 )
                {
                    kilometrestraveled += (finalReading - initialReading);
                    totalfuel +=fuel;
                }
            }      
              int mileage = kilometrestraveled/totalfuel;
              obj.displayResults(mileage,totalfuel,kilometrestraveled);
           
        }catch (SQLException e){
            obj.alert("SQL Exception", "Alert");
         }
        catch(Exception e){
            obj.alert( e.getMessage(),"Internal Error");
        }
    
    }
}
