package com.openclassrooms.entrevoisins.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.util.Objects;

/**
 * Model object representing a Neighbour
 */
public class Neighbour implements Parcelable {

    /** Identifier */
    private long id;

    /** Full name */
    private String name;

    /** Avatar */
    private String avatarUrl;

    /** Adress */
    private String address;

    /** Phone number */
    private String phoneNumber;

    /** About me */
    private String aboutMe;

    /** Is Favorite*/
    private boolean isFavorite;

    /**
     * Constructor
     * @param id
     * @param name
     * @param avatarUrl
     */
    public Neighbour(long id, String name, String avatarUrl, String address,
                     String phoneNumber, String aboutMe, Boolean isFavorite) {
        this.id = id;
        this.name = name;
        this.avatarUrl = avatarUrl;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.aboutMe = aboutMe;
        this.isFavorite = isFavorite;
    }

    /**
     * implementation parcelable part 1
     * @param in
     */
    protected Neighbour(Parcel in) {
        id = in.readLong();
        name = in.readString();
        avatarUrl = in.readString();
        address = in.readString();
        phoneNumber = in.readString();
        aboutMe = in.readString();
        isFavorite = in.readInt() == 1;

    }
    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeLong(id);
        parcel.writeString(name);
        parcel.writeString(avatarUrl);
        parcel.writeString(address);
        parcel.writeString(phoneNumber);
        parcel.writeString(aboutMe);
        parcel.writeInt(isFavorite ? 1 : 0);

    }

    /**
     * implementation parcelable part Creator
     */
    public static final Creator<Neighbour> CREATOR = new Creator<Neighbour>() {
        @Override
        public Neighbour createFromParcel(Parcel in) {
            return new Neighbour(in);
        }

        @Override
        public Neighbour[] newArray(int size) {
            return new Neighbour[size];
        }
    };

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAboutMe() {
        return aboutMe;
    }

    public void setAboutMe(String aboutMe) {
        this.aboutMe = aboutMe;
    }

    public boolean getIsFavorite() {return isFavorite;}

    public void setIsFavorite(boolean isFavorite) {this.isFavorite = isFavorite;}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Neighbour neighbour = (Neighbour) o;
        return Objects.equals(id, neighbour.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    /**
     * Implementation parcelable part 2
     * @param parcel
     * @param i
     */

}
