package com.javibl8;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.playfy.Application;
import com.playfy.model.Song;
import com.playfy.repository.SongsRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.http.*;
import org.springframework.web.client.HttpClientErrorException;

import javax.validation.constraints.AssertTrue;
import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(locations = "classpath:db-test.properties")
@Sql("/songs_database.sql")
public class PlayfyTests {

    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    private int port;

    @Autowired
    private SongsRepository songsRepository;

    private String getRootUrl() {
        return "http://localhost:" + port;
    }

    @Test
    public void getAllSongs(){
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<>(null, headers);

        ResponseEntity<String> response = restTemplate.exchange(getRootUrl() + "/api/songs", HttpMethod.GET, entity, String.class);
        List<Song> allSongs = songsRepository.findAll();

        for(Song s : allSongs){
            Assert.assertTrue(response.getBody().contains(s.getID()+""));
            Assert.assertTrue(response.getBody().contains(s.getTitle()));
            Assert.assertTrue(response.getBody().contains(s.getAlbum()));
            Assert.assertTrue(response.getBody().contains(s.getAuthor()));
            Assert.assertTrue(response.getBody().contains(s.getYear()+""));
        }
        Assert.assertEquals(response.getStatusCode(),HttpStatus.OK);
        Assert.assertNotNull(response.getBody());
    }

    @Test
    public void testGetSongById(){
        Song song = restTemplate.getForObject(getRootUrl() + "/api/songs/0", Song.class);
        Optional<Song> one = songsRepository.findById(0);

        Assert.assertNotNull(song);
        Assert.assertEquals(song.getTitle(),one.get().getTitle());
        Assert.assertTrue(song.getAlbum().equals(one.get().getAlbum()));
        Assert.assertEquals(song.getYear(), one.get().getYear());
        Assert.assertTrue(song.getAuthor().equals(one.get().getAuthor()));
        Assert.assertEquals(song.getID(),one.get().getID());
    }

    @Test
    public void testAddSong(){
        String author = "Exa";
        String title = "Example";
        String album = "Example Album";
        int year = 1992;
        int id = 10;

        Song song = new Song();
        song.setYear(year);
        song.setAuthor(author);
        song.setTitle(title);
        song.setAlbum(album);
        song.setID(id);

        long count = songsRepository.count();

        ResponseEntity<Song> postResponse = restTemplate.postForEntity(getRootUrl() + "/api/songs", song, Song.class);
        Assert.assertNotNull(postResponse);
        Assert.assertNotNull(postResponse.getBody());

        long count1 = songsRepository.count();
        Assert.assertTrue(count1>count);

        songsRepository.deleteById(id);
    }

    @Test
    public void testUpdatePost(){
        int ID = 5;
        String author = "Exa";
        String title = "Example";
        String album = "Example Album";
        int year = 1992;

        Song song1=new Song();
        song1.setYear(year);
        song1.setAuthor(author);
        song1.setTitle(title);
        song1.setAlbum(album);
        song1.setID(ID);

        ResponseEntity<Song> postResponse = restTemplate.postForEntity(getRootUrl() + "/api/songs", song1, Song.class);

        Song song = restTemplate.getForObject(getRootUrl() + "/api/songs/" + ID, Song.class);
        song.setYear(year);
        song.setAuthor(author);
        song.setTitle(title);
        song.setAlbum(album);
        song.setID(ID);

        restTemplate.put(getRootUrl() + "/api/songs/" + ID, song);

        Song updatedSong = restTemplate.getForObject(getRootUrl() + "/api/songs/" + ID, Song.class);
        Assert.assertNotNull(updatedSong);

        Optional<Song> one = songsRepository.findById(5);

        Assert.assertEquals(title,one.get().getTitle());
        Assert.assertTrue(album.equals(one.get().getAlbum()));
        Assert.assertEquals(year, one.get().getYear());
        Assert.assertTrue(author.equals(one.get().getAuthor()));
        Assert.assertEquals(one.get().getID(),one.get().getID());

        songsRepository.deleteById(ID);
    }

    @Test
    public void testDeletePost(){
        int id=1;
        Song song = restTemplate.getForObject(getRootUrl() + "/api/songs/" + id, Song.class);
        Assert.assertNotNull(song);

        restTemplate.delete(getRootUrl() + "/api/songs/" + id);

        try{
            song = restTemplate.getForObject(getRootUrl() + "/api/songs/" + id, Song.class);
        } catch (final HttpClientErrorException e) {
            Assert.assertEquals(e.getStatusCode(), HttpStatus.NOT_FOUND);
        }
    }
}
