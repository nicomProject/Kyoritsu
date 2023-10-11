package com.enicom.board.kyoritsu.dao.entity.admin;

import com.enicom.board.kyoritsu.dao.type.admin.MenuGroup;
import com.enicom.board.kyoritsu.dao.type.MenuTarget;
import com.enicom.board.kyoritsu.dao.type.admin.MenuType;
import com.enicom.board.kyoritsu.login.Role;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.*;
import org.hibernate.annotations.Comment;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity(name = "mn_menu")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@SequenceGenerator(name = "SEQ_MENU_GENERATOR", sequenceName = "SEQ_MENU", initialValue = 1, allocationSize = 1)
public class MenuAdmin {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_MENU_GENERATOR")
    @Column(name = "rec_key")
    private Long recKey;

    @Column(name = "code", length = 20)
    @Enumerated(EnumType.STRING)
    @Comment("메뉴 코드")
    private MenuType code;

    @Column(name = "grp", length = 20)
    @Enumerated(EnumType.STRING)
    @Comment("메뉴 그룹")
    private MenuGroup group = MenuGroup.SYSTEM;

    @Column(name = "name", length = 20)
    @Comment("메뉴명")
    private String name;

    @Column(name = "url", length = 100)
    @Comment("메뉴 URL")
    private String url;

    @Column(name = "read_role")
    @Enumerated(EnumType.STRING)
    @Comment("권한 - 조회 권한")
    private Role readRole = Role.ADMIN;

    @Column(name = "edit_role")
    @Enumerated(EnumType.STRING)
    @Comment("권한 - 수정 권한")
    private Role editRole = Role.ADMIN;

    @Column(name = "icon")
    @Comment("아이콘 Class - FontAwesome")
    private String icon;

    @Column(name = "target")
    @Builder.Default
    @Enumerated(EnumType.STRING)
    @Comment("새창 여부 - {self: 사용 안함, blank: 사용함}")
    private MenuTarget target = MenuTarget.SELF;

    @Column(name = "order_seq")
    @Builder.Default
    @Comment("메뉴 보여질 순서 설정 - 오름차순 정렬")
    private Integer order = 0;

    @Column(name = "use_yn")
    @Builder.Default
    private Integer use = 1;

    @Column(name = "create_user", length = 50)
    private String createUser;

    @Column(name = "edit_user", length = 50)
    private String editUser;

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

    public static MenuAdminBuilder builder(){
        return new MenuAdminBuilder();
    }
    public static MenuAdminBuilder builder(MenuType type){
        return builder().name(type.getName()).url(String.format("/admin/%s", type.getCode()));
    }
}