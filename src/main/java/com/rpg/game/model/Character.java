package com.rpg.game.model;

 public abstract class Character {                                                                                                                                                                                         
                                                                                                                                                                                                                     
        private String name;                                                                                                                                                                                         
        private int level;                                                                                                                                                                                           
        private int life;
        private int maxLife;                                                                                                                                                                                            
        private int strength;  
        private int damage;
        private final int initialLevel = 1;                                                                                                                                                                                      
                                                                                                                                                                                                                     
        // Constructor                                                                                                                                                                                               
        public Character(String name,int maxLife,int strength,int damage) {                                                                                                                                                                              
            this.name = validateName(name);                                                                                                                                                                                      
            this.level= initialLevel;
            this.maxLife = validatePositiveNumber(maxLife);
            this.life = maxLife; 
            this.strength = validatePositiveNumber(strength);
            this.damage = validatePositiveNumber(damage);                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                          
        }                           
        
        //Validation for stats like maxLife,Strength...
        private int validatePositiveNumber(int stat) {
            if(stat < 0){
                throw new IllegalArgumentException("Invalid stat"); 
            }
            return stat;
        }
        
        //Name validation
        private String validateName(String name) {
            if (name == null || name.trim().isEmpty())
                {
                    throw new IllegalArgumentException("Invalid name"); 
                }
            return name.trim();
        }

        // Getters                                                                                                                                                                                       
        public String getName() {                                                                                                                                                                                    
            return name;                                                                                                                                                                                             
        }                                                                                                                                                                                                            
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                
        public int getLevel() {                                                                                                                                                                                      
            return level;                                                                                                                                                                                            
        }                                                                                                                                                                                                            
                                                                                                                                                                                                                                                                                                                                                                                                                              
                                                                                                                                                                                                                     
        public int getLife() {                                                                                                                                                                                       
            return life;                                                                                                                                                                                             
        }                                                                                                                                                                                                            
                                                                                                                                                                                                                       
        public int getMaxLife (){
            return this.maxLife;
        }
                                                                                                                                                                                                                             
        public int getStrength() {                                                                                                                                                                                   
            return strength;                                                                                                                                                                                         
        }                                                                                                                                                                                                            
                                                                                                                                                                                                                     
        public int getDamage() {
        return damage;
        }
                                                                                                                                                                                                                     
        // Method to return stats                                                                                                                                                                                   
        public String getStats() {                                                                                                                                                                                     
            return ("The hero " + this.name + " is level " + this.level + " with " + this.life + " HP and " + this.strength + " strength.");                                                                                  
        }
        
        //Method to simulate the attack from one character to another
        public void receiveDamage(int damage) {
            //Validate life before took damage. If it's less or equal damage, goes to 0.
            this.life=(Math.max(0,(this.life - damage)));
        }

        public abstract void attack (Character target);

        public void recoverLife(int life) {
            this.life = life;
        }

        // Dentro de Character.java
        public void updateStats(int newLevel, int newStrength) {    
            if (newLevel <= 0 || newStrength <= 0) {
                throw new IllegalArgumentException("El nivel y la fuerza deben ser mayores a cero.");
                }
            this.level = newLevel;
            this.strength = newStrength;
}

    }
