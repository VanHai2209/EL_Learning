package com.example.myapplication.model;

import android.os.Parcel;
import android.os.Parcelable;

public class WordData implements Parcelable {
    protected WordData(Parcel in) {
        // Đọc dữ liệu từ Parcel vào các trường của WordData
        id = in.readString();
        en = in.readString();
        vn = in.readString();
        type = in.readString();
        IPA = in.readString();
        example = in.readString();
        image = in.readString();
        audio = in.readString();
        idTopic = in.readString();
    }

    public static final Creator<WordData> CREATOR = new Creator<WordData>() {
        @Override
        public WordData createFromParcel(Parcel in) {
            return new WordData(in);
        }

        @Override
        public WordData[] newArray(int size) {
            return new WordData[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        // Ghi dữ liệu từ các trường của WordData vào Parcel
        dest.writeString(id);
        dest.writeString(en);
        dest.writeString(vn);
        dest.writeString(type);
        dest.writeString(IPA);
        dest.writeString(example);
        dest.writeString(image);
        dest.writeString(audio);
        dest.writeString(idTopic);
    }
    private String id;
    private String en;
    private String vn;
    private String type;
    private String IPA;
    private String example;
    private String image;
    private String audio;
    private String idTopic;

    public WordData(String id, String en, String vn, String type, String IPA, String example, String image, String audio, String idTopic) {
        this.id = id;
        this.en = en;
        this.vn = vn;
        this.type = type;
        this.IPA = IPA;
        this.example = example;
        this.image = image;
        this.audio = audio;
        this.idTopic = idTopic;
    }

    public String getId() {
        return id;
    }

    public String getEn() {
        return en;
    }

    public String getVn() {
        return vn;
    }

    public String getType() {
        return type;
    }

    public String getIPA() {
        return IPA;
    }

    public String getExample() {
        return example;
    }

    public String getImage() {
        return image;
    }

    public String getAudio() {
        return audio;
    }

    public String getIdTopic() {
        return idTopic;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setEn(String en) {
        this.en = en;
    }

    public void setVn(String vn) {
        this.vn = vn;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setIPA(String IPA) {
        this.IPA = IPA;
    }

    public void setExample(String example) {
        this.example = example;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setAudio(String audio) {
        this.audio = audio;
    }

    public void setIdTopic(String idTopic) {
        this.idTopic = idTopic;
    }
}
