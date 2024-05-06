package legoset.model;

import java.time.Year;


import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

import jpa.YearConverter;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class LegoSet {

    @Id
    private String number;

    @Column(nullable=false)
    private String name;

    @Column(nullable=false)
    @Convert(converter=YearConverter.class)
    private Year yearOfRelease;

    @Column(nullable=false)
    private int pieces;

}
