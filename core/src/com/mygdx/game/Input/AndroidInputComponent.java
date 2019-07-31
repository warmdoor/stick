package com.mygdx.game.Input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector2;

public class AndroidInputComponent implements InputComponent, InputProcessor {
    private static final float WIDTH = Gdx.graphics.getWidth();
    private static final float HEIGHT = Gdx.graphics.getHeight();

    private Vector2 moveOrigin;
    private Vector2 shootOrigin;
    private Vector2 moveDelta;
    private Vector2 shootDelta;
    private boolean hasMovementInput = false;
    private boolean isShooting = false;

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
        if (screenX < WIDTH / 2 && screenY > HEIGHT / 2) {
            hasMovementInput = true;
            moveDelta = new Vector2(0, 0);
            moveOrigin = new Vector2(screenX, screenY);
        } else if (screenX > WIDTH / 2 && screenY > HEIGHT / 2) {
            isShooting = true;
            shootDelta = new Vector2(0, 0);
            shootOrigin = new Vector2(screenX, screenY);
        }
        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        hasMovementInput = false;
        return true;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        Vector2 newTouch = new Vector2(screenX, screenY);
        moveDelta = newTouch.cpy().sub(moveOrigin);
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
    public Vector2 getMoveDelta() {
        return moveDelta;
    }

    @Override
    public Vector2 getShootDelta() {
        return null;
    }

    @Override
    public boolean hasMovementInput() {
        return hasMovementInput;
    }

    @Override
    public void update() {
        // stub
    }

    @Override
    public boolean hasShot() {
        return isShooting;
    }

    @Override
    public void setHasShot(boolean hasShot) {
        isShooting = hasShot;
    }
}
