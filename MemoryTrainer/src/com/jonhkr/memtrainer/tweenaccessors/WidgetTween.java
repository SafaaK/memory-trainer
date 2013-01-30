package com.jonhkr.memtrainer.tweenaccessors;

import com.badlogic.gdx.scenes.scene2d.ui.Widget;

import aurelienribon.tweenengine.TweenAccessor;

public class WidgetTween implements TweenAccessor<Widget> {

	public static final int ALPHA = 1;

	@Override
	public int getValues(Widget target, int tweenType, float[] returnValues) {
		switch (tweenType) {
		case (WidgetTween.ALPHA):
			returnValues[0] = target.getColor().a;
			return 1;
		}
		return 0;
	}

	@Override
	public void setValues(Widget target, int tweenType, float[] newValues) {
		switch (tweenType) {
		case (WidgetTween.ALPHA):
			target.setColor(1, 1, 1, newValues[0]);
			break;
		}
	}

}
