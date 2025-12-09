package com.cat;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.utils.ScreenUtils;
import com.cat.Entities.Player;
import com.cat.Entities.Enemy;
import com.cat.Managers.SpriteManager;
import com.cat.Managers.TextureManager;
import com.cat.Managers.EntityManager;

public class catMain extends ApplicationAdapter {
    private SpriteManager sm;
    private EntityManager em;
    private TextureManager tm;

    @Override
    public void create() {
        tm = new TextureManager();
        tm.loadTextures();

        sm = SpriteManager.getInstance();
        em = EntityManager.getInstance();

        em.addPlayer(new Player(400, 400, 10, 100,20, "vampire"));
        em.addEntity(new Enemy(200, 200, 2, 100, 10 ,"heart"));
    }

    @Override
    public void render() {
        ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);

        sm.begin();
        em.update();
        sm.end();
    }

    @Override
    public void dispose() {
        sm.dispose();
    }
}
