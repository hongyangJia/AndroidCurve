package curve.com;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by hongyang on 17-6-21.
 */

public class DateStorage implements Parcelable {

    public Double value;
    public String date;


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.value);
        dest.writeString(this.date);
    }

    public DateStorage() {

    }

    protected DateStorage(Parcel in) {
        this.value = (Double) in.readValue(Double.class.getClassLoader());
        this.date = in.readString();
    }

    public static final Creator<DateStorage> CREATOR = new Creator<DateStorage>() {
        @Override
        public DateStorage createFromParcel(Parcel source) {
            return new DateStorage(source);
        }

        @Override
        public DateStorage[] newArray(int size) {
            return new DateStorage[size];
        }
    };

    @Override
    public String toString() {
        return "DateStorage{" +
                "value=" + value +
                ", date='" + date + '\'' +
                '}';
    }
}
