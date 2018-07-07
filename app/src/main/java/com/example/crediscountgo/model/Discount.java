package com.example.crediscountgo.model;

public class Discount extends Surprise {

    public String getQrCodeContent() {
        return qrCodeContent;
    }

    public void setQrCodeContent(String qrCodeContent) {
        this.qrCodeContent = qrCodeContent;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }


    public int getImgId() {
        return imgId;
    }

    public void setImgId(int imgId) {
        this.imgId = imgId;
    }

    private int imgId;
    private String qrCodeContent;

    public int getQrCodeId() {
        return qrCodeId;
    }

    public void setQrCodeId(int qrCodeId) {
        this.qrCodeId = qrCodeId;
    }

    private int qrCodeId;
    private String title;
    private String message;
    private String startDate;
    private String endDate;

}
