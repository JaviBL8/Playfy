package com.javibl8;

import com.playfy.Application;
import com.playfy.model.Song;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.http.*;
import org.springframework.web.client.HttpClientErrorException;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PlayfyTests {

    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    private int port;

    private String getRootUrl() {
        return "http://localhost:" + port;
    }

    @Test
    public void getAllSongs(){
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<String>(null, headers);

        ResponseEntity<String> response = restTemplate.exchange(getRootUrl() + "/users", HttpMethod.GET, entity, String.class);
        Assert.assertNotNull(response.getBody());
    }

    @Test
    public void testGetSongById(){
        Song song = restTemplate.getForObject(getRootUrl() + "songs/0", Song.class);
        System.out.println(song.getTitle());
        Assert.assertNotNull(song);
    }

    @Test
    public void testAddSong(){
        Song song = new Song();
        song.setTitle("Example");
        song.setAlbum("Example Album");
        song.setID(1);

        ResponseEntity<Song> postResponse = restTemplate.postForEntity(getRootUrl() + "/songs", song, Song.class);
        Assert.assertNotNull(postResponse);
        Assert.assertNotNull(postResponse.getBody());
    }

    @Test
    public void testUpdatePost(){
        int id = 0;
        Song song = restTemplate.getForObject(getRootUrl() + "/songs/" + id, Song.class);
        song.setAlbum("Example");
        song.setTitle("Example title");
        song.setID(id);

        restTemplate.put(getRootUrl() + "/songs/" + id, song);

        Song updatedSong = restTemplate.getForObject(getRootUrl() + "/songs/" + id, Song.class);
        Assert.assertNotNull(updatedSong);
    }

    @Test
    public void testDeletePost(){
        int id=1;
        Song song = restTemplate.getForObject(getRootUrl() + "/songs/" + id, Song.class);
        Assert.assertNotNull(song);

        restTemplate.delete(getRootUrl() + "/songs/" + id);

        try{
            song = restTemplate.getForObject(getRootUrl() + "/songs/" + id, Song.class);
        } catch (final HttpClientErrorException e) {
            Assert.assertEquals(e.getStatusCode(), HttpStatus.NOT_FOUND);
        }
    }
}
