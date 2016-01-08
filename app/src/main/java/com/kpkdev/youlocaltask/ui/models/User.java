
package com.kpkdev.youlocaltask.ui.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class User implements Serializable {

    @SerializedName("radius")
    @Expose
    private Integer radius;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("success")
    @Expose
    private String success;

    @SerializedName("gender")
    @Expose
    private Integer gender;

    @SerializedName("aboutMe")
    @Expose
    private String aboutMe;

    @SerializedName("ages")
    @Expose
    private Integer ages;

    @SerializedName("fullname")
    @Expose
    private String fullname;

    @SerializedName("longitude")
    @Expose
    private Double longitude;

    @SerializedName("lastUpdateDate")
    @Expose
    private String lastUpdateDate;

    @SerializedName("token")
    @Expose
    private String token;

    @SerializedName("birthday")
    @Expose
    private String birthday;

    @SerializedName("locationId")
    @Expose
    private String locationId;

    @SerializedName("birthday_public")
    @Expose
    private Boolean birthdayPublic;

    @SerializedName("latitude")
    @Expose
    private Double latitude;

    @SerializedName("phonenumber")
    @Expose
    private String phonenumber;

    @SerializedName("avatarOriginal")
    @Expose
    private String avatarOriginal;

    @SerializedName("email")
    @Expose
    private String email;

    @SerializedName("userId")
    @Expose
    private String userId;

    @SerializedName("avatar")
    @Expose
    private String avatar;

    /**
     * No args constructor for use in serialization
     *
     */
    public User() {
    }

    /**
     *
     * @param phonenumber
     * @param birthday
     * @param locationId
     * @param avatarOriginal
     * @param birthdayPublic
     * @param avatar
     * @param aboutMe
     * @param lastUpdateDate
     * @param email
     * @param token
     * @param userId
     * @param name
     * @param gender
     * @param radius
     * @param longitude
     * @param latitude
     * @param success
     * @param fullname
     * @param ages
     */
    public User(Integer radius, String name, String success, Integer gender, String aboutMe, Integer ages, String fullname, Double longitude, String lastUpdateDate, String token, String birthday, String locationId, Boolean birthdayPublic, Double latitude, String phonenumber, String avatarOriginal, String email, String userId, String avatar) {
        this.radius = radius;
        this.name = name;
        this.success = success;
        this.gender = gender;
        this.aboutMe = aboutMe;
        this.ages = ages;
        this.fullname = fullname;
        this.longitude = longitude;
        this.lastUpdateDate = lastUpdateDate;
        this.token = token;
        this.birthday = birthday;
        this.locationId = locationId;
        this.birthdayPublic = birthdayPublic;
        this.latitude = latitude;
        this.phonenumber = phonenumber;
        this.avatarOriginal = avatarOriginal;
        this.email = email;
        this.userId = userId;
        this.avatar = avatar;
    }


    /**
     *
     * @return
     *     The radius
     */
    public Integer getRadius() {
        return radius;
    }

    /**
     *
     * @param radius
     *     The radius
     */
    public void setRadius(Integer radius) {
        this.radius = radius;
    }

    /**
     *
     * @return
     *     The name
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name
     *     The name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return
     *     The success
     */
    public String getSuccess() {
        return success;
    }

    /**
     *
     * @param success
     *     The success
     */
    public void setSuccess(String success) {
        this.success = success;
    }

    /**
     *
     * @return
     *     The gender
     */
    public Integer getGender() {
        return gender;
    }

    /**
     *
     * @param gender
     *     The gender
     */
    public void setGender(Integer gender) {
        this.gender = gender;
    }

    /**
     *
     * @return
     *     The aboutMe
     */
    public String getAboutMe() {
        return aboutMe;
    }

    /**
     *
     * @param aboutMe
     *     The aboutMe
     */
    public void setAboutMe(String aboutMe) {
        this.aboutMe = aboutMe;
    }

    /**
     *
     * @return
     *     The ages
     */
    public Integer getAges() {
        return ages;
    }

    /**
     *
     * @param ages
     *     The ages
     */
    public void setAges(Integer ages) {
        this.ages = ages;
    }

    /**
     *
     * @return
     *     The fullname
     */
    public String getFullname() {
        return fullname;
    }

    /**
     *
     * @param fullname
     *     The fullname
     */
    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    /**
     *
     * @return
     *     The longitude
     */
    public Double getLongitude() {
        return longitude;
    }

    /**
     *
     * @param longitude
     *     The longitude
     */
    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    /**
     *
     * @return
     *     The lastUpdateDate
     */
    public String getLastUpdateDate() {
        return lastUpdateDate;
    }

    /**
     *
     * @param lastUpdateDate
     *     The lastUpdateDate
     */
    public void setLastUpdateDate(String lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    /**
     *
     * @return
     *     The token
     */
    public String getToken() {
        return token;
    }

    /**
     *
     * @param token
     *     The token
     */
    public void setToken(String token) {
        this.token = token;
    }

    /**
     *
     * @return
     *     The birthday
     */
    public String getBirthday() {
        return birthday;
    }

    /**
     *
     * @param birthday
     *     The birthday
     */
    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    /**
     *
     * @return
     *     The locationId
     */
    public String getLocationId() {
        return locationId;
    }

    /**
     *
     * @param locationId
     *     The locationId
     */
    public void setLocationId(String locationId) {
        this.locationId = locationId;
    }

    /**
     *
     * @return
     *     The birthdayPublic
     */
    public Boolean getBirthdayPublic() {
        return birthdayPublic;
    }

    /**
     *
     * @param birthdayPublic
     *     The birthday_public
     */
    public void setBirthdayPublic(Boolean birthdayPublic) {
        this.birthdayPublic = birthdayPublic;
    }

    /**
     *
     * @return
     *     The latitude
     */
    public Double getLatitude() {
        return latitude;
    }

    /**
     *
     * @param latitude
     *     The latitude
     */
    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    /**
     *
     * @return
     *     The phonenumber
     */
    public String getPhonenumber() {
        return phonenumber;
    }

    /**
     *
     * @param phonenumber
     *     The phonenumber
     */
    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    /**
     *
     * @return
     *     The avatarOriginal
     */
    public String getAvatarOriginal() {
        return avatarOriginal;
    }

    /**
     *
     * @param avatarOriginal
     *     The avatarOriginal
     */
    public void setAvatarOriginal(String avatarOriginal) {
        this.avatarOriginal = avatarOriginal;
    }

    /**
     *
     * @return
     *     The email
     */
    public String getEmail() {
        return email;
    }

    /**
     *
     * @param email
     *     The email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     *
     * @return
     *     The userId
     */
    public String getUserId() {
        return userId;
    }

    /**
     *
     * @param userId
     *     The userId
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     *
     * @return
     *     The avatar
     */
    public String getAvatar() {
        return avatar;
    }

    /**
     *
     * @param avatar
     *     The avatar
     */
    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

}
