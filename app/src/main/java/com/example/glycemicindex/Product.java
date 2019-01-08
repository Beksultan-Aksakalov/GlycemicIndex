package com.example.glycemicindex;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Comparator;

public class Product implements Parcelable {

    int id;
    String name;
    String index;

    public Product(int id, String name, String index) {
        this.id = id;
        this.name = name;
        this.index = index;
    }


    protected Product(Parcel in) {
        id = in.readInt();
        name = in.readString();
        index = in.readString();
    }

    public static final Creator<Product> CREATOR = new Creator<Product>() {
        @Override
        public Product createFromParcel(Parcel in) {
            return new Product(in);
        }

        @Override
        public Product[] newArray(int size) {
            return new Product[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public static final Comparator<Product> sortByName = (o1, o2) -> o1.name.compareTo(o2.name);

    public static final Comparator<Product> sortByIndex = ((o1, o2) -> o1.index.compareTo(o2.index));

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeString(index);
    }
}
