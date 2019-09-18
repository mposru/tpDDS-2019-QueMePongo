@startuml
class Prenda {

  }

class TipoDePrenda {
}

enum Trama {
 
}
enum Material{

}
enum Categoria {

}

abstract class TipoDeGuardarropa{
  }

abstract class EstadoAtuendo {
  }

abstract class Decision {
  }

enum Periodo {
  }

Prenda --> TipoDePrenda
TipoDePrenda --> Categoria
Prenda --> Material
Prenda --> Color
Prenda --> Color
Prenda --> Trama
TipoDePrenda --> "*" Material : materialesValidos
Guardarropa --> "*" Prenda : prendasSuperiores
Guardarropa --> "*" Prenda : prendasInferiores
Guardarropa --> "*" Prenda : accesorios
Guardarropa --> "*" Prenda : calzado
Usuario --> "*" Guardarropa
Guardarropa --> "*" Usuario
Guardarropa --> TipoDeGuardarropa
Premium --|> TipoDeGuardarropa
Gratuito --|> TipoDeGuardarropa
Atuendo --> "*" Prenda :prendasSuperiores
Atuendo --> "*" Prenda :prendasInferiores
Atuendo --> "*" Prenda :accesorios
Atuendo --> "*" Prenda :calzados
Atuendo --> EstadoAtuendo
Nuevo --|> EstadoAtuendo
Aceptado --|> EstadoAtuendo
Calificado --|> EstadoAtuendo
Rechazado --|> EstadoAtuendo
Usuario --> "*" Decision :decisiones

Aceptar --|> Decision
Calificar --|> Decision
Recalificar --|> Decision
Rechazar --|> Decision

Usuario --> Calendario
Calendario --> "*" Evento
Usuario --> "*" Atuendo :atuendosAceptados
Usuario --> "*" Atuendo :atuendosRechazados
Usuario --> "*" AtuendosSugeridosPorEvento :atuendosSugeridos
Evento --> Periodo


@enduml