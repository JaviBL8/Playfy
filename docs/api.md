# API

## Songs
| Método | URI | Párámetros | Devuelve | Descripción |
|---|---|---|---|---|
| GET | /api/songs  | | [{"id":`Integer`,"title":`String`,"album":`String`}, ...] | Lista de canciones registradas |
| GET | /api/songs/{id} | `id` de la canción | {"id":`Integer`,"title":`String`,"album":`String`} | Datos de la canción con ese `id` |
| POST | /api/songs  | `título` y `album` de la canción  | status: `Boolean` | Añade la canción a la base de datos |
| POST | /api/songs/{id} | `id` de la canción | status: `Boolean` | Actualiza los datos de una canción con `id` |
| DELETE | /api/songs/{id} | `id` de la canción | response: `Boolean` | Borra la canción con ese `id` |
