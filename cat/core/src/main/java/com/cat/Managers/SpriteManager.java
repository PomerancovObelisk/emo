package com.cat.Managers;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class SpriteManager {
    private static SpriteManager instance;

    public static SpriteManager getInstance() {
        if (instance == null) {
            instance = new SpriteManager();
        }
        return instance;
    }

    private final SpriteBatch batch;

    private SpriteManager() {
        batch = new SpriteBatch();
    }

    public void begin() {
        batch.begin();
    }

    public void end() {
        batch.end();
    }

    public void dispose() {
        batch.dispose();
        TextureManager.disposeAll();
    }

    public void draw(float x, float y, float width, float height, String textureKey) {
        Texture tex = TextureManager.get(textureKey);
        if (tex != null) {
            batch.draw(tex, x, y, width, height);
        }
    }

    public Sprite createSprite(String textureKey, float x, float y, float width, float height) {
        Texture tex = TextureManager.get(textureKey);
        if (tex == null) return null;
        Sprite sprite = new Sprite(tex);
        sprite.setSize(width, height);
        sprite.setPosition(x, y);
        return sprite;
    }

    public SpriteBatch getBatch() {
        return batch;
    }
}
