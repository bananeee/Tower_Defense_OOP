package Entity;

import javafx.scene.canvas.GraphicsContext;

public abstract class GameEntity {
    protected int tickCount = 1;
    public void tick() {
        tickCount ++;
        if (tickCount == 55440)
            tickCount = 0;
    }

    public void draw(GraphicsContext gc) {};
    public void onUpdate() {};
}
