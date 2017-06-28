package curve.com;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hongyang on 17-6-22.
 */

public class GraphView extends LinearLayout {

    private static final int INTERVAL = 5;
    private static final int ROW = 5;
    private static final int DUFULET = 0;
    private static final int SCREEN_WIDTH_ALLOCATION = 11;
    private int dayWidth;
    private int dayHeight;
    private int dayAllHeight;
    private int dayWidthLift;
    private int dayHeightTop;
    private double low = 0;
    private double high = 0;
    private int circle = 10;


    private List<DateStorage> dateStorages;
    private List<CurveStorage> curveStorages;
    private List<CurveStorage> annotateStorages;
    private LinearLayout linearLayout;
    private NormalView normalView;
    private HorizontalScrollView horizontalScrollView;
    private CurveView curveView;

    public GraphView(Context context) {
        super(context);
        this.initMetrics();
        this.init();
    }

    public GraphView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.initMetrics();
        this.init();
    }

    private  void init(){
        setOrientation(HORIZONTAL);
        curveView = new CurveView(getContext());
        horizontalScrollView = new HorizontalScrollView(getContext());
        horizontalScrollView.setHorizontalScrollBarEnabled(false);
        linearLayout = new LinearLayout(getContext());
        horizontalScrollView.addView(linearLayout);
        normalView = new NormalView(getContext());
        addView(normalView,new ViewGroup.LayoutParams(dayWidth+30,dayHeight*7));
        addView(horizontalScrollView);
    }

    private   void scrollTo(final int x){
        Log.e("scrollTo",x+"");
        horizontalScrollView.post(new Runnable() {
            @Override
            public void run() {
                Log.e("scrollTo",x+"run");
                horizontalScrollView.scrollTo(x,0);
            }
        });
    }

    private void allocation() {
        int max = (int) Tool.getMax(dateStorages);
        int min = (int) Tool.getMin(dateStorages);
        low = min - INTERVAL;
        high = max + INTERVAL;
    }

    public void setDateStorage(List<DateStorage> dateStorages) {
        this.dateStorages = dateStorages;
        this.allocation();
        this.converter();
        this.converterAnnotate();
        this.linearLayout.addView(curveView,new ViewGroup.LayoutParams(dateStorages.size()<10?10*dayWidth:dateStorages.size()*dayWidth,dayHeight*6));
        this.curveView.setDateStorage(dateStorages,curveStorages,dayWidth,dayHeight,dayAllHeight,dayWidthLift,dayHeightTop,circle);
        this.normalView.setNormalStorage(annotateStorages);
        scrollTo(dateStorages.size()<10?0:dateStorages.size()*dayWidth);
    }

    private void converter() {
        curveStorages = new ArrayList<>();
        double number = dayAllHeight  / (high - low);
        CurveStorage curveStorage;
        for (int i = DUFULET; i < dateStorages.size(); i++) {
            curveStorage = new CurveStorage();
            curveStorage.locationX =  (double) dayWidthLift+ dayWidth * i;
            curveStorage.locationY = (dayAllHeight- (dateStorages.get(i).value - low) * number)+dayHeightTop;
            curveStorage.value = dateStorages.get(i).value;
            curveStorages.add(curveStorage);
        }
    }

    private void converterAnnotate(){
        annotateStorages = new ArrayList<>();
        CurveStorage curveStorage;
        for (int i = 0; i < ROW+1; i++) {
            curveStorage = new CurveStorage();
            curveStorage.locationX=(double)dayWidth/ ROW+1;
            curveStorage.locationY=(double)dayHeight * i+ 15+dayHeightTop;
            curveStorage.value=  (int)high - i * ((high - low) / ROW);
            annotateStorages.add(curveStorage);
        }
    }

    private void initMetrics() {
        Activity activity = (Activity) getContext();
        DisplayMetrics metrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int widthPixels = metrics.widthPixels;
        int heightPixels = metrics.heightPixels;
        dayWidth = widthPixels / SCREEN_WIDTH_ALLOCATION;
        dayHeight = (int) (heightPixels * 0.3 / ROW);
        dayAllHeight =dayHeight*ROW;
        dayWidthLift = (int) (dayWidth*0.6);
        dayHeightTop=dayHeight/2;
        circle=metrics.widthPixels>=1080?10:8;
    }

    private void clear(List<?> objects){
        if (objects!=null){
            objects.clear();
        }
        objects=null;
    }

    public void recycle() {
        if (curveView != null) {
            curveView.recycle();
            curveView=null;
        }
         this.normalView=null;
         clear(dateStorages);
         clear(curveStorages);
         clear(annotateStorages);
    }
}
