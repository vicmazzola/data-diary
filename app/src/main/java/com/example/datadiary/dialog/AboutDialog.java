package com.example.datadiary.dialog;

import android.app.Dialog;
import android.content.Context;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.datadiary.R;
import com.google.android.material.button.MaterialButton;

/**
 * Custom dialog that displays information about the app.
 * It uses a predefined layout and includes a close button to dismiss the dialog.
 */
public class AboutDialog extends Dialog {

    /**
     * Default constructor. Uses the standard theme and initializes the layout.
     * @param context the context from the activity that opens the dialog
     */
    public AboutDialog(@NonNull Context context) {
        super(context);
        setContentView(R.layout.dialog_about);

        MaterialButton closeButton = findViewById(R.id.dialog_close_button);
        closeButton.setOnClickListener(v -> dismiss());
    }

    /**
     * Constructor that allows using a custom theme.
     * @param context the context from the activity
     * @param themeResId the theme resource ID to style the dialog
     */
    public AboutDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
        setContentView(R.layout.dialog_about);
    }

    /**
     * Constructor that allows customizing cancel behavior.
     * @param context the context from the activity
     * @param cancelable whether the dialog is cancelable
     * @param cancelListener a callback to be invoked when the dialog is canceled
     */
    protected AboutDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        setContentView(R.layout.dialog_about);
    }
}