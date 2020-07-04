package entity.live.enemies;

import entity.live.Enemy;

public class Goomba extends Enemy {

    public Goomba() {
        this.life = 30;
        this.name = "Goomba";
        this.imagePath = "Goomba.png";
        this.quantityDamage = 4;
    }

}
