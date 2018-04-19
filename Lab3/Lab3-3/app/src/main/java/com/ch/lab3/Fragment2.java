package com.ch.lab3;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class Fragment2 extends Fragment {
    /*
     * Instance Method without Operator.
     */
    public static Fragment2 newInstance() {
        Bundle args = new Bundle();

        Fragment2 fragment = new Fragment2();
        fragment.setArguments(args);

        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup mView = (ViewGroup) inflater.inflate(R.layout.fragment_second, container, false);

        return mView;
    }
}
