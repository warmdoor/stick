package com.mygdx.game.Input;

import com.badlogic.gdx.controllers.Controller;
import com.badlogic.gdx.controllers.ControllerListener;
import com.badlogic.gdx.controllers.Controllers;
import com.badlogic.gdx.controllers.PovDirection;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;

public class DesktopInputComponent implements InputComponent, ControllerListener {
    private Vector2 origin;
    private Vector2 delta;
    private boolean hasInput = false;
    private Array<Controller> controllers;

    DesktopInputComponent() {
        controllers = Controllers.getControllers();
    }

    @Override
    public boolean hasInput() {
        return hasInput;
    }

    @Override
    public Vector2 getDelta() {
        return delta;
    }

    @Override
    public void connected(Controller controller) {
        // stub
    }

    @Override
    public void disconnected(Controller controller) {
        // stub
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
