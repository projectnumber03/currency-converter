package ru.shilov.cc.currencyconverter.entity;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Data
@Entity
@SuperBuilder
@NoArgsConstructor
@Table(name = "valute_course")
public final class ValuteCourse {

    @Id
    private UUID id;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    @ManyToOne
    @JoinColumn(name = "valute_detail_id", referencedColumnName = "id", nullable = false)
    private ValuteDetail valuteDetail;

    @Column
    private Integer nominal;

    @Column
    @Getter(AccessLevel.NONE)
    private Double value;

    public Double getValue() {
        return value / nominal;
    }

}
