package com.example.evalleo;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.icu.util.IslamicCalendar;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class ActividadPrincipal extends Activity {

    static MediaPlayer mediaPlayer;
    static boolean isPlayingAudio=false;
    String TextoMasLargo;
    int CantidadErroresNabo;
    int CantidadLargoIgual;
    EditText PrimerTexto;
    EditText SegundoTexto;
    CheckBox checkBox;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad_principal);
        TextoMasLargo="";
        CantidadErroresNabo=0;
        CantidadLargoIgual=0;
        PrimerTexto=findViewById(R.id.PrimerTexto);
        SegundoTexto=findViewById(R.id.SegundoTexto);
        checkBox=findViewById(R.id.CheckboxDeLeoLog);
        PlayAudio(this,R.raw.help);
    }
    public static void PlayAudio(Context c, int id){
        mediaPlayer = MediaPlayer.create(c, id);
        SoundPool soundPool = new SoundPool(4, AudioManager.STREAM_MUSIC,50);
        if (!mediaPlayer.isPlaying())
        {
            isPlayingAudio= true;
            mediaPlayer.start();
            mediaPlayer.setLooping(true);
        }
    }
    public void Trabajar(View vista)
    {
        if (PrimerTexto.getText().toString().equals("Fin") || SegundoTexto.getText().toString().equals("Fin"))
        {
            Intent LlevarAResultado=new Intent(this,ActividadResultados.class);
            Bundle paqueteDeDatos=new Bundle();
            paqueteDeDatos.putString("TextoMasLargo",TextoMasLargo);
            paqueteDeDatos.putInt("CantidadErroresNabo",CantidadErroresNabo);
            paqueteDeDatos.putInt("CantidadLargoIgual",CantidadLargoIgual);
            LlevarAResultado.putExtras(paqueteDeDatos);
            startActivity(LlevarAResultado);
        }
        boolean TextoVacio = ValidarTextoVacio();
        if (TextoVacio)
        {
            Toast Vacio = Toast.makeText(getApplicationContext(), "Alguno de los textos se encuentra sin texto", Toast.LENGTH_SHORT);
            Vacio.show();
            CantidadErroresNabo++;
        }
        else {
            if (checkBox.isChecked())//verifico si esta checkeado

            {
                Log.d("LeoLog", "" + PrimerTexto.getText());
                Log.d("LeoLog", "" + SegundoTexto.getText());
                VerificarLongitudYSimilitud();
            }



                PrimerTexto.getText().clear();
                SegundoTexto.getText().clear();
                checkBox.clearFocus();

        }
    }
    private void VerificarLongitudYSimilitud()
    {
        if (PrimerTexto.getText().toString().equals(SegundoTexto.getText().toString()))
        {
            CantidadLargoIgual++;
        }
        if (PrimerTexto.getText().toString().length()>TextoMasLargo.length())
        {
            TextoMasLargo=PrimerTexto.getText().toString();
        }

        if (SegundoTexto.getText().toString().length()>TextoMasLargo.length())
        {
            TextoMasLargo=SegundoTexto.getText().toString();
        }
    }
    private boolean ValidarTextoVacio()
    {
        boolean Veracidad;
        if (PrimerTexto.getText().toString().equals("") || SegundoTexto.getText().toString().equals(""))
        {
            Veracidad=true;
        }
        else
        {
            Veracidad=false;
        }
        return Veracidad;
    }
}
