package com.rpg.game.model;

 public abstract class Character {                                                                                                                                                                                         
                                                                                                                                                                                                                     
        private String name;                                                                                                                                                                                         
        private int level;                                                                                                                                                                                           
        private int life;
        private int maxLife;                                                                                                                                                                                            
        private int strength;  
        private int damage;                                                                                                                                                                                      
                                                                                                                                                                                                                     
        // Constructor                                                                                                                                                                                               
        public Character(String name) {                                                                                                                                                                              
            this.setName(name);                                                                                                                                                                                      
            this.setLevel(1);                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                           
        }                           
        
        //Maxlife needs to be seeting after the creation of the character.
        //We use this variable to have a reference to prevent, for example
        //an overhealing from Mage
        
                                                                                                                                                                                                                     
        // Getters and Setters                                                                                                                                                                                       
        public String getName() {                                                                                                                                                                                    
            return name;                                                                                                                                                                                             
        }                                                                                                                                                                                                            
                                                                                                                                                                                                                     
        private void setName(String name) {                                                                                                                                                                           
            this.name = name;                                                                                                                                                                                        
        }                                                                                                                                                                                                            
                                                                                                                                                                                                                     
        public int getLevel() {                                                                                                                                                                                      
            return level;                                                                                                                                                                                            
        }                                                                                                                                                                                                            
                                                                                                                                                                                                                     
        public void setLevel(int level) {                                                                                                                                                                            
            if (level > 0) {                                                                                                                                                                                         
                this.level = level;                                                                                                                                                                                  
            }                                                                                                                                                                                                        
        }                                                                                                                                                                                                            
                                                                                                                                                                                                                     
        public int getLife() {                                                                                                                                                                                       
            return life;                                                                                                                                                                                             
        }                                                                                                                                                                                                            
                                                                                                                                                                                                                     
        public void setLife(int life) { 
            //validating the initial number on creation                                                                                                                                                                          
            if (life >= 0) {                                                                                                                                                                                         
                this.life = life;                                                                                                                                                                                    
            }                                                                                                                                                                                                        
        }   
        
        public int getMaxLife (){
            return this.maxLife;
        }

        public void setMaxLife(int maxLife) {
            this.maxLife = maxLife;
        }
                                                                                                                                                                                                                     
        public int getStrength() {                                                                                                                                                                                   
            return strength;                                                                                                                                                                                         
        }                                                                                                                                                                                                            
                                                                                                                                                                                                                     
        public void setStrength(int strength) {                                                                                                                                                                      
            if (strength >= 0) {                                                                                                                                                                                     
                this.strength = strength;                                                                                                                                                                            
            }                                                                                                                                                                                                        
        }     
        
        public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }
                                                                                                                                                                                                                     
        // Method to return stats                                                                                                                                                                                   
        public String getStats() {                                                                                                                                                                                     
            return ("The hero " + name + " is level " + level + " with " + life + " HP and " + strength + " strength.");                                                                                  
        }
        
        public abstract void attack (Character target);

        //Method to simulate the attack from one character to another
        public void receiveDamage(int damage) {
            //Validate life before took damage. If it's less or equal damage, goes to 0.
            this.setLife(Math.max(0,(this.life - damage)));

            //Worst logic, simplified by Math.max
            /* if (this.life <= damage){
                this.setLife(0);
            } else {
                this.setLife(this.life - damage);
            } */
        }

    
    }
