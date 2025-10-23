package cn.tofucat.bomb.system;

import cn.tofucat.bomb.components.Mappers;
import cn.tofucat.bomb.components.PositionComponent;
import cn.tofucat.bomb.components.PlayerComponent;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.SortedIteratingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import java.util.Comparator;

/**
 *
 * @author zzzxb
 * 2025/10/22
 */
public class UnifiedRenderSystem extends SortedIteratingSystem {
    private SpriteBatch batch;
    private AssetManager assetManager;

    private OrthographicCamera camera;
    private final Vector2 viewportSize;
    // 定义世界单位（1个单位 = 32像素）
    private static final float WORLD_WIDTH = 20f;  // 对应640像素
    private static final float WORLD_HEIGHT = 15f; // 对应480像素
    private static final float PIXELS_PER_UNIT = 32f; // 1世界单位 = 32像素
    private static final float WORLD_PIXELS_WIDTH = WORLD_WIDTH * PIXELS_PER_UNIT;
    private static final float WORLD_PIXELS_HEIGHT = WORLD_HEIGHT * PIXELS_PER_UNIT;

    public UnifiedRenderSystem(SpriteBatch batch, AssetManager assetManager) {
        super(Family.all(PositionComponent.class).get(), new ZComparator());
        this.batch = batch;
        this.assetManager = assetManager;

        // 创建摄像机，考虑宽高比
        float aspectRatio = (float) Gdx.graphics.getWidth() / (float) Gdx.graphics.getHeight();
        if (aspectRatio >= 1.33f) { // 宽屏
            camera = new OrthographicCamera(WORLD_PIXELS_WIDTH, WORLD_PIXELS_WIDTH / aspectRatio);
        } else { // 竖屏或方屏
            camera = new OrthographicCamera(WORLD_PIXELS_HEIGHT * aspectRatio, WORLD_PIXELS_HEIGHT);
        }

        camera.position.set(WORLD_PIXELS_WIDTH / 2, WORLD_PIXELS_HEIGHT / 2, 0);
        viewportSize = new Vector2(camera.viewportWidth, camera.viewportHeight);
    }

    @Override
    public void update(float deltaTime) {
        camera.update();
        batch.setProjectionMatrix(camera.combined);

        batch.begin();
        super.update(deltaTime);
        batch.end();
    }

    @Override
    protected void processEntity(Entity entity, float v) {
        PlayerComponent playerComponent = Mappers.PLAYER_COMPONENT_MAPPER.get(entity);
        if (playerComponent != null) {
            PositionComponent movement = Mappers.MOVEMENT_COMPONENT_MAPPER.get(entity);
            Texture texture = assetManager.get("player.png", Texture.class);
            batch.draw(texture, movement.x, movement.y);
        }
    }

    private static class ZComparator implements Comparator<Entity> {
        @Override
        public int compare(Entity e1, Entity e2) {
            return 0;
        }
    }
}
