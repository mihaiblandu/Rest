package com.rest.api.server.dos;

import com.rest.api.response.dos.HexColorPaginationResponse;
import com.rest.entity.HexColor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@RestController
@RequestMapping("/api/dos/v1")
public class HexColorApi {

    public static final int COLORS_SIZE = 1_000_000;

    private List<HexColor> listHexColors;

    private  String randomColor(){
        var randomInt = ThreadLocalRandom.current().nextInt(0xffffff + 1);
        return String.format("#%06x",randomInt);
    }
    public HexColorApi(){
        listHexColors = IntStream.rangeClosed(1,COLORS_SIZE).boxed().parallel().map(v->{
            var hexColor = new HexColor();
            hexColor.setId(v);
            hexColor.setHexColors(randomColor());
            return hexColor;
        }).collect(Collectors.toList());


    }
/*
    @GetMapping(value = "/random-colors", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<HexColor> randomColors ()  {
        return listHexColors;
    }
*/

    @GetMapping(value = "/random-colors-pagination", produces = MediaType.APPLICATION_JSON_VALUE)
    public HexColorPaginationResponse randomColors (@RequestParam(required = true, name = "page", defaultValue = "0" ) int page,
                                                    @Valid @Max(200) @Min(5) @RequestParam(required = false, name = "size",defaultValue = "100" ) int size)  {

        var startIndex = page * size;
        var subList = listHexColors.subList(startIndex,startIndex +size);

        var response = new HexColorPaginationResponse();
        response.setColors(subList);
        response.setSize(size);
        response.setCurrentPage(page);
        return response;
    }


}
