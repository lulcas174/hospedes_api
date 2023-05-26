# Documentação dos endpoints
<s>eu tentei documentar via swagger mas eu tive muitos problemas~ 😢</s>


Todos os endpoints seguirão o padrão: api/v1/{checkin_ou_hospede}/{endpoint}

# Endpoints de hospedes

## POST /criar
    {
        "nome": "string",
        "documentoCPF": "string",
        "telefone": "string",
        "dataDeNascimento": "string"
        "dataSaida": "string"
        "adicionalVeiculo": "boolean"
    }

## GET /listar
    [
        {
            "id": "string",
            "nome": "string",
            "documentoCPF": "string",
            "telefone": "string",
            "dataDeNascimento": "string"
            "dataSaida": "string"
            "adicionalVeiculo": "boolean"
        }
    ]

## PUT /atualizar/{id}
    {
        "nome": "string",
        "documentoCPF": "string",
        "telefone": "string",
        "dataDeNascimento": "string"
        "dataSaida": "string"
        "adicionalVeiculo": "boolean"
    }
    - Você pode atualizar apenas um campo se quiser


## Delete /deletar/{id}
    - Deleta o hospede pelo id


# Endpoints de check-in

## POST /realizar-novo-checkin
    {
        "hospedeId": "string",
        "dataEntrada": "string",
        "dataSaida": "string",
        "adicionalVeiculo": "boolean",
        "valorTotalEstadia": "double",
        "valorDaUltimaEstadia": "double",
        "valorTotalGastoHotel": "double"
    }

## GET /hospedes-no-hotel
    [
        {
            "id": "string",
            "hospedeId": "string",
            "dataEntrada": "string",
            "dataSaida": "string",
            "adicionalVeiculo": "boolean",
            "valorTotalEstadia": "double",
            "valorDaUltimaEstadia": "double",
            "valorTotalGastoHotel": "double"
        }
    ]

## GET /hospedes-checkout
    [
        {
            "id": "string",
            "hospedeId": "string",
            "dataEntrada": "string",
            "dataSaida": "string",
            "adicionalVeiculo": "boolean",
            "valorTotalEstadia": "double",
            "valorDaUltimaEstadia": "double",
            "valorTotalGastoHotel": "double"
        }
    ]


