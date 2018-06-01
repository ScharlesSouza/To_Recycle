package br.untins.torecycle.to_recycle.fragmento;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import br.untins.torecycle.to_recycle.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class SobreFrag extends Fragment {


    public SobreFrag() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sobre, null);
    }

}
