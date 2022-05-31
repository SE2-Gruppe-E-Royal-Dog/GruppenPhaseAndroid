package com.uni.gruppenphaseandroid.playingfield;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.uni.gruppenphaseandroid.manager.GameManager;

public class FigureUIimpl extends FigureUI {

    private ImageView imageView;

    public void createFigureUI(View view, String tag, RelativeLayout relativeLayout, int imageResource) {
        imageView = new ImageButton(view.getContext());
        imageView.setLayoutParams(new RelativeLayout.LayoutParams(140, 140));
        relativeLayout.addView(imageView);
        imageView.setTag(tag);
        imageView.setImageResource(imageResource);
    }

    @Override
    public void moveFigureToPosition(FieldUI targetFieldUI) {

        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) imageView.getLayoutParams();
        marginLayoutParams.setMargins(targetFieldUI.getMarginLeft(), targetFieldUI.getMarginTop(), 0, 0);
        imageView.setLayoutParams(marginLayoutParams);
    }

    @Override
    public void setButtonClickBehaviour(Figure figure){
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                    Log.e("Figur", "Figure was selected!");
                    GameManager.getInstance().figureGotSelected(figure);
                    //TODO EFFECT
                } catch (Exception e) {
                    Log.e("Figur", "Figure selection ended up in exception! " + e.getMessage());
                }
            }
        });
    }
}
