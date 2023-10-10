package com.enicom.board.kyoritsu.dao.entity;

import com.enicom.board.kyoritsu.dao.id.CodeId;
import com.enicom.board.kyoritsu.dao.type.CodeGroup;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity(name = "tb_code")
@Builder
@AllArgsConstructor
@NoArgsConstructor(force = true)
@Getter
@Setter
@ToString
@IdClass(CodeId.class)
public class Code {
    @Id
    @Column(name = "grp", length = 10)
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private CodeGroup group = CodeGroup.SYSTEM;

    @Id
    @Column(name = "code", length = 20, nullable = false)
    private String code;

    @Column(name = "value1", length = 100)
    private String value1;

    @Column(name = "value2", length = 100)
    private String value2;

    @Column(name = "value3", length = 100)
    private String value3;

    @Column(name = "use_yn")
    private Integer use_yn;

    @Column(name = "description", length = 3000)
    private String description;

    @Column(name = "create_user")
    private String createUser;

    @Column(name = "edit_user")
    private String editUser;

    @Column(name = "delete_user")
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

    public static CodeBuilder builder() {
        return new CodeBuilder();
    }

    public static CodeBuilder builder(String key, String value) {
        return builder().code(key).value1(value);
    }

    public static CodeBuilder builder(CodeGroup group, String key, String value) {
        return builder().group(group).code(key).value1(value);
    }

    public static CodeBuilder builder(CodeGroup group, String key) {
        return builder().group(group).code(key);
    }
}
