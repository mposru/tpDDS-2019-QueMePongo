@startuml

entity guardarropas  {
  (PK) id_guardarropa
  --
  tipo_guardarropa
}

entity prendas {
(PK) id_prenda
--
(FK) id_guardarropa
(FK) id_atuendo
(FK) id_tipoDePrenda
  rojo_primario
  verde_primario
  azul_primario
  rojo_secundario
  verde_secundario
  azul_secundario
}



entity usuarios {
(PK) id_usuario
--
  nombreDeUsuario
  numeroDeCelular
  contrasenia

}
entity usuarios_guardarropas {
(PK) id_usuario
(PK) id_guardarropa

}

entity decisiones {
(PK) id_decision
--
(FK) id_usuario
}
entity atuendos_aceptados {
(PK) id_aceptado
--
(FK) id_usuario
}
entity atuendos_rechazados {
(PK) id_rechazado
--
(FK) id_usuario
}


entity atuendos {
(PK) id_atuendo

}
entity eventos {
  (PK) id_evento
  --
  (FK) id_calendario
}

entity tipo_prendas {
(PK) id_tipoDePrenda
}

entity atuendos_sugeridos_eventos {
(PK) id_atuendo_sugerido
(PK) id_atuendo
--
(FK) id_usuario
}


guardarropas ||--o{ prendas
guardarropas ||--|{ usuarios_guardarropas
usuarios ||--|{ usuarios_guardarropas
usuarios ||--o{ decisiones
usuarios ||--o{ atuendos_aceptados
usuarios ||--o{ atuendos_rechazados
atuendos ||--o{ atuendos_prendas
prendas ||--o{ atuendos_prendas
usuarios ||--|{ eventos

prendas }o--|| tipo_prendas
atuendos_sugeridos_eventos }o--|| atuendos
usuarios ||--o{ atuendos_sugeridos_eventos
atuendos_sugeridos_eventos |o--|| eventos


@enduml