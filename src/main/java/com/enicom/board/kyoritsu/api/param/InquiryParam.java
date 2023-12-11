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

    private String title;
    private String key;
    private String inquiryName;
    private String inquiryPwd;
    private String inquirySecret;
    private String inquiryPhone;
    private String inquiryContent;
    private String answerUser;

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
            inquiry.setTitle(this.inquiryPhone);
        }
        if (this.inquiryName != null) {
            inquiry.setAnswer(this.inquiryName);
        }
        if (this.inquiryPwd != null) {
            inquiry.setAnswer(this.inquiryPwd);
        }
        if (this.inquiryContent != null) {
            inquiry.setAnswer(this.inquiryContent);
        }
    }

}
