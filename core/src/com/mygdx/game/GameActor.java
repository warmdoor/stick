package com.mygdx.game;

import com.badlogic.gdx.math.Vector2;

public interface GameActor {
    void draw();
    void startMovement(Vector2 delta);
    void stopMovement();
}
