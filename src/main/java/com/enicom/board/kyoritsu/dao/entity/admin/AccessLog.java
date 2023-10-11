package com.enicom.board.kyoritsu.dao.entity.admin;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.*;
import org.hibernate.annotations.Comment;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity(name = "mn_access_log")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor(force = true)
@Getter
@SequenceGenerator(name = "SEQ_ACCESS_GENERATOR", sequenceName = "SEQ_ACCESS", initialValue = 1, allocationSize = 1)
public class AccessLog {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_ACCESS_GENERATOR")
    @Column(name = "rec_key")
    private Long recKey;

    @Column(name = "ip", length = 20)
    private String ip;

    @Column(name = "login_id", length = 20)
    private String loginId;
    
    @Column(name = "login_result")
    @Builder.Default
    @Comment("로그인 성공 여부 {1: 성공, 0: 실패}")
    private Integer loginResult = 1;

    @Column(name = "login_date")
    @Builder.Default
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime loginDate = LocalDateTime.now();
}
