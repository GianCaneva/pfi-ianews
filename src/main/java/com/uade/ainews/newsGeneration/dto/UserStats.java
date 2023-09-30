package com.uade.ainews.newsGeneration.dto;



import lombok.*;

import java.math.BigDecimal;
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserStats {
    private Integer politicsInterest;
    private BigDecimal politicsTime;
    private Integer economyInterest;
    private BigDecimal economyTime;
    private Integer sportsInterest;
    private BigDecimal sportsTime;
    private Integer socialInterest;
    private BigDecimal socialTime;
    private Integer internationalInterest;
    private BigDecimal internationalTime;
    private Integer policeInterest;
    private BigDecimal policeTime;
}
