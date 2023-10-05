package com.enicom.board.kyoritsu.api.param.type;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class PagingParam {
    @NotNull
    private int pageIdx = 1;
    @NotNull
    private int pageSize = 25;
    private String sortField;
    private String sortDir;
}
