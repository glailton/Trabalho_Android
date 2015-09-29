package br.com.fa7.especializacao.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.ByteArrayOutputStream;

/**
 * Created by iaufc26.costa on 29/09/2015.
 */
public class Util {
    public static byte[] convertBitmapToByteArray(Bitmap image, int qualityImage){
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.PNG, qualityImage, stream);
        return stream.toByteArray();
    }

    public static Bitmap convertByteArrayToBitmap(byte[] image){
        return BitmapFactory.decodeByteArray(image, 0, image.length);
    }
}
