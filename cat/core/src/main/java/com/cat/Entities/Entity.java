package com.cat.Entities;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.cat.Managers.EntityManager;
import com.cat.Managers.SpriteManager;
import com.cat.Utils.EntityType;

public abstract class Entity {
    protected float x;
    protected float y;
    protected float dir;
    protected float width;
    protected float height;
    protected float scale;
    protected float knockBackInertia;
    protected float speed;
    protected int health;
    protected int damage;
    protected String texture;
    protected boolean isDestroyed = false;
    protected EntityType type;

    protected Sprite sprite;
    protected final SpriteManager sm = SpriteManager.getInstance();
    protected final EntityManager em = EntityManager.getInstance();

    protected Entity(float x, float y, float width, float height,
                     float scale, float speed, int health, int damage,
                     String texture, EntityType type) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.scale = scale;
        this.speed = speed;
        this.health = health;
        this.damage = damage;
        this.texture = texture;
        this.type = type;

        this.sprite = sm.createSprite(texture, x, y, width, height);
    }

    protected Entity(float x, float y, float width, float height,
                     String texture, EntityType type) {
        this(x, y, width, height, 1f, 0f, 1, 0, texture, type);
    }

    public abstract void update();

    public abstract void onCollide(Entity other);

    protected void knockback() {
        if (knockBackInertia > 0) {
            x += (float) (Math.cos(dir + Math.PI) * knockBackInertia);
            y += (float) (Math.sin(dir + Math.PI) * knockBackInertia);
            knockBackInertia *= 0.8f;
            if (knockBackInertia < 0.1f) {
                knockBackInertia = 0;
            }
        }
    }

    public void destroy() {
        isDestroyed = true;
    }

    public boolean isDestroyed() {
        return isDestroyed;
    }

    public Sprite getSprite() {
        return sprite;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }

    public int getHealth() {
        return health;
    }

    public int getDamage() {
        return damage;
    }

    public String getTexture() {
        return texture;
    }
}
