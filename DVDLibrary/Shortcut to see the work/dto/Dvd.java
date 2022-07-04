/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ye.dvdlibrary.dto;

/**
 *
 * @author Yonathan
 */
public class Dvd {
    private String title;
    private String movieId;
    private String releaseDate;
    private String mpaaRating;
    private String directorName;
    private String studio;
    private String userRating;
    
    // How an individual DVD will be searched up with *
    // 'movieId' is only passed as a parameter & will NOT require a setter in this field
    public Dvd(String movieId) {
        this.movieId = movieId;
    }

    // All other fields are read/write & will be set manually after a DVD object gets instantiated
    public String getMovieId() {
        return movieId;
    }
    
    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }
    // Requirement 6
    public String getTitle(){
        return title;
    }
    // Requirement 6
    public void setTitle(String title){
        this.title = title;
    }

    public String getReleaseDate(){
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate){
        this.releaseDate = releaseDate;
    }
    
    public String getMpaaRating(){
        return mpaaRating;
    }
    
    public void setMpaaRating(String mpaaRating){
        this.mpaaRating = mpaaRating;
    }
    
    public String getDirectorName(){
        return directorName;
    }
    
    public void setDirectorName(String directorName){
        this.directorName = directorName;
    }
    
    public void setStudio(String studio){
        this.studio = studio;
    }
    
    public String getStudio(){
        return studio;
    }
    
    public String setUserRating(String userRating){
      return this.userRating = userRating;
    }
    
    public String getUserRating(){
        return userRating;
    }
}