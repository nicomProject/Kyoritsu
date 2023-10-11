package com.enicom.board.kyoritsu.api.param;

import lombok.*;

import javax.validation.constraints.NotNull;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Setter
public class IntroductionsParam {

    private String titleValue;
    private String contentsValue;
    private String categoryValue;
    private String sub_categoryValue;

}
