# Contexto del Proyecto: RPG REST API (Spring Boot)

> Este archivo sirve como bitácora y memoria compartida para sincronizar el contexto de trabajo y las sesiones con Antigravity entre diferentes equipos (hogar y trabajo).

---

## 1. Perfil del Asistente (System Prompt / Directrices)

Actúa como un **Tech Lead y Desarrollador Senior de Java / Spring Boot**. 
El objetivo es aprender las mejores prácticas de la industria para conseguir empleo como desarrollador backend.
No te limites a que el código "simplemente funcione". Exige y aplica los siguientes estándares:

1. **Convenciones REST estrictas y códigos de estado HTTP correctos:**
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

## 2. Arquitectura y Estado Actual del Proyecto

### Estructura de paquetes:
- `com.rpg.game.controller`
  - `GuildController.java`: Controlador REST base en `/api/guild`. 
    - `GET /api/guild/members`: Lista todos los personajes (`200 OK`).
    - `GET /api/guild/members/strongest`: Retorna el miembro con mayor fuerza usando `Optional` y `ResponseEntity` (`200 OK` o `404 Not Found`).
    - `GET /api/guild/members/{name}`: Busca por nombre con `ResponseEntity` (`200 OK` si existe, `404 Not Found` si no).
    - `POST /api/guild/members`: Da de alta un nuevo miembro recibiendo un DTO y retornando estado `201 Created`.
- `com.rpg.game.service`
  - `GuildService.java`: Servicio de negocio `@Service`. Maneja la lista en memoria, búsqueda por nombre (`findByName`), obtención del más fuerte (`getStrongestMember`) y creación de nuevos héroes (`createCharacter`).
- `com.rpg.game.model`
  - `Character.java`: Clase base abstracta. Inicialización en constructor con validación directa (*Fail-Fast*).
  - `Warrior.java`: Subclase de `Character`. Atributo específico: `rage`.
  - `Mage.java`: Subclase de `Character`, implementa `Healer`. Atributo específico: `mana`.
  - `Healer.java`: Interfaz de comportamiento para curación.
  - `CharacterDeadException.java`: Excepción de dominio.
- `com.rpg.game.dto`
  - `CharacterCreationDTO.java`: Record inmutable para mapear las peticiones de creación de personajes (`name`, `type`).
- `requests.http`
  - Archivo de pruebas locales integrado mediante la extensión *Rest Client* para validar flujos `GET` y `POST`.

---

## 3. Registro de Avance (Completado)

- [x] **Punto 1:** Renombrado de `Guild` a `GuildService` (nomenclatura Clean Code).
- [x] **Punto 2:** `GuildController` refactorizado con `ResponseEntity` para retornar `404 Not Found` en lugar de `null` en búsquedas individuales.
- [x] **Punto 3:** Optimización de modelos (eliminación de setters innecesarios, constructores seguros y *Fail-Fast*).
- [x] **Punto 4:** Implementación del endpoint `GET /api/guild/members/strongest` y su lógica de negocio basada en Streams y `Comparator`.
- [x] **Punto 5:** Creación del `CharacterCreationDTO` (Record de Java 17+) para desacoplar el dominio de la entrada HTTP.
- [x] **Punto 6:** Implementación de la petición `POST /api/guild/members` respondiendo correctamente con código HTTP `201 Created` y pruebas funcionales mediante el archivo `requests.http`.

---

## 4. Próximos Pasos (Roadmap)

1. **Actualización y Modificación (PUT / PATCH)**:
   - Permitir modificar atributos de personajes existentes (ej. cambiar nivel o vida).
2. **Eliminación de Recursos (DELETE)**:
   - Endpoint para remover un miembro del gremio por su nombre.
3. **Manejo Global de Excepciones**:
   - Implementar `@RestControllerAdvice` para estandarizar los errores de la API (ej. transformar `IllegalArgumentException` en `400 Bad Request`).
