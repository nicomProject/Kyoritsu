package com.enicom.board.kyoritsu.dao.entity;

import com.enicom.board.kyoritsu.dao.type.MenuTarget;
import com.enicom.board.kyoritsu.dao.type.MenuType;
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

@Entity(name = "tb_menu")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@SequenceGenerator(name = "SEQ_MENU_GENERATOR", sequenceName = "SEQ_MENU", initialValue = 1, allocationSize = 1)
public class MainMenu {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_MENU_GENERATOR")
    @Column(name = "rec_key")
    private Long recKey;

    @Column(name = "name", length = 20)
    private String name;

    @Column(name = "url", length = 100)
    private String url;

    @JoinColumn(name = "p_id")
    @Comment("메뉴 parent")
    @ManyToOne
    private MainMenu menu;

    @Column(name = "type", length = 10, nullable = false)
    @Builder.Default
    @Enumerated(EnumType.STRING)
    @Comment("메뉴 타입 - { intro: 소개페이지, group: 메뉴 그룹, genernal: 일반 }")
    private MenuType type = MenuType.INTRO;

    @JoinColumn(name = "content_id")
    @ManyToOne
    private Content content;

    @Column(name = "target", length = 10, nullable = false)
    @Builder.Default
    @Enumerated(EnumType.STRING)
    @Comment("새창 여부 - {self: 사용 안함, blank: 사용함}")
    private MenuTarget target = MenuTarget.SELF;

    @Column(name = "order_seq")
    @Builder.Default
    @ColumnDefault("0")
    @Comment("메뉴 보여질 순서 설정 - 오름차순 정렬")
    private Integer order = 0;

    @Column(name = "use_yn")
    @Builder.Default
    @ColumnDefault("1")
    private Integer use = 1;

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