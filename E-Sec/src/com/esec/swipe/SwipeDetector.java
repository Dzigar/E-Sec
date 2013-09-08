package com.esec.swipe;

import java.sql.SQLException;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Message;
import android.view.MotionEvent;
import android.view.View;

public class SwipeDetector implements View.OnTouchListener {

	private static SwipeDetector swipeDetector;
	private SwipeController swipeController;

	public static final int MSG_UPDATE_ADAPTER = 0;
	public static final int MSG_CHANGE_ITEM = 1;
	public static final int MSG_ANIMATION_REMOVE = 2;

	public static enum Action {
		LR, RL, TB, BT, None
	}

	private static final int HORIZONTAL_MIN_DISTANCE = 100;
	private static final int VERTICAL_MIN_DISTANCE = 80;
	private float downX, downY, upX, upY;
	private Action mSwipeDetected = Action.None;

	private SwipeDetector() {
		swipeController = new SwipeController();

	}

	public static SwipeDetector getSwipeDetector() {
		if (swipeDetector == null) {
			swipeDetector = new SwipeDetector();
		}
		return swipeDetector;
	}

	/**
	 * 
	 * @return true if swipe is detected
	 */
	public boolean swipeDetected() {
		return mSwipeDetected != Action.None;
	}

	@SuppressLint("HandlerLeak")
	private Handler handler = new Handler() {
		public void handleMessage(Message msg) {

			View view = (View) msg.obj;
			try {
				view.startAnimation(swipeController.getDeleteAnimation(
						0,
						(msg.arg2 == 0) ? -view.getWidth() : 2 * view
								.getWidth(), msg.arg1));
			} catch (SQLException e) {
				e.getLocalizedMessage();
			}

		}
	};

	public Action getAction() {
		return mSwipeDetected;
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN: {
			downX = event.getX();
			downY = event.getY();
			mSwipeDetected = Action.None;
			return false;
		}
		case MotionEvent.ACTION_MOVE: {
			upX = event.getX();
			upY = event.getY();

			float deltaX = downX - upX;
			float deltaY = downY - upY;

			if (Math.abs(deltaX) > HORIZONTAL_MIN_DISTANCE) {
				if (deltaX < 0) {
					mSwipeDetected = Action.LR;
					return true;
				}
				if (deltaX > 0) {
					mSwipeDetected = Action.RL;
					return true;
				}
			} else

			if (Math.abs(deltaY) > VERTICAL_MIN_DISTANCE) {
				if (deltaY < 0) {
					mSwipeDetected = Action.TB;
					return false;
				}
				if (deltaY > 0) {
					mSwipeDetected = Action.BT;
					return false;
				}
			}
			return true;
		}
		}
		return false;
	}

	public void sendMessage(View view, int i) {
		Message msg = new Message();
		msg.what = MSG_ANIMATION_REMOVE;
		msg.arg2 = swipeDetector.getAction() == SwipeDetector.Action.LR ? 1 : 0;
		msg.obj = view;
		msg.arg1 = i;
		handler.sendMessage(msg);
	}
}
