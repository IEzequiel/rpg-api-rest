package com.rpg.game.service;

import org.springframework.stereotype.Service;
import com.rpg.game.model.Character;
import com.rpg.game.model.Warrior;
import com.rpg.game.model.Mage;
import java.util.ArrayList;
import java.util.List;

@Service // <-- Le dice a Spring que esta clase maneja la lógica de negocio
public class Guild {
    private final List<Character> members = new ArrayList<>();

    // Constructor: Carga datos ficticios al arrancar la app
    public Guild() {
        this.members.add(new Warrior("Aragorn"));
        this.members.add(new Mage("Gandalf"));
    }

    public List<Character> getMembersList() {
        return new ArrayList<>(members); // Copia defensiva
    }

    public void addMember(Character character) {
        this.members.add(character);
    }
}
