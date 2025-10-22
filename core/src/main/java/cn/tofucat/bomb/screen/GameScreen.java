package cn.tofucat.bomb.screen;

import cn.tofucat.bomb.component.*;
import cn.tofucat.bomb.system.*;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

/**
 *
 * @author zzzxb
 * 2025/10/22
 */
public class GameScreen extends ScreenAdapter {
    private Engine engine;
    private SpriteBatch batch;
    private AssetManager assetManager;

    @Override
    public void show() {
        batch = new SpriteBatch();
        engine = new PooledEngine();
        assetManager = new AssetManager();

        assetManager.load("bomb.png", Texture.class);
        assetManager.load("enemy.png", Texture.class);
        assetManager.load("player.png", Texture.class);
        assetManager.finishLoading();

        engine.addSystem(new PlayerControlSystem());
        engine.addSystem(new UnifiedRenderSystem(batch, assetManager));

        engine.addEntity(createPlayerEntity());
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(Color.WHITE);
        engine.update(delta);
    }

    private Entity createPlayerEntity() {
        Entity player = engine.createEntity();
        player.add(new IdentityComponent("豆腐猫"));
        player.add(new HealthComponent(1, 1));
        player.add(new StatusComponent());
        player.add(new MovementComponent(100, 100, 10,100));
        player.add(new PlayerComponent());
        return player;
    }

    @Override
    public void hide() {
        batch.dispose();
        assetManager.dispose();
    }
}
