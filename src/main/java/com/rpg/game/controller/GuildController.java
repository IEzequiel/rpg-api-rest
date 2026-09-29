package com.rpg.game.controller;

import org.springframework.web.bind.annotation.*;
import com.rpg.game.service.Guild;
import com.rpg.game.model.Character;
import java.util.List;

@RestController // Indica que responderá en formato JSON
@RequestMapping("/api/guild") // Ruta base de la URL
public class GuildController {

    private final Guild guildService;

    // Inyección de dependencias por constructor
    public GuildController(Guild guildService) {
        this.guildService = guildService;
    }

    @GetMapping("/members") // Petición GET: http://localhost:8080/api/guild/members
    public List<Character> getMembers() {
        return guildService.getMembersList();
    }

    @GetMapping("/members/{name}")
    public Character getMemberByName(@PathVariable String name) {
            return guildService.findByName(name)
                .orElse(null); // Si lo encuentra devuelve el personaje; si no, devuelve null (Spring lo responderá vacío)
}

}
