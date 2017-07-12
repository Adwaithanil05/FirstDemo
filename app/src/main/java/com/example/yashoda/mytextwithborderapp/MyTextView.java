package com.example.yashoda.mytextwithborderapp;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by yashoda on 11/7/17.
 */

public class MyTextView extends android.support.v7.widget.AppCompatTextView {

    private static Paint getWhiteBorderPaint(){
        Paint p = new Paint(Color.RED);
        return p;
    }

    private static final Paint BLACK_BORDER_PAINT = getWhiteBorderPaint();

    static {
        BLACK_BORDER_PAINT.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OUT));
    }

    @Override
    public void setText(CharSequence text, BufferType type) {

        super.setText(String.format(text.toString()), type);
    }

    private static final int BORDER_WIDTH = 1;

//    private Typeface typeface;

    public MyTextView(Context context) {
        super(context);
    }

    public MyTextView(Context context, AttributeSet attrs) {
        super(context, attrs);

        setDrawingCacheEnabled(false);

        setTypeface(attrs);
    }

    private void setTypeface(AttributeSet attrs) {
        /*final String typefaceFileName = attrs.getAttributeValue(null, "typeface");
        if (typefaceFileName != null) {
            typeface = Typeface.createFromAsset(getContext().getAssets(), typefaceFileName);
        }*/
        Typeface tf = Typeface.createFromAsset(getContext().getAssets(),
                "fonts/impactreg.ttf");

        setTypeface(tf);
    }

    public MyTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        setTypeface(attrs);
    }

    @Override
    public void draw(Canvas aCanvas) {
        aCanvas.saveLayer(null, BLACK_BORDER_PAINT, Canvas.HAS_ALPHA_LAYER_SAVE_FLAG
                | Canvas.FULL_COLOR_LAYER_SAVE_FLAG | Canvas.MATRIX_SAVE_FLAG);

        drawBackground(aCanvas, -BORDER_WIDTH, -BORDER_WIDTH);
        drawBackground(aCanvas, BORDER_WIDTH + BORDER_WIDTH, 0);
        drawBackground(aCanvas, 0, BORDER_WIDTH + BORDER_WIDTH);
        drawBackground(aCanvas, -BORDER_WIDTH - BORDER_WIDTH, 0);

        aCanvas.restore();
        super.draw(aCanvas);

    }

    private void drawBackground(Canvas aCanvas, int aDX, int aDY) {
        aCanvas.translate(aDX, aDY);
        super.draw(aCanvas);
    }
}