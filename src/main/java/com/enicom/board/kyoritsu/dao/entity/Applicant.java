package com.enicom.board.kyoritsu.dao.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Comment;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity(name = "tb_applicant")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor(force = true)
@Getter
@SequenceGenerator(name = "SEQ_APPLICANT_GENERATOR", sequenceName = "SEQ_APPLICANT", initialValue = 1, allocationSize = 1)
public class Applicant {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_APPLICANT_GENERATOR")
    @Column(name = "rec_key")
    private Long recKey;

    @Column(name = "category", length = 20)
    @Comment("채용공고 카테고리")
    private String category;

    @Column(name = "career", length = 100)
    @Comment("경력구분")
    private String career;

    @Column(name = "image", length = 1000)
    @Comment("증명사진")
    private String image;

    @Column(name = "name", length = 50)
    @Comment("지원자명")
    private String name;

    @Column(name = "gender", length = 50)
    @Comment("성별")
    private String gender;

    @Column(name = "birth_date")
    @Comment("생년월일")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime birthDate;

    @Column(name = "nationality", length = 50)
    @Comment("국적")
    private String nationality;

    @Column(name = "phone", length = 50)
    @Comment("휴대전화")
    private String phone;

    @Column(name = "email", length = 50)
    @Comment("이메일")
    private String email;

    @Column(name = "education", length = 500)
    @Comment("최종학력")
    private String education;

    @Column(name = "certificate", length = 500)
    @Comment("자격사항")
    private String certificate;

    @Column(name = "contents", length = 10000)
    @Comment("자기소개서")
    private String contents;

    @Column(name = "veteran_status", length = 50)
    @Comment("보훈여부")
    private String veteranStatus;

    @Column(name = "disability", length = 50)
    @Comment("장애여부")
    private String disability;

    @Column(name = "military_status", length = 50)
    @Comment("병역여부")
    private String militaryStatus;

    @Column(name = "military_category", length = 100)
    @Comment("병역구분")
    private String militaryCategory;

    @Column(name = "military_start_date")
    @Comment("입대일자")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime militaryStartDate;

    @Column(name = "military_end_date")
    @Comment("전역일자")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime militaryEndDate;

    @JoinColumn(name = "result_id")
    @ManyToOne
    private Content resultId;


}
