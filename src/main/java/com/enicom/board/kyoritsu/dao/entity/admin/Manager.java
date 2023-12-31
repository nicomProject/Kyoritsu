package com.enicom.board.kyoritsu.dao.entity.admin;

import com.enicom.board.kyoritsu.login.MemberDetail;
import com.enicom.board.kyoritsu.login.Role;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity(name = "mn_user")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor(force = true)
@Getter
@SequenceGenerator(name = "SEQ_MANAGER_GENERATOR", sequenceName = "SEQ_MANAGER", initialValue = 1, allocationSize = 1)
public class Manager {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_MANAGER_GENERATOR")
    @Column(name = "rec_key")
    private Long recKey;


    @Column(name = "id", length = 20, unique = true, nullable = false)
    @NonNull
    private String userId;

    @Column(name = "password", length = 200, nullable = false)
    private String password;

    @Column(name = "name", length = 20)
    private String name;

    @Builder.Default
    @Enumerated(EnumType.STRING)
    @Column(name = "role", length = 10)
    private Role role = Role.ADMIN;

    @Builder.Default
    @Column(name = "enable")
    private Integer enable = 1;

    @Builder.Default
    @Column(name = "failure_cnt")
    private Integer failureCnt = 0;

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

    @Column(name = "login_date")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime loginDate;

    public MemberDetail toMember() {
        MemberDetail member = MemberDetail.builder().id(userId).password(password).build();
        if (name != null) {
            member.setName(name);
        }
        if (role != null) {
            member.setRole(role);
        }
        if (enable != null) {
            member.setEnable(enable);
        }
        return member;
    }
}
