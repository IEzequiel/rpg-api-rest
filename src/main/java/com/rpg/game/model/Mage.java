package com.rpg.game.model;

public class Mage extends Character implements Healer {

    private int mana;

    public Mage(String name) {
        super(name);
        super.setDamage(10);
        super.setLife(100);
        super.setMaxLife(100);
        super.setStrength(50);
        this.setMana(100);
    }

    public int getMana() {
        return mana;
    }

    private void setMana(int mana) {
        this.mana = mana;
    }

    //Method to replenish mana
    public void meditate() {
    System.out.println(getName() + " se concentra y recarga su maná por completo en segundo plano.");
    this.setMana(100); // En un entorno asíncrono real, esto tardaría, pero aquí no bloquea el flujo
            }

    @Override
    public void heal(Character player) {
        int lifePlayer = player.getLife();
        
         //Also, mage lose mana when heals, so we need to validate if we have enough
        if (this.mana >= 10){
            player.setLife(Math.min(player.getMaxLife(), lifePlayer + 20));
            //Worst logic
            /* if(lifePlayer >= player.getMaxLife() - 20) {
                player.setLife(player.getMaxLife());
            } else {
                player.setLife(lifePlayer + 20);
            } */
        setMana(this.getMana() - 10);
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
        setMana(this.getMana() - 20); 
        System.out.println(this.getName() + " attack " + target.getName() + " and damage him for " + targetDamage + "hp"); 
    } else {
        System.out.println(this.getName() + " doesn't have enough mana. Meditation is active now!");
        this.meditate(); // Mage's autocharge
        }      
    }
    
}
