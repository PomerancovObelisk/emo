package com.cat.Entities;

import com.badlogic.gdx.Gdx;
import com.cat.Utils.EntityType;
import java.util.List;

public class Bullet extends Entity {
    private static final float HOMING_STRENGTH = 0.1f;
    private Entity target;

    public Bullet(float x, float y, float width, float height, int damage, String texture) {
        super(x, y, 24, 24, 1.2f, 0, 1, damage, texture, EntityType.BULLET);

        dir = (float) (Math.atan2(
            Gdx.input.getX() - x,
            -((Gdx.graphics.getHeight() - Gdx.input.getY()) - y)
        ) - (Math.PI / 2));

        speed = 8f;
        this.damage = damage;

        findNearestEnemy();
    }

    private void findNearestEnemy() {
        List<Entity> entities = em.getEntities();
        float closestDistance = Float.MAX_VALUE;

        for (Entity e : entities) {
            if (e.type == EntityType.ENEMY) {
                float dx = e.getX() - x;
                float dy = e.getY() - y;
                float distance = dx * dx + dy * dy; // comparing dist

                if (distance < closestDistance) {
                    closestDistance = distance;
                    target = e;
                }
            }
        }
    }

    @Override
    public void update() {
        if (target != null && !target.isDestroyed()) {
            float targetX = target.getX() + target.getWidth()/2;
            float targetY = target.getY() + target.getHeight()/2;

            float dx = targetX - x;
            float dy = targetY - y;
            float targetDir = (float) Math.atan2(dy, dx);

            float angleDiff = (float) ((((targetDir - dir) % (2 * Math.PI)) + 3 * Math.PI) % (2 * Math.PI)) - (float) Math.PI;
            dir += angleDiff * HOMING_STRENGTH;
        }

        this.x += (float) (Math.cos(dir) * speed);
        this.y += (float) (Math.sin(dir) * speed);

        if (sprite != null) {
            sprite.setPosition(x - width/2, y - height/2);
            sprite.setSize(width, height);
            sprite.setRotation((float) Math.toDegrees(dir) + 90);
        }
        sm.draw(x, y, width, height, texture);
    }

    @Override
    public void onCollide(Entity e) {
        if (e.type != EntityType.PLAYER) {
            e.health -= this.damage;

            if (e.type != EntityType.ENEMY || e.health <= 0) {
                e.destroy();
            }

            this.destroy();
        }
    }
}
