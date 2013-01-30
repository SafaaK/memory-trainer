package com.jonhkr.memtrainer.screens;

import aurelienribon.tweenengine.BaseTween;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenCallback;
import aurelienribon.tweenengine.TweenManager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.jonhkr.memtrainer.GameMain;
import com.jonhkr.memtrainer.tweenaccessors.WidgetTween;

public class SplashScreen implements Screen {

	GameMain game;
	private Stage stage;
	private Label splash;
	private TweenManager tweenManager;

	public SplashScreen(GameMain game) {
		this.game = game;
	}

	@Override
	public void render(float delta) {
		GL10 gl = Gdx.graphics.getGL10();

		gl.glClearColor(0.11f, 0.49f, 0.404f, 1);
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

		tweenManager.update(delta);
		stage.act(delta);
		stage.draw();

		if (Gdx.input.isTouched()) {
			game.setScreen(new MenuScreen(game));
		}
	}

	@Override
	public void resize(int width, int height) {
		stage.setViewport(width, height, true);
		splash.setPosition(stage.getWidth() / 2 - splash.getWidth() / 2,
				stage.getHeight() / 2 - splash.getHeight() / 2);
	}

	@Override
	public void show() {
		stage = new Stage();
		tweenManager = new TweenManager();

		LabelStyle ls = new LabelStyle(GameMain.arial40, new Color(0.796f,
				0.871f, 0.855f, 1));
		splash = new Label("Memory Trainer", ls);
		splash.setColor(1, 1, 1, 0);
		stage.addActor(splash);

		Tween.registerAccessor(Label.class, new WidgetTween());

		TweenCallback tweenCallback = new TweenCallback() {

			@Override
			public void onEvent(int type, BaseTween<?> source) {
				game.setScreen(new MenuScreen(game));
			}
		};

		Tween.to(splash, WidgetTween.ALPHA, 2).target(1).repeatYoyo(1, 2)
				.setCallback(tweenCallback)
				.setCallbackTriggers(TweenCallback.COMPLETE)
				.start(tweenManager);
	}

	@Override
	public void hide() {
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}

	@Override
	public void dispose() {
		stage.dispose();
	}

}
