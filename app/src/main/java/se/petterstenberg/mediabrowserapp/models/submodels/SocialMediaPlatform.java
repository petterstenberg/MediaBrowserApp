
package se.petterstenberg.mediabrowserapp.models.submodels;

import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class SocialMediaPlatform {

    @SerializedName("platform")
    private String mPlatform;

    @SerializedName("platformurl")
    private String mPlatformurl;

    public String getPlatform() {
        return mPlatform;
    }

    public String getPlatformurl() {
        return mPlatformurl;
    }

}
