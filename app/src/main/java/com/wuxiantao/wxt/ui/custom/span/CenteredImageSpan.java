package com.wuxiantao.wxt.ui.custom.span;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.text.style.ImageSpan;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:CenteredImageSpan
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-8-20 下午11:18
 * Description:${DESCRIPTION}
 */
public class CenteredImageSpan extends ImageSpan {


    public CenteredImageSpan(Context context,final int drawableRes) {
        super(context, drawableRes);
    }

    @Override
    public void draw(Canvas canvas, CharSequence text, int start, int end, float x, int top, int y, int bottom,Paint paint) {
        super.draw(canvas, text, start, end, x, top, y, bottom, paint);
        // image to draw
        Drawable b = getDrawable();
        // font metrics of text to be replaced
        Paint.FontMetricsInt fm = paint.getFontMetricsInt();
        int transY = (y + fm.descent + y + fm.ascent) / 2
                - b.getBounds().bottom / 2;

        canvas.save();
        canvas.translate(x, transY);
        b.draw(canvas);
        canvas.restore();
    }
}
