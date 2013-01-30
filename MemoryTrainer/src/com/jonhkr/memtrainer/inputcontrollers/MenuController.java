package com.jonhkr.memtrainer.inputcontrollers;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.jonhkr.memtrainer.screens.GameScreen;
import com.jonhkr.memtrainer.screens.MenuScreen;

public class MenuController extends InputListener {
	
	private MenuScreen screen;
	
	private Boolean isCursorInActor;
	
	public MenuController(MenuScreen screen){
		this.screen = screen;
	}
	
	public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
		return true;
	}
	
	public void touchUp(InputEvent event, float x, float y, int pointer,
			int button) {
		if (!isCursorInActor) {
			return;
		}
		
		Actor actor = event.getListenerActor();
		
		if(actor.equals(screen.playBtn)){
			screen.game.setScreen(new GameScreen());
		} else if(actor.equals(screen.statsBtn)){
			System.out.println("stats");
		} else if(actor.equals(screen.optionsBtn)){
			System.out.println("option");
		}
	}
	
	public void enter (InputEvent event, float x, float y, int pointer, Actor toActor) {
		isCursorInActor = true;
	}
	
	public void exit (InputEvent event, float x, float y, int pointer, Actor toActor) {
		isCursorInActor = false;
	}
}
