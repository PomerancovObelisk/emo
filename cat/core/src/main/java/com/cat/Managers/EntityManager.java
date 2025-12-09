package com.cat.Managers;

import com.cat.Entities.Entity;
import com.cat.Entities.Player;

import java.util.ArrayList;
import java.util.List;

public class EntityManager {
    private static EntityManager instance;

    public static EntityManager getInstance() {
        if (instance == null) instance = new EntityManager();
        return instance;
    }

    private final List<Entity> entities = new ArrayList<>();
    private final List<Entity> buffer = new ArrayList<>();
    private Player player;

    private EntityManager() {
    }

    public void addEntity(Entity e) {
        buffer.add(e);
    }

    public void addPlayer(Player p) {
        this.player = p;
        addEntity(p);
    }

    public Player getPlayer() {
        return player;
    }

    public List<Entity> getEntities() {
        return new ArrayList<>(entities);
    }

    public void update() {
        for (Entity e : entities) {
            e.update();
        }

        for (int i = 0; i < entities.size(); i++) {
            Entity e = entities.get(i);
            for (int j = i + 1; j < entities.size(); j++) {
                Entity e2 = entities.get(j);
                if (e.getSprite() != null && e2.getSprite() != null &&
                    e.getSprite().getBoundingRectangle().overlaps(e2.getSprite().getBoundingRectangle())) {
                    e.onCollide(e2);
                    e2.onCollide(e);
                }
            }
        }


        entities.removeIf(Entity::isDestroyed);
        entities.addAll(buffer);
        buffer.clear();
    }
}
