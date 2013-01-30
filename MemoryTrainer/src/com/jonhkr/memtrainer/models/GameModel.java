package com.jonhkr.memtrainer.models;

import java.util.ArrayList;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.ui.Button;

public class GameModel {
	
	private static ArrayList<Integer> indexCaches = new ArrayList<Integer>();
	
	public static ArrayList<Button> getRandomizedTiles(ArrayList<Button> buttons, Integer currLevel){
		indexCaches.clear();
		for (int i = 0; i < currLevel; i++){
			Integer random = MathUtils.random(0, buttons.size()-1);
			Button button = buttons.get(random);
			
			if(!button.isChecked()){
				button.setChecked(true);
				indexCaches.add(random);
			}else{
				i--;
			}
		}
		return buttons;		
	}
	
	public static Boolean isTrialCorrect(ArrayList<Button> buttons){
		for(Integer i : indexCaches){
			if(!buttons.get(i).isChecked()){
				return false;
			}
		}
		return true;
	}
}
