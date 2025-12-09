package com.cat.Entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.cat.Utils.EntityType;

public class Player extends Entity {

    public Player(float x, float y, float speed, int health, int damage, String texture) {
        super(x, y, 48, 48, 1.5f, speed * 0.6f, health, damage, texture, EntityType.PLAYER);
    }

    @Override
    public void update() {
        if (Gdx.input.isKeyPressed(Input.Keys.W)) y += speed;
        if (Gdx.input.isKeyPressed(Input.Keys.S)) y -= speed;
        if (Gdx.input.isKeyPressed(Input.Keys.A)) x -= speed;
        if (Gdx.input.isKeyPressed(Input.Keys.D)) x += speed;

        if (sprite != null) {
            sprite.setPosition(x - width/2, y - height/2);
            sprite.setSize(width, height);
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            em.addEntity(new Bullet(x, y, 16, 16, damage, "bullet"));
        }

        sm.draw(x, y, width, height, texture);
    }

    @Override
    public void onCollide(Entity e) {
        if (e.type == EntityType.ENEMY) {
            this.health = 0;
            this.destroy();
        }

        if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {
            e.knockBackInertia = 3.0f;
        }
    }
}
