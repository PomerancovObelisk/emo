package com.cat.Entities;

import com.cat.Utils.EntityType;

public class Enemy extends Entity {
    public Enemy(float x, float y, float speed, int health, int damage, String texture) {
        super(x, y, 40, 40, 1.3f, speed * 0.7f, health, damage, texture, EntityType.ENEMY);
    }

    @Override
    public void update() {

        Player p = em.getPlayer();
        if (p != null) {
            float dx = p.getX() - x;
            float dy = p.getY() - y;

            dir = (float) (Math.atan2(dx, -dy) - (Math.PI / 2));
            if (Math.abs(dx) >= 16f) x += (float) (Math.cos(dir) * speed / 2);
            if (Math.abs(dy) >= 16f) y += (float) (Math.sin(dir) * speed / 2);
        }

        if (sprite != null) {
            sprite.setPosition(x - width/2, y - height/2);
            sprite.setSize(width, height);
        }

        sm.draw(x, y, width, height, texture);

        knockback();
        if (health <= 0) destroy();
    }

    @Override
    public void onCollide(Entity e) {
        if (e.type == EntityType.BULLET) {
            if (this.health <= 0) {
                this.destroy();
            }
        }
    }
}
