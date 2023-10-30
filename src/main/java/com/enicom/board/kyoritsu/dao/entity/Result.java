package com.enicom.board.kyoritsu.dao.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity(name = "tb_result")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor(force = true)
@Getter
@SequenceGenerator(name = "SEQ_RESULT_GENERATOR", sequenceName = "SEQ_RESULT", initialValue = 1, allocationSize = 1)
public class Result {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_RESULT_GENERATOR")
    @Column(name = "rec_key")
    private Long recKey;

    @Column(name = "category", length = 20)
    private String category;

    @Column(name = "template_id", length = 100)
    private String templateId;

    @Column(name = "template_content", length = 1000)
    private String templateContent;

}
