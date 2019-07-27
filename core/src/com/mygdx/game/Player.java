package com.mygdx.game;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

public class Player implements GameActor {
    private float x;
    private float y;
    private float currentSpeed;
    private ShapeRenderer shapeRenderer;
    private boolean isMoving = false;
    private float angle;

    Player(float startX, float startY) {
        x = startX;
        y = startY;
        currentSpeed = 15;
        shapeRenderer = new ShapeRenderer();
    }

    @Override
    public void draw() {
        drawDebug();
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
}
