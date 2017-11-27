
package se.petterstenberg.mediabrowserapp.models.submodels;

import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class ProgramCategory {

    @SerializedName("id")
    private Long mId;

    @SerializedName("name")
    private String mName;

    public Long getId() {
        return mId;
    }

    public String getName() {
        return mName;
    }

}
