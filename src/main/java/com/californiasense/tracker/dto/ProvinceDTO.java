package com.californiasense.tracker.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class ProvinceDTO {

    private Long id;

    @NotNull
    @Size(max = 255)
    private String name;

    @NotNull
    @Size(max = 2)
    private String postalAbbr;

    @NotNull
    @Size(max = 6)
    private String isoCode;

    @NotNull
    @Size(max = 255)
    private String capital;

}
