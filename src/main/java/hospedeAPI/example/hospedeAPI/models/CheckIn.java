package hospedeAPI.example.hospedeAPI.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Entity @Getter @Setter
public class CheckIn implements Serializable {

    @Id  @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne
    private Hospede hospede;

    private Date dataEntrada;

    private Date dataSaida;

    private Boolean adicionalVeiculo;

    @Column(nullable = true)
    private Double valorTotalEstadia;

    private Double valorDaUltimaEstadia;

    private Double valorTotalGastoHotel;

}
