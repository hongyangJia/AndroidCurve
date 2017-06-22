package curve.com;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Shader;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.List;

/**
 * Created by hongyang on 17-6-19.
 */

public class CurveView extends View {

    private static final int ROW = 6;
    private static final int DUFULET = 0;
    private static final int TEXT_SIZE = 32;
    private static final int TWENTY_SIX = 32;
    private static final Paint.Style PAINT_STYLE = Paint.Style.STROKE;
    private static final int STROKE_WIDTH = 4;
    private static final int COLOR = Color.parseColor("#ffff0000");
    private static final int ALPHA = 100;
    private static final int CIRCLE = 10;

    private int dayWidth;
    private int dayHeight;
    private int dayAllHeight;
    private int dayWidthLift;
    private int dayHeightTop;
    private float[] intervals = {8, 8};
    private List<DateStorage> dateStorages;
    private List<CurveStorage> curveStorages;
    private CurveStorage currentValue;
    private boolean isValue = false;
    private Bitmap bitmap = BitmapFactory.decodeResource(this.getContext().getResources(), R.mipmap.show_value);

    public CurveView(Context context) {
        super(context);
    }

    public CurveView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public void setDateStorage(List<DateStorage> dateStorages, List<CurveStorage> curveStorages, int dayWidth, int dayHeight, int dayAllHeight, int dayWidthLift,int dayHeightTop) {
        this.dateStorages = dateStorages;
        this.curveStorages = curveStorages;
        this.dayWidth = dayWidth;
        this.dayHeight = dayHeight;
        this.dayAllHeight = dayAllHeight;
        this.dayWidthLift = dayWidthLift;
        this.dayHeightTop=dayHeightTop;
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (dateStorages == null) return;
        Paint paint = new Paint();
        onDrawBackground(paint, canvas);
        onDrawText(paint, canvas);
        onDrawCurve(paint, canvas);
        onDrawShadow(paint, canvas);
        if (isValue) {
            onDrawValue(paint, canvas);
        }
    }

    private void onDrawBackground(Paint paint, Canvas canvas) {
        Tool.nullPointerException(dateStorages,"onDrawBackground : null");
        Path path = new Path();
        paint.reset();
        setPaintParameter(paint, intervals, Color.GRAY);
        int length = dateStorages.size() < 10 ? 10 : dateStorages.size();
        for (int i = DUFULET; i < length; i++) {
            path.reset();
            path.moveTo((dayWidth * i + dayWidthLift), dayHeightTop);
            path.lineTo((dayWidth * i + dayWidthLift), dayHeightTop + dayHeight * ROW);
            canvas.drawPath(path, paint);
        }

        for (int i = DUFULET; i < ROW; i++) {
            path.reset();
            path.moveTo(dayWidthLift, dayHeight * i + dayHeightTop);
            path.lineTo(dayWidth * (length-1) + dayWidthLift, dayHeight * i + dayHeightTop);
            canvas.drawPath(path, paint);
        }

    }

    private void onDrawText(Paint paint, Canvas canvas) {
        Tool.nullPointerException(dateStorages,"onDrawText : null");
        paint.reset();
        paint.setColor(Color.BLACK);
        paint.setTextSize(TEXT_SIZE);
        paint.setAntiAlias(true);
        for (int i = DUFULET; i < dateStorages.size(); i++) {
            canvas.drawText(dateStorages.get(i).date, dayWidth * i +15, (float) (dayHeight * ROW + dayHeight * 0.4 + dayHeightTop), paint);
        }
    }

    private void onDrawCurve(Paint paint, Canvas canvas) {
        Tool.nullPointerException(curveStorages,"onDrawCurve : null");
        paint.reset();
        Path path = new Path();
        for (int i = DUFULET; i < curveStorages.size(); i++) {
            onDrawCircle(paint, canvas, i);
            onLinePath(paint, path, canvas, i);
        }
    }

    private void onLinePath(Paint paint, Path path, Canvas canvas, int position) {
        Tool.nullPointerException(curveStorages,"onLinePath : null");
        if (position + 1 >= curveStorages.size()) return;
        paint.reset();
        paint.setStrokeWidth(STROKE_WIDTH);
        setPaintParameter(paint, new float[]{8, 8}, Color.RED);
        path.moveTo(curveStorages.get(position).locationX, curveStorages.get(position).locationY);
        path.lineTo(curveStorages.get(position + 1).locationX, curveStorages.get(position + 1).locationY);
        canvas.drawPath(path, paint);
    }

    private void onDrawShadow(Paint paint, Canvas canvas) {
        Tool.nullPointerException(curveStorages,"onDrawShadow : null");
        Path path = new Path();
        paint.reset();
        path.moveTo(dayWidthLift, dayAllHeight);
        paint.setAlpha(ALPHA);
        LinearGradient lg = new LinearGradient(
                Tool.getMaxLocation(curveStorages).locationX,
                Tool.getMaxLocation(curveStorages).locationY,
                Tool.getMaxLocation(curveStorages).locationX, dayAllHeight,
                COLOR, Color.WHITE, Shader.TileMode.MIRROR);
        paint.setShader(lg);
        for (int i = DUFULET; i < curveStorages.size(); i++) {
            path.lineTo(curveStorages.get(i).locationX, curveStorages.get(i).locationY);
        }
        path.lineTo(curveStorages.get(curveStorages.size() - 1).locationX, dayAllHeight);
        canvas.drawPath(path, paint);
    }

    private void onDrawValue(Paint paint, Canvas canvas) {
        Tool.nullPointerException(currentValue,"onDrawValue : null");
        if (currentValue == null) return;
        paint.reset();
        paint.setColor(Color.RED);
        paint.setTextSize(TWENTY_SIX);
        paint.setAntiAlias(true);
        canvas.drawBitmap(bitmap, currentValue.locationX - bitmap.getHeight(), currentValue.locationY - bitmap.getHeight(), paint);
        canvas.drawText(String.valueOf(currentValue.value), currentValue.locationX - TWENTY_SIX, currentValue.locationY - bitmap.getHeight() / 2, paint);

    }

    private void setPaintParameter(Paint paintParameter, float[] floats, int colors) {
        paintParameter.setAntiAlias(true);
        paintParameter.setStyle(PAINT_STYLE);
        paintParameter.setPathEffect(new DashPathEffect(floats, 1));
        paintParameter.setColor(colors);
    }

    private void onDrawCircle(Paint paintParameter, Canvas canvas, int position) {
        paintParameter.reset();
        paintParameter.setAntiAlias(true);
        paintParameter.setColor(Color.RED);
        canvas.drawCircle(curveStorages.get(position).locationX, curveStorages.get(position).locationY, CIRCLE, paintParameter);
    }

    private void viewValue(double locationX) {
        currentValue = Tool.getLocationValue(curveStorages, (int) locationX);
        isValue = true;
        invalidate(currentValue.locationX - bitmap.getHeight(), currentValue.locationY - bitmap.getHeight(), currentValue.locationX, currentValue.locationY);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                return true;
            case MotionEvent.ACTION_UP:
                viewValue(event.getX());
                break;
        }
        return super.onTouchEvent(event);
    }

    public void recycle() {
        if (bitmap != null) {
            bitmap.recycle();
            bitmap = null;
        }
    }

}

