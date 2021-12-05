package com.example.project242.Transactions.TransactionsStatements;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.widget.PopupWindow;

public class StatementGenerator {

    private PopupWindow window;
    private Context context;
    private View rootLayout;
    private View layout;
    private int gravity;
    private int x;
    private int y;
    private int windowWidth;
    private int windowHeight;

    public StatementGenerator(Context context, View rootLayout, View layout) {
        window = new PopupWindow(context);
    }

    public void setDimensions(int width, int height) {
        windowWidth = width;
        windowHeight = height;
    }


    public void setPosition(int x, int y, int gravity) {

    }


    public void showWindow() {
        window = new PopupWindow(layout, windowWidth, windowHeight, true);
        window.showAtLocation(rootLayout, gravity, x, y);

    }

}
