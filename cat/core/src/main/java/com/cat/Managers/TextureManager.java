package com.cat.Managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

import java.util.HashMap;
import java.util.Map;

public class TextureManager {

    private static final Map<String, Texture> textures = new HashMap<>();

    public TextureManager() {
    }

    public void loadTextures() {
        // pngs
        load("heart", "heart.png");
        load("libgdx", "libgdx.png");
        load("vampire", "vampire.png"); //emo

        // stare nazvy
        load("amongas", "vampire.png");
        load("mogus", "vampire.png");
        load("bullet", "heart.png");
    }

    private void load(String key, String fileName) {
        if (!textures.containsKey(key)) {
            textures.put(key, new Texture(Gdx.files.internal(fileName)));
        }
    }

    public static Texture get(String key) {
        return textures.get(key);
    }

    public static void disposeAll() {
        for (Texture t : textures.values()) {
            t.dispose();
        }
        textures.clear();
    }
}
