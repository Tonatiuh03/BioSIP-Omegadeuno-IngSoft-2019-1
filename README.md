# Omegadeuno-IngSoft-2019-1
Repositorio del Proyecto del curso de Ingeniería de Software

# Instrucciones para montar el sistema

1. Instalación de la base de datos
    1. Se requiere instalar el manejador de bases de datos PostgreSQL en versión 9 o superior.
    2. Configurar un usuario para que tenga acceso a la base de datos, por defecto el sistema utiliza al usuario postres.
    3. En caso de que se requiera hacer un cambio de host, puerto, usuario y/o contraseña se deberá hacer los cambios respectivos en el siguiente [archivo][1]
    4. Se deberá montar la base de datos <b>biosip</b>, contenida en el archivo [biosip.sql][4], se recomienda ocupar el programa <b>PgAdmin</b> y también se recomienda copiar el archivo a una ruta más corta, hemos detectado que PgAdmin no puede acceder al archivo si no se encuentra en una ruta corta como <b>c:/users/<usuario>/Documents</b>.
    
2. Instalación de los directorios de repositorios de imágenes
    1. Se deberá agregar el directorio [biosip-img][2] en <b>c:/</b>, se acordó esto debido a que la mayoría ocuparía Windows como sistema operativo para desarrollar.
    2. En caso de que se necesite montar en otro lugar o sistema operativo, se requiere modificar la constante de configuración <b>IMG_REPO</b> en el archivo [Config.java][3]
    
3. Compilación
    1. Recomendamos ocupar Netbeans para la compilación del proyecto para que, posteriormente, se monte el archivo .war en un server.


[1]:https://github.com/Tonatiuh03/BioSIP-Omegadeuno-IngSoft-2019-1/blob/master/Primera%20Iteracio%CC%81n/Proyecto/biosip/src/main/setup/sun-resources.xml

[2]:https://github.com/Tonatiuh03/BioSIP-Omegadeuno-IngSoft-2019-1/tree/master/Primera%20Iteracio%CC%81n/Proyecto/biosip/src/main/resources/biosip-img

[3]:https://github.com/Tonatiuh03/BioSIP-Omegadeuno-IngSoft-2019-1/blob/master/Primera%20Iteracio%CC%81n/Proyecto/biosip/src/main/java/mx/unam/is20191/utils/Config.java

[4]:https://github.com/Tonatiuh03/BioSIP-Omegadeuno-IngSoft-2019-1/blob/master/Primera%20Iteracio%CC%81n/Proyecto/biosip/src/main/setup/biosip.sql
