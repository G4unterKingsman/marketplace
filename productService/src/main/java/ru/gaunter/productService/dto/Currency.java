package ru.gaunter.productService.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Currency {

    @JsonProperty("ID")
    private String id;

    @JsonProperty("NumCode")
    private String numCode;

    @JsonProperty("CharCode")
    private String charCode;

    @JsonProperty("Nominal")
    private Integer nominal;

    @JsonProperty("Name")
    private String name;

    @JsonProperty("Value")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private BigDecimal value;

    @JsonProperty("Previous")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private BigDecimal previous;

    /*
    "ID": "R01010",
            "NumCode": "036",
            "CharCode": "AUD",
            "Nominal": 1,
            "Name": "Австралийский доллар",
            "Value": 51.1295,
            "Previous": 51.4582
     */
}
