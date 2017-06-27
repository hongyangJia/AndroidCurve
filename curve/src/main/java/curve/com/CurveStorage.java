package curve.com;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by hongyang on 17-6-21.
 */

public class CurveStorage implements Parcelable {

    public Double locationX;
    public Double locationY;
    public Double value;


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.locationX);
        dest.writeValue(this.locationY);
        dest.writeValue(this.value);
    }

    public CurveStorage() {
    }

    protected CurveStorage(Parcel in) {
        this.locationX = (Double) in.readValue(Double.class.getClassLoader());
        this.locationY = (Double) in.readValue(Double.class.getClassLoader());
        this.value = (Double) in.readValue(Double.class.getClassLoader());
    }

    public static final Creator<CurveStorage> CREATOR = new Creator<CurveStorage>() {
        @Override
        public CurveStorage createFromParcel(Parcel source) {
            return new CurveStorage(source);
        }

        @Override
        public CurveStorage[] newArray(int size) {
            return new CurveStorage[size];
        }
    };
}
