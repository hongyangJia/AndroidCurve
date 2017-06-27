package curve.com;

import java.util.List;

/**
 * Created by hongyang on 17-6-21.
 */

public class Tool {

    public  static double getMax(List<DateStorage> arr) {
        double max = arr.get(0).value;
        for (int i = 1; i < arr.size(); i++) {
            if (arr.get(i).value > max) {
                max = arr.get(i).value;
            }
        }
        return max;
    }

    public static double getMin(List<DateStorage> arr) {
        double max = arr.get(0).value;
        for (int i = 1; i < arr.size(); i++) {
            if (arr.get(i).value < max) {
                max = arr.get(i).value;
            }
        }
        return max;
    }

    public static CurveStorage getMaxLocation(List<CurveStorage>curveStorages) {
        CurveStorage max = curveStorages.get(0);
        for (int i = 1; i < curveStorages.size(); i++) {
            if (curveStorages.get(i).locationY < max.locationY) {
                max = curveStorages.get(i);
            }
        }
        return max;
    }

    public static CurveStorage getLocationValue(List<CurveStorage>curveStorages,int currentLocationX) {
        double min = 500;
        double position =0;
        CurveStorage  curveStorage = new CurveStorage();
        for (int i = 0; i < curveStorages.size(); i++) {
             int x= (int) (curveStorages.get(i).locationX-currentLocationX);
                 x=Math.abs(x);
                if (x<min){
                      min =x;
                      curveStorage.locationX=curveStorages.get(i).locationX;
                      curveStorage.locationY=curveStorages.get(i).locationY;
                      curveStorage.value=curveStorages.get(i).value;
                 }
        }
        return curveStorage;
    }

    public  static   <T>void nullPointerException(T t,String message){
        if (t==null){
            throw  new NullPointerException(message);
        }
    }

}
