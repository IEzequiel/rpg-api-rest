package com.rpg.game.model;

public class CharacterDeadException extends RuntimeException {
    public CharacterDeadException (String message){
        super(message);
    }
}
