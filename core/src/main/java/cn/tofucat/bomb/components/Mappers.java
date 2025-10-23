package cn.tofucat.bomb.components;

import com.badlogic.ashley.core.ComponentMapper;

/**
 *
 * @author zzzxb
 * 2025/10/22
 */
public class Mappers {
    public static final ComponentMapper<PlayerComponent> PLAYER_COMPONENT_MAPPER = ComponentMapper.getFor(PlayerComponent.class);
    public static final ComponentMapper<PositionComponent> MOVEMENT_COMPONENT_MAPPER = ComponentMapper.getFor(PositionComponent.class);
}
