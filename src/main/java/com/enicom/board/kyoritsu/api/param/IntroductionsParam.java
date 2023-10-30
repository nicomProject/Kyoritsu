package com.enicom.board.kyoritsu.api.param;

import com.enicom.board.kyoritsu.dao.entity.Content;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Setter
public class IntroductionsParam {

    private String title;
    private String contents;
    private String category;
    private String sub_category;
    private String key;
    private String sub_title;

    @JsonIgnore
    public Content create(){
        Content content = Content.builder()
                .build();

        applyTo(content);
        return content;
    }

    @JsonIgnore
    public void applyTo(Content content) {
        if (this.title != null) {
            content.setTitle(this.title);
        }
        if (this.contents != null) {
            content.setContent(this.contents);
        }
        if (this.category != null) {
            content.setCategory(this.category);
        }
        if (this.sub_category != null) {
            content.setSubcategory(this.sub_category);
        }
        if (this.key != null) {
            content.setRecKey(Long.valueOf(this.key));
        }
        if (this.sub_title != null) {
            content.setSubtitle(this.sub_title);
        }
    }

}
