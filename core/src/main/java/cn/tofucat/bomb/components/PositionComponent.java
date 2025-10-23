package cn.tofucat.bomb.components;

import com.badlogic.ashley.core.Component;

/**
 *
 * @author zzzxb
 * 2025/10/22
 */
public class PositionComponent implements Component {
    public float x;
    public float y;
    public float z;
    public int moveSpeed;

    public PositionComponent(float x, float y, float z, int moveSpeed) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.moveSpeed = moveSpeed;
    }
}
