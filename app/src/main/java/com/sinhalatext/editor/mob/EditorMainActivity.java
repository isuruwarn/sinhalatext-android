package com.sinhalatext.editor.mob;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;


public class EditorMainActivity extends ActionBarActivity {

    private static final String TAG = "EditorMainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor_main);
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


    public void selectAll(View view) {
        EditText editTextMain = (EditText) findViewById( R.id.editTextMain );
        editTextMain.selectAll();
    }

    public void clearText(View view) {
        EditText editTextMain = (EditText) findViewById( R.id.editTextMain );
        editTextMain.setText("");
    }

    public void copyText(View view) {
        EditText editTextMain = (EditText) findViewById( R.id.editTextMain );
        editTextMain.selectAll();

        // get handle to the clipboard service
        ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);

        // place text on the clipboard
        ClipData clip = ClipData.newPlainText("sinhala text", editTextMain.getText() );
        clipboard.setPrimaryClip(clip);
    }

}
