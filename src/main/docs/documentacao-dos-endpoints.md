# Documentação dos endpoints
<s>eu tentei documentar via swagger mas eu tive muitos problemas~ 😢</s>


Todos os endpoints seguirão o padrão: api/v1/{checkin_ou_hospedes}/{operacao}

# Endpoints de hospedes

## POST /criar
    Para criar um novo hospede.
    
        {
            "nome": "string",
            "documentoCPF": "string",
            "telefone": "string",
            "dataNascimento": "string"
            "dataSaida": "string"
            "adicionalVeiculo": "boolean"
       }
    

## GET /listar
    Para listar os hospedes você não precisa de body, apenas acessar o endpoint /listar e você tera o JSON esperado de:
    [
        {
            "nome": "string",
            "documentoCPF": "string",
            "telefone": "string",
            "dataNascimento": "string"
            "dataSaida": "string"
            "adicionalVeiculo": "boolean"
       }
    ] 



## PUT /atualizar/{id}
    {
        "nome": "string",
        "documentoCPF": "string",
        "telefone": "string",
        "dataNascimento": "string"
        "dataSaida": "string"
        "adicionalVeiculo": "boolean"
    }
    - Você pode atualizar apenas um campo se quiser


## Delete /deletar/{id}
    - Deleta o hospede pelo id


# Endpoints de check-in

## POST /realizar-novo-checkin
   - Para realizar um checkin é necessário apenas enviar o documentoCPF da pessoa.
    {
       "documentoCPF": "string"
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


