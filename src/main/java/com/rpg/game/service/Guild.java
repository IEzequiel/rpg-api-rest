package com.rpg.game.service;

import org.springframework.stereotype.Service;
import com.rpg.game.model.Character;
import com.rpg.game.model.Warrior;
import com.rpg.game.model.Mage;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service // <-- Le dice a Spring que esta clase maneja la lógica de negocio
public class Guild {
    private final List<Character> members = new ArrayList<>();

    // Constructor: filling with test members
    public Guild() {
        this.members.add(new Warrior("Aragorn"));
        this.members.add(new Mage("Gandalf"));
    }

    //GET members list
    public List<Character> getMembersList() {
        return new ArrayList<>(members); // Copia defensiva
    }
    
    //add members to the Guild
    public void addMember(Character character) {
        this.members.add(character);
    }

    //GET Search member by Name
    public Optional<Character> findByName(String name){
        return this.members.stream().filter(member -> member.getName().equalsIgnoreCase(name)).findFirst();
    }

    

}
