# demo-h2-database

Este enfoque simplifica el desarrollo y las pruebas de aplicaciones Spring Boot al embeber una base de datos H2 dentro de la misma aplicación, lo que elimina la necesidad de conexiones complejas a bases de datos externas y proporciona un entorno de pruebas funcional

Spring Boot: Es un framework que facilita la creación de aplicaciones Java. Proporciona una configuración predeterminada para muchas cosas, lo que permite a los desarrolladores concentrarse en la lógica de negocio en lugar de la configuración.

Base de datos H2: Es una base de datos relacional escrita en Java. Es liviana y puede ser embebida en aplicaciones Java, lo que significa que la base de datos se ejecuta dentro del mismo proceso que la aplicación. Esto elimina la necesidad de configuraciones externas de bases de datos y conexiones complejas.

Embebida en la aplicación: En lugar de ejecutar una instancia separada de H2 o cualquier otra base de datos, la base de datos H2 se incluye dentro del archivo de la aplicación Spring Boot. Esto simplifica la implementación y distribución de la aplicación, ya que no es necesario instalar ni configurar una base de datos separada.

Entorno de pruebas funcional: Al utilizar una base de datos embebida como H2, los desarrolladores pueden crear un entorno de pruebas completamente funcional sin depender de una instancia de base de datos externa como MySQL. Esto facilita las pruebas automatizadas y la integración continua, ya que no hay necesidad de configurar y mantener una base de datos separada para pruebas.