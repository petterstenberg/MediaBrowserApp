package se.petterstenberg.mediabrowserapp.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import se.petterstenberg.mediabrowserapp.models.submodels.Pagination;
import se.petterstenberg.mediabrowserapp.models.submodels.Program;

public class ProgramsResponse {

    @SerializedName("copyright")
    private String mCopyright;

    @SerializedName("pagination")
    private Pagination mPagination;

    @SerializedName("programs")
    private List<Program> mPrograms;

    public String getCopyright() {
        return mCopyright;
    }

    public Pagination getPagination() {
        return mPagination;
    }

    public List<Program> getPrograms() {
        return mPrograms;
    }

}
