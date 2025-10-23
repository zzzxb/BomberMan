package cn.tofucat.bomb.system;

import cn.tofucat.bomb.components.Mappers;
import cn.tofucat.bomb.components.ObstacleComponent;
import cn.tofucat.bomb.components.PositionComponent;
import cn.tofucat.bomb.components.PlayerComponent;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.utils.Array;

/**
 *
 * @author zzzxb
 * 2025/10/22
 */
public class PlayerControlSystem extends IteratingSystem {
    private static final int GRID_SIZE = 32;
    private static final float MOVE_TIME = 0.15f; // 移动动画时间

    private boolean isMoving = false;
    private float moveTimer = 0;
    private float startX, startY, targetX, targetY;

    public PlayerControlSystem() {
        super(Family.all(PlayerComponent.class).get());
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        PositionComponent movement = Mappers.MOVEMENT_COMPONENT_MAPPER.get(entity);
        if (!isMoving) {
            // 检查输入
            if (Gdx.input.isKeyPressed(Input.Keys.W)) {
                startMove(movement, 0, GRID_SIZE);
            } else if (Gdx.input.isKeyPressed(Input.Keys.S)) {
                startMove(movement, 0, -GRID_SIZE);
            } else if (Gdx.input.isKeyPressed(Input.Keys.A)) {
                startMove(movement, -GRID_SIZE, 0);
            } else if (Gdx.input.isKeyPressed(Input.Keys.D)) {
                startMove(movement, GRID_SIZE, 0);
            }
        } else {
            // 执行移动动画
            moveTimer += deltaTime;
            float progress = Math.min(moveTimer / MOVE_TIME, 1.0f);

            // 线性插值
            movement.x = startX + (targetX - startX) * progress;
            movement.y = startY + (targetY - startY) * progress;

            if (progress >= 1.0f) {
                // 移动完成，确保精确对齐格子
                movement.x = targetX;
                movement.y = targetY;
                isMoving = false;
            }
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.Q)) {
            Gdx.app.exit();
        }
    }

    private void startMove(PositionComponent movement, float dx, float dy) {
        if (canMoveTo(movement.x + dx, movement.y + dy)) {
            isMoving = true;
            moveTimer = 0;
            startX = movement.x;
            startY = movement.y;
            targetX = movement.x + dx;
            targetY = movement.y + dy;
        }
    }

    private boolean canMoveTo(float targetX, float targetY) {
        // 将像素坐标转换为格子坐标
        int gridX = (int) (targetX / GRID_SIZE);
        int gridY = (int) (targetY / GRID_SIZE);

        // 检查边界
        if (gridX < 0 || gridX >= Gdx.graphics.getWidth() || gridY < 0 || gridY >= Gdx.graphics.getHeight()) {
            return false;
        }

        // 检查该格子是否可通行
        return !isBlocked(gridX, gridY);
    }

    private boolean isBlocked(int gridX, int gridY) {
        // 获取所有障碍物实体
        Family obstacleFamily = Family.all(ObstacleComponent.class, PositionComponent.class).get();
        ImmutableArray<Entity> obstacles = getEngine().getEntitiesFor(obstacleFamily);

        for (Entity obstacle : obstacles) {
            PositionComponent obstaclePos = obstacle.getComponent(PositionComponent.class);
            int obstacleGridX = (int) (obstaclePos.x / GRID_SIZE);
            int obstacleGridY = (int) (obstaclePos.y / GRID_SIZE);

            if (obstacleGridX == gridX && obstacleGridY == gridY) {
                return true; // 这个格子有障碍物
            }
        }

        return false;
    }
}
