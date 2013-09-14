package com.esec.userinterface.dialog;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.EditText;
import android.widget.Toast;

import com.esec.activity.MainActivity;
import com.esec.activity.R;
import com.esec.activity.fragment.ListShoppingFragment;
import com.esec.controller.ControllerListShopping;

public class AddShoppingDialog implements DialogInterface.OnClickListener {

	private Activity activity;

	private AlertDialog dialogShop;

	private EditText titleStuff;
	private EditText numStuff;

	public AddShoppingDialog(Activity activity) {
		this.activity = activity;
		LayoutInflater inflater = activity.getLayoutInflater();

		AlertDialog.Builder builder = new AlertDialog.Builder(activity);
		// Change places of buttons POSITVE and NEGATIVE
		builder.setTitle(activity.getString(R.string.add_purchase))
				.setView(inflater.inflate(R.layout.add_shopping, null))
				.setPositiveButton(activity.getString(android.R.string.ok),
						this)
				.setNegativeButton(activity.getString(android.R.string.cancel),
						this).setCancelable(false);

		dialogShop = builder.create();
		dialogShop.show();

		titleStuff = (EditText) dialogShop.findViewById(R.id.titleStuff);
		numStuff = (EditText) dialogShop.findViewById(R.id.numStuff);

	}

	/**
	 * Show dialog to add Shopping
	 */
	public void show() {
		dialogShop.show();
	}

	@Override
	public void onClick(DialogInterface dialog, int which) {
		switch (which) {
		case -2:// dialog.BUTTON_NEGATIVE
			break;
		case -1:// dialog.BUTTON_POSITIVE

			try {
				if (titleStuff.getText().toString().equals("")
						|| numStuff.getText().toString().equals("")) {
					Toast toast = Toast.makeText(activity,
							activity.getString(R.string.field_is_empty),
							Toast.LENGTH_SHORT);
					toast.show();
					break;
				}

				ControllerListShopping cls = ControllerListShopping
						.getShoppingController(activity);
				cls.addShopping(titleStuff.getText().toString(),
						Integer.parseInt(numStuff.getText().toString()));

			} catch (Exception e) {
				Log.i(getClass().getName(), e.getLocalizedMessage());
			}

			dialog.cancel();
			MainActivity.getActivity().updateFragment(
					new ListShoppingFragment());
			break;
		default:
			break;
		}

	}

}
