package cn.tofucat.bomb.component;

import com.badlogic.ashley.core.Component;

/**
 *
 * @author zzzxb
 * 2025/10/22
 */
public class IdentityComponent implements Component {
    public String name;
    public String nickName;
    public int group;
    public String groupName;

    public IdentityComponent() {
    }

    public IdentityComponent(String name) {
        this.name = name;
    }

    public IdentityComponent(int group, String groupName, String name, String nickName) {
        this.group = group;
        this.groupName = groupName;
        this.name = name;
        this.nickName = nickName;
    }
}
