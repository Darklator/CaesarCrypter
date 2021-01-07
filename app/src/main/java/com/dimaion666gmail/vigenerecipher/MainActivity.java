package com.dimaion666gmail.vigenerecipher;

import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

import static android.content.Intent.ACTION_SEND;

public class MainActivity extends AppCompatActivity {
    // Эти переменные используются в нескольких местах, поэтому я решил сделать их глобальными. Так
    // используется меньше кода.
    private ToggleButton isDecryptingToggleButtonView;
    private EditText keyEditTextView;
    private EditText textToBeTranslatedEditTextView;
    private TextView translatedTextTextView;

    private String translatedText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Ищем заготовку карточки для входного текста и заполняем отличительными компонентами.
        CardView textToBeTranslatedCard = findViewById(R.id.text_to_be_translated_card);

        ViewStub textToBeTranslatedToolbarStub = textToBeTranslatedCard.findViewById(R.id.toolbar_stub);
        textToBeTranslatedToolbarStub.setLayoutResource(R.layout.text_to_be_translated_toolbar);
        textToBeTranslatedToolbarStub.inflate();

        ViewStub textToBeTranslatedTextStub = textToBeTranslatedCard.findViewById(R.id.text_stub);
        textToBeTranslatedTextStub.setLayoutResource(R.layout.text_to_be_translated_text);
        textToBeTranslatedTextStub.inflate();

        // Ищем заготовку карточки для выходного текста и заполняем отличительными компонентами.
        CardView translatedTextCard = findViewById(R.id.translated_text_card);

        ViewStub translatedTextToolbarStub = translatedTextCard.findViewById(R.id.toolbar_stub);
        translatedTextToolbarStub.setLayoutResource(R.layout.translated_text_toolbar);
        translatedTextToolbarStub.inflate();

        ViewStub translatedTextTextStub = translatedTextCard.findViewById(R.id.text_stub);
        translatedTextTextStub.setLayoutResource(R.layout.translated_text_text);
        translatedTextTextStub.inflate();

        // Получаем каждое представление только по одному разу.
        isDecryptingToggleButtonView = findViewById(R.id.is_decrypting_toggle_button);
        keyEditTextView = findViewById(R.id.key_edittext);
        textToBeTranslatedEditTextView = findViewById(R.id.text_to_be_translated_text);
        translatedTextTextView = findViewById(R.id.translated_text_text);

        // Нужно, чтобы при клике на любом представлении, кроме EditText, пропадала клавиатура.
        setupClosingKeyboardListeners(findViewById(R.id.parent));

        // Если активность была вызвана через ACTION_SEND, то получаем текст, который хочет
        // перевести пользователь.
        Intent intent = getIntent();
        String action = intent.getAction();
        if (ACTION_SEND.equals(action))
            textToBeTranslatedEditTextView.setText(intent.getStringExtra(Intent.EXTRA_TEXT));

        if (savedInstanceState != null) {
            // Сохраняется только translatedText, потому что только её представление сбрасывает
            // содержимое, а translatedText обнуляется.
            translatedText = savedInstanceState.getString("translatedText");
            translatedTextTextView.setText(translatedText);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        // Сохраняется только переменная translatedText, так как содержимое других представлений
        // не сбрасывается со сменой конфигурации устройства. Скорее всего, такая особенность
        // появилась из-за инициализации карточек через ViewStub.
        savedInstanceState.putString("translatedText", translatedText);
        super.onSaveInstanceState(savedInstanceState);
    }

    public void onClickTranslate(View view) {
        Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                boolean isDecrypting = isDecryptingToggleButtonView.isChecked();
                String key = String.valueOf(keyEditTextView.getText());
                String textToBeTranslated = String.valueOf(textToBeTranslatedEditTextView.getText());

                try {
                    translatedText = VigenereCipher.translate(isDecrypting, key, textToBeTranslated);
                    translatedTextTextView.setText(translatedText);
                }
                catch (InvalidKeyException ikex) {
                    Toast exceptionMessage = Toast.makeText(getApplicationContext(),
                            R.string.wrong_key, Toast.LENGTH_SHORT);
                    exceptionMessage.show();
                }
            }
        });
    }

    public void onClickPaste(View view) {
        ClipboardManager clipboard = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);

        if (clipboard.hasPrimaryClip()) {
            ClipData clipData = clipboard.getPrimaryClip();
            textToBeTranslatedEditTextView.setText(clipData.getItemAt(0).coerceToText(this));
            textToBeTranslatedEditTextView.setSelection(textToBeTranslatedEditTextView.getText().length());
        } else {
            Toast toast = Toast.makeText(getApplicationContext(), R.string.no_content, Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    public void onClickCancel(View view) {
        textToBeTranslatedEditTextView.setText(null);
    }

    public void onClickCopy(View view) {
        ClipboardManager clipboard = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
        ClipData clipData = ClipData.newPlainText("Translated text", translatedText);
        clipboard.setPrimaryClip(clipData);

        Toast message = Toast.makeText(getApplicationContext(), R.string.output_copied, Toast.LENGTH_SHORT);
        message.show();
    }

    public void onClickShare(View view) {
        Intent intent = new Intent(ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, translatedText);
        startActivity(intent);
    }

    public void setupClosingKeyboardListeners(View view) {
        // Установка слушателей касаний для нетекстовых представлений. Эти слушатели будут скрывать
        // клавиатуру.
        if (!(view instanceof EditText)) {
            view.setOnTouchListener(new View.OnTouchListener() {
                public boolean onTouch(View v, MotionEvent event) {
                    closeKeyBoard();
                    return false;
                }
            });
        }

        // Если текущее представление является контейнером, то пробегаемся по его детям посредством
        // рекурсии.
        if (view instanceof ViewGroup) {
            for (int i = 0; i < ((ViewGroup) view).getChildCount(); i++) {
                View innerView = ((ViewGroup) view).getChildAt(i);
                setupClosingKeyboardListeners(innerView);
            }
        }
    }

    private void closeKeyBoard() {
        View view = this.getCurrentFocus();

        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
}