# Food Recipes

Recetas de comida es un aplicación responsiva con compatibilidad de diseño claro y obscuro, 
con pruebas automatizadas así como también pruebas de unidad.

Contiene las siguientes 3 pantallas:

* Pantalla principal (con opción de filtrado).
* Pantalla de detalles.
* Pantalla que muestra un mapa con la ubicación de el origen de la receta.


<img width="300" alt="500" src="https://user-images.githubusercontent.com/33889248/225787819-f6af11de-5b45-4258-becf-f7a3662b9e28.png"> <img width="300" alt="500" src="https://user-images.githubusercontent.com/33889248/225788255-9fb6f12f-74d6-4dba-a010-02e7bb5465b3.png"> <img width="300" alt="500" src="https://user-images.githubusercontent.com/33889248/225788562-126a04f6-2386-4d90-95b5-b8b3e9029161.png">


Esta aplicación contiene un patrón de diseño de architectura MVVM respetando la filosofía de Clean architecture y principios SOLID.


<img width="698" alt="image" src="https://user-images.githubusercontent.com/33889248/225790804-a49a5eff-4eef-422d-92db-712184af8ac9.png">


Componentes y librerías utilizadas:

UI

* ViewBinding
* Glide
* Constraints
* Material design
* Jetpack Navigation
* Search View
* CardView
* Collapsing toolbar layout
* Expand list
* Google Maps

INJECTION:

* Dagger 2, Decidí usar dagger 2 por lo fuerte que puede ser en proyectos modulares o multimodulares.

PD: No recomiendo el uso de koin por posibles NullPointerExceptions en tiempo de ejecución, si deseas usar distinto a dagger2 recomiendo Hilt,
para realizar la migración no necesitas un AppComponent, este es sustituido por anotación en tu clase App, de igual manera framents, actitivies,
modules, viewmodels deben llevar sus correctas anotaciones proporcionadas por Dagger Hilt.

ASYNC:

* Flow
* Coroutines
* Rx
* LiveData
* MutableLiveData
* StateFlow
* MutableStateFlow

DATA LOCAL - NETWORK:

* Room
* Retrofit
* HttpLogingInterceptor

TESTS

* Mockito
* JUnit
* Espresso
* Mock web server
* Uiautomator

Este proyecto contiene una arquitectura y guidelines que posibilita la facilidad de agregar features como:

* Swipe refresh (Para actualizar la información con un gesto swipe de arriba para abajo)
* RemoteMediator (Si requieres que tu aplicación esté disponible sin conexión)
* Paging (Para mostrar información paginada sin sobre cargar datos en una sola llamada al servidor)
* AdMobd (Para mostrar anunción entre la lista de items mostrados)
* Leak cannary (Para detectar si existen memory leaks y solventarlos)
* WorkManagers con restricciones (Para poder detectar si el teléfono es conectado a internet y actualizar los items en caso de tener opción offline)
* Migración de XML a Compose (para desarrollar UI con programación declarativa)







