# prueba-microservicios
CRUD de prueba para desarrollo

#Comando para remover imagen de docker si existe
<br><strong>docker rm image microservicetest</strong>

#Compilar el jar para usarlo en el contenedor
<br><strong>./gradlew build -x test</strong>

#Usar el jar para a la imagen
<br><strong>docker image build -t microservicetest .</strong>

#Correr entorno de contenedores con java y mysql
<br><strong>docker-compose up -d</strong>

#Parar contenedores
<br><strong>docker-compose down</strong>

#La carpeta database/persistence guarda toda la información de mysql para que al reiniciar no se borre, si se desea remover toda la información es necesario borrar el contenido de la misma.
#Windows
rmdir -p database/persistence/ && mkdir database/persistence

#Linux
rm -r -f database/persistence/ && mkdir database/persistence
