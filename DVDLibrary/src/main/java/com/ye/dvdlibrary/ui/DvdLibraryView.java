/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ye.dvdlibrary.ui;
import com.ye.dvdlibrary.dto.Dvd;
import java.util.List;
/**
 *
 * @author Yonathan
 */
public class DvdLibraryView {
    //private UserIO io = new UserIOConsoleImpl();
    // Dependency injection is being utilized here
    private UserIO io;
    public DvdLibraryView(UserIO io) {
        this.io = io;
    }

    public int printMenuAndGetSelection() {
            io.print("Main Menu");
            io.print("1. Add a DVD");
            io.print("2. Remove a DVD");
            io.print("3. Edit a DVD");
            io.print("4. List all DVDs");
            io.print("5. Display a DVD");
            io.print("6. Enter title for DVD");
            io.print("7. Exit");
            return io.readInt("Please select from the above choices.", 1, 6);
    }
    
    public Dvd getNewDvdInfo(){
        String movieId = io.readString("Please enter the Movie ID");
        String title = io.readString("Please add the title");
        String releaseDate = io.readString("Please enter the release date");
        String mpaaRating = io.readString("Please enter the MPAA rating");
        String directorName = io.readString("Please enter the name of the director");
        String studio = io.readString("Please enter the studio that produced the film");
        String userRating = io.readString("Please enter any ratings that you might have");
        Dvd currentDvd = new Dvd(movieId); // Identifier for any Dvd
        currentDvd.setTitle(title);
        currentDvd.setMpaaRating(mpaaRating);
        currentDvd.setReleaseDate(releaseDate);
        currentDvd.setStudio(studio);
        currentDvd.setDirectorName(directorName); 
        currentDvd.setUserRating(userRating);
        return currentDvd;
    }
    
    public void displayCreateDvdBanner() {
        io.print("=== Create Dvd ===");
    }
    
    public void displayCreateSuccessBanner() {
        io.readString("Dvd successfully created. Please hit enter to continue");
    }
    
    // Options to edit an item object that already exists
    public int displayEditOptions() {
        io.print("What field would you like to edit?");
        io.print("Movie Id");
        io.print("Title");
        io.print("Release Date");
        io.print("MPAA Rating");
        io.print("Director's Name");
        io.print("Studio");
        io.print("User Rating");
        
        return io.readInt("Please pick the field you would like to alter", 1 ,6);
    }
    
    public String editDvdFields(int caseChoice) {
        String choice = "";
        switch (caseChoice) {
            case 1:
                choice = io.readString("Please enter a new movie id");
                break;
            case 2:
                choice = io.readString("Please enter a new title");
                break;
            case 3:
                choice = io.readString("Please enter a new release date");
                break;
            case 4:
                choice = io.readString("Please enter a new MPAA rating");
                break;
            case 5:
                choice = io.readString("Please enter the new name of the director");
                break;
            case 6:
                choice = io.readString("Please enter a new studio");
                break;
            case 7:
                choice = io.readString("Please enter another user rating");
                break;
        }
        return choice;
    }
    
    public void displaySuccessfulEdit(Dvd dvdToBeEdited) {
        if (dvdToBeEdited != null) {
            io.print("Dvd was successfully edited.");
        } else {
            io.print("No such DVD.");
        }
        io.readString("Please hit enter to continue.");
    }

    public void displayAllBanner() {
        io.print("=== Display All Dvds ===");
    }
    
    public void displayDvdList(List<Dvd> dvdList) {
            for (Dvd currentDvd : dvdList) {
                String dvdInfo = String.format("#%s : %s %s %s %s %s %s",
                        // Supposed to be able to list all elements, but there is an issue currently
                        currentDvd.getMovieId(),
                        currentDvd.getTitle(),
                        currentDvd.getMpaaRating(),
                        currentDvd.getReleaseDate(),
                        currentDvd.getStudio(),
                        currentDvd.getDirectorName(),
                        currentDvd.getUserRating());
                io.print(dvdInfo);
            }
            io.readString("Please hit enter to continue.");
    }
    
    public void displayDvdBanner() {
        io.print("=== Display DVD ===");
    }
    
    public String getDvdmovieIdChoice() {
        return io.readString("Please enter the DVD movie ID choice");
    }

    public String displayTitle() {
        return io.readString("=== DVD title ===");
    }
    // Requirement #6
    public void displayTitleSuccessBanner() {
        io.print("Search for DVD title was successful. Please hit enter to continue.");
    }
    // Would be used for the edit option as well
    public String getDvdTitle() {
        return io.readString("Please enter the DVD title.");
    }
    
    // Pulls based on 'movieId', instead of the title like I originally desired *
    public void displayDvd(Dvd dvd){
        if (dvd != null) {
            io.print(dvd.getMovieId());
            io.print(dvd.getTitle());
            io.print(dvd.getMpaaRating());
            io.print(dvd.getDirectorName());
            io.print(dvd.getReleaseDate());
            io.print(dvd.getStudio());
            io.print(dvd.getUserRating());
        } else {
            io.print("No such DVD");
        }
        io.readString("Please hit enter to continue.");
    }
    
    public void displayRemoveDvdBanner() {
        io.print("=== Remove Dvd ===");
    }
    
    public void displayRemoveResult(Dvd dvdRecord) {
        if (dvdRecord != null) {
            io.print("Dvd was successfully removed.");
        } else {
            io.print("No such DVD.");
        }// Might need to enter the question again asking if the user would like to do it again
        io.readString("Please hit enter to continue.");
    }
    
    public void displayExitBanner() {
        io.print("Good Bye!");
    }
    
    public void displayUnknownCmdBanner() {
        io.print("Unknown command");
    }
    
    public void displayErrorMsg(String errMsg) {
        io.print("=== ERROR ===");
        io.print(errMsg);
    }
    
}
