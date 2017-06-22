package curve.com;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import java.util.List;

/**
 * Created by hongyang on 17-6-22.
 */

public class NormalView extends View {

    private List<CurveStorage> storages;
    private static final int TEXT_SIZE = 32;

    public NormalView(Context context) {
        super(context);
    }

    public NormalView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public void setNormalStorage(List<CurveStorage> storages){
        this.storages=storages;
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Paint paint = new Paint();
        paint.setTextSize(TEXT_SIZE);
        paint.setColor(Color.BLACK);
        paint.setAntiAlias(true);
        for (int i=0;i<storages.size();i++){
            canvas.drawText(String.valueOf(storages.get(i).value+"KG"),storages.get(i).locationX,storages.get(i).locationY,paint);
        }
        super.onDraw(canvas);
    }

}
