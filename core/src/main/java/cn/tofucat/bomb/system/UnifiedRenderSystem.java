package cn.tofucat.bomb.system;

import cn.tofucat.bomb.component.Mappers;
import cn.tofucat.bomb.component.MovementComponent;
import cn.tofucat.bomb.component.PlayerComponent;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.SortedIteratingSystem;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Comparator;

/**
 *
 * @author zzzxb
 * 2025/10/22
 */
public class UnifiedRenderSystem extends SortedIteratingSystem {
    private SpriteBatch batch;
    private AssetManager assetManager;

    public UnifiedRenderSystem(SpriteBatch batch, AssetManager assetManager) {
        super(Family.all(MovementComponent.class).get(), new ZComparator());
        this.batch = batch;
        this.assetManager = assetManager;
    }

    @Override
    public void update(float deltaTime) {
        batch.begin();
        super.update(deltaTime);
        batch.end();
    }

    @Override
    protected void processEntity(Entity entity, float v) {
        PlayerComponent playerComponent = Mappers.PLAYER_COMPONENT_MAPPER.get(entity);
        if(playerComponent != null) {
            MovementComponent movement = Mappers.MOVEMENT_COMPONENT_MAPPER.get(entity);
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
