/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import play.data.validation.Required;

/**
 *
 * @author seifu001
 */
public class login {
     // validator
    @Required public String username;
    @Required public String password;
    
    //constructor, though java automatically creates one
    public login(){}
    
    /**constructor of each login attempt, helpful for multiple user access
        @param String
        @param String
        @return none
        */
    public login(String onlineID,String passcode){
        this.username=onlineID;
        this.password=passcode;
    }
    
    /** setter
        @param String
        @return none
        */
    public void setUsername(String onlineID){
        this.username=onlineID;
    }
    
    /** getter
        @param String
        @return String
        */
    public String getUsername(){
        return this.username;
    }
    
    /** setter
        @param String
        @return none
        */
    public void setPassword(String passcode){
        this.password=passcode;
    }
    
    /** getter
        @param String
        @return String
        */
    public String getPassword(){
        return this.password;
    }
   /** recieves constructed login object from controller
    * returns true/false if designated user object exists or not
        @param String
        @param String
        @return boolean
        */
    public boolean check(String username,String password){
     
                boolean verify=false;
                Connection conn=null;
                Statement stmt=null;
                ResultSet rs=null;
                try{
                     
                  
                  String url = "jdbc:mysql:/...";
                  String user= "...";
                  String pwd="...";

                    Class.forName("com.mysql.jdbc.Driver");
                    conn = DriverManager.getConnection(url,user,pwd);
                    stmt=conn.createStatement();
                    
                    String sql;
                    
                    
                     sql="SELECT username,password from heroku_c36d590dae49126.account";
                     stmt.executeQuery(sql);
                     rs = stmt.getResultSet();
                     
                    //read each resultset 
                    while(rs.next()){
                         String loginID=rs.getString("username");                       
                         String pas=rs.getString("password");
                         //String loginID=Integer.toString(uID);
                           
                         if (username.equals(loginID) && password.equals(pas)){
                             verify=true; 
                         }
                        
                    }

                    rs.close();
                    stmt.close();
                    conn.close();
                }catch(SQLException se){
                    se.printStackTrace();

                }catch(Exception e){
                    e.printStackTrace();
                }finally{
              //finally block used to close resources
              try{
                 if(stmt!=null)
                    stmt.close();
              }catch(SQLException se2){
              }// nothing we can do
              try{
                 if(conn!=null)
                    conn.close();
              }catch(SQLException se){
                 se.printStackTrace();
              }//end finally try
           }//end try
       return verify;
    }
    
}
