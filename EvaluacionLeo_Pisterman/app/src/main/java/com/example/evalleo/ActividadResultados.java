package com.example.evalleo;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class ActividadResultados extends Activity {

    TextView PrimerResultado;
    TextView SegundoResultado;
    TextView TercerResultado;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad_resultados);
        Bundle DatosRecibidos=this.getIntent().getExtras();
        PrimerResultado=findViewById(R.id.PrimerRsultado);
        SegundoResultado=findViewById(R.id.SegundoRsultado);
        TercerResultado=findViewById(R.id.TercerRsultado);
        PrimerResultado.setText("la cantidad de veces que fuiste un nabo y apretaste el boton sin que tengas algo en los dos textos fue "+DatosRecibidos.getInt("CantidadErroresNabo"));
        SegundoResultado.setText("La cantidad de veces que el largo de los dos textos fue el mismo fue  "+DatosRecibidos.getInt("CantidadLargoIgual"));
        TercerResultado.setText("el texto mas largo fue "+DatosRecibidos.getString("TextoMasLargo"));
    }
}
