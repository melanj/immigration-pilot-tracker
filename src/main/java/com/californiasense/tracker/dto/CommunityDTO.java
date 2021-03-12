package com.californiasense.tracker.dto;

import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class CommunityDTO {

    private Long id;

    @Size(max = 255)
    private String name;

    private Long province;

}
