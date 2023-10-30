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
    private String question;
    private String answer;
    private String inquiryId;
    private String inquiryPwd;
    private String inquirySecret;
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
        if (this.title != null) {
            inquiry.setTitle(this.title);
        }
        if (this.question != null) {
            inquiry.setQuestion(this.question);
        }
        if (this.answer != null) {
            inquiry.setAnswer(this.answer);
        }
        if (this.inquiryId != null) {
            inquiry.setAnswer(this.inquiryId);
        }
        if (this.inquiryPwd != null) {
            inquiry.setAnswer(this.inquiryPwd);
        }
        if (this.inquirySecret != null) {
            inquiry.setAnswer(this.inquirySecret);
        }
        if (this.answerUser != null) {
            inquiry.setAnswer(this.answerUser);
        }
    }

}
