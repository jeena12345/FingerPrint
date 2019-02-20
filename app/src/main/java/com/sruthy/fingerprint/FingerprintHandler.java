package com.sruthy.fingerprint;


import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.fingerprint.FingerprintManager;
import android.media.MediaPlayer;
import android.os.CancellationSignal;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class  FingerprintHandler extends FingerprintManager.AuthenticationCallback  {
    MediaPlayer mediaPlayer,mediaPlayer1;

    private Context context;

    // Constructor
    public FingerprintHandler(Context mContext) {
        context = mContext;

    }


    public void startAuth(FingerprintManager manager, FingerprintManager.CryptoObject cryptoObject) {
        CancellationSignal cancellationSignal = new CancellationSignal();
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.USE_FINGERPRINT) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        manager.authenticate(cryptoObject, cancellationSignal, 0, this, null);
    }


    @Override
    public void onAuthenticationError(int errMsgId, CharSequence errString) {
        this.update("Fingerprint Authentication error\n" + errString, false);
    }


    @Override
    public void onAuthenticationHelp(int helpMsgId, CharSequence helpString) {
        this.update("Fingerprint Authentication help\n" + helpString, false);
    }


    @Override
    public void onAuthenticationFailed() {
        this.update("Fingerprint Authentication failed.", false);
        Toast.makeText(context,"error",Toast.LENGTH_SHORT).show();
        mediaPlayer1=MediaPlayer.create(context,R.raw.okkk);
        mediaPlayer1.start();
        ImageView imageView1=((Activity)context).findViewById(R.id.img1);
        ImageView imageView=((Activity)context).findViewById(R.id.img2);
        imageView.setVisibility(View.VISIBLE);
        imageView1.setVisibility(View.INVISIBLE);

    }


    @Override
    public void onAuthenticationSucceeded(FingerprintManager.AuthenticationResult result) {
        this.update("Fingerprint Authentication succeeded.", true);
        mediaPlayer=MediaPlayer.create(context,R.raw.t);
        mediaPlayer.start();
        ImageView imageView1=((Activity)context).findViewById(R.id.img2);
        ImageView imageView=((Activity)context).findViewById(R.id.img1);
        imageView.setVisibility(View.VISIBLE);
        imageView1.setVisibility(View.INVISIBLE);

    }


    public void update(String e, Boolean success){
        TextView textView = (TextView) ((Activity)context).findViewById(R.id.errorText);
        textView.setText(e);
        if(success){

            textView.setTextColor(ContextCompat.getColor(context,R.color.colorPrimaryDark));
        }
    }
}