package com.enicom.board.kyoritsu.dao.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity(name = "tb_content")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@SequenceGenerator(name = "SEQ_CONTENT_GENERATOR", sequenceName = "SEQ_CONTENT", initialValue = 1, allocationSize = 1)
public class Content {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_CONTENT_GENERATOR")
    @Column(name = "rec_key")
    private Long recKey;

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "title", length = 20)
    private String title;

    @Column(name = "subtitle", length = 100)
    private String subtitle;

    @Column(name = "content", length = 10000)
    private String content;

    @Column(name = "hit")
    @Builder.Default
    @ColumnDefault("0")
    private Integer hit = 0;

    @Column(name = "create_user", length = 50)
    private String createUser;

    @Column(name = "edit_user", length = 50)
    private String editUser;

    @Column(name = "delete_user", length = 50)
    private String deleteUser;

    @Column(name = "create_date")
    @Builder.Default
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime createDate = LocalDateTime.now();

    @Column(name = "edit_date")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime editDate;

    @Column(name = "delete_date")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime deleteDate;
}
