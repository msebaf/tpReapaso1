package com.miempresa.tp1repaso.ui.salir;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Alert {

    public static void mostrarDialogoSalida(Context context){

        new AlertDialog.Builder(context)
                .setTitle("Cerrando")
                .setMessage("Desea cerrar la aplicación ?")
                .setPositiveButton("Sí", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        ((AppCompatActivity)context).finishAndRemoveTask();

                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(context,"Contimnuamos",Toast.LENGTH_LONG).show();
                    }
                })
                .show();

    }
}
