package com.mygdx.game.Input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.controllers.Controller;
import com.badlogic.gdx.controllers.ControllerListener;
import com.badlogic.gdx.controllers.Controllers;
import com.badlogic.gdx.controllers.PovDirection;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;

public class DesktopInputComponent implements InputComponent {
    private Vector2 origin;
    private Vector2 moveDelta;
    private Vector2 shootDelta;
    private boolean hasInput = false;
    private boolean hasShot = false;
    private boolean usesController = false;
    private float moveX;
    private float moveY;
    private float shootX;
    private float shootY;
    private Controller controller;

    private static final float DEAD_ZONE = 0.05f;

    public DesktopInputComponent() {
        Array<Controller> controllers = Controllers.getControllers();
        if (!controllers.isEmpty()) {
            controller = controllers.get(0);
            controller.addListener(new ControllerHandler());
            usesController = true;
        }
        origin = new Vector2(0, 0);
        moveDelta = new Vector2(0, 0);
        shootDelta = new Vector2(0, 0);
        moveX = 0;
        moveY = 0;
        shootX = 0;
        shootY = 0;
    }

    @Override
    public boolean hasMovementInput() {
        if (usesController) {
            checkControllerState();
        }
        if (moveDelta.equals(origin)) {
            hasInput = false;
        }
        return hasInput;
    }

    @Override
    public void update() {
        if (usesController) {
            checkControllerState();
        }
    }

    @Override
    public boolean hasShot() {
        return hasShot;
    }

    @Override
    public void setHasShot(boolean hasShot) {
        this.hasShot = hasShot;
    }

    private void checkControllerState() {
        float x = controller.getAxis(0);
        float y = controller.getAxis(1);
        if (Math.abs(x) < DEAD_ZONE && Math.abs(x) > 0) x = 0;
        if (Math.abs(y) < DEAD_ZONE && Math.abs(y) > 0) y = 0;
        moveDelta = new Vector2(x, y);
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
    public boolean keyDown(int keycode) {
        if (keycode == Input.Keys.A) {
            moveX = -1;
            hasInput = true;
        }
        if (keycode == Input.Keys.D) {
            moveX = 1;
            hasInput = true;
        }
        if (keycode == Input.Keys.W) {
            moveY = -1;
            hasInput = true;
        }
        if (keycode == Input.Keys.S) {
            moveY = 1;
            hasInput = true;
        }
        if (keycode == Input.Keys.NUMPAD_7) {
            shootX = -1;
            shootY = -1;
            hasShot = true;
        }
        if (keycode == Input.Keys.NUMPAD_8) {
            shootX = 0;
            shootY = -1;
            hasShot = true;
        }
        if (keycode == Input.Keys.NUMPAD_9) {
            shootX = 1;
            shootY = -1;
            hasShot = true;
        }
        if (keycode == Input.Keys.NUMPAD_6) {
            shootX = 1;
            shootY = 0;
            hasShot = true;
        }
        if (keycode == Input.Keys.NUMPAD_3) {
            shootX = 1;
            shootY = 1;
            hasShot = true;
        }
        if (keycode == Input.Keys.NUMPAD_2) {
            shootX = 0;
            shootY = 1;
            hasShot = true;
        }
        if (keycode == Input.Keys.NUMPAD_1) {
            shootX = -1;
            shootY = 1;
            hasShot = true;
        }
        if (keycode == Input.Keys.NUMPAD_4) {
            shootX = -1;
            shootY = 0;
            hasShot = true;
        }
        moveDelta = new Vector2(moveX, moveY);
        shootDelta = new Vector2(shootX, shootY);
        return true;
    }

    @Override
    public boolean keyUp(int keycode) {
        if (keycode == Input.Keys.A || keycode == Input.Keys.D) {
            moveX = 0;
        }
        if (keycode == Input.Keys.W || keycode == Input.Keys.S) {
            moveY = 0;
        }
        if (keycode == Input.Keys.SPACE) hasShot = false;
        moveDelta = new Vector2(moveX, moveY);
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }

    private class ControllerHandler implements ControllerListener {

        @Override
        public void connected(Controller controller) {
            usesController = true;
        }

        @Override
        public void disconnected(Controller controller) {
            usesController = false;
        }

        @Override
        public boolean buttonDown(Controller controller, int buttonCode) {
            return false;
        }

        @Override
        public boolean buttonUp(Controller controller, int buttonCode) {
            return false;
        }

        @Override
        public boolean axisMoved(Controller controller, int axisCode, float value) {
            hasInput = true;
            return false;
        }

        @Override
        public boolean povMoved(Controller controller, int povCode, PovDirection value) {
            return false;
        }

        @Override
        public boolean xSliderMoved(Controller controller, int sliderCode, boolean value) {
            return false;
        }

        @Override
        public boolean ySliderMoved(Controller controller, int sliderCode, boolean value) {
            return false;
        }

        @Override
        public boolean accelerometerMoved(Controller controller, int accelerometerCode, Vector3 value) {
            return false;
        }
    }
}
