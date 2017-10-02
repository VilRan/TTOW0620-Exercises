package xyz.placeholder.shopping_list;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

public class ProductFragment extends DialogFragment {
	private OnFragmentInteractionListener mListener;

	public ProductFragment() {
		// Required empty public constructor
	}

	public Dialog onCreateDialog(Bundle savedInstanceState) {
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

		LayoutInflater inflater = getActivity().getLayoutInflater();

		final View dialogView = inflater.inflate(R.layout.fragment_product, null);
		builder.setView(dialogView)
			.setTitle("Add a new product")
			.setPositiveButton("OK", new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int id) {
					String product = ((EditText)dialogView.findViewById(R.id.product)).getText().toString();
					int count = Integer.parseInt(
						((EditText)dialogView.findViewById(R.id.count)).getText().toString()
					);
					double price = Double.parseDouble(
						((EditText)dialogView.findViewById(R.id.price)).getText().toString()
					);

					mListener.onDialogPositiveClick(ProductFragment.this, product, count, price);
				}
			})
			.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int id) {
					mListener.onDialogNegativeClick(ProductFragment.this);
				}
			});
		return builder.create();
	}

	@Override
	public void onAttach(Context context) {
		super.onAttach(context);
		if (context instanceof OnFragmentInteractionListener) {
			mListener = (OnFragmentInteractionListener) context;
		} else {
			throw new RuntimeException(context.toString()
				+ " must implement OnFragmentInteractionListener");
		}
	}

	@Override
	public void onDetach() {
		super.onDetach();
		mListener = null;
	}

	public interface OnFragmentInteractionListener {
		void onDialogPositiveClick(DialogFragment dialog, String product, int count, double price);
		void onDialogNegativeClick(DialogFragment dialog);
	}
}
