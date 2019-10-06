package com.playfy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.playfy.model.Song;

@Repository
public interface SongsRepository extends JpaRepository<Song, Integer> {}
