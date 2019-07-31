package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.Input.InputComponent;

public class GameScreen extends ScreenAdapter {
    private static final float WIDTH = Gdx.graphics.getWidth();
    private static final float HEIGHT = Gdx.graphics.getHeight();

    private Game game;
    private SpriteBatch batch;
    private BitmapFont font;
    private Player player;
    private InputComponent inputHandler;
    private Array<Shot> shots;

    GameScreen(Game game, InputComponent inputComponent) {
        this.game = game;
        this.inputHandler = inputComponent;
        shots = new Array<Shot>();
    }

    @Override
    public void show() {
        player = new Player(WIDTH / 2, HEIGHT / 2, inputHandler);
        font = new BitmapFont(Gdx.files.internal("noto_serif.fnt"));
        font.setColor(Color.WHITE);
        batch = new SpriteBatch();
    }

    @Override
    public void render(float delta) {
        update();
        drawDebug();
    }

    private void update() {
        updatePlayer();
        for (Shot shot : shots) {
            shot.update();
        }
    }

    private void updatePlayer() {
        if (inputHandler.hasMovementInput()) {
            player.startMovement(inputHandler.getMoveDelta());
        } else {
            player.stopMovement();
        }
        inputHandler.update();
        if (inputHandler.hasShot()) {
            shots.add(new Shot(player.getX(), player.getY(), inputHandler.getShootDelta()));
            inputHandler.setHasShot(false);
        }
    }

    private void drawDebug() {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        player.updatePosition();
        player.clampPosition(WIDTH, HEIGHT);
        player.draw();
        for (Shot shot : shots) {
            shot.draw();
        }
    }

    @Override
    public void dispose() {
    }
}
