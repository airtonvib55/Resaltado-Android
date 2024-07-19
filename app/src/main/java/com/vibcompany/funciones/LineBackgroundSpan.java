package com.vibcompany.funciones;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.text.style.ReplacementSpan;

public class LineBackgroundSpan extends ReplacementSpan {
    private final int backgroundColor;
    private final int textColor;
    private final float cornerRadius;
    private final float padding;

    public LineBackgroundSpan(int backgroundColor, int textColor, float cornerRadius, float padding) {
        this.backgroundColor = backgroundColor;
        this.textColor = textColor;
        this.cornerRadius = cornerRadius;
        this.padding = padding;
    }

    @Override
    public int getSize(Paint paint, CharSequence text, int start, int end, Paint.FontMetricsInt fm) {
        if (fm != null) {
            paint.getFontMetricsInt(fm);
        }
        return Math.round(paint.measureText(text, start, end) + padding * 2);
    }

    @Override
    public void draw(Canvas canvas, CharSequence text, int start, int end, float x, int top, int y, int bottom, Paint paint) {
        Paint.FontMetricsInt fm = paint.getFontMetricsInt();
        float rectTop = top - fm.ascent + padding;
        float rectBottom = bottom + padding;

        RectF rect = new RectF(x, rectTop, x + measureText(paint, text, start, end) + padding * 2, rectBottom);
        paint.setColor(backgroundColor);
        canvas.drawRoundRect(rect, cornerRadius, cornerRadius, paint);

        paint.setColor(textColor);
        canvas.drawText(text, start, end, x + padding, y, paint);
    }

    private float measureText(Paint paint, CharSequence text, int start, int end) {
        return paint.measureText(text, start, end);
    }
}
