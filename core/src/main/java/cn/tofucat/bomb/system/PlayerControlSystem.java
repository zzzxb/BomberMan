package cn.tofucat.bomb.system;

import cn.tofucat.bomb.component.Mappers;
import cn.tofucat.bomb.component.MovementComponent;
import cn.tofucat.bomb.component.PlayerComponent;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

/**
 *
 * @author zzzxb
 * 2025/10/22
 */
public class PlayerControlSystem extends IteratingSystem {
    public PlayerControlSystem() {
        super(Family.all(PlayerComponent.class).get());
    }

    @Override
    protected void processEntity(Entity entity, float delta) {
        MovementComponent movement = Mappers.MOVEMENT_COMPONENT_MAPPER.get(entity);
        float step = movement.moveSpeed * delta;

        if(Gdx.input.isKeyPressed(Input.Keys.W)){
            movement.y += step;
        }else if(Gdx.input.isKeyPressed(Input.Keys.S)){
            movement.y -= step;
        }else if(Gdx.input.isKeyPressed(Input.Keys.A)){
            movement.x -= step;
        }else if(Gdx.input.isKeyPressed(Input.Keys.D)){
            movement.x += step;
        }

        if(Gdx.input.isKeyJustPressed(Input.Keys.Q)) {
            Gdx.app.exit();
        }
    }
}
