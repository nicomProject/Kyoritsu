package com.enicom.board.kyoritsu.dao.entity;

import lombok.*;

import javax.persistence.*;

@Entity(name = "tb_content")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@SequenceGenerator(name = "SEQ_CONTENT_GENERATOR", sequenceName = "SEQ_CONTENT", initialValue = 1, allocationSize = 1)
public class Content {
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_CONTENT_GENERATOR")
    @Column(name = "rec_key")
    private Long recKey;

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "content")
    private String content;
}
