package com.jonhkr.memtrainer.screens;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Button.ButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Timer;
import com.jonhkr.memtrainer.inputcontrollers.GameController;
import com.jonhkr.memtrainer.models.GameModel;

public class GameScreen implements Screen {

	Stage stage;
	Table tilesTable;
	Skin skin;
	ButtonStyle btnStyle;

	public Integer currLevel = 2;
	public Integer numRows = 2;
	public Integer numColumns = 1;
	public ArrayList<Button> buttons;
	GameController controller;

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
		
		tilesTable.setPosition(
				stage.getWidth() / 2 - tilesTable.getWidth() / 2,
				stage.getHeight() / 2 - tilesTable.getHeight() / 2);
	}

	@Override
	public void show() {
		stage = new Stage();
		tilesTable = new Table();
		skin = new Skin();
		buttons = new ArrayList<Button>();

		Gdx.input.setInputProcessor(stage);

		TextureAtlas atlas = new TextureAtlas(
				Gdx.files.internal("data/atlases/tiles.atlas"));

		NinePatch tileDown = atlas.createPatch("tileEnabled");
		tileDown.setColor(new Color(1f, 1f, 1f, 0.7f));
		
		skin.addRegions(atlas);
		skin.add("tileDown", tileDown);

		btnStyle = new ButtonStyle();
		btnStyle.up = skin.getDrawable("tile");
		btnStyle.down = skin.getDrawable("tileDown");
		btnStyle.checked = skin.getDrawable("tileEnabled");
		
		controller = new GameController(this);


		stage.addActor(tilesTable);
		
		startLevel(currLevel);
	}
	
	public void startLevel(int level){
		if (numRows == numColumns) {
			if(currLevel > level) {
				numColumns--;
			}else{
				numRows++;
			}
		} else if(numRows > numColumns){
			if(currLevel > level) {
				numRows--;
			}else{
				numColumns++;
			}
		}
		
		currLevel = level;
		
		int buttonsAmout = buttons.size();
		
		System.out.println(buttonsAmout + " " + (numRows*numColumns));
		if (buttonsAmout > numRows*numColumns) {
			for(int i = buttonsAmout-1; i > (numRows*numColumns); i--) {
				buttons.get(i).remove();
				buttons.remove(i);
			}
			buttonsAmout = buttons.size();
		}
		
		for (int i = 0; i < numRows*numColumns; i++) {
			if(buttonsAmout > i){
				buttons.get(i).setChecked(false);
			}else{
				Button btn = new Button(btnStyle);
				btn.setSize(40, 40);
				btn.addListener(controller);
				buttons.add(btn);
			}
			buttons.get(i).setDisabled(true);
		}
		
		buttons = GameModel.getRandomizedTiles(buttons, level);
		int index = 0;		
		tilesTable.clear();
		
		for (int i = 0; i < numRows; i++) {
			tilesTable.row().fill(true, true).expand(true, true).pad(1);
			for (int j = 0; j < numColumns; j++) {
				tilesTable.add(buttons.get(index));
				index++;
			}
		}
		
		tilesTable.setSize((buttons.get(0).getWidth() + 2) * numColumns,
				(buttons.get(0).getHeight() + 2) * numRows);
		
		tilesTable.setPosition(
				stage.getWidth() / 2 - tilesTable.getWidth() / 2,
				stage.getHeight() / 2 - tilesTable.getHeight() / 2);
		
		Timer t = new Timer();
		t.scheduleTask(new Timer.Task() {
			
			@Override
			public void run() {
				System.out.println("as");
				for(Button button : buttons) {
					button.setChecked(false);
					button.setDisabled(false);
				}
			}
		}, 1);
		
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

	}

}
