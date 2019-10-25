## Status
 Para conocer el estado del servicio tuve que decidir entre varias opciones. En primer lugar, como usaba Actuator de SpringBoot podría añadir unas directivas al `application.properties` y así activaba el endpoint */actuator/health*. Con health se pueden hacer muchas cosas, pero sin embargo tiene limitaciones, hice una prueba en la que podía visualizar la memoria que se ocupaba durante su ejecución, todo ello se puede ver en el issue de Desarrollo. Pero no podía cambiar el status que por defecto aparecía "UP" a "OK". Sin embargo, linko toda la documentación que estuve usando para intentar que funcionase.

 Finalmente, realicé una estructura con map<String,Map> donde iba contruyendo el JSON con cada ruta y cada estado. Para ello comprobaba los endpoint GET (para el resto no le veía mucho sentido), y comprobaba que los resultados fuesen los esperados. Como finalmente esto no era necesario para la práctica, lo sustituí por un map<String,String> que mostrase "status: OK"

### Bibliografía

Actuator health:
  - https://www.baeldung.com/spring-boot-actuators
  - https://www.callicoder.com/spring-boot-actuator/
  - https://dzone.com/articles/magic-with-spring-boot-actuator
