package controllers;

import play.*;
import play.mvc.*;

import java.util.*;

import models.*;

public class Application extends Controller {

    public static void index() {
        render();
    }
public static void signIn(String username, String password) {
        boolean verify=false;
        //creaat a login object sent to a constructor in model 
        login existing = new login(username,password);
        
        //send to model(login.java) and connect to database
        verify=existing.check(existing.getUsername(),existing.getPassword());
            
            //This boolean check allows/disallows routing to next page 
            if(verify){
             //use it to read saved data   
            //  List accounts = AccountRepo.getAccountTypes();
            //  List activity = AccountRepo.getAccountActivity();
                
             render("@Application.build");
                 
            }
       
         }
}