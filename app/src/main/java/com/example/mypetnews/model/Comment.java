package com.example.mypetnews.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;
import java.util.List;

public final class Comment implements Parcelable {

    @SerializedName("author")
    @Expose
    private String author;
    @SerializedName("text")
    @Expose
    private String text;
    @SerializedName("time")
    @Expose
    private Date time;
    @SerializedName("comments")
    @Expose
    private List<Comment> comments;
    @SerializedName("commentCount")
    @Expose
    private Integer commentCount;

    private Integer margin;

    protected Comment(Parcel in) {
        author = in.readString();
        text = in.readString();
        long tmpDate = in.readLong();
        this.time = tmpDate == -1 ? null : new Date(tmpDate);
        comments = in.createTypedArrayList(Comment.CREATOR);
        if (in.readByte() == 0) {
            commentCount = null;
        } else {
            commentCount = in.readInt();
        }
        if (in.readByte() == 0) {
            margin = null;
        } else {
            margin = in.readInt();
        }
    }

    public static final Creator<Comment> CREATOR = new Creator<Comment>() {
        @Override
        public Comment createFromParcel(Parcel in) {
            return new Comment(in);
        }

        @Override
        public Comment[] newArray(int size) {
            return new Comment[size];
        }
    };

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public Integer getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(Integer commentCount) {
        this.commentCount = commentCount;
    }

    public Integer getMargin() {
        return margin;
    }

    public void setMargin(Integer margin) {
        this.margin = margin;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(author);
        parcel.writeString(text);
        parcel.writeLong(time != null ? time.getTime() : -1);
        parcel.writeTypedList(comments);
        if (commentCount == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(commentCount);
        }
        if (margin == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(margin);
        }
    }
}