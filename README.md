# Plugin de notificación en Java para Rundeck

Desarrollar un plugin de notificación en Java para Rundeck, capaz de realizar solicitudes HTTP flexibles. Aquí tienes un resumen de lo que necesitas hacer:

### Tarea
Crear un plugin de notificación en Java que permita:
- Seleccionar el método HTTP (GET, POST, PUT, DELETE, etc.).
- Definir el contenido del cuerpo (body content) y el tipo de contenido (content type) si es necesario para el método HTTP.
- No debe usar HttpURLConnection; en su lugar, debe utilizar una biblioteca externa para manejar las solicitudes HTTP.
- Compilar y ejecutar con Java 11.

### Entregables
- Repositorio de código fuente: Un repo que construya un archivo JAR.
- Documentación: Según sea necesario.

#### Objetivos
1. Pulido:
    - Código limpio y mantenible.
    - Mejores prácticas usadas.
    - Idiomas estándar de Java (o Groovy) usados.
    - Formato consistente.
    - Uso adecuado de las características del lenguaje (try-with-resources, lambdas de Java 8, etc).
    - Javadoc es requerido.
    - Comentarios inline solo donde sea absolutamente necesario.

2. Corrección:
    - El código es probado para confirmar que funciona como se espera en casos de uso normales y óptimos.
    - El código maneja casos de error previsibles.
    - La entrada se valida antes de usarla.

3. Completitud:

    - El código tiene pruebas unitarias que cubren todas las tareas descritas en la especificación.
    - El código es lo más útil posible para el usuario final (valores predeterminados sensatos, manejo elegante de condiciones límite o errores).
    - Si es posible, incluye características adicionales que mejoren la experiencia del usuario.

---

## Pasos a seguir
1. Configuración del Proyecto: 
    - [x] Configura el entorno de desarrollo con Java 11 y crear un nuevo proyecto Java. Usaré Maven o Gradle como herramienta de construcción.
2. Estructura del Plugin:
    - [ ] Definir la estructura del plugin basada en los ejemplos de los enlaces proporcionados.
3. Implementación de Funcionalidad:
    - [ ] Utilizar una biblioteca externa para solicitudes HTTP (por ejemplo, Apache HttpClient, OkHttp).
    - [ ] Implementar la lógica para manejar diferentes métodos HTTP, cuerpo y tipo de contenido.
4. Pruebas:
    - [ ] Escribir pruebas unitarias para validar la funcionalidad del plugin.
5. Documentación:
    - [ ] Documenta tu código y escribe la documentación necesaria para la instalación y uso del plugin.
6. Construcción del JAR:
    - [ ] Configura tu herramienta de construcción para empaquetar tu plugin en un archivo JAR.
7. Revisión y Pulido:
    - [ ] Revisa el código para asegurarte de que cumple con las mejores prácticas y los objetivos de corrección y completitud.

