package com.sinhalatext.editor.mob;

import android.content.Context;
import android.text.InputType;
import android.util.AttributeSet;
import android.view.inputmethod.BaseInputConnection;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.widget.EditText;

/**
 * Created by isuru on 5/24/15.
 */

public class SinEditText extends EditText {

    private Context mContext;

    private static final String TAG = "SinEditText";

    public SinEditText(Context context) {
        super(context);
        mContext = context;
    }

    public SinEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
    }

    public SinEditText(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        mContext = context;
    }

    /*
    @Override
    public boolean onKeyPreIme(int keyCode, KeyEvent event) {

        Log.i(TAG, "onKeyPreImeeeeeeeeeee");
        if (keyCode == KeyEvent.KEYCODE_BACK) {

            //hide keyboard
            InputMethodManager mgr = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
            mgr.hideSoftInputFromWindow(this.getWindowToken(), 0);

            //lose focus
            this.clearFocus();

            return true;
        }
        return false;
    }
    */

/*
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        Log.i(TAG, "onKeyDownnnnnnnnnnnn");
        return true;
    }
*/

    public InputConnection onCreateInputConnection(EditorInfo outAttrs) {
        //Log.d(TAG, "onCreateInputConnectionnnnn");
        BaseInputConnection fic = new BaseInputConnection(this, false);
        outAttrs.actionLabel = null;
        outAttrs.inputType = InputType.TYPE_NULL;
        return fic;
    }

}