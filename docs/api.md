# API

## Songs
| Método | URI | Párámetros | Devuelve | Descripción |
|---|---|---|---|---|
| GET | /status | | [{"status": ok, "route": ...}] | Muestra el estado y las rutas con GET activas |
| GET | /songs  | | [{"id":`Integer`,"title":`String`,"album":`String`,"year":`String`}, ...] | Muestra una lista de las canciones almacenadas en la BD |
| GET | /songs/{id} | `id` de la canción | {"id":`Integer`,"title":`String`,"album":`String`,"year": `int`, "author": `String`} | Muestra los atributos de la canción con ese `id` (year y author añadido al modelo de datos) |
| POST | /songs  | `title`, `album`, `year`, `author` de la canción  | status: `Boolean` | Añade la canción a la base de datos |
| POST | /songs/{id} | `id` de la canción y atributos para actualizar | status: `Boolean` | Actualiza los datos de una canción con `id` |
| DELETE | /songs/{id} | `id` de la canción | response: `Boolean` | Borra la canción con ese `id` |

Si se intentase acceder a una ruta no recogida aquí se mostrará "status": `error`.
Además si se llama a un método con los parámetros equivocados aparecerá error también junto
a un mensaje.
