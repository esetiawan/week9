package id.ac.week10;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName="mhs")
public class Mahasiswa implements Parcelable {
    @PrimaryKey
    @ColumnInfo(name="mhs_nrp")
    private int nrp;
    @ColumnInfo(name="mhs_name")
    private String name;
    @ColumnInfo(name="mhs_major")
    private String major;
    @ColumnInfo(name="mhs_gender")
    private String gender;

    public int getNrp() {
        return nrp;
    }

    public void setNrp(int nrp) {
        this.nrp = nrp;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.nrp);
        dest.writeString(this.name);
        dest.writeString(this.major);
        dest.writeString(this.gender);
    }

    public Mahasiswa() {
    }

    protected Mahasiswa(Parcel in) {
        this.nrp = in.readInt();
        this.name = in.readString();
        this.major = in.readString();
        this.gender = in.readString();
    }

    public static final Creator<Mahasiswa> CREATOR = new Creator<Mahasiswa>() {
        @Override
        public Mahasiswa createFromParcel(Parcel source) {
            return new Mahasiswa(source);
        }

        @Override
        public Mahasiswa[] newArray(int size) {
            return new Mahasiswa[size];
        }
    };
}
