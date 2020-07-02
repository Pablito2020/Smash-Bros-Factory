package gui;

import acm.graphics.GImage;
import acm.program.GraphicsProgram;
import entity.live.Enemy;
import entity.live.Hero;
import gui.components.Button;

import java.awt.*;

public class FightDisplay implements Display {

    private final GraphicsProgram applicationContext;
    private GImage imageHero;
    private GImage imageEnemy;
    public final Button attackButton;
    private final Hero hero;
    private final Enemy enemy;

    public FightDisplay(GraphicsProgram applicationContext, Hero hero, Enemy enemy) {
        this.applicationContext = applicationContext;
        this.hero = hero;
        this.enemy = enemy;
        this.attackButton = new Button(applicationContext.getWidth() / 2, applicationContext.getHeight() / 2, "Attack!");
    }

    private void addHero(Hero hero) {
        String imagePath = hero.getImagePath();
        imageHero = new GImage(imagePath);
        double x = (applicationContext.getWidth() - imageHero.getWidth()) * 1 / 4;
        double y = (applicationContext.getHeight() - imageHero.getHeight()) * 2 / 3;
        applicationContext.add(imageHero, x, y);
    }

    private void addEnemy(Enemy enemy) {
        String imagePath = enemy.getImagePath();
        imageEnemy = new GImage(imagePath);
        double x = (applicationContext.getWidth() - imageEnemy.getWidth()) * 3 / 4;
        double y = (applicationContext.getHeight() - imageEnemy.getHeight()) / 3;
        applicationContext.add(imageEnemy, x, y);
    }

    @Override
    public void clean() {
        applicationContext.remove(imageHero);
        applicationContext.remove(imageEnemy);
        applicationContext.remove(attackButton);
    }

    @Override
    public void addElements() {
        this.applicationContext.setBackground(Color.BLACK);
        this.applicationContext.setTitle("Smash!");
        this.applicationContext.add(attackButton);
        this.addHero(hero);
        this.addEnemy(enemy);
    }

    public void executeFight() {
        while(!enemy.hasDied() && !hero.hasDied()) {
            enemy.attack(hero);
            hero.attack(enemy);
            applicationContext.setTitle("Enemy: "+  enemy.percentageLife() + ", Hero: " + hero.percentageLife());
            applicationContext.pause(2000);
        }
    }

}
