# README

## Objetivo

Diseñar un sistema básico de autenticación y autorización que use base de datos (JPA) como fuente de usuarios, e implemente un control de acceso basado en roles.

## Instrucciones por equipo

1. *Cree una base de datos** (puede ser MySQL o H2) con una tabla usuarios que contenga los siguientes campos: 
* username (PK) 
* password (encriptada con BCrypt) 
* role (por ejemplo, USER, ADMIN)

2. **Configure un proyecto en Spring Tool Suite (STS)** con las siguientes dependencias: 
* Spring Web 
* Spring Security 
* Spring Data JPA
* Driver para la base de datos (MySQL o H2) 
* Thymeleaf

3. **Cree la entidad JPA Usuario y una interfaz UsuarioRepository**, que permita buscar usuarios por username.

4. **Implemente un servicio de autenticación personalizado**: 
* Cree una clase que implemente UserDetailsService. 
* Configure el acceso a la base de datos para autenticar usuarios usando JPA.

5. **Cree una clase de configuración de seguridad (SecurityConfig) donde**: 
* Se defina un formulario de login personalizado. 
* Se configuren las reglas de acceso: 
- Las rutas que comienzan con /admin/** deben estar protegidas para usuarios con rol ADMIN. 
- Las rutas que comienzan con /perfil/** deben estar protegidas para cualquier usuario autenticado.

6. **Cree un conjunto de vistas (usando Thymeleaf)**: 
* login.html → formulario de autenticación. 
* admin.html → panel exclusivo para administradores. 
* perfil.html → perfil del usuario autenticado. 
* panel.html → panel general para usuarios autenticados.

---

## Ejecución

1. **Crear la base de datos y la tabla de usuarios**

   - Crear una base de datos llamada `login_security` en MySQL (o configurar H2 si prefiere embebida).
   - Crear la tabla `usuarios` con al menos las columnas:
     - `username` VARCHAR PRIMARY KEY
     - `password` VARCHAR (almacenará el hash BCrypt)
     - `role` VARCHAR (por ejemplo: `USER`, `ADMIN`)

   Ejemplo (MySQL):

   ```sql
   CREATE DATABASE login_security;
   USE login_security;

   CREATE TABLE usuarios (
     username VARCHAR(100) NOT NULL PRIMARY KEY,
     password VARCHAR(255) NOT NULL,
     role VARCHAR(50) NOT NULL
   );
   ```

2. **Generar hashes BCrypt para contraseñas y rellenar usuarios**

   - Puede usar la clase auxiliar `CrearHash.java` incluida en el repositorio para generar hashes BCrypt desde la línea de comandos o desde su IDE.

   - Ejemplo simple de uso (Java) para generar hash BCrypt:

     ```java
     import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

     public class CrearHash {
         public static void main(String[] args) {
             BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
             String raw = "password"; // cambiar por la contraseña deseada
             System.out.println(encoder.encode(raw));
         }
     }
     ```

   - Insertar usuarios en la tabla `usuarios` usando los hashes generados. Ejemplo:

   ```sql
   INSERT INTO usuarios (username, password, role) VALUES ('admin', '$2a$10$...hash...', 'ADMIN');
   INSERT INTO usuarios (username, password, role) VALUES ('user', '$2a$10$...hash...', 'USER');
   ```

3. **Configurar `application.properties` con credenciales MySQL**

   - Editar `src/main/resources/application.properties` y poner los datos de conexión a la base de datos. Ejemplo:

   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/login_security?useSSL=false&serverTimezone=UTC
   spring.datasource.username=tu_usuario
   spring.datasource.password=tu_contraseña

   spring.jpa.hibernate.ddl-auto=none
   spring.jpa.show-sql=true
   spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect
   ```



4. **Ejecutar la aplicación**

   - Desde terminal (en la raíz del proyecto):
     ```bash
     mvn spring-boot:run
     ```
   - O ejecutar la aplicación desde Spring Tool Suite (STS) con `Run as -> Spring Boot App`.

5. **Abrir la aplicación**

   - Navegar a: `http://localhost:8080/login`

## Capturas
### Login
![Catalogo](img//login.png)

### Panel
![Catalogo](img//panel.png)

### Admin
![Catalogo](img//admin.png)

## Estructura recomendada del repositorio

```
/ (raíz del repo)
├─ src/
├─ pom.xml
├─ README.md
├─ CrearHash.java
└─ img/
   ├─ login.png
   ├─ panel.png
   └─ admin.png
```


