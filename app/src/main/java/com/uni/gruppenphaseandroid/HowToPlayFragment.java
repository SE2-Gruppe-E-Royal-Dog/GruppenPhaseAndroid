package com.uni.gruppenphaseandroid;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.DialogFragment;

import com.github.barteksc.pdfviewer.PDFView;

public class HowToPlayFragment extends DialogFragment {

    PDFView mPdf;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_howtoplay, container, false);

        mPdf = view.findViewById(R.id.pdfView);
        mPdf.fromAsset("Howtoplay.pdf").load();

        return view;

    }

    @Override
    public void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);


    }
}
