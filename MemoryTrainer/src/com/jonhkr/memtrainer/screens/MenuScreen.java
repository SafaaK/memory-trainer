package com.jonhkr.memtrainer.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.jonhkr.memtrainer.GameMain;
import com.jonhkr.memtrainer.inputcontrollers.MenuController;

public class MenuScreen implements Screen {

	public GameMain game;
	public Stage stage;
	public TextButton playBtn;
	public TextButton statsBtn;
	public TextButton optionsBtn;
	private Table btnTable;

	public MenuScreen(GameMain game){
		this.game = game;
	}
	
	@Override
	public void render(float delta) {
		GL10 gl = Gdx.graphics.getGL10();

		gl.glClearColor(0.11f, 0.49f, 0.404f, 1);
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

		stage.act(delta);
		stage.draw();
	}

	@Override
	public void resize(int width, int height) {
		stage.setViewport(width, height, true);
		btnTable.setPosition(Gdx.graphics.getWidth() / 2 - btnTable.getWidth()
				/ 2, Gdx.graphics.getHeight() / 2 - btnTable.getHeight() / 2);
	}

	@Override
	public void show() {
		stage = new Stage();
		Gdx.input.setInputProcessor(stage);

		TextButtonStyle menuBtnStyle = new TextButtonStyle();
		menuBtnStyle.font = GameMain.arial40;
		menuBtnStyle.fontColor = new Color(0.796f, 0.871f, 0.855f, 1);
		menuBtnStyle.overFontColor = new Color(0.98f, 0.855f, 0.639f, 1);
		menuBtnStyle.downFontColor = new Color(0.996f, 0.69f, 0.157f, 1);

		playBtn = new TextButton("Play", menuBtnStyle);
		playBtn.setName("PLAY");
		statsBtn = new TextButton("Statistics", menuBtnStyle);
		statsBtn.setName("STATS");
		optionsBtn = new TextButton("Options", menuBtnStyle);
		optionsBtn.setName("OPTIONS");

		btnTable = new Table();

		btnTable.row().expand(true, true).pad(10, 0, 10, 0);
		btnTable.add(playBtn);
		btnTable.row().expand(true, true).pad(10, 0, 10, 0);
		btnTable.add(statsBtn);
		btnTable.row().expand(true, true).pad(10, 0, 10, 0);
		btnTable.add(optionsBtn);

		btnTable.setWidth(stage.getWidth());
		btnTable.setHeight(300);

		MenuController controller = new MenuController(this);
		playBtn.addListener(controller);
		statsBtn.addListener(controller);
		optionsBtn.addListener(controller);
		stage.addActor(btnTable);
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
