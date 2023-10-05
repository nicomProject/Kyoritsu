package com.enicom.board.kyoritsu.dao.entity;

import com.enicom.board.kyoritsu.dao.type.MenuTarget;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.*;
import org.hibernate.annotations.Comment;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity(name = "tb_menu")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@SequenceGenerator(name = "SEQ_MENU_GENERATOR", sequenceName = "SEQ_MENU", initialValue = 1, allocationSize = 1)
public class Menu {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_MENU_GENERATOR")
    @Column(name = "rec_key")
    private Long recKey;

    @Column(name = "name", length = 20)
    private String name;

    @Column(name = "url", length = 100)
    private String url;

    @Column(name = "thumbnail_url", length = 100)
    private String thumbnailUrl;

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

    @Builder.Default
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime createDate = LocalDateTime.now();

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime updateDate;
}