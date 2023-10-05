package com.enicom.board.kyoritsu.dao.id;

import com.enicom.board.kyoritsu.dao.type.CodeGroup;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import java.io.Serializable;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class CodeId implements Serializable {
    @Id
    @Column(name = "grp", length = 10)
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private CodeGroup group = CodeGroup.SYSTEM;

    @Id
    @NonNull
    @Column(name = "code", length = 20, nullable = false)
    private String code;
}
