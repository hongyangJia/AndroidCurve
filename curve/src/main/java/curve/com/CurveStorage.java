package curve.com;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by hongyang on 17-6-21.
 */

public class CurveStorage implements Parcelable {

    public int locationX;
    public int locationY;
    public Double value;


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.locationX);
        dest.writeInt(this.locationY);
        dest.writeValue(this.value);
    }

    public CurveStorage() {
    }

    protected CurveStorage(Parcel in) {
        this.locationX = in.readInt();
        this.locationY = in.readInt();
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
