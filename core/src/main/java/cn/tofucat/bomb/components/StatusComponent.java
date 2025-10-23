package cn.tofucat.bomb.components;

import com.badlogic.ashley.core.Component;

/**
 *
 * @author zzzxb
 * 2025/10/22
 */
public class StatusComponent implements Component {
    /**
     * -1 持续无敌， 0 正常, >0 无敌时间
     */
    public int invincibleTime;
}
