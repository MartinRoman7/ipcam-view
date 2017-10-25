package com.github.niqdev.ipcam.Fragments.contacts;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.niqdev.ipcam.R;


/**
 * Created by ctin on 19/10/17.
 */

public class ContactsFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        if(container!=null){
            View rootView = inflater.inflate(R.layout.contacts_layout,container,false);


            return rootView;
        }
        return null;
    }
}
