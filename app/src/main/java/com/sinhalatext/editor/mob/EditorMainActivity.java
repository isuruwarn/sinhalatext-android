package com.sinhalatext.editor.mob;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;


public class EditorMainActivity extends ActionBarActivity { //implements TextView.OnEditorActionListener {

    private static final String TAG = "EditorMainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor_main);

        //Log.d(TAG, "onCreateeeeeeeeeeeeeeeeeeee");
        //final SinEditText editTextMain = (SinEditText) findViewById(R.id.editTextMain);

        /*
        editTextMain.setOnEditorActionListener( new TextView.OnEditorActionListener() {

            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                Log.i(TAG, "DEBUG MESSAGE KEYYYYYYYYYYYYYYY " + event.getUnicodeChar() );
                return false;
            }
        });
        */


        /*
        editTextMain.addTextChangedListener( new TextWatcher() {

            public void afterTextChanged(Editable s) {

            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                editTextMain.setText(s);
            }
        });
        */
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_editor_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {

        //Log.d(TAG, "dispatchKeyEvent KeyCode=" + event.getKeyCode() );
        int keyAction = event.getAction();

        if(keyAction == KeyEvent.ACTION_DOWN) {

            int keyUnicode = event.getUnicodeChar(event.getMetaState());
            char keyPressed = (char) keyUnicode;
            String inputKey = String.valueOf(keyPressed);

            if( inputKey.matches("[A-Za-z]") ||
                inputKey.equals( "`" ) ||
                inputKey.equals( "~" ) ||
                inputKey.equals( "@" ) ||
                inputKey.equals( "^" ) ||
                inputKey.equals( "_" ) ||
                inputKey.equals( "[" ) ||
                inputKey.equals( "{" ) ||
                inputKey.equals( "]" ) ||
                inputKey.equals( "}" ) ||
                inputKey.equals( "\\" ) ||
                inputKey.equals( "|" ) ||
                inputKey.equals( "<" ) ||
                inputKey.equals( ">" ) ) {

                String newSymbol = Symbol.getSymbol(keyUnicode);
                //Log.d(TAG, "Key pressed=" + keyPressed + " newSymbol=" + newSymbol);

                EditText editTextMain = (EditText) findViewById( R.id.editTextMain );
                int startPos = editTextMain.getSelectionStart();
                int endPos = editTextMain.getSelectionEnd();
                int lengthBefore = editTextMain.getText().length();
                String head = editTextMain.getText().subSequence(0, startPos).toString();
                String tail = editTextMain.getText().subSequence(endPos, lengthBefore).toString();
                String updatedText = head + newSymbol + tail;
                editTextMain.setText(updatedText);
                editTextMain.setSelection(startPos + newSymbol.length());
                return false;
            }
        }

        return super.dispatchKeyEvent(event);
    }

    /*
    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {

        Log.i(TAG, "DEBUG MESSAGE KEYYYYYYYYYYYYYYY " + event.getUnicodeChar() );
        return false;
    }
    */

}
