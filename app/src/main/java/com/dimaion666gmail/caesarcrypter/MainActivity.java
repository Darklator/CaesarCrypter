package com.dimaion666gmail.caesarcrypter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ToggleButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    // TODO: User paste string must not save its previous font

    private VigenereCipherService vigenereCipher;
    private boolean bound = false;

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            VigenereCipherService.VigenereCipherBinder vigenereCipherBinder =
                    (VigenereCipherService.VigenereCipherBinder) service;
            vigenereCipher = vigenereCipherBinder.getVigenereCipher();
            bound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            bound = false;
        }
    };

    private String userKey;
    private boolean isDecrypting;
    private String toBeTranslatedText;
    private String translatedText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = new Intent(this, VigenereCipherService.class);
        bindService(intent, connection, Context.BIND_AUTO_CREATE);

        if (savedInstanceState != null) {
            userKey = savedInstanceState.getString("userKey");
            isDecrypting = savedInstanceState.getBoolean("isDecrypting");
            toBeTranslatedText = savedInstanceState.getString("toBeTranslatedText");
            translatedText = savedInstanceState.getString("translatedText");


            EditText userKeyEditTextView = (EditText) findViewById(R.id.user_key);
            ToggleButton isDecryptingToggleButton = (ToggleButton) findViewById(R.id.is_decrypting_toggle_button);
            EditText toBeTranslatedEditTextView = (EditText) findViewById(R.id.text_to_be_translated);
            TextView translatedTextView = (TextView) findViewById(R.id.translated_text);


            userKeyEditTextView.setText(userKey);
            isDecryptingToggleButton.setChecked(isDecrypting);
            toBeTranslatedEditTextView.setText(toBeTranslatedText);
            translatedTextView.setText(translatedText);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putString("userKey", userKey);
        savedInstanceState.putBoolean("isDecrypting", isDecrypting);
        savedInstanceState.putString("toBeTranslatedText", toBeTranslatedText);
        savedInstanceState.putString("translatedText", translatedText);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (bound) {
            unbindService(connection);
            bound = false;
        }
    }

    public void onClickEncryptingOrDecrypting(View view) {
        ToggleButton isDecryptingToggleButton = (ToggleButton) view;
        isDecrypting = isDecryptingToggleButton.isChecked();
    }

    public void onClickTranslate(View view) {
        EditText userKeyEditTextView = (EditText) findViewById(R.id.user_key);
        EditText toBeTranslatedEditTextView = (EditText) findViewById(R.id.text_to_be_translated);
        TextView translatedTextView = (TextView) findViewById(R.id.translated_text);

        userKey = String.valueOf(userKeyEditTextView.getText());
        toBeTranslatedText = String.valueOf(toBeTranslatedEditTextView.getText());
        try {
            translatedText = vigenereCipher.getTranslation(isDecrypting, userKey, toBeTranslatedText);
            translatedTextView.setText(translatedText);
        }
        catch (InvalidKeyException ikex) {
            Toast exceptionMessage = Toast.makeText(getApplicationContext(), ikex.getMessage(), Toast.LENGTH_SHORT);
            exceptionMessage.show();
        }
    }
}