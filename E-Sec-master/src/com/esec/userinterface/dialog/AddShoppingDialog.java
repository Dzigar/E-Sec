package com.esec.userinterface.dialog;

import java.sql.SQLException;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.EditText;
import android.widget.Toast;

import com.esec.activity.MainActivity;
import com.esec.activity.R;
import com.esec.activity.fragment.ShoppingFragment;
import com.esec.connection.HelperFactory;
import com.esec.model.Shopping;

public class AddShoppingDialog implements DialogInterface.OnClickListener {

	private Activity activity;

	private AlertDialog dialogShop;

	private EditText titleStuff;

	public AddShoppingDialog(Activity activity) {
		this.activity = activity;
		LayoutInflater inflater = activity.getLayoutInflater();

		AlertDialog.Builder builder = new AlertDialog.Builder(activity);
		builder.setTitle(activity.getString(R.string.add_purchase))
				.setView(inflater.inflate(R.layout.add_shopping_dialog, null))
				.setPositiveButton(activity.getString(android.R.string.ok),
						this)
				.setNegativeButton(activity.getString(android.R.string.cancel),
						this).setCancelable(false);

		dialogShop = builder.create();
		dialogShop.show();

		titleStuff = (EditText) dialogShop.findViewById(R.id.titleStuff);

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
		case DialogInterface.BUTTON_NEGATIVE:
			dialog.cancel();
			break;
		case DialogInterface.BUTTON_POSITIVE:

			if (titleStuff.getText().toString().equals("")) {
				Toast toast = Toast.makeText(activity,
						activity.getString(R.string.field_is_empty),
						Toast.LENGTH_SHORT);
				toast.show();
				break;
			}
			try {
				HelperFactory
						.getHelper()
						.getShoppingDAO()
						.create(new Shopping(titleStuff.getText().toString(), 0));
			} catch (SQLException e) {
				Log.i(getClass().getName(), e.getLocalizedMessage());
			}
			MainActivity.getActivity().updateFragment(new ShoppingFragment());
			dialog.cancel();
			break;
		default:
			break;
		}
	}
}
