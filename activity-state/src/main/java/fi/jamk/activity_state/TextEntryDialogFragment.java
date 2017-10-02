package fi.jamk.activity_state;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

public class TextEntryDialogFragment extends DialogFragment {
    public interface TextEntryDialogListener {
        void onDialogPositiveClick(DialogFragment dialog, String text);
        void onDialogNegativeClick(DialogFragment dialog);
    }

    TextEntryDialogListener mListener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mListener = (TextEntryDialogListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + "must implement TextEntryDialogListener");
        }
    }

    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();

        final View dialogView = inflater.inflate(R.layout.textentry_dialog, null);
        builder.setView(dialogView)
            .setTitle("Give a new text")
            .setPositiveButton("Add", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int id) {
                    EditText editText = (EditText)dialogView.findViewById(R.id.editText);
                    String text = editText.getText().toString();
                    mListener.onDialogPositiveClick(TextEntryDialogFragment.this, text);
                }
            })
            .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int id) {
                    mListener.onDialogNegativeClick(TextEntryDialogFragment.this);
                }
            });
        return builder.create();
    }
}
