package action;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author acremona
 */
public abstract class Action {
    
    public abstract void executer(HttpServletRequest request);
    
}
