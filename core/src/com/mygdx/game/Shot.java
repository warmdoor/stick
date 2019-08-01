package com.mygdx.game;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

public class Shot implements GameActor {
    private float x;
    private float y;
    private float angle;
    private float currentSpeed;
    private ShapeRenderer shapeRenderer;

    Shot(float startX, float startY, Vector2 delta) {
        this.x = startX;
        this.y = startY;
        this.angle = delta.angleRad();
        currentSpeed = 20;
        shapeRenderer = new ShapeRenderer();
    }

    private void drawDebug() {
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.circle(x, y, 5);
        shapeRenderer.end();
    }

    public void update() {
        x += MathUtils.cos(angle) * currentSpeed;
        y -= MathUtils.sin(angle) * currentSpeed;
    }

    @Override
    public void draw() {
        drawDebug();
    }

    @Override
    public void startMovement(Vector2 delta) {
    }

    @Override
    public void stopMovement() {
        // stub
    }

    @Override
    public void dispose() {
        shapeRenderer.dispose();
    }
}
