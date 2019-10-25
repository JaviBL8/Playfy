## Tests

He ampliado la funcionalidad de los tests interaccionando con la base de datos diréctamente. Para ello, he añadido a mi clase testeadora el siguiente decorador de Spring `@Sql("/songs_database.sql")` que ejecuta un fichero generado por SQL como copia de seguridad de las tablas que tengo en local. En mi caso, este dump me sirve para generar las tablas, y combinado con la creación de la base de datos para los tests de Travis permite hacer tests bastante consistentes.

Paso ahora a explicar los 5 tests que he realizado.

~~~
@Test
public void testGetAllSongs(){
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
~~~

Testeo getAllSongs, para ello realizo una petición usando el método GET y además almaceno en una lista todas las canciones que tengo en la base de datos. Realizo un bucle for en el que compruebo si los diferentes atributos de cada canción se encuentran en mi BD con assert.

~~~
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
~~~
Testeo GetSongById, en este caso vuelvo a usar GET que me devuelve solo una canción; el procedimiento, por lo tanto, es igual que el test anterior pero eliminando el bucle for. Hay algunos asserts que son distintos por el formato en el que devuelve findById la canción con *id*.

~~~
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
~~~

Testeo addSong. Esta función usa el método POST, en primer lugar creo una canción con cualquier tipo de parámetros, cuento el número de canciones que hay. Realizo la petición y después hago un assert para comprobar si se ha incrementado el tamaño. Por último, elimino la cación con ese ID que he añadido.

~~~
@Test
public void testUpdateSong(){
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
~~~
Testeo updateSong. Uso el método POST. Creo una canción como he hecho en el test anterior. Realizo una petición actualizando los datos de esa canción creada y compruebo que los datos se han actualizado correctamente. Finalmente borro la canción de prueba.

~~~
@Test
public void testDeletePost(){
    int id=1;
    Song songId = restTemplate.getForObject(getRootUrl() + "/api/songs/" + id, Song.class);
    Assert.assertNotNull(songId);

    restTemplate.delete(getRootUrl() + "/api/songs/" + id);

    try{
        Song song = restTemplate.getForObject(getRootUrl() + "/api/songs/" + id, Song.class);
    } catch (final HttpClientErrorException e) {
        Assert.assertEquals(e.getStatusCode(), HttpStatus.NOT_FOUND);
    }

    songsRepository.save(songId);
}
~~~
Testeo deletePost. Uso el método POST. Hago una llamada a delete para borrar la canción, y compruebo si la consulta a ese id me devuelve algo o no, para comprobar que está correcto. Por último añado la canción que acabo de eliminar.
