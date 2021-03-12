package com.californiasense.tracker.dto;

import java.util.List;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class PilotDTO {

    private Long id;

    @Size(max = 255)
    private String name;

    private Long communities;

    private List<Long> provinces;

}
