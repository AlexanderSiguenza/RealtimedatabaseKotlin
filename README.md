# Proyecto de Gestión de Personas en Android con Firebase

## Descripción

Este proyecto es una aplicación Android desarrollada en Kotlin que utiliza Firebase Realtime Database para gestionar registros de personas. La aplicación permite agregar, editar y eliminar registros de personas, y mostrar una lista de todas las personas en la base de datos.

## Funcionalidades

- **Agregar**: Permite añadir nuevos registros de personas con campos como nombre, DUI, apellido, teléfono, edad y dirección.
- **Editar**: Permite actualizar los registros existentes.
- **Eliminar**: Permite eliminar registros de personas.
- **Mostrar**: Muestra una lista de personas con sus datos en una `ListView`.

## Tecnologías Utilizadas

- **Android SDK**: Plataforma para el desarrollo de aplicaciones Android.
- **Kotlin**: Lenguaje de programación utilizado para desarrollar la aplicación.
- **Firebase Realtime Database**: Servicio de base de datos en tiempo real proporcionado por Firebase para almacenar y sincronizar datos.
- **Material Design**: Se utilizan componentes de Material Design para mejorar la interfaz de usuario.

## Estructura del Proyecto

### Clases Principales

1. **`Persona`**: Clase de modelo que representa a una persona. Contiene los campos `nombre`, `dui`, `apellido`, `telefono`, `edad`, `direccion` y un método para convertir el objeto a un mapa para su almacenamiento en Firebase.

2. **`PersonasActivity`**: Actividad principal que muestra una lista de personas. Permite la visualización, edición y eliminación de registros.

3. **`AddPersonaActivity`**: Actividad para agregar o editar registros de personas. Proporciona campos de entrada para cada atributo de la persona.

4. **`AdaptadorPersona`**: Adaptador personalizado para mostrar los registros de personas en una `ListView`.

### Archivos de Layout

1. **`activity_personas.xml`**: Layout principal que contiene una `ListView` para mostrar la lista de personas y un `FloatingActionButton` para agregar nuevos registros.

2. **`persona_layout.xml`**: Layout para cada ítem en la `ListView`. Muestra los campos `nombre`, `dui`, `apellido`, `telefono`, `edad`, `direccion`.

3. **`activity_add_persona.xml`**: Layout para la actividad de agregar o editar personas. Incluye campos de entrada para todos los atributos de una persona.

## Configuración del Proyecto

1. **Configuración de Firebase**:
    - Crea un proyecto en [Firebase Console](https://console.firebase.google.com/).
    - Añade tu aplicación Android al proyecto y descarga el archivo `google-services.json`.
    - Coloca el archivo `google-services.json` en el directorio `app/` de tu proyecto Android.
    - Habilita Firebase Realtime Database en tu consola de Firebase y configura las reglas de acceso según sea necesario.

2. **Dependencias de Gradle**:
    - Asegúrate de añadir las siguientes dependencias en tu archivo `build.gradle` (nivel de aplicación):

      ```groovy
      implementation 'com.google.firebase:firebase-database:21.0.3'
      implementation 'com.google.firebase:firebase-core:21.0.0'
      implementation 'com.google.android.material:material:1.8.0'
      ```

    - En el archivo `build.gradle` (nivel de proyecto), añade el siguiente plugin:

      ```groovy
      classpath 'com.google.gms:google-services:4.3.15'
      ```

    - Asegúrate de aplicar el plugin de Google Services en el archivo `build.gradle` (nivel de aplicación):

      ```groovy
      apply plugin: 'com.google.gms.google-services'
      ```

## Uso

1. **Ejecuta la Aplicación**: Compila y ejecuta la aplicación en un emulador o dispositivo físico Android.
2. **Agregar una Persona**: Haz clic en el botón flotante para agregar una nueva persona. Completa los campos y guarda el registro.
3. **Editar una Persona**: Haz clic en un ítem de la lista para editar el registro. Modifica los campos y guarda los cambios.
4. **Eliminar una Persona**: Mantén presionado un ítem de la lista para eliminar el registro.

## Notas

- **Validaciones**: Asegúrate de manejar las validaciones de entrada en el código para evitar errores en el registro de datos.
- **Seguridad**: Configura las reglas de seguridad de Firebase Realtime Database para proteger los datos de acceso no autorizado.

## Contribuciones

Si deseas contribuir a este proyecto, por favor, realiza un fork del repositorio y envía tus pull requests. Para cualquier problema o consulta, abre un "issue" en el repositorio.

## Licencia

Este proyecto está licenciado bajo la Licencia MIT. Consulta el archivo [LICENSE](LICENSE) para más detalles.

---

¡Gracias por usar este proyecto! Si tienes alguna pregunta o sugerencia, no dudes en contactar.


