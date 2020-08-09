package ru.shilov.cc.currencyconverter.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@Entity
@NoArgsConstructor
@RequiredArgsConstructor
@Table(name = "history")
public final class Result {

    @Id
    private UUID id = UUID.randomUUID();

    @NonNull
    @Column(name = "source_code")
    private String sourceCode;

    @NonNull
    @Column(name = "destination_code")
    private String destinationCode;

    @NonNull
    @Column(name = "source_amount")
    private Double sourceAmount;

    @NonNull
    @Column(name = "destination_amount")
    private Double destinationAmount;

    @NonNull
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

}
