package com.enicom.board.kyoritsu.dao.entity;

import com.enicom.board.kyoritsu.dao.type.MenuTarget;
import com.fasterxml.jackson.annotation.JsonEnumDefaultValue;
import lombok.*;
import org.hibernate.annotations.Comment;

import javax.persistence.*;

@Entity(name = "tb_menu")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Menu {
    @Id
    @Column(name = "code", length = 20)
    private String code;

    @ManyToOne
    @JoinColumn(name = "p_code")
    @Comment("상위 메뉴")
    private Menu menu;

    @Column(name = "name", length = 20)
    private String name;

    @Column(name = "url", length = 100)
    private String url;

    @ManyToOne
    @JoinColumn(name = "co_id")
    @Comment("내용 아이디")
    private Content content;

    @Column(name = "target")
    @Builder.Default
    @Enumerated(EnumType.STRING)
    @Comment("새창 여부 - {self: 사용 안함, blank: 사용함}")
    private MenuTarget target = MenuTarget.SELF;

    @Column(name = "order_seq")
    @Builder.Default
    private Integer order = 0;

    @Column(name = "use_yn")
    @Builder.Default
    private Integer use = 1;
}