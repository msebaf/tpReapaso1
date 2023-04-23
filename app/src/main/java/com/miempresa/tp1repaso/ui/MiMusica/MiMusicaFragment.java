package com.miempresa.tp1repaso.ui.MiMusica;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.miempresa.tp1repaso.R;
import com.miempresa.tp1repaso.databinding.FragmentMiMusicaBinding;

public class MiMusicaFragment extends Fragment {

    private MiMusicaViewModel mViewModel;
    private FragmentMiMusicaBinding binding;

    public static MiMusicaFragment newInstance() {
        return new MiMusicaFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        MiMusicaViewModel galleryViewModel =
                new ViewModelProvider(this).get(MiMusicaViewModel.class);

        binding = FragmentMiMusicaBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        binding.Escuchar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mViewModel.reproducir();
            }
        });
        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(MiMusicaViewModel.class);
        // TODO: Use the ViewModel
    }

}