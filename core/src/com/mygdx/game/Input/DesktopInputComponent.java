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

import java.util.Arrays;
import java.util.List;

public class DesktopInputComponent implements InputComponent {
    private Vector2 origin;
    private Vector2 delta;
    private boolean hasInput = false;
    private boolean usesController = false;
    private Controller controller;
    private List<Integer> activeKeys;

    private static final float DEAD_ZONE = 0.05f;

    public DesktopInputComponent() {
        Array<Controller> controllers = Controllers.getControllers();
        if (!controllers.isEmpty()) {
            controller = controllers.get(0);
            controller.addListener(new ControllerHandler());
            usesController = true;
        }
        activeKeys = Arrays.asList(Input.Keys.W, Input.Keys.A, Input.Keys.S, Input.Keys.D);
        origin = new Vector2(0, 0);
    }

    @Override
    public boolean hasInput() {
        if (hasInput) {
            if (usesController) {
                checkControllerState();
            }
            checkKeyboardState();
        }
        return hasInput;
    }

    private void checkControllerState() {
        float x = controller.getAxis(0);
        float y = controller.getAxis(1);
        if (Math.abs(x) < DEAD_ZONE && Math.abs(x) > 0) x = 0;
        if (Math.abs(y) < DEAD_ZONE && Math.abs(y) > 0) y = 0;
        checkDelta(x, y);
    }

    private void checkKeyboardState() {
        float x = 0;
        float y = 0;
        if (Gdx.input.isKeyPressed(Input.Keys.A)) x = -1;
        if (Gdx.input.isKeyPressed(Input.Keys.D)) x = 1;
        if (Gdx.input.isKeyPressed(Input.Keys.W)) y = -1;
        if (Gdx.input.isKeyPressed(Input.Keys.S)) y = 1;
        checkDelta(x, y);
    }

    private void checkDelta(float x, float y) {
        delta = new Vector2(x, y);
        if (delta.equals(origin)) {
            hasInput = false;
        }
    }

    @Override
    public Vector2 getDelta() {
        return delta;
    }

    @Override
    public boolean keyDown(int keycode) {
        if (activeKeys.contains(keycode)) hasInput = true;
        return true;
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
