package cn.tofucat.bomb;

import cn.tofucat.bomb.screen.HomeScreen;
import com.badlogic.gdx.Game;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class BomberManMain extends Game {
    @Override
    public void create() {
        setScreen(new HomeScreen(this));
    }
}
