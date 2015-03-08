package com.nicodangelo.memegenerator;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.app.Activity;
import android.widget.Button;
import android.widget.EditText;

//created by Jake Cox

public class top_section_fragment extends Fragment
{
    private static EditText topTextInput;
    private static EditText bottomTextInput;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        //this is the reference to the XML file we created
        View view = inflater.inflate(R.layout.top_section_fragment,container,false);

        topTextInput = (EditText) view.findViewById(R.id.topTextInput);
        bottomTextInput = (EditText) view.findViewById(R.id.bottomTextInput);
        final Button button = (Button) view.findViewById(R.id.button);

        button.setOnClickListener(
                new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        buttonClicked(v);
                    }
                }
        );

        return view;
    }

    public void buttonClicked(View v)
    {

    }
}
