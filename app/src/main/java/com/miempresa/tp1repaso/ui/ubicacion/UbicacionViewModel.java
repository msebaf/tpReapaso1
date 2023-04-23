package com.miempresa.tp1repaso.ui.ubicacion;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;
import android.location.Location;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.Priority;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

import java.util.List;

public class UbicacionViewModel extends AndroidViewModel {

    public MutableLiveData<MapaActual> mapa = null;

    private MutableLiveData<LatLng> ubicacion;
    private FusedLocationProviderClient fused;
    private  LatLng YO;
    private Context context;

    public UbicacionViewModel(@NonNull Application application) {
        super(application);
        this.context=application.getApplicationContext();
    }
    // TODO: Implement the ViewModel


    public LiveData<MapaActual> getMapa() {
        if (mapa == null) {
            this.mapa = new MutableLiveData<>();
        }
        return mapa;
    }

    public LiveData<LatLng> getUbicacion(){
        if(ubicacion==null){
            ubicacion=new MutableLiveData<>();
        }
        return ubicacion;
    }


    public void llamarMapa(LatLng latLng) {
        this.YO=latLng;
        mapa.setValue(new MapaActual());
    }


    public class MapaActual implements OnMapReadyCallback {


        @Override
        public void onMapReady(@NonNull GoogleMap googleMap) {
            googleMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
            googleMap.addMarker(new MarkerOptions().position(YO).title("Yo"));

            CameraPosition camPos = new CameraPosition.Builder()
                    .target(YO)
                    .zoom(15)
                    .bearing(0)
                    .tilt(70)
                    .build();
            CameraUpdate update = CameraUpdateFactory.newCameraPosition(camPos);
            googleMap.animateCamera(update);

        }
    }

    public void obtenerUltimaUbicacion() {
        //Obtener lectura de ubicación más reciente.
        fused = LocationServices.getFusedLocationProviderClient(context);

        @SuppressLint("MissingPermission") Task<Location> tarea = fused.getLastLocation();
        tarea.addOnSuccessListener( getApplication().getMainExecutor(), new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if(location!=null){
                    Log.d("salida ",location.getLongitude()+"");
                    YO=new LatLng(location.getLatitude(),location.getLongitude());
                    ubicacion.postValue(YO);


                }
            }
        });
        Log.d("salida hilo principal ","Salida");
    }
}
