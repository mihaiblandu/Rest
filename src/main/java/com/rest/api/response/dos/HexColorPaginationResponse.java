package com.rest.api.response.dos;

import com.rest.entity.HexColor;
import lombok.Data;

import java.util.List;


@Data
public class HexColorPaginationResponse {
    private List<HexColor> colors;
    private int currentPage;
    private int size;
}
