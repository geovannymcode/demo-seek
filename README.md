# **Demo-Seek**

## **Documentación de Prueba Técnica para demo-seek**

---

### **Introducción**
Esta documentación detalla el desarrollo de un servicio **API RESTful** para la administración de candidatos. El proyecto sigue los principios **SOLID** y aplica la **Arquitectura Hexagonal** para asegurar la modularidad, escalabilidad y facilidad de mantenimiento.

---

## **Descripción del Proyecto**
El servicio permite realizar operaciones CRUD sobre candidatos, autenticación de usuarios y otras funcionalidades. Está implementado con **Spring Boot** y utiliza seguridad mediante **JWT**.

---

## **Arquitectura y Estructura del Proyecto**

La estructura del proyecto sigue la **Arquitectura Hexagonal**, organizando las responsabilidades de manera clara:

```plaintext
src/
  main/
    java/
      com/
        geovannycode/
          application/
            service/            --> Servicios que contienen la lógica de negocio.
              impl/             --> Implementación de servicios.

          domain/
            model/              --> Entidades del dominio, como Candidate.

          infrastructure/
            config/             --> Configuraciones de seguridad, OpenAPI y MVC.
            controller/         --> Controladores para manejar solicitudes HTTP.
              impl/             --> Implementación de los controladores.
            mapper/             --> Mapeadores entre request/response y entidades.
            repository/         --> Interfaces de repositorio JPA.
            request/            --> Clases para peticiones HTTP.
            response/           --> Clases para respuestas HTTP.
            utils/              --> Clases utilitarias, como generación de JWT.

  resources/
    db/
      migration/                --> Scripts de migración con Flyway.
        V1__create_candidate_table.sql
        V2__add_candidate_data.sql
    application.yaml            --> Configuración de la aplicación.

  test/
    java/
      com/
        geovannycode/
          application/
            service/            --> Pruebas unitarias de servicios.
          infrastructure/
            controller/         --> Pruebas integración de controladores.
```

## **Tecnologías y Herramientas Utilizadas**
- **Java 21**
- **Spring Boot 3.2.4**
- **Spring Data JPA**
- **Flyway**: Migración de base de datos.
- **JWT (jjwt)**: Seguridad mediante tokens JWT.
- **Spring Security**
- **MySQL**: Base de datos relacional.
- **Docker Compose**: Orquestación de servicios.
- **Spotless**: Formateo de código.
- **JUnit**: Pruebas unitarias.
- **Springdoc OpenAPI**: Documentación interactiva.

---

## **Swagger - Documentación de la API**

La documentación interactiva de los endpoints está disponible a través de **Swagger** en la siguiente URL:

[http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui/index.html)

---

## **Colección de Postman**

Para probar la API de manera rápida, puedes importar la colección de Postman:

[Descargar la Colección de Postman](https://github.com/geovannymcode/demo-seek/demo-seek-collection.json)

---

## **Patrones de Diseño**

- **Singleton**:  
  El uso de `@Component` y `@Configuration` en las clases de configuración y utilidades asegura que solo haya una instancia de estas clases en el contexto de Spring.

- **Factory**:  
  La clase `SecurityUtil` con el método `getSigningKey` actúa como una fábrica para crear instancias de `Key`.

- **Filter**:  
  La clase `JWTAuthorizationFilter` extiende `OncePerRequestFilter` y actúa como un filtro para las solicitudes HTTP, verificando la validez del JWT.

---

## **Principios SOLID**

- **Single Responsibility Principle (SRP)**:  
  Cada clase tiene una única responsabilidad.  
  Ejemplo:  
  - `JWTAuthorizationFilter`: Se encarga de la autorización basada en JWT.  
  - `JWTTokenGenerator`: Genera tokens JWT.  
  - `SecurityConfig`: Configura la seguridad de la aplicación.

- **Open/Closed Principle (OCP)**:  
  Las clases están abiertas para la extensión pero cerradas para la modificación.  
  Ejemplo:  
  - Puedes agregar más filtros o configuraciones de seguridad sin modificar las clases existentes.

- **Liskov Substitution Principle (LSP)**:  
  Las clases que implementan interfaces pueden ser usadas indistintamente sin alterar el comportamiento del programa.  
  Ejemplo:  
  - `AuthControllerImpl` implementa `AuthController`.

- **Interface Segregation Principle (ISP)**:  
  Las interfaces están bien definidas y no obligan a las clases a implementar métodos que no necesitan.  
  Ejemplo:  
  - `AuthController` define solo los métodos necesarios para la autenticación.

- **Dependency Inversion Principle (DIP)**:  
  Las clases dependen de abstracciones y no de implementaciones concretas.  
  Ejemplo:  
  - `AuthControllerImpl` depende de la abstracción `JWTTokenGenerator`.

---

## **Buenas Prácticas**

- **Inyección de Dependencias**:  
  Uso de `@Autowired` y **constructor injection** para inyectar dependencias, lo que facilita la prueba y el mantenimiento del código.

- **Configuración Externa**:  
  Uso de `application.yml` para configurar propiedades externas como `jwt.secret`, `jwt.user` y `jwt.pass`.

- **Separación de Preocupaciones**:  
  Separación clara entre **controladores**, **servicios**, **repositorios** y **configuraciones**.

- **Uso de Anotaciones de Spring**:  
  Uso de anotaciones como `@RestController`, `@RequestMapping`, `@Value`, `@Component`, `@Configuration`, `@Bean`, etc., para simplificar la configuración y el manejo de dependencias.

- **Pruebas Unitarias**:  
  Uso de **Mockito** para crear pruebas unitarias, asegurando que cada unidad de código funcione correctamente de manera aislada.

---

## **Ejecutar el Proyecto**

### **Clonar el repositorio**
```bash
git clone https://github.com/Geovanny0401/demo-seek.git
cd demo-seek
```

Opciones para ejecutar la aplicación
A. Usando Maven

```bash
mvn spring-boot:run
```

## **Endpoints**

### **Autenticación de Usuarios**
- Path: /api/auth/login
- Method: POST

```bash
curl --request POST \
  --url http://localhost:8080/api/auth/login \
  --header 'Content-Type: application/json' \
  --data '{
    "username": "admin",
    "password": "Password123!"
  }'
```

### **Operaciones de Candidatos**

1. **Crear un Candidato**

- Path: /api/candidates
- Method: POST

2. **Listar Candidatos Activos**
- Path: /api/candidates
- Method: GET

3. **Buscar Candidato por ID**
- Path: /api/candidates/{id}
- Method: GET

4. **Actualizar un Candidato**
- Path: /api/candidates/{id}
- Method: PUT

5. **Eliminar un Candidato**
- Path: /api/candidates/{id}
- Method: DELETE

## **Diagrama de Secuencia**
![Diagrama_Seek](https://github.com/user-attachments/assets/2375d923-bbc3-449f-810c-65ede7d88d58)

Pruebas Implementadas
Pruebas Unitarias: Se validan los servicios y controladores.
Pruebas de Integración: Simulan el flujo completo de las operaciones.
Ejecutar pruebas:

```bash
mvn test
```

## **Conclusiones y Mejoras Futuras**
El proyecto proporciona una solución sólida para la gestión de candidatos. Mejoras futuras incluyen:

- Implementación de caché con Redis.
- Métricas de observabilidad con Spring Actuator.
- Aumentar la cobertura de pruebas.

## **Licencia**
Este proyecto está licenciado bajo la licencia Apache-2.0.

## **Contacto**
- Geovanny Mendoza
- Email: Geovanny0401@gmail.com
- GitHub: Geovannymcode
