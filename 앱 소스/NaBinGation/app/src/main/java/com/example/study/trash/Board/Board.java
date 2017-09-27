package com.example.study.trash.Board;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by KwakSeYoun on 2015. 10. 25..
 */
public class Board implements Parcelable {
    private int board_id;
    private String writer;
    private String title;
    private String content;
    private String regdate;
    private int status;
    private int location;

    public Board() {
    }

    public Board(Parcel source) {
        board_id = source.readInt();
        title = source.readString();
        writer = source.readString();
        content = source.readString();
        regdate = source.readString();
        status = source.readInt();
        location = source.readInt();
    }

    public int getBoard_id() {
        return board_id;
    }

    public void setBoard_id(int board_id) {
        this.board_id = board_id;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getRegdate() {
        return regdate;
    }

    public void setRegdate(String regdate) {
        this.regdate = regdate;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getLocation() {
        return location;
    }

    public void setLocation(int location) {
        this.location = location;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeInt(board_id);
        parcel.writeString(title);
        parcel.writeString(writer);
        parcel.writeString(content);
        parcel.writeString(regdate);
        parcel.writeInt(status);
        parcel.writeInt(location);
    }

    public static Parcelable.Creator<Board> CREATOR = new Parcelable.Creator<Board>() {
        @Override
        public Board createFromParcel(Parcel source) {
            return new Board(source);
        }

        @Override
        public Board[] newArray(int size) {
            return new Board[size];
        }
    };
}
