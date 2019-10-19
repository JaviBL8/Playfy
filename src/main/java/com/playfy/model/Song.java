package com.playfy.model;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Entity
@Table(name = "songs")
@EntityListeners(AuditingEntityListener.class)
public class Song {

    @Id
    private Integer id;

    @Column(name = "author", nullable = false)
    private String author;

    @Column(name = "year", nullable = false)
    private Integer year;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "album", nullable = false)
    private String album;



    /* Métodos set y get */
    public long getID() {
        return this.id;
    }

    public void setID(Integer ID) {
        this.id=ID;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author=author;
    }

    public int getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year=year;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title=title;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album=album;
    }


}
