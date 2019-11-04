package com.playfy.controller;

import com.playfy.exception.ResourceNotFoundException;
import com.playfy.model.Song;
import com.playfy.repository.SongsRepository;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/")
public class SongController {

    @Autowired
    private SongsRepository songsRepository;

    @GetMapping("/status")
    public Map<String, Object> showStatus(){
        HashMap<String, Object> map = new HashMap<>();
        map.put("status:","OK");

        HashMap<String, Object> rutas = new HashMap<>();

        HashMap<String, Object> allSongsMap = new HashMap<>();

        List<Song> allSongs = getAllSongs();
        if(!allSongs.isEmpty()) {
            allSongsMap.put("status: ", "OK");
            allSongsMap.put("contenido: ", allSongs);
        } else {
            allSongsMap.put("status: ", "DOWN");
        }
        rutas.put("GET /api/songs", allSongsMap);

        HashMap<String, Object> songsByIdMap = new HashMap<>();
        try {
            ResponseEntity<Song> songsById = getSongsById(0);
            Song song = songsById.getBody();
            songsByIdMap.put("status: ", "OK");
            songsByIdMap.put("contenido: ", song);
        } catch (ResourceNotFoundException e) {
            songsByIdMap.put("status","DOWN");
        }
        rutas.put("GET /api/songs/{id}", songsByIdMap);
        map.put("rutas",rutas);

        return map;
    }

    @GetMapping("/songs")
    public List<Song> getAllSongs() {
        return songsRepository.findAll();
    }

    @GetMapping("/songs/{id}")
    public ResponseEntity<Song> getSongsById(@PathVariable(value = "id") Integer songId)
            throws ResourceNotFoundException {
        Song song = songsRepository
                        .findById(songId)
                        .orElseThrow(() -> new ResourceNotFoundException("Song with id "  + songId + " not found"));

        return ResponseEntity.ok().body(song);
    }

    @PostMapping("/songs")
    public Song addSong(@Valid @RequestBody Song song) {
        return songsRepository.save(song);
    }

    @PostMapping("/songs/{id}")
    public ResponseEntity<Song> updateSong(
           @PathVariable(value = "id") Integer songId, @Valid @RequestBody Song songDetails)
           throws ResourceNotFoundException {
        Song song =
                songsRepository
                        .findById(songId)
                        .orElseThrow(() -> new ResourceNotFoundException("Song with id " + songId + " not found"));

        song.setTitle(songDetails.getTitle());
        song.setAlbum(songDetails.getAlbum());
        song.setAuthor(songDetails.getAuthor());
        song.setYear(songDetails.getYear());
        final Song updatedSong = songsRepository.save(song);
        return ResponseEntity.ok(updatedSong);
    }

    @DeleteMapping("/songs/{id}")
    public Map<String, Boolean> deleteSong(@PathVariable(value = "id") Integer songId)
            throws Exception {
        Song song =
                songsRepository
                    .findById(songId)
                    .orElseThrow(() -> new ResourceNotFoundException("Song with id " + songId + " not found"));

        songsRepository.delete(song);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

}
