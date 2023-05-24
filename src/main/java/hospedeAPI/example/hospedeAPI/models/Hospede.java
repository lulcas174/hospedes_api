package hospedeAPI.example.hospedeAPI.models;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;

@Entity
public class Hospede implements Serializable {

    @Id @Getter @Setter @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Getter @Setter @NotNull
    private String nome;

    @Getter @Setter @NotNull
    private String telefone;

    @Getter @Setter @NotNull
    private String documento;

    @Getter @Setter @NotNull
    private String aniversario;

    @Getter @Setter @NotNull
    private String dataSaida;

    @Getter @Setter
    private Boolean adicionalVeiculo;
}
