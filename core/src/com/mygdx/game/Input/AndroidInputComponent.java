package com.mygdx.game.Input;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector2;

public class AndroidInputComponent implements InputComponent, InputProcessor {
    private Vector2 origin;
    private Vector2 delta;
    private boolean hasInput = false;

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        hasInput = true;
        delta = new Vector2(0, 0);
        origin = new Vector2(screenX, screenY);
        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        hasInput = false;
        return true;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        Vector2 newTouch = new Vector2(screenX, screenY);
        delta = newTouch.cpy().sub(origin);
        return true;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }

    @Override
    public Vector2 getDelta() {
        return delta;
    }

    public Vector2 getOrigin() {
        return origin;
    }

    @Override
    public boolean hasInput() {
        return hasInput;
    }
}
