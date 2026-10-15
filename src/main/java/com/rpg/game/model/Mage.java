package com.rpg.game.model;

public class Mage extends Character implements Healer {

    private int mana;

    public Mage(String name) {
        super(name,100,50,10);
        this.mana = 100;
    }

    public int getMana() {
        return mana;
    }

    //Method to replenish mana
    public void meditate() {
    System.out.println(getName() + "meditating to replenish mana");
    this.mana= 100; // En un entorno asíncrono real, esto tardaría, pero aquí no bloquea el flujo
            }

    @Override
    public void heal(Character player) {
        int lifePlayer = player.getLife();
        
            //Also, mage lose mana when heals, so we need to validate if we have enough
            if (this.mana >= 10)    {
                player.recoverLife(Math.min(player.getMaxLife(), lifePlayer + 20));
                this.mana = this.getMana() - 10;
                System.out.println(player.getName() + " was healed by 20 hp");
            } else {
                System.out.println("Not enough mana");
                    }
    }

    @Override
    public void attack(Character target) {
    int targetDamage = super.getDamage(); //
    
    if (this.getLife() <= 0){
        throw new CharacterDeadException(this.getName() + " can't attack because is dead");
            }

    if (this.mana >= 20) { 
        target.receiveDamage(targetDamage); 
        this.mana = this.getMana() - 20; 
        System.out.println(this.getName() + " attack " + target.getName() + " and damage him for " + targetDamage + "hp"); 
    } else {
        System.out.println(this.getName() + " doesn't have enough mana. Meditation is active now!");
        this.meditate(); // Mage's autocharge
        }      
    }
    
}
