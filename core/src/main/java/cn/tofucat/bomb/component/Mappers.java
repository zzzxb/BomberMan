package cn.tofucat.bomb.component;

import com.badlogic.ashley.core.ComponentMapper;

/**
 *
 * @author zzzxb
 * 2025/10/22
 */
public class Mappers {
    public static final ComponentMapper<PlayerComponent> PLAYER_COMPONENT_MAPPER = ComponentMapper.getFor(PlayerComponent.class);
    public static final ComponentMapper<MovementComponent> MOVEMENT_COMPONENT_MAPPER = ComponentMapper.getFor(MovementComponent.class);
}
