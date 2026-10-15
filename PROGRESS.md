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
    - `GET /api/guild/members/{name}`: Busca por nombre con `ResponseEntity` (`200 OK` si existe, `404 Not Found` si no).
- `com.rpg.game.service`
  - `GuildService.java`: Servicio de negocio `@Service`. Maneja la lista de miembros de la hermandad en memoria, búsqueda por nombre (`findByName`) y búsqueda del más fuerte (`getStrongestMember`).
- `com.rpg.game.model`
  - `Character.java`: Clase base abstracta. Inicialización en constructor con validación directa (*Fail-Fast*).
  - `Warrior.java`: Subclase de `Character`. Inicializa stats con `super(...)`. Atributo específico: `rage`.
  - `Mage.java`: Subclase de `Character`, implementa `Healer`. Inicializa stats con `super(...)`. Atributo específico: `mana`, método `heal(...)` y `meditate()`.
  - `Healer.java`: Interfaz de comportamiento para curación.
  - `CharacterDeadException.java`: Excepción de dominio cuando un personaje sin vida intenta atacar.

---

## 3. Registro de Avance (Completado)

- [x] **Punto 1:** Renombrado de `Guild` a `GuildService` (nomenclatura Clean Code para componentes de servicio).
- [x] **Punto 2:** `GuildController` refactorizado con `ResponseEntity` para retornar `404 Not Found` cuando `findByName(...)` retorna vacío en vez de retornar `null` con `200 OK`.
- [x] **Punto 3:** 
  - Eliminación de setters públicos y privados innecesarios en `Character`, `Warrior` y `Mage`.
  - Inicialización limpia mediante llamada al constructor padre `super(name, maxLife, strength, damage)`.
  - Implementación de validaciones directas en el constructor (*Fail-Fast*).
  - Corrección de validación de `name` y convención de variable para `initialLevel` en `Character.java`.
  - Verificación exitosa de la suite de pruebas unitarias (`./mvnw test` en verde).

---

## 4. Próximos Pasos (Roadmap)

1. **Continuar con los ejercicios propuestos por Dan Vega / Gemini** (nuevos endpoints, métodos de servicio, operaciones POST/PUT/DELETE, etc.).
2. **DTOs con Records (Java 17+)**:
   - Evitar exponer las entidades de dominio directamente en la API REST.
   - Crear DTOs inmutables con `record` para las respuestas.
3. **Manejo Global de Excepciones**:
   - Implementar `@RestControllerAdvice` para capturar `CharacterDeadException` y `IllegalArgumentException` y convertirlas en respuestas HTTP estándar (ej. `400 Bad Request`).
