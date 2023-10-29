# Unchained News 🟩
> Aplicación para la optimización de lectura de noticias digitales y libre de sesgos.
---
## Descripción 
El presente código forma parte de una constelación de servicios que, en conjunto, trabajan para conseguir el objetivo deseado.
En particular, la presente aplicación, es el administrador de todo el flujo relacionado con el gestor de usuarios y gestor de noticias.
Recibe las peticiones desde el front end, y es el responsable de comunicarse con otros servicios (como por ejemplo servicio de python o base de datos), procesar la información y devolverla de forma correcta.
También se encarga de la validación de los datos que ingresan al sistema, como los que se devuelven, manteniendo a la base de datos en un estado consistente.


## Pre-requisitos

Este programa fue diseñado para ser utilizado con las siguientes especificaciones:
* [Amazon Corretto 17.0.7](https://docs.aws.amazon.com/corretto/latest/corretto-17-ug/downloads-list.html)
* [Maven 3.8.7](https://maven.apache.org/docs/3.8.7/release-notes.html)

Si bien la utilización de otras versiones pueden permitir la ejecución del programa, no se puede asegurar su correcto funcionamiento.

Además, el servicio de Python debe estar inicializado y ejecutándose correctamente.

## Instalación

Agregar el proyecto al scope de la librería de Maven, para asegurarse para instalar las dependencias necesarias mediante el comando a continuación.

```bash
mvn clean install
```

Esto generará un archivo jar (con todas las dependencias) que se ejecutará una vez creado.

## Ejecución

En una terminal, ejecute el método principal en Main.java mediante el siguiente comando (asegúrese de estar en la carpeta raíz del proyecto).

```bash
mvn spring-boot:run
```
Alternativamente, puede ejecutar el método principal en Main.java en su IDE elegido, por ejemplo: IntelliJ.

## Uso

* Para comenzar a utilizar los servicios de la aplicación debe crearse un usuario, consultando al servicio `free/user/register` [POST] con el siguiente JSON enviado en el cuerpo de la consulta.
```json
{
    "email": "your_email@gmail.com",
    "password": "your_password"
}
```
* Una vez realizado el registro, debe procederse a la consulta para el login `/free/login` [POST]
Este proceso devolverá un token dentro del encabezado de la consulta. Debe buscar el atributo `Authorization` y ver su valor. 
Este valor está conformado de la siguiente forma `Bearar {TOKEN}`. Es el TOKEN lo que se debe incluir en cada solicitud al backend para garantizar la correcta respuesta.

## Cloud

El presente servicio se encuentra desplegado en un servicio cloud de Google (Google Cloud Platform), el cual se puede acceder a través del siguiente link.

`https://service-app-dot-unchainednews.rj.r.appspot.com`

Se puede consultar el estado de la aplicación consultando la dirección `/free/news/healthcheck`
## Autores
Caneva, Gianfranco

## Contacto

Por cualquier sugerencia, problema o inconveniente comunicarse con `gfocaneva@gmail.com`