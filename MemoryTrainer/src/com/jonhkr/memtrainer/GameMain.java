package com.jonhkr.memtrainer;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.jonhkr.memtrainer.screens.SplashScreen;

public class GameMain extends Game{

	public static BitmapFont arial40;
	public static BitmapFont arial16;
	
	Stage stage;
	
	@Override
	public void create() {
		
		Texture arial40Texture = new Texture(Gdx.files.internal("data/fonts/arial40cbdeda.png"), true);
		arial40Texture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		Texture arial16Texture = new Texture(Gdx.files.internal("data/fonts/arial16000000.png"), true);
		arial40Texture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		
		arial40 = new BitmapFont(Gdx.files.internal("data/fonts/arial40cbdeda.fnt"), new TextureRegion(arial40Texture),false);
		
		arial16 = new BitmapFont(Gdx.files.internal("data/fonts/arial16000000.fnt"), new TextureRegion(arial16Texture),false);
		
		setScreen(new SplashScreen(this));
	}
	
}
