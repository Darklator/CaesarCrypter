package com.dimaion666gmail.caesarcrypter;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClickEncryptOrDecrypt(View view) {

    }

    public void onClickTranslate(View view) {
        CaesarCrypter caesarCrypter = new CaesarCrypter();

        EditText userKeyEditTextView = (EditText)findViewById(R.id.user_key);
        EditText toBeTranslatedEditTextView = (EditText)findViewById(R.id.text_to_be_translated);
        TextView translatedTextView = (TextView)findViewById(R.id.translated_text);

        String userKey = String.valueOf(userKeyEditTextView.getText());
        String toBeTranslatedText = String.valueOf(toBeTranslatedEditTextView.getText());
        String translatedText = caesarCrypter.translate(false, userKey, toBeTranslatedText);

        translatedTextView.setText(translatedText);
    }
}