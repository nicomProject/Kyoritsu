package com.enicom.board.kyoritsu.api.param;

import com.enicom.board.kyoritsu.dao.entity.Inquiry;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Setter
public class InquiryParam {

    private String key;
    private String inquiryName;
    private String inquiryPwd;
    private String inquiryPhone;
    private String inquiryContent;
    private String inquiryTitle;
    private String answerUser;
    private String inquirySecret;

    @JsonIgnore
    public Inquiry create(){
        Inquiry inquiry = Inquiry.builder()
                .build();
        applyTo(inquiry);
        return inquiry;
    }

//
    @JsonIgnore
    public void applyTo(Inquiry inquiry) {
        if (this.inquiryPhone != null) {
            inquiry.setInquiryPhone(this.inquiryPhone);
        }
        if (this.inquiryName != null) {
            inquiry.setInquiryName(this.inquiryName);
        }
        if (this.inquiryPwd != null) {
            inquiry.setInquiryPwd(this.inquiryPwd);
        }
        if (this.inquiryContent != null) {
            inquiry.setInquiryContent(this.inquiryContent);
        }
        if (this.inquiryTitle != null) {
            inquiry.setInquiryTitle(this.inquiryTitle);
        }
        if (this.inquirySecret != null) {
            inquiry.setInquirySecret(this.inquirySecret);
        }
    }

}
