package ru.shilov.cc.currencyconverter.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Data
@Entity
@SuperBuilder
@NoArgsConstructor
@Table(name = "valute_detail")
public final class ValuteDetail {

    @Id
    private UUID id;

    @Column(name = "num_code")
    private String numCode;

    @Column(name = "char_code", unique = true)
    private String charCode;

    @Column
    private String name;

}
