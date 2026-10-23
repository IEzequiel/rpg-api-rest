# Contexto del Proyecto: RPG REST API (Spring Boot)

> Este archivo sirve como bitácora y memoria compartida para sincronizar el contexto de trabajo y las sesiones con Antigravity entre diferentes equipos (hogar y trabajo).

---

## 1. Perfil del Asistente (System Prompt / Directrices)

Actúa como un **Tech Lead y Desarrollador Senior de Java / Spring Boot**. 
El objetivo es aprender las mejores prácticas de la industria para conseguir empleo como desarrollador backend.
No te limites a que el código "simplemente funcione". Exige y aplica los siguientes estándares:

1. **Convenciones REST strictly y códigos de estado HTTP correctos:**
   - Recursos bien nombrados (plurales, sustantivos).
   - Uso semántico de verbos (`GET`, `POST`, `PUT`, `PATCH`, `DELETE`).
   - Respuestas HTTP explícitas (`200 OK`, `201 Created`, `404 Not Found`, `400 Bad Request`, `204 No Content`).
2. **Inyección de dependencias por constructor:**
   - Uso de campos `private final`.
   - Evitar `@Autowired` en atributos.
3. **Clean Code, OOP y manejo robusto de excepciones:**
   - Encapsulamiento real (no exponer setters innecesarios).
   - Fail-Fast: Validar invariantes en el constructor y lanzar excepciones específicas (`IllegalArgumentException`, etc.).
   - Métodos con nombres de negocio ("Tell, Don't Ask").
4. **Java 17+ Moderno:**
   - Uso de `Records` para DTOs.
   - `Optional`, Streams API y programación funcional donde aporte claridad.
   - Métodos auxiliares de validación `static` o utilitarios estándar (`Objects.requireNonNull`).
5. **Feedback formativo estilo Pull Request:**
   - Explicar siempre el **"por qué"** detrás de cada corrección técnica y arquitectónica.

---

## 2. Arquitectura y Estado Actual del Proyecto (CRUD Completo)

### Estructura de paquetes:
- `com.rpg.game.controller`
  - `GuildController.java`: Controlador REST base en `/api/guild`.
    - `GET /api/guild/members`: Lista todos los personajes (`200 OK`).
    - `GET /api/guild/members/strongest`: Retorna el miembro más fuerte (`200 OK` / `404 Not Found`).
    - `GET /api/guild/members/{name}`: Busca por nombre (`200 OK` / `404 Not Found`).
    - `POST /api/guild/members`: Crea un personaje vía DTO (`201 Created`).
    - `PUT /api/guild/members/{name}`: Actualiza atributos vía DTO (`200 OK` / `404 Not Found`).
    - `DELETE /api/guild/members/{name}`: Elimina personaje por nombre (`204 No Content` / `404 Not Found`).
- `com.rpg.game.service`
  - `GuildService.java`: Servicio `@Service`. Controla la lista en memoria, prevención de duplicados, operaciones de consulta, actualización y eliminación.
- `com.rpg.game.model`
  - `Character.java`, `Warrior.java`, `Mage.java`, `Healer.java`, `CharacterDeadException.java`.
- `com.rpg.game.dto`
  - `CharacterCreationDTO.java`: Record para creación (`name`, `type`).
  - `CharacterUpdateDTO.java`: Record para actualización (`level`, `strength`).
- `requests.http`
  - Suite de pruebas de integración local (*REST Client*) probada y funcional.

---

## 3. Registro de Avance (Completado - Bloque CRUD)

- [x] **Punto 1:** Renombrado y arquitectura del servicio `GuildService`.
- [x] **Punto 2:** Manejo explicito de respuestas HTTP con `ResponseEntity` (`404 Not Found` en lugar de `null`).
- [x] **Punto 3:** Optimización de modelos (encapsulamiento y validaciones *Fail-Fast*).
- [x] **Punto 4:** Implementación del endpoint `GET /api/guild/members/strongest`.
- [x] **Punto 5:** Creación de DTOs con `Record` en Java 17+ (`CharacterCreationDTO` y `CharacterUpdateDTO`).
- [x] **Punto 6:** Implementación de `POST /api/guild/members` con código `201 Created`.
- [x] **Punto 7:** Implementación de `PUT /api/guild/members/{name}` para actualización de estadísticas.
- [x] **Punto 8:** Implementación de `DELETE /api/guild/members/{name}` respondiendo `204 No Content`.
- [x] **Punto 9:** Pruebas completas del ciclo de vida CRUD verificadas con `requests.http`.

---

## 4. Próximos Pasos (Roadmap - Siguiente etapa del Curso)

1. **Validación y Prevención de Duplicados en Servicio**:
   - Aplicar la comprobación de nombres existentes antes de registrar nuevos personajes.
2. **Manejo Global de Excepciones con `@RestControllerAdvice`**:
   - Capturar `IllegalArgumentException` (como cuando se intenta duplicar un personaje o enviar un nivel negativo) y transformarla automáticamente en un código HTTP `400 Bad Request` bien estructurado.
3. **Avanzar en el Video de Dan Vega (Rango objetivo: 1:40 a 2:15)**:
   - Revisar los temas que abarca Dan Vega en el siguiente bloque (Spring Data JPA, Persistencia en Base de Datos H2/PostgreSQL o Inyección de Propiedades).
