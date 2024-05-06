# administrador_visitantes
🚀 Aventurándome en el mundo de Android 

Documentación del Proyecto de Control de Visitas
Resumen del Proyecto
Este proyecto, llamado nombre_apellido_20240506, es una aplicación móvil desarrollada en Kotlin para la empresa Mi Administración. Su propósito es gestionar el control y registro de visitas en edificios y condominios, facilitando el registro de entradas y salidas de visitantes de manera eficiente y segura.

Requerimientos
La aplicación cumple con los siguientes requerimientos:

Registro persistente de visitantes incluyendo RUT, nombre, apellido, hora de entrada, departamento o casa de visita, y hora de salida.
Confirmación de registro mediante un mensaje de éxito.
Modificación de la fecha y hora de salida.
Listado de todas las visitas registradas.
Capacidad para compartir datos de visitantes con otras aplicaciones.
Arquitectura de la Aplicación
Base de Datos
Se utiliza SQLite para la persistencia de datos debido a su integración nativa con Android y su capacidad para manejar datos estructurados de manera eficiente. Se ha definido una base de datos llamada VisitorDatabase.db con una tabla visitors que incluye las siguientes columnas:

rut: RUT del visitante (clave primaria).
firstName: Nombre del visitante.
lastName: Apellido del visitante.
entryTime: Hora de entrada.
exitTime: Hora de salida.
apartment: Departamento o casa visitada.
Content Provider
El PersonaProvider permite compartir información de visitantes con otras aplicaciones a través de un ContentProvider, asegurando que la interacción con los datos sea segura y controlada. Esto se logra mediante URI específicas que permiten la consulta e inserción de datos.

Clases Principales
DatabaseHelper: Gestiona la creación y actualización de la base de datos.
Visitor: Modelo de datos para los visitantes.
MainActivity: Interfaz principal que muestra el listado de visitantes y permite la navegación a las funcionalidades de registro y edición.
AddVisitorActivity: Permite el registro de nuevos visitantes y la edición de la hora de salida.
VisitorsAdapter: Adaptador para la visualización de visitantes en un RecyclerView.
Funcionalidad de Compartir Datos
El proveedor de contenidos implementado permite a otras aplicaciones acceder a los datos de visitantes bajo estrictos controles de seguridad, utilizando URIs específicas y métodos adecuados para cada tipo de operación (consulta, inserción, actualización y eliminación).

Conclusiones y Futuras Mejoras
Este proyecto establece una base  para el manejo eficiente de visitas en edificaciones. Futuras mejoras podrían incluir la integración con sistemas de seguridad física, notificaciones en tiempo real de entradas y salidas, y una interfaz de usuario más interactiva.

Referencias
Documentación oficial de Android para SQLite y Content Providers.
Android Developers
Ejemplos y tutoriales específicos de Kotlin y manejo de bases de datos en Android.
