/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ye.dvdlibrary.ui;

/**
 *
 * @author Yonathan
 */
public interface UserIO {
    void print(String msg);
    
    double readDouble(String prompt);
    
    double readDouble(String prompt, double min, double max);
    
    float readFloat(String prompt);
    
    float readFloat(String prompt, float min, float max);
    
    int readInt(String prompt);

    int readFloat(String prompt, int min, int max);

    long readLong(String prompt);
    
    long readLong(String prompt, long min, long max);
    
    String readString(String prompt);

    public int readInt(String prompt, int i, int i0);
}
