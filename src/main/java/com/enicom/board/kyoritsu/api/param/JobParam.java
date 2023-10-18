package com.enicom.board.kyoritsu.api.param;

import com.enicom.board.kyoritsu.dao.entity.Job;
import com.enicom.board.kyoritsu.dao.entity.Notice;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Setter
public class JobParam {

    private String title;
    private String contents;
    private String category;
    private String key;
    private String date_to;
    private String date_from;

    @JsonIgnore
    public Job create(){
        Job job = Job.builder()
                .build();
        applyTo(job);
        return job;
    }

//
    @JsonIgnore
    public void applyTo(Job job) {


        if (this.title != null) {
            job.setTitle(this.title);
        }
        if (this.contents != null) {
            job.setContent(this.contents);
        }
        if (this.category != null) {
            job.setCategory(this.category);
        }
//        if (this.date_to != null) {
//            job.setToDate(this.date_to);
//        }
//        if (this.date_from != null) {
//            job.setFromDate(this.date_from);
//        }
    }

}
