# Test Java Smartjob #

## Requerimientos: ##
### Spring boot 3.2.0 ###
### Spring boot web ###
### Base de datos en memoria: H2 ###
### Persistencia datos: JPA hibernate ###
### Build dependencias: Maven ###
### Java version: Java 17 ###
### Jwt ###


## Cómo Probar ##

- 1. Levantar project con un IDE para Java (Eclipse, intellij, SpringToolSuite4)
- 2. Actualizar dependencias con Maven haciendo click sobre el proyecto en "maven > update Project"

![image](https://github.com/davidlarocka/testsmartjob/assets/1700287/66be975f-1586-4966-a74e-02f18ea97f39)

      
- 3. Levantar Servidor embebido en Spring boot web. Para eso debemos ir sobre el proyecto en "package explorer" haciendo click  en  "Run > Run As > Spring boot App"
![image](https://github.com/davidlarocka/testsmartjob/assets/1700287/097d3ea1-c6d0-4e8a-9da4-e5ca4fd5bd70)
- 4. Al levantar la aplicación la BD se creará en memoria con lo mapeado en las clases del modelo. No es necesario hacer un restore. 
## Realizar peticiones ## 
- En una app para realizar peticiones Http (como postman) hacer el siguiente llamado: 
> [!NOTE]
http://localhost:8080/registro

- con la siguiente data en el body, en formato Json:

```
{
  "name": "Juan Rodriguez",
  "email": "juan@rodriguez.org",
  "password": "hunter2",
  "phones": [
    {
      "number": "1234567",
      "citycode": "1",
      "contrycode": "57"
    },
    {
      "number": "123456733",
      "citycode": "1",
      "contrycode": "57"
    }
  ]
}
```

## Ver datos en base de datos ## 
- una ver creado el usuario, se puede visualizar en modo desarrollo enla consola web de h2 desde el navegador accediendo a la siguiente ruta
> http://localhost:8080/h2-console

![image](https://github.com/davidlarocka/testsmartjob/assets/1700287/48d1499a-1c94-41ef-a2ec-1192f01158ee)

- El usuario y la clave para acceder a la BD se encuentran configurados en el application.properties de la app

## Pruebas integrales ##

-Spring boot test
-JUnit

![image](https://github.com/davidlarocka/testsmartjob/assets/1700287/9d308bf0-e535-4aa9-a962-accb4754488c)

- test1_create_simple_user_successfull_with_correctData: Se espera que cree el usuario cuando los datos son correctos
- test2_no_create_Simple_user_with_email_is_used_by_otheruser : Se espera que NO cree el usuario cuando ya existe un email registrado en BD por otro usuario
- test3_no_create_simple_user_fail_with_incorrectdata_email_no_format : Se espera que NO cree el usuario cuando el dato enviado en la petición para email no tiene un formato admitido. 

## Diagrama de la solución ##

![image](https://github.com/davidlarocka/testsmartjob/assets/1700287/38547282-1870-4528-a12a-002af93ad9be)

