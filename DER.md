@startuml

entity Guardarropa  {
  (PK) id_guardarropa
  --
  tipo_guardarropa
}

entity Prenda  {
(PK) id_prenda
--
(FK) id_guardarropa
(FK) id_atuendo
}

entity Usuario {
(PK) id_usuario

}
entity Usuario_Guardarropa {
(PK) id_usuario
(PK) id_guardarropa

}

entity Decision {
(PK) id_decision
--
(FK) id_usuario
}
entity AtuendoAceptado {
(PK) id_aceptado
--
(FK) id_usuario
}
entity AtuendoRechazado {
(PK) id_rechazado
--
(FK) id_usuario
}

entity Calendario {
(PK) id_calendario
--
(FK) id_usuario
}

entity Atuendo {
(PK) id_atuendo

}
entity Evento {
  (PK) id_evento
  --
  (FK) id_calendario
}

entity TipoDePrenda {
(PK) id_tipoDePrenda
}

entity AtuendoSugeridoPorEvento {
(PK) id_atuendo_sugerido
(PK) id_atuendo
--
(FK) id_usuario
}


Guardarropa ||--o{ Prenda
Guardarropa ||--|{ Usuario_Guardarropa
Usuario ||--|{ Usuario_Guardarropa
Usuario ||--o{ Decision
Usuario ||--o{ AtuendoAceptado
Usuario ||--o{ AtuendoRechazado
Usuario ||--|| Calendario
Atuendo |o--|{ Prenda

Calendario ||--o{ Evento
Prenda |o--|| TipoDePrenda
AtuendoSugeridoPorEvento ||--|{ Atuendo
Usuario ||--o| AtuendoSugeridoPorEvento
AtuendoSugeridoPorEvento |o--|| Evento

@enduml