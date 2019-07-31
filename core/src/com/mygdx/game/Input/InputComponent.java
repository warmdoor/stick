package com.mygdx.game.Input;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector2;

public interface InputComponent extends InputProcessor {
    boolean hasMovementInput();
    boolean hasShot();
    void update();
    void setHasShot(boolean hasShot);
    Vector2 getMoveDelta();
    Vector2 getShootDelta();
}
