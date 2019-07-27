package com.mygdx.game.Input;

import com.badlogic.gdx.math.Vector2;

public interface InputComponent {
    boolean hasInput();
    Vector2 getDelta();
}
