package com.dimaion666gmail.vigenerecipher;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewStub;
import android.widget.EditText;
import androidx.appcompat.widget.ShareActionProvider;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.view.MenuItemCompat;

public class MainActivity extends AppCompatActivity {
    // TODO: User paste string must not save its previous font

    private ShareActionProvider shareActionProvider;

    private String userKey;
    private boolean isDecrypting;
    private String toBeTranslatedText;
    private String translatedText;
    private VigenereCipher vigenereCipher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        vigenereCipher = new VigenereCipher();

        // Ищем заготовку карточки для входного текста и заполняем отличительными компонентами
        CardView textToBeTranslatedCard = findViewById(R.id.text_to_be_translated_card);

        ViewStub textToBeTranslatedToolbarStub = textToBeTranslatedCard.findViewById(R.id.toolbar_stub);
        textToBeTranslatedToolbarStub.setLayoutResource(R.layout.text_to_be_translated_toolbar);
        textToBeTranslatedToolbarStub.inflate();

        ViewStub textToBeTranslatedTextStub = textToBeTranslatedCard.findViewById(R.id.text_stub);
        textToBeTranslatedTextStub.setLayoutResource(R.layout.text_to_be_translated_text);
        textToBeTranslatedTextStub.inflate();

        // Ищем заготовку карточки для выходного текста и заполняем отличительными компонентами
        CardView translatedTextCard = findViewById(R.id.translated_text_card);

        ViewStub translatedTextToolbarStub = translatedTextCard.findViewById(R.id.toolbar_stub);
        translatedTextToolbarStub.setLayoutResource(R.layout.translated_text_toolbar);
        translatedTextToolbarStub.inflate();

        ViewStub translatedTextTextStub = translatedTextCard.findViewById(R.id.text_stub);
        translatedTextTextStub.setLayoutResource(R.layout.translated_text_text);
        translatedTextTextStub.inflate();

        if (savedInstanceState != null) {
            userKey = savedInstanceState.getString("userKey");
            isDecrypting = savedInstanceState.getBoolean("isDecrypting");
            toBeTranslatedText = savedInstanceState.getString("toBeTranslatedText");
            translatedText = savedInstanceState.getString("translatedText");


            EditText userKeyEditTextView = (EditText) findViewById(R.id.key);
            ToggleButton isDecryptingToggleButton = (ToggleButton) findViewById(R.id.is_decrypting_toggle_button);
            EditText toBeTranslatedEditTextView = (EditText) findViewById(R.id.text_to_be_translated_text);
            TextView translatedTextView = (TextView) findViewById(R.id.translated_text_text);


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

    public void onClickEncryptingOrDecrypting(View view) {
        ToggleButton isDecryptingToggleButton = (ToggleButton) view;
        isDecrypting = isDecryptingToggleButton.isChecked();
    }

    public void onClickTranslate(View view) {
        long startTime = System.nanoTime();

        final EditText userKeyEditTextView = (EditText) findViewById(R.id.key);
        final EditText toBeTranslatedEditTextView = (EditText) findViewById(R.id.text_to_be_translated_text);
        final TextView translatedTextView = (TextView) findViewById(R.id.translated_text_text);

        Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                userKey = String.valueOf(userKeyEditTextView.getText());
                toBeTranslatedText = String.valueOf(toBeTranslatedEditTextView.getText());
                try {
                    translatedText = vigenereCipher.translate(isDecrypting, userKey, toBeTranslatedText);
                    translatedTextView.setText(translatedText);
                    setShareActionProvider(translatedText);
                }
                catch (InvalidKeyException ikex) {
                    Toast exceptionMessage = Toast.makeText(getApplicationContext(), ikex.getMessage(), Toast.LENGTH_SHORT);
                    exceptionMessage.show();
                }
            }
        });

        Log.i("onClickTranslate speed", Long.toString(System.nanoTime() - startTime));
    }

    public void onClickCancel(View view) {
        EditText toBeTranslatedEditTextView = (EditText) findViewById(R.id.text_to_be_translated_text);
        toBeTranslatedEditTextView.setText(null);
    }

    public void onClickShare(View view) {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        MenuItem menuItem = menu.findItem(R.id.action_share);
        shareActionProvider = (ShareActionProvider) MenuItemCompat.getActionProvider(menuItem);
        setShareActionProvider(translatedText);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void setShareActionProvider(String text) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, text);
        shareActionProvider.setShareIntent(intent);
    }
}