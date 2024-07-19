package com.vibcompany.funciones;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.text.style.ReplacementSpan;

public class RoundedBackgroundSpan extends ReplacementSpan {
    private final int backgroundColor;
    private final int textColor;
    private final float cornerRadius;
    private final float horizontalPadding;
    private final float verticalPadding;  // Añadido

    public RoundedBackgroundSpan(int backgroundColor, int textColor, float cornerRadius, float horizontalPadding, float verticalPadding) {
        this.backgroundColor = backgroundColor;
        this.textColor = textColor;
        this.cornerRadius = cornerRadius;
        this.horizontalPadding = horizontalPadding;
        this.verticalPadding = verticalPadding;  // Añadido
    }

    @Override
    public int getSize(Paint paint, CharSequence text, int start, int end, Paint.FontMetricsInt fm) {
        if (fm != null) {
            paint.getFontMetricsInt(fm);
        }
        return Math.round(paint.measureText(text, start, end) + horizontalPadding * 2);
    }

    @Override
    public void draw(Canvas canvas, CharSequence text, int start, int end, float x, int top, int y, int bottom, Paint paint) {
        Paint.FontMetricsInt fm = paint.getFontMetricsInt();
        float rectTop = y + fm.ascent - verticalPadding;  // Ajustado
        float rectBottom = y + fm.descent + verticalPadding;  // Ajustado

        RectF rect = new RectF(x, rectTop, x + measureText(paint, text, start, end) + horizontalPadding * 2, rectBottom);
        paint.setColor(backgroundColor);
        canvas.drawRoundRect(rect, cornerRadius, cornerRadius, paint);

        paint.setColor(textColor);
        canvas.drawText(text, start, end, x + horizontalPadding, y, paint);
    }

    private float measureText(Paint paint, CharSequence text, int start, int end) {
        return paint.measureText(text, start, end);
    }
}
