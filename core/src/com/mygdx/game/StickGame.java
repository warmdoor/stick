package com.mygdx.game;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.mygdx.game.Input.AndroidInputComponent;
import com.mygdx.game.Input.DesktopInputComponent;
import com.mygdx.game.Input.InputComponent;

public class StickGame extends Game {
    private GameScreen screen;

	@Override
	public void create () {
	    InputComponent inputComponent = determineInputComponent();
	    screen = new GameScreen(this, inputComponent);
		setScreen(screen);
	}

	@Override
	public void dispose () {
	}

	private InputComponent determineInputComponent() {
	    InputComponent component = new DesktopInputComponent();
		if (Gdx.app.getType() == Application.ApplicationType.Android) {
			component = new AndroidInputComponent();
		}
		Gdx.input.setInputProcessor(component);
		return component;
	}
}
