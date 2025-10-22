package cn.tofucat.bomb.component;

import com.badlogic.ashley.core.Component;

/**
 *
 * @author zzzxb
 * 2025/10/22
 */
public class MovementComponent implements Component {
    public float x;
    public float y;
    public float z;
    public int moveSpeed;

    public MovementComponent(float x, float y, float z, int moveSpeed) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.moveSpeed = moveSpeed;
    }
}
