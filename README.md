# Twitter - Uala

Prueba de ingreso para Uala. Se diseña una pequeña versión de Twitter donde se pueden realizar diferentes tareas.

## Índice
    1. Servicios
    2. Servicios Extra
    3. Ejecución
    4. Verificación del despliegue online

## Servicios

### Home:
Servicio que nos permite ver el timeline de las personas que seguimos.
### make tweet:
Servicio que nos permite crear un tweet de menos de 280 caracteres.
### follow:
Servicio que nos permite empezar a seguir a un usuario.

## Servicios Extra

### Find user by id: 
Servicio que nos permite validar la existencia de un usuario si conocemos su ID.
### Find user by nickname: 
Servicio que nos permite validar la existencia de un usuario si conocemos su nickname.
### Followers: 
Nos muestra la cantidad de personas que nos siguen en la app.
### Followings: 
Nos muestra la cantidad de personas que estamos siguiendo.
### Unfollow: 
Servicio que nos permite dejar de seguir a un usuario.
### List users: 
Servicio que nos permite ver la lista de usuarios.
### Create User: 
Servicio que nos permite crear un usuario nuevo.

## Ejecución

Para ejecutar el proyecto se debe ejecutar el archivo `TwitterUalaApplication` que esta ubicado en el siguiente path:

```
com.uala.twitter.TwitterUalaApplication
```

Para verificar la correcta ejecución, se puede ejecutar la siguiente URL:

http://localhost:8000/version/

## Verificación del despliegue online
Actualmente el proyecto se encuentra desplegado en la nube de Digital Ocean en un docker. 
Con la siguiente URL podemos validar el despliegue de la app:

http://165.22.91.188:8000/version/