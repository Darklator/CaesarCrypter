package com.dimaion666gmail.caesarcrypter;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

public class VigenereCipherService extends Service {

    private final IBinder binder = new VigenereCipherBinder();
    private final CaesarCrypter caesarCrypter = new CaesarCrypter();

    public class VigenereCipherBinder extends Binder {
        VigenereCipherService getVigenereCipher() {
            return VigenereCipherService.this;
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    public String getTranslation(boolean isDecrypting, String letterShifts, String text) throws InvalidKeyException {
        return caesarCrypter.translate(isDecrypting, letterShifts, text);
    }
}
