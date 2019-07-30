package com.mygdx.game.Input;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector2;

public interface InputComponent extends InputProcessor {
    boolean hasInput();
    boolean isShooting();
    Vector2 getDelta();
}
