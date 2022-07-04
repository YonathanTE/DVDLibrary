/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ye.dvdlibrary.dao;

/**
 *
 * @author Yonathan
 */
// 'extends' uses inheritance
public class DvdLibraryDaoException extends Exception {
    // Used when something is wrong in the app but isn't caused by another exception
    public DvdLibraryDaoException (String msg) {
        super(msg);
    }
    // Used when something is wrong in the app that's caused by another exception in the underlying implementation
    public DvdLibraryDaoException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
