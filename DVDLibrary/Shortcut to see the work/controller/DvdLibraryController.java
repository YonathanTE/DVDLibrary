/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ye.dvdlibrary.controller;

import com.ye.dvdlibrary.dao.DvdLibraryDao;
import com.ye.dvdlibrary.dao.DvdLibraryDaoException;
import com.ye.dvdlibrary.ui.UserIO;
import com.ye.dvdlibrary.dao.*;
import com.ye.dvdlibrary.dto.Dvd;
import com.ye.dvdlibrary.ui.DvdLibraryView;
import java.util.*;
/**
 *
 * @author Yonathan
 */
public class DvdLibraryController {
    private UserIO io = new UserIOConsoleImpl();
    /* Hard-coded references to 'DvdLibraryDaoFileImpl & DvdLibraryView: */
    //private DvdLibraryView view = new DvdLibraryView(io);    
    //private DvdLibraryDao dao = new DvdLibraryDaoFileImpl();
    
    private DvdLibraryView view;
    private DvdLibraryDao dao; // Member, or part of the controller
    // Dependency injection is being utilized here 
    public DvdLibraryController(DvdLibraryDao dao, DvdLibraryView view) {
        this.dao = dao;
        this.view = view;
    }
        /* Purpose for having the view instance in constructor: DvdLibraryController should NOT inject 'UserIO' implementation into 
        'DvdLibraryView', that is supposed to be done by the 'App' class    */
    
    public void run() {
        boolean keepGoing = true;
        int menuSelection = 0;
        try {
        while (keepGoing) {
            // To retrieve the 'getMenuSelection()' for the ability to select from the menu
            menuSelection = getMenuSelection();
 
            // To iterate through the main menu
            switch(menuSelection) {
                case 1:
                    createDvd(); // Works 
                    break;
                case 2:
                    removeDvd(); // Works
                    break;
                case 3:
                    editDvd();
                    break;
                case 4:
                    listDvds(); // Works
                    break;
                case 5:
                    viewDvd(); // Works
                    break;
                case 6:
                    displayDvd(); // Works
                    break;
                case 7:
                    keepGoing = false;
                    break;
                default:
                    unknownCmmd();
            }
        } exitMessage(); //io.print("All done!");                
    } catch (DvdLibraryDaoException e) {
    // 'getMessage' is inherited *
    view.displayErrorMsg(e.getMessage());
    }
  }
    
    private int getMenuSelection()  {
        return view.printMenuAndGetSelection();
    }
    
    private void createDvd() throws DvdLibraryDaoException{
        view.displayCreateDvdBanner();
        Dvd newDvd = view.getNewDvdInfo();
        dao.addDVD(newDvd.getMovieId(), newDvd);
        view.displayCreateSuccessBanner();
    }
    
    private void listDvds() throws DvdLibraryDaoException {
        view.displayAllBanner();
        List<Dvd> dvdList = dao.getAllDvds();
        view.displayDvdList(dvdList);
    }
    
    private void viewDvd() throws DvdLibraryDaoException {
        view.displayDvdBanner();
        String movieId = view.getDvdmovieIdChoice(); 
        Dvd dvd = dao.getDvd(movieId);
        view.displayDvd(dvd);
    }
    
    private void removeDvd() throws DvdLibraryDaoException {
        view.displayRemoveDvdBanner();
        String title = view.getDvdmovieIdChoice(); 
        Dvd removedDvd = dao.removeDvd(title);
        view.displayRemoveResult(removedDvd);
    }
    
    // editDvd method would be here
    private void editDvd() throws DvdLibraryDaoException {
        String findTitle = view.getDvdTitle();
        int caseChoices = view.displayEditOptions();
        Dvd existingDvd = dao.getDvd(findTitle);
        String newInput = view.editDvdFields(caseChoices);
        Dvd dvdToBeEdited = dao.editDvdInfo(newInput, existingDvd, caseChoices);
        view.displaySuccessfulEdit(dvdToBeEdited);
    }
    
    private void unknownCmmd() {
        view.displayUnknownCmdBanner();
    }
    
    private void exitMessage() {
        view.displayExitBanner();
    }
    // Requirement #6
    private void displayDvd() throws DvdLibraryDaoException{
        view.displayTitle();
        String title = view.getDvdTitle(); // Works
        Dvd dvdChoice = dao.getTitle(title);
        view.displayDvd(dvdChoice);
    }
    
}
