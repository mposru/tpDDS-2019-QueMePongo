package domain;

public enum TipoAbrigo {
    BASICO {
        public int obtenerTemperaturaMaxima() { return 40; }
        public int obtenerTemperaturaMinima() { return 20; }
    },
    MEDIANO {
        public int obtenerTemperaturaMaxima() { return 20; }
        public int obtenerTemperaturaMinima() { return 10; }
    },
    ALTO {
        public int obtenerTemperaturaMaxima() { return 10; }
        public int obtenerTemperaturaMinima() { return 0; }
    },
    NINGUNO {
        public int obtenerTemperaturaMaxima() { return 0; }
        public int obtenerTemperaturaMinima() { return 0; }
    };
    public abstract int obtenerTemperaturaMaxima();
    public abstract int obtenerTemperaturaMinima();
}
