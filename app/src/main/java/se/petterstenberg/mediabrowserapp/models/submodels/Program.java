
package se.petterstenberg.mediabrowserapp.models.submodels;

import com.google.gson.annotations.SerializedName;

import java.util.List;

@SuppressWarnings("unused")
public class Program {

    @SerializedName("archived")
    private Boolean mArchived;

    @SerializedName("channel")
    private Channel mChannel;

    @SerializedName("description")
    private String mDescription;

    @SerializedName("email")
    private String mEmail;

    @SerializedName("hasondemand")
    private Boolean mHasondemand;

    @SerializedName("haspod")
    private Boolean mHaspod;

    @SerializedName("id")
    private Long mId;

    @SerializedName("name")
    private String mName;

    @SerializedName("phone")
    private String mPhone;

    @SerializedName("programcategory")
    private ProgramCategory mProgramCategory;

    @SerializedName("programimage")
    private String mProgramimage;

    @SerializedName("programimagetemplate")
    private String mProgramimagetemplate;

    @SerializedName("programimagetemplatewide")
    private String mProgramimagetemplatewide;

    @SerializedName("programimagewide")
    private String mProgramimagewide;

    @SerializedName("programslug")
    private String mProgramslug;

    @SerializedName("programurl")
    private String mProgramurl;

    @SerializedName("responsibleeditor")
    private String mResponsibleeditor;

    @SerializedName("socialimage")
    private String mSocialimage;

    @SerializedName("socialimagetemplate")
    private String mSocialimagetemplate;

    @SerializedName("socialmediaplatforms")
    private List<SocialMediaPlatform> mSocialMediaPlatforms;

    public Boolean getArchived() {
        return mArchived;
    }

    public Channel getChannel() {
        return mChannel;
    }

    public String getDescription() {
        return mDescription;
    }

    public String getEmail() {
        return mEmail;
    }

    public Boolean getHasondemand() {
        return mHasondemand;
    }

    public Boolean getHaspod() {
        return mHaspod;
    }

    public Long getId() {
        return mId;
    }

    public String getName() {
        return mName;
    }

    public String getPhone() {
        return mPhone;
    }

    public ProgramCategory getProgramCategory() {
        return mProgramCategory;
    }

    public String getProgramimage() {
        return mProgramimage;
    }

    public String getProgramimagetemplate() {
        return mProgramimagetemplate;
    }

    public String getProgramimagetemplatewide() {
        return mProgramimagetemplatewide;
    }

    public String getProgramimagewide() {
        return mProgramimagewide;
    }

    public String getProgramslug() {
        return mProgramslug;
    }

    public String getProgramurl() {
        return mProgramurl;
    }

    public String getResponsibleeditor() {
        return mResponsibleeditor;
    }

    public String getSocialimage() {
        return mSocialimage;
    }

    public String getSocialimagetemplate() {
        return mSocialimagetemplate;
    }

    public List<SocialMediaPlatform> getSocialMediaPlatforms() {
        return mSocialMediaPlatforms;
    }
}
