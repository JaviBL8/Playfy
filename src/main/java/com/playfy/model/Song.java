package com.playfy.model;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Entity
@Table(name = "songs")
@EntityListeners(AuditingEntityListener.class)
public class Song {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "album", nullable = false)
    private String album;

    /*Metodos set y get*/

    public long getID() {
        return this.id;
    }

    public void setID(Integer ID) {
        this.id=ID;
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
