# Food Recipes

Recetas de comida es un aplicación responsiva con compatibilidad de diseño claro y obscuro, 
con pruebas automatizadas así como también pruebas de unidad.

Contiene las siguientes 3 pantallas:

* Pantalla principal (con opción de filtrado).
* Pantalla de detalles.
* Pantalla que muestra un mapa con la ubicación de el origen de la receta.

<img width="300" alt="500" src="https://user-images.githubusercontent.com/33889248/228990903-0608a4f3-4add-4a1d-b30d-34520e6259a1.png"> <img width="300" alt="500" src="https://user-images.githubusercontent.com/33889248/228990930-a69cf419-76a4-45e1-b26d-489f051661a7.png"> <img width="300" alt="500" src="https://user-images.githubusercontent.com/33889248/228990948-fa01cf81-798a-4198-b7b3-95aa6ec38398.png"><img width="300" alt="500" src="https://user-images.githubusercontent.com/33889248/228990967-3b53adf3-e979-43f2-8ecf-de3f8850f6aa.png">


Esta aplicación modular contiene un patrón de diseño de architectura MVVM respetando la filosofía de Clean architecture y principios SOLID.


<img width="698" alt="image" src="https://user-images.githubusercontent.com/33889248/225790804-a49a5eff-4eef-422d-92db-712184af8ac9.png">


Componentes y librerías utilizadas:

UI

* Jetpack Compose
* Coil Compose
* Material Design 3 Compose
* Navigation compose
* Google Maps Compose

INJECTION:

* Dagger Hilt Mejor adaptación para nuevos proyectos con Jetpack compose.

ASYNC:

* Flow
* Coroutines
* Rx
* StateFlow
* MutableStateFlow

DATA LOCAL - NETWORK:

* Room
* Retrofit
* HttpLogingInterceptor

TESTS

* Mockito
* JUnit
* Hilt compose test
* Mock web server
* Uiautomator

Recomendaciones: 
* Gradle a Kts
