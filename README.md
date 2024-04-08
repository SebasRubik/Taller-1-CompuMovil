# Taller de Computación Móvil: Explorador de Destinos

Este proyecto es un taller práctico para la clase de Introducción a la Computación Móvil. La aplicación permite a los usuarios explorar diferentes destinos turísticos, agregarlos a una lista de favoritos, y recibir recomendaciones basadas en sus preferencias.

## Características

- **Explorar Destinos:** Permite a los usuarios ver una lista de destinos turísticos filtrados por categorías.
- **Favoritos:** Los usuarios pueden añadir destinos a una lista de favoritos para fácil acceso.
- **Recomendaciones:** Basado en las categorías más frecuentes en la lista de favoritos, la aplicación sugiere un destino aleatorio.

## Cómo empezar

### Prerrequisitos

- Android Studio
- JDK (Java Development Kit)

### Configuración del entorno

1. Instale Android Studio y JDK si aún no lo ha hecho.
2. Clone el repositorio del proyecto o descargue el código fuente.


### Importar el proyecto a Android Studio

1. Abra Android Studio.
2. Seleccione "Open an existing Android Studio project" y navegue hasta la ubicación del proyecto descargado.
3. Espere a que Android Studio configure el proyecto automáticamente.

### Ejecución del proyecto

1. Una vez que el proyecto esté abierto y configurado, seleccione un emulador o conecte un dispositivo Android a su computadora.
2. Ejecute la aplicación presionando el botón "Run" ('▶') en Android Studio.

## Guía de uso

1. **Pantalla Principal:** Al iniciar la aplicación, se mostrará la pantalla principal con tres botones ("Explorar Destinos", "Favoritos", "Recomendaciones") y un spinner para seleccionar categorías de viaje.
2. **Explorar Destinos:** Al seleccionar una categoría y pulsar "Explorar Destinos", se abrirá una nueva actividad con una lista de destinos correspondientes.
3. **Ver Detalles y Favoritos:** Pulsar un destino en la lista abre una nueva actividad con todos los detalles del destino. Desde aquí, se puede añadir el destino a la lista de favoritos.
4. **Lista de Favoritos:** Accesible desde la pantalla principal, muestra todos los destinos marcados como favoritos.
5. **Recomendaciones:** Basado en la categoría más frecuente de los favoritos, se mostrará un destino recomendado al pulsar el botón correspondiente.

## Desarrollo

Este proyecto utiliza ConstraintLayout para la disposición de sus componentes UI, facilitando su adaptabilidad a diferentes tamaños de pantalla y orientaciones. La lógica para cargar y filtrar los destinos turísticos se maneja a través de actividades y el acceso a datos se realiza mediante la lectura de un archivo JSON.

### Desarrollado por: Los Impedidos

### Samuel Peña, Sebastián Córdoba, Tomás De Aza

## Enlace al video demostrativo:

https://youtu.be/VcEKBTTy1_4 

