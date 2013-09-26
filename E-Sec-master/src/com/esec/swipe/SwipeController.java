package com.esec.swipe;

import java.sql.SQLException;

import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;

import com.esec.activity.MainActivity;
import com.esec.listeners.DeleteAnimationListener;

public class SwipeController {

	/**
	 * start animation of removal
	 * 
	 * @throws SQLException
	 */
	public Animation getDeleteAnimation(float fromX, float toX, int position)
			throws SQLException {
		Animation animation = new TranslateAnimation(fromX, toX, 0, 0);
		animation.setStartOffset(50);
		animation.setDuration(1000);
		animation.setAnimationListener(new DeleteAnimationListener(position));
		animation.setInterpolator(AnimationUtils.loadInterpolator(
				MainActivity.getActivity(),
				android.R.anim.anticipate_overshoot_interpolator));
		return animation;
	}
}
