package com.enicom.board.kyoritsu.dao.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.*;
import org.hibernate.annotations.Comment;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity(name = "tb_inquiry")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor(force = true)
@Getter
@SequenceGenerator(name = "SEQ_INQUIRY_GENERATOR", sequenceName = "SEQ_INQUIRY", initialValue = 1, allocationSize = 1)
public class Inquiry {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_INQUIRY_GENERATOR")
    @Column(name = "rec_key")
    private Long recKey;

    @Column(name = "title", length = 100)
    private String title;

    @Column(name = "answer", length = 10000)
    private String answer;

    @Column(name = "inquiry_name", length = 20, nullable = false)
    @Comment("문의 작성한 사용자 아이디")
    private String inquiryName;

    @Column(name = "inquiry_pwd", length = 200, nullable = false)
    @Comment("문의 작성한 사용자 비밀번호")
    private String inquiryPwd;

    @Column(name = "inquiry_phone", length = 200, nullable = false)
    @Comment("문의 작성한 사용자 핸드폰번호")
    private String inquiryPhone;

    @Column(name = "inquiry_content", length = 10000)
    @Comment("문의 작성한 내용")
    private String inquiryContent;

    @Column(name = "inquiry_secret")
    @Comment("문의 공개 여부")
    @Builder.Default
    private Integer inquirySecret = 1;

    @Column(name = "answer_user", length = 50)
    private String answerUser;

    @Column(name = "delete_user", length = 50)
    private String deleteUser;

    @Column(name = "create_date")
    @Builder.Default
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime createDate = LocalDateTime.now();

    @Column(name = "answer_date")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime answerDate;

    @Column(name = "delete_date")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime deleteDate;
}
