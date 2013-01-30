package com.jonhkr.memtrainer.inputcontrollers;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.jonhkr.memtrainer.models.GameModel;
import com.jonhkr.memtrainer.screens.GameScreen;

public class GameController extends InputListener {
	private GameScreen screen;

	private Boolean isCursorInActor;
	private Integer checkedNum = 0;

	public GameController(GameScreen screen) {
		this.screen = screen;
	}

	public boolean touchDown(InputEvent event, float x, float y, int pointer,
			int button) {
		return true;
	}

	public void touchUp(InputEvent event, float x, float y, int pointer,
			int button) {
		Actor actor = event.getListenerActor();
		
		if (!isCursorInActor || ((Button) actor).isDisabled()) {
			return;
		}

		if(((Button) actor).isChecked()) {
			checkedNum++;
		} else {
			checkedNum--;
		}
		
		if (checkedNum == screen.currLevel) {
			if(GameModel.isTrialCorrect(screen.buttons)){
				screen.startLevel(screen.currLevel+1);
			}else{
				screen.startLevel(screen.currLevel-1);
				System.out.println("nop");
			}
			
			checkedNum=0;
			
		}
	}

	public void enter(InputEvent event, float x, float y, int pointer,
			Actor toActor) {
		isCursorInActor = true;
	}

	public void exit(InputEvent event, float x, float y, int pointer,
			Actor toActor) {
		isCursorInActor = false;
	}
}
