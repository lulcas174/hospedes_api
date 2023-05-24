package hospedeAPI.example.hospedeAPI.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Entity
public class CheckIn implements Serializable {

    @Id @Getter @Setter @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne @Getter @Setter
    private Hospede hospede;

    @Getter @Setter
    private Date dataEntrada;

    @Getter @Setter
    private Date dataSaida;

    @Getter @Setter
    private Boolean adicionalVeiculo;

    @Getter @Setter @Column(nullable = true)
    private Double valorTotalEstadia;

}
