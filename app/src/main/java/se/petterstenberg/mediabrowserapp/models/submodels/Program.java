
package se.petterstenberg.mediabrowserapp.models.submodels;

import com.google.gson.annotations.SerializedName;

import java.util.List;

@SuppressWarnings("unused")
public class Program {

    @SerializedName("archived")
    private boolean mArchived;

    @SerializedName("channel")
    private Channel mChannel;

    @SerializedName("description")
    private String mDescription;

    @SerializedName("email")
    private String mEmail;

    @SerializedName("hasondemand")
    private boolean mHasOnDemand;

    @SerializedName("haspod")
    private boolean mHasPod;

    @SerializedName("id")
    private long mId;

    @SerializedName("name")
    private String mName;

    @SerializedName("phone")
    private String mPhone;

    @SerializedName("programcategory")
    private ProgramCategory mProgramCategory;

    @SerializedName("programimage")
    private String mProgramImage;

    @SerializedName("programimagetemplate")
    private String mProgramImageTemplate;

    @SerializedName("programimagetemplatewide")
    private String mProgramImageTemplateWide;

    @SerializedName("programimagewide")
    private String mProgramImageWide;

    @SerializedName("programslug")
    private String mProgramSlug;

    @SerializedName("programurl")
    private String mProgramUrl;

    @SerializedName("responsibleeditor")
    private String mResponsibleEditor;

    @SerializedName("socialimage")
    private String mSocialImage;

    @SerializedName("socialimagetemplate")
    private String mSocialImageTemplate;

    @SerializedName("socialmediaplatforms")
    private List<SocialMediaPlatform> mSocialMediaPlatforms;

    public boolean isArchived() {
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

    public boolean hasOnDemand() {
        return mHasOnDemand;
    }

    public boolean hasPod() {
        return mHasPod;
    }

    public long getId() {
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

    public String getProgramImage() {
        return mProgramImage;
    }

    public String getProgramImageTemplate() {
        return mProgramImageTemplate;
    }

    public String getProgramImageTemplateWide() {
        return mProgramImageTemplateWide;
    }

    public String getProgramImageWide() {
        return mProgramImageWide;
    }

    public String getProgramSlug() {
        return mProgramSlug;
    }

    public String getProgramUrl() {
        return mProgramUrl;
    }

    public String getResponsibleEditor() {
        return mResponsibleEditor;
    }

    public String getSocialImage() {
        return mSocialImage;
    }

    public String getSocialImageTemplate() {
        return mSocialImageTemplate;
    }

    public List<SocialMediaPlatform> getSocialMediaPlatforms() {
        return mSocialMediaPlatforms;
    }
}
