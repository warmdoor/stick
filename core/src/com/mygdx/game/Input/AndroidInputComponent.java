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
    private int movePointer = -1;
    private int shootPointer = -1;
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
            movePointer = pointer;
        } else if (screenX > WIDTH / 2 && screenY > HEIGHT / 2) {
            isShooting = true;
            shootDelta = new Vector2(0, 0);
            shootOrigin = new Vector2(screenX, screenY);
            shootPointer = pointer;
        }
        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        if (pointer == movePointer) {
            hasMovementInput = false;
            movePointer = -1;
        } else if (pointer == shootPointer) {
            isShooting = false;
            shootPointer = -1;
        }
        return true;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        if (pointer == movePointer) {
            Vector2 newTouch = new Vector2(screenX, screenY);
            moveDelta = newTouch.cpy().sub(moveOrigin);
        } else if (pointer == shootPointer) {
            Vector2 newTouch = new Vector2(screenX, screenY);
            shootDelta = newTouch.cpy().sub(shootOrigin);
        }
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
        return shootDelta;
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
