package com.miempresa.tp1repaso.ui.ubicacion;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.miempresa.tp1repaso.R;
import com.miempresa.tp1repaso.databinding.FragmentGalleryBinding;
import com.miempresa.tp1repaso.databinding.FragmentUbicacionBinding;

public class UbicacionFragment extends Fragment {

    private FragmentUbicacionBinding binding;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        UbicacionViewModel homeViewModel =
                new ViewModelProvider(this).get(UbicacionViewModel.class);

        binding = FragmentUbicacionBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        homeViewModel.obtenerUltimaUbicacion();
        homeViewModel.getMapa().observe(getViewLifecycleOwner(), new Observer<UbicacionViewModel.MapaActual>() {
            @Override
            public void onChanged(UbicacionViewModel.MapaActual mapaActual) {
                SupportMapFragment smf=(SupportMapFragment)getChildFragmentManager().findFragmentById(R.id.map);
                smf.getMapAsync(mapaActual);
            }
        });
        homeViewModel.getUbicacion().observe(getViewLifecycleOwner(), new Observer<LatLng>() {
            @Override
            public void onChanged(LatLng latLng) {
                homeViewModel.llamarMapa(latLng);
            }
        });

        homeViewModel.obtenerUltimaUbicacion();
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }



}