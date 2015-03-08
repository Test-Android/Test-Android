package com.nicodangelo.memegenerator;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

//created by Jake Cox

public class top_section_fragment extends Fragment
{
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        //this is the reference to the XML file we created
        View view = inflater.inflate(R.layout.top_section_fragment,container,false);
        return view;
    }
}
