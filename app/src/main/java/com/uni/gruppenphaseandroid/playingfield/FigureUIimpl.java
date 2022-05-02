package com.uni.gruppenphaseandroid.playingfield;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

public class FigureUIimpl extends FigureUI {

    private ImageButton imageButton;
    //private View view;

    public void createFigureUI(View view, String tag, RelativeLayout relativeLayout, int imageResource){
        imageButton = new ImageButton(view.getContext());
        imageButton.setLayoutParams(new RelativeLayout.LayoutParams(140, 140));
        relativeLayout.addView(imageButton);
        imageButton.setTag(tag);
        imageButton.setImageResource(imageResource);
        //imageButton.setBackgroundColor(0);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageButton.setVisibility(View.INVISIBLE);
            }
        });
    }

    @Override
    public void moveFigureToPosition(FieldUI targetFieldUI) {

        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) imageButton.getLayoutParams();
        //marginLayoutParams.setMargins(marginLayoutParams.leftMargin, marginLayoutParams.topMargin, targetFieldUI.getMarginRight(), targetFieldUI.getMarginBottom());
        marginLayoutParams.setMargins(targetFieldUI.getMarginLeft(),targetFieldUI.getMarginTop(),0,0);
        imageButton.setLayoutParams(marginLayoutParams);
    }
}
