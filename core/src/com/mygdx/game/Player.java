package com.mygdx.game;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Input.InputComponent;

public class Player implements GameActor {
    private float x;
    private float y;
    private float currentSpeed;
    private float angle;
    private float controllerXValue;
    private float controllerYValue;
    private ShapeRenderer shapeRenderer;
    private InputComponent inputHandler;
    private boolean isMoving = false;

    Player(float startX, float startY, InputComponent inputHandler) {
        x = startX;
        y = startY;
        currentSpeed = 15;
        this.inputHandler = inputHandler;
        shapeRenderer = new ShapeRenderer();
    }

    @Override
    public void draw() {
        drawDebug();
    }

    @Override
    public void dispose() {
        shapeRenderer.dispose();
    }

    private void drawDebug() {
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.circle(x, y, 20);
        shapeRenderer.end();
    }

    public void updatePosition() {
        if (isMoving) {
            x += MathUtils.cos(angle) * currentSpeed;
            y -= MathUtils.sin(angle) * currentSpeed;
        }
    }

    public void stopMovement() {
        isMoving = false;
    }

    public void startMovement(Vector2 delta) {
        angle = delta.angleRad();
        controllerXValue = delta.x;
        controllerYValue = delta.y;
        isMoving = true;
    }

    public void clampPosition(float width, float height) {
        if (x <= 0) {
            x = 0;
        }
        if (x >= width) {
            x = width;
        }
        if (y <= 0) {
            y = 0;
        }
        if (y >= height) {
            y = height;
        }
    }

    public float getAngle() {
        return angle;
    }

    public float getControllerXValue() {
        return controllerXValue;
    }

    public float getControllerYValue() {
        return controllerYValue;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }
}
