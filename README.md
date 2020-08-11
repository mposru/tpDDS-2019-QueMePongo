# Qué Me Pongo <img src="/icono.PNG" width="55" align="bottom">

#### Trabajo Práctico Grupal y Anual, realizado para la materia _Diseño de Sistemas_ en la UTN FRBA - Año 2019

Consiste en una aplicación para gestionar los guardarropas de un usuario y sugerir prendas en base al clima y gustos.

La sugerencia en base al clima se hace consultando la API de _DarkSky_ o _Accuweather_

La aplicación fue realizada en _Java_ y pensada desde su dominio. El trabajo práctico consistió en 6 entregables, cuyos enunciados se pueden encontrar en la carpeta `/entregables` del repositorio. A medida que fueron avanzando los entregables, la complejidad del dominio fue aumentando. 

Utilizamos patrones de diseño como Builder, Singleton, State, Strategy, Command , y otros. También utilizamos el patrón arquitectural MVC.

Para persistir los datos utilizamos _JPA-Hibernate_ en una base de datos _MySQL_ y utilizamos el framework web Spark para la vista de usuario. Para disponibilizar la app en la Web utilizamos _Heroku_.
