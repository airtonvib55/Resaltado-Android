package com.vibcompany.funciones;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Layout;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.text.style.BackgroundColorSpan;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import org.w3c.dom.Text;

import java.util.prefs.BackingStoreException;

public class MainActivity extends AppCompatActivity {

    int estado = 0;
    int estadoBtn = 0;
    String color = "none";
    String textoOriginal = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        TextView txtVerso = findViewById(R.id.txtVerso);
        TextView numVerso = findViewById(R.id.numVerso);
        ImageButton btnFavorito = findViewById(R.id.btnFavorito);
        ImageButton btnFavorito2 = findViewById(R.id.btnFavorito2);

        textoOriginal = txtVerso.getText().toString();

        txtVerso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(estado == 0){
                    txtVerso.setTextColor(Color.parseColor("#FFFF64"));
                    btnFavorito.setVisibility(View.VISIBLE);
                    btnFavorito2.setVisibility(View.VISIBLE);
                    estado = 1;
                }else if(estado == 1){
                    txtVerso.setTextColor(Color.parseColor("#FFFFFF"));
                    btnFavorito.setVisibility(View.GONE);
                    btnFavorito2.setVisibility(View.GONE);
                    estado = 0;
                }else if (estado == 2){
                    btnFavorito.setVisibility(View.VISIBLE);
                    btnFavorito2.setVisibility(View.VISIBLE);
                    estado = 3;
                }else{
                    btnFavorito.setVisibility(View.GONE);
                    btnFavorito2.setVisibility(View.GONE);
                    estado = 2;
                }
            }
        });

        btnFavorito.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(estadoBtn == 1 && color == "amarillo"){
                    txtVerso.setText(textoOriginal); //quitar resaltado
                    //txtVerso.setBackgroundColor(Color.TRANSPARENT);

                    txtVerso.setPadding(8, 5, 5, 5);

                    txtVerso.setTextColor(Color.parseColor("#FFFFFF"));
                    btnFavorito.setVisibility(View.GONE);
                    btnFavorito2.setVisibility(View.GONE);
                    color = "none";
                    estado = 0;
                    estadoBtn = 0;
                }else{
                    // Create a TextPaint instance
                    TextPaint textPaint = new TextPaint();
                    textPaint.setTextSize(txtVerso.getTextSize());
                    // Create a StaticLayout to handle line breaks
                    StaticLayout layout = new StaticLayout(
                            textoOriginal, textPaint, txtVerso.getWidth(),
                            Layout.Alignment.ALIGN_NORMAL, 1.0f, 0.0f, false);

                    SpannableString spannable = new SpannableString(textoOriginal);
                    for (int i = 0; i < layout.getLineCount(); i++) {
                        int lineStart = layout.getLineStart(i);
                        int lineEnd = layout.getLineEnd(i);
                        RoundedBackgroundSpan span = new RoundedBackgroundSpan(Color.parseColor("#FFFF64"), Color.BLACK, 12, 5, 2);
                        spannable.setSpan(span, lineStart, lineEnd, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                    }

                    txtVerso.setText(spannable); //texto resaltado

                    txtVerso.setPadding(3, 5, 0, 5);

                    txtVerso.setTextColor(Color.parseColor("#000000"));
                    btnFavorito.setVisibility(View.GONE);
                    btnFavorito2.setVisibility(View.GONE);
                    color = "amarillo";
                    estado = 2;
                    estadoBtn = 1;
                }
            }
        });

        btnFavorito2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(estadoBtn == 1 && color == "rojo"){
                    txtVerso.setText(textoOriginal); //quitar resaltado

                    txtVerso.setBackgroundColor(Color.TRANSPARENT);
                    txtVerso.setTextColor(Color.parseColor("#FFFFFF"));
                    btnFavorito.setVisibility(View.GONE);
                    btnFavorito2.setVisibility(View.GONE);
                    estado = 0;
                    estadoBtn = 0;
                }else{
                    //txtVerso.setBackgroundColor(Color.parseColor("#FF6464"));

                    //Resaltar de AMARILLO solo el texto y no todo el espacio
                    SpannableString spanString = new SpannableString(txtVerso.getText().toString());
                    BackgroundColorSpan bckSpanAmarillo = new BackgroundColorSpan(Color.parseColor("#FF6464"));
                    spanString.setSpan(bckSpanAmarillo, 0, txtVerso.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                    txtVerso.setText(spanString); //texto resaltado

                    txtVerso.setTextColor(Color.parseColor("#000000"));
                    btnFavorito.setVisibility(View.GONE);
                    btnFavorito2.setVisibility(View.GONE);
                    color = "rojo";
                    estado = 2;
                    estadoBtn = 1;
                }
            }
        });
    }
}