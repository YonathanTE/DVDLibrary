/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ye.dvdlibrary.dao;

import com.ye.dvdlibrary.dto.Dvd;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Yonathan
 */
public class DvdLibraryDaoFileImpl implements DvdLibraryDao {
    // Name of file was the issue. I assumed it would read as 'library', but it wanted the actual name of 'library.txt'
    public static final String LIBRARY_FILE = "library.txt";
    public static final String DELIMITER = "::";
    
    Scanner inputReader = new Scanner(System.in);
    private Map<String, Dvd> dvds = new HashMap<>();    
    
    /**
     *
     * @param movieId
     * @param dvd
     * @return
     * @throws DvdLibraryDaoException
     */
    @Override
    public Dvd addDVD(String movieId, Dvd dvd) throws DvdLibraryDaoException {
        loadLibrary();
        Dvd prevDvd = dvds.put(movieId, dvd);
        writeLibrary();
        return prevDvd;
    }
    
    /* Error from the 'getAllDvds' method declaration in the DvdLibraryDao interface 
    does NOT specify that it throws an DvdLibraryDaoException    */
    @Override
    public List<Dvd> getAllDvds() throws DvdLibraryDaoException {
        loadLibrary();
        return new ArrayList(dvds.values());
    }

    @Override
    public Dvd getTitle(String title) {
        // To search for a DVD by title (Requirement #6)
        return dvds.get(title);
    }

    @Override
    public Dvd getDvd(String movieId) throws DvdLibraryDaoException {
        loadLibrary();
        return dvds.get(movieId);
    }
    
    @Override
    public Dvd removeDvd(String movieId) throws DvdLibraryDaoException {
        loadLibrary();
        Dvd removedDvd = dvds.remove(movieId);
        writeLibrary();
        return removedDvd;
        // For requirement #9, ask again and permit the code to work again within a loop *
    }
    
    
    @Override
    public Dvd editDvdInfo(String newInput, Dvd editedDvd, int field) throws DvdLibraryDaoException {
        try {
            loadLibrary();
            switch (field) {
                case 1:
                    editedDvd.setMovieId(newInput);
                    writeLibrary();
                    break;
                case 2:
                    editedDvd.setTitle(newInput);
                    writeLibrary();
                    break;
                case 3:
                    editedDvd.setReleaseDate(newInput);
                    writeLibrary();
                    break;
                case 4:
                    editedDvd.setMpaaRating(newInput);
                    writeLibrary();
                    break;
                case 5:
                    editedDvd.setDirectorName(newInput);
                    writeLibrary();
                    break;
                case 6:
                    editedDvd.setStudio(newInput);
                    writeLibrary();
                    break;
                case 7:
                    editedDvd.setUserRating(newInput);
                    writeLibrary();
                    break;
                default:
                    System.out.println("Not an option");
            }            
        } catch (DvdLibraryDaoException ex) {
            Logger.getLogger(DvdLibraryDaoFileImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return editedDvd;
    }
    
    private Dvd unmarshallDvd(String dvdAsText){
        String[] dvdTokens = dvdAsText.split(DELIMITER);
        String movieId = dvdTokens[0];
        Dvd dvdFromFile = new Dvd(movieId); 
        dvdFromFile.setTitle(dvdTokens[1]);
        dvdFromFile.setDirectorName(dvdTokens[2]);
        dvdFromFile.setMpaaRating(dvdTokens[3]);
        dvdFromFile.setReleaseDate(dvdTokens[4]);
        dvdFromFile.setStudio(dvdTokens[5]);
        dvdFromFile.setUserRating(dvdTokens[6]);
        return dvdFromFile;
    }
    
    private void loadLibrary() throws DvdLibraryDaoException { // Error *
        Scanner inputReader;
        try {
            // Make the Scanner for reading the file
            inputReader = new Scanner(
                            new BufferedReader(
                            new FileReader(LIBRARY_FILE)));
        } catch (FileNotFoundException e) {
            throw new DvdLibraryDaoException("Unable to load library data into memory", e);
        }
        String currentLine;
        Dvd currentDvd;
        /* Loop goes through the LIBRARY_FILE line by line, decoding each line 
        into a Dvd object by calling the unmarshallDvd method */
        while (inputReader.hasNextLine()) {
            currentLine = inputReader.nextLine();
            // Unmarshall the line into a DVD
            currentDvd = unmarshallDvd(currentLine);           
            // Use the dvd movieId as the map key for the dvd object
            dvds.put(currentDvd.getMovieId(), currentDvd);
        }
        inputReader.close();
    }
    
    private String marshallDvd(Dvd aDvd) {
        // Movie Id
        String dvdAsTxt = aDvd.getMovieId() + DELIMITER;
        // Title
        dvdAsTxt += aDvd.getTitle() + DELIMITER;
        // Release date
        dvdAsTxt += aDvd.getReleaseDate() + DELIMITER;
        // MPAA rating
        dvdAsTxt += aDvd.getMpaaRating() + DELIMITER;
        // Director's name
        dvdAsTxt += aDvd.getDirectorName() + DELIMITER;
        // Studio
        dvdAsTxt += aDvd.getStudio() + DELIMITER;
        // User rating or note, ensure the delimiter is skipped
        dvdAsTxt += aDvd.getUserRating();
        return dvdAsTxt;
    }
    
    private void writeLibrary() throws DvdLibraryDaoException {
        PrintWriter out;
        
        try {
            out = new PrintWriter(new FileWriter(LIBRARY_FILE));
        } catch(IOException e) {
            throw new DvdLibraryDaoException("Could not save the data for the dvd", e);
        }
        
        String dvdAsTxt;
        List<Dvd> dvdList = this.getAllDvds();
        for (Dvd currentDvd : dvdList) {
            // Convert Dvd object into a String
            dvdAsTxt = marshallDvd(currentDvd);
            // Writing the object into the file
            out.println(dvdAsTxt);
            out.flush();
        }
        out.close();
    }
}