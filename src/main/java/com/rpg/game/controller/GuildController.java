package com.rpg.game.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.rpg.game.service.GuildService;
import com.rpg.game.model.Character;
import java.util.List;

@RestController // Indica que responderá en formato JSON
@RequestMapping("/api/guild") // Ruta base de la URL
public class GuildController {

    private final GuildService guildService;

    // Inyección de dependencias por constructor
    public GuildController(GuildService guildService) {
        this.guildService = guildService;
    }

    @GetMapping("/members") // Petición GET: http://localhost:8080/api/guild/members
    public List<Character> getMembers() {
        return guildService.getMembersList();
    }

    @GetMapping("/members/strongest")
    public ResponseEntity<Character> getStrongestMember() {
        return guildService.getStrongestMember()
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/members/{name}")
    public ResponseEntity <Character> getMemberByName(@PathVariable String name) {
            return guildService.findByName(name)
                    .map(ResponseEntity::ok) //Si ok, respuesta 200 con personaje
                     .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/members")
    public ResponseEntity<Character> addMember(@RequestBody com.rpg.game.dto.CharacterCreationDTO request) {
        Character createdCharacter = guildService.createCharacter(request);
        
        // Retornamos HTTP 201 Created junto con el objeto creado
        return ResponseEntity.status(org.springframework.http.HttpStatus.CREATED)
                .body(createdCharacter);
    }

}
