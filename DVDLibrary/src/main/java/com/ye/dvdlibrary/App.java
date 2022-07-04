/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ye.dvdlibrary;

import com.ye.dvdlibrary.controller.*;
import com.ye.dvdlibrary.dao.*;
import com.ye.dvdlibrary.ui.*;

/**
 *
 * @author Yonathan
 */
public class App {
    public static void main(String[] args) throws DvdLibraryDaoException{
        //DvdLibraryController controller = new DvdLibraryController();
        // Dependency injection is being utilized here
        UserIO io = new UserIOConsoleImpl();
        DvdLibraryView appView = new DvdLibraryView(io);
        DvdLibraryDao appDao = new DvdLibraryDaoFileImpl();
        DvdLibraryController controller = new DvdLibraryController(appDao, appView);
        controller.run();
    }
}
