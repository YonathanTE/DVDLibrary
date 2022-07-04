/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ye.dvdlibrary.dao;

import com.ye.dvdlibrary.dto.Dvd;
import java.util.*;
/**
 *
 * @author Yonathan
 */
public interface DvdLibraryDao {
    // Requirement(s):
    // 1 
    Dvd addDVD(String movieId, Dvd dvd) throws DvdLibraryDaoException; /* NEED TO INSPECT & DISCOVER WHY IT DOES NOT WORK. 
    IF THIS DOES NOT WORK, THAT'S REALLLLY BAD. LIKE REALLY BAD... **
    */
    // 2
    Dvd removeDvd(String movieId) throws DvdLibraryDaoException;
    // 3
    Dvd editDvdInfo(String newInput, Dvd editedDvd, int field) throws DvdLibraryDaoException;
    // Dvd editDvdInfo(String eTitle,String eReleaseDate,String eMpaaRating,String eDirectorName,String eStudio,String eUserRating);
    // 4
    List<Dvd> getAllDvds() throws DvdLibraryDaoException;
    // 5
    Dvd getDvd(String movieId) throws DvdLibraryDaoException;
    
    //Dvd getMpaaRating(String mpaaRating) throws DvdLibraryDaoException;
    // 6
    Dvd getTitle(String title) throws DvdLibraryDaoException;

}
