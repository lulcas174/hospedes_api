package hospedeAPI.example.hospedeAPI.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Entity @Getter @Setter
public class Hospede implements Serializable {

    @Id  @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank
    private String nome;

    @NotBlank
    private String telefone;

    @NotBlank
    private String documentoCPF;

    @NotBlank
    private String dataNascimento;
    
    @NotBlank
    private String dataSaida;

    private Boolean adicionalVeiculo;
}
