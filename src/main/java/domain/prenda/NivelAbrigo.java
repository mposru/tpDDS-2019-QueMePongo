package domain.prenda;

//Singleton que me calcula el nivel de abrigo entre dos temperaturas
    public class NivelAbrigo   {

            private static NivelAbrigo instanceOfNivelAbrigo;
            private NivelAbrigo() {}

            public static NivelAbrigo getInstance() {
                if (instanceOfNivelAbrigo == null) {
                    instanceOfNivelAbrigo = new NivelAbrigo();
                }
                return instanceOfNivelAbrigo;}
            public double getNivelAbrigo(double temperaturaMin,double temperaturaMax) {
                return (-(temperaturaMin+temperaturaMax) + 25);
    }

}
