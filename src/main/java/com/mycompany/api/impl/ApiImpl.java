/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.api.impl;

import com.mycompany.api.MapAPI;
import com.mycompany.api.model.A;
import com.mycompany.api.model.B;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 *
 * @author BestKung
 */
public class ApiImpl<form, to> {

    private form f;
    private to t;

    public ApiImpl(form f, to t) {
        this.f = f;
        this.t = t;
    }

    public void getAttribute(form f, to t) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, InstantiationException, NoSuchMethodException {
        Class a = f.getClass();
        Class b = t.getClass();
        System.out.println(a);
        System.out.println(b);

        Method mA[] = a.getMethods();
        Method mB[] = b.getMethods();
        Method mSetB[] = new Method[mA.length];
        Method mGetA[] = new Method[mA.length];

        int incrementMethodGet = 0;
        int incrementMethodSet = 0;

        for (int i = 0; i < mA.length; i++) {
            for (int j = 0; j < mB.length; j++) {
                if (mA[i].getName().equals(mB[j].getName()) && (mB[j].getName().substring(0, 3).equals("get") || mB[j].getName().substring(0, 3).equals("set")) && (!mB[j].getName().equals("getClass"))) {
                    if (mA[i].getName().substring(0, 3).equals("get")) {
                        mGetA[incrementMethodGet] = mB[j];
                        incrementMethodGet++;
                    }
                    if (mA[i].getName().substring(0, 3).equals("set")) {
                        mSetB[incrementMethodSet] = mB[j];
                        incrementMethodSet++;
                    }
                    break;
                }
            }
        }

        try {
            for (int i = 0; i < mSetB.length; i++) {
                for (int j = 0; j < mGetA.length; j++) {
                    if (mSetB[i].getName().substring(3).equals(mGetA[j].getName().substring(3))) {
                        Method methodA = a.getDeclaredMethod(mGetA[j].getName(), null);
                        Method methodB = b.getDeclaredMethod(mSetB[i].getName(), mSetB[i].getParameterTypes());
                        methodB.invoke(t, methodA.invoke(f, null));
                        break;
                    }

                }
            }
        } catch (NullPointerException e) {
            System.err.println("Null Pointer Naja");
        }

    }

}
