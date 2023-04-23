package com.miempresa.tp1repaso.ui.MiMusica;

import android.app.Application;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.ViewModel;

public class MiMusicaViewModel extends AndroidViewModel {


    public MiMusicaViewModel(@NonNull Application application) {
        super(application);
    }
    // TODO: Implement the ViewModel



    public void reproducir(){
        Intent intent = new Intent(getApplication().getApplicationContext(), ServicioMusical.class );
        getApplication().getApplicationContext().startService(intent);
    }
}