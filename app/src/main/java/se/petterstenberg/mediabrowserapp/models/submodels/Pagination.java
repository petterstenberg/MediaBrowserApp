package se.petterstenberg.mediabrowserapp.models.submodels;

import com.google.gson.annotations.SerializedName;


@SuppressWarnings("unused")
public class Pagination {

    @SerializedName("nextpage")
    private String mNextPageUrl;

    @SerializedName("page")
    private int mPage;

    @SerializedName("size")
    private int mSize;

    @SerializedName("totalhits")
    private int mTotalHits;

    @SerializedName("totalpages")
    private int mTotalPages;

    public String getNextPageUrl() {
        return mNextPageUrl;
    }

    public int getPage() {
        return mPage;
    }

    public int getSize() {
        return mSize;
    }

    public int getTotalHits() {
        return mTotalHits;
    }

    public int getTotalPages() {
        return mTotalPages;
    }
}
