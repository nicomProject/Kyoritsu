package com.enicom.board.kyoritsu.dao.entity;

import com.enicom.board.kyoritsu.dao.id.CodeId;
import com.enicom.board.kyoritsu.dao.type.CodeGroup;
import lombok.*;

import javax.persistence.*;

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
