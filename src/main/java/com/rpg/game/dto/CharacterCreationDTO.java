package com.rpg.game.dto;

public record CharacterCreationDTO(
    String name,
    String type // Puede ser "warrior" o "mage"
) {}
