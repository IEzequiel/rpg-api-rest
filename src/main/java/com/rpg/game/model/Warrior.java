package com.rpg.game.model;

public class Warrior extends Character {

    
    private int rage;
    // Constructor
    public Warrior(String name) {
        super(name,200,80,20);     
        this.rage = 50;
        

    }

    public int getRage() {
        return rage;
    }


    @Override
    public void attack (Character target) {

        if (this.getLife() <= 0){
        throw new CharacterDeadException(this.getName() + " can't attack because is dead");
                }

        int targetDamage = super.getDamage();
        target.receiveDamage(targetDamage);
        System.out.println(this.getName() + " attack " + target.getName() + " and damage him for " + targetDamage + "hp");
    }


}
