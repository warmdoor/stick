package com.mygdx.game;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Disposable;

public interface GameActor extends Disposable {
    void draw();
    void startMovement(Vector2 delta);
    void stopMovement();
}
