/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.api.main;

import com.mycompany.api.MapAPI;
import com.mycompany.api.impl.ApiImpl;
import com.mycompany.api.model.A;
import com.mycompany.api.model.B;
import java.lang.reflect.InvocationTargetException;

/**
 *
 * @author BestKung
 */
public class Main {
    public static void main(String[] args) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, InstantiationException, NoSuchMethodException {
        A a = new A();
        a.setId(1);
        a.setfName("f");
        a.setlName("g");
        
        
        B b = new B();
        ApiImpl<A,B> apiImpl = new ApiImpl(a,b);
        
        apiImpl.getAttribute(a,b);
        System.out.println(b.getId());
        System.out.println(b.getfName());
        System.out.println(b.getlName());
    }
}
