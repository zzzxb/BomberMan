package cn.tofucat.bomb.components;

import com.badlogic.ashley.core.Component;

/**
 *
 * @author zzzxb
 * 2025/10/22
 */
public class HealthComponent implements Component {
    /**
     * 自定义健康值
     */
    public int currentHitPoints;
    /**
     * 最大健康值
     */
    public int maxHitPoints;
    /**
     * 自定义魔法值
     */
    public int currentManaPoints;
    /**
     * 最大魔法值
     */
    public int maxManaPoints;

    public HealthComponent() {
    }

    public HealthComponent(int maxHitPoints, int maxManaPoints) {
        this.maxHitPoints = maxHitPoints;
        this.currentHitPoints = maxHitPoints;
        this.maxManaPoints = maxManaPoints;
        this.currentManaPoints = maxManaPoints;
    }
}
