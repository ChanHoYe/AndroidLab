package com.ch.lab3;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class Fragment1 extends Fragment {
    /*
     * Instance Method without Operator.
     */
    public static Fragment1 newInstance() {
        Bundle args = new Bundle();

        Fragment1 fragment = new Fragment1();
        fragment.setArguments(args);

        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup mView = (ViewGroup) inflater.inflate(R.layout.fragment_first, container, false);

        return mView;
    }
}
