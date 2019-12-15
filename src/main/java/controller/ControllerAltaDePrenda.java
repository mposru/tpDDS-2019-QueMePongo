package controller;

import domain.Guardarropa;
import domain.RepositorioDeUsuarios;
import domain.RepositorioTipoDePrendas;
import domain.Usuario;
import domain.prenda.*;
import net.sf.oval.internal.util.LinkedSet;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ControllerAltaDePrenda {
    public ModelAndView mostrarAltaDePrenda(Request req, Response res) {
        Map<String,Object > model = new HashMap<>();
        List<TipoDePrenda> tipoDePrendas = RepositorioTipoDePrendas.getInstance().getTipoDePrendas();
        System.out.println("Cantidad de tipo de prendas"+ tipoDePrendas.size());
        int i=1;
        model.put("tipoDePrendas",tipoDePrendas);
        tipoDePrendas.forEach(tipoDePrenda -> model.put("nombreTipoDePrenda"+(i+1),tipoDePrenda.getNombreTipoPrenda()));






        return new ModelAndView(model,"altaDePrenda.hbs");
    }
    public ModelAndView seleccionAltaDePrenda (Request req, Response res) {
        Usuario usuario= RepositorioDeUsuarios.getInstance().buscarUsuarioPorId(Integer.parseInt(req.cookie("id")));
        String idGuardarropa=req.queryParams("numC");
        Guardarropa guardarropa = usuario.verificarSiIdDeGuardarropa(idGuardarropa);
        Borrador borrador = new Borrador();
        Color colorPrimario = new Color(Integer.parseInt(req.queryParams("rojo1C")),Integer.parseInt(req.queryParams("verde1C")),Integer.parseInt("azul1C"));
        borrador.definirColorPrimario(colorPrimario);
        Color colorSecundario = new Color(Integer.parseInt(req.queryParams("rojo2C")),Integer.parseInt(req.queryParams("verde2C")),Integer.parseInt("azul2C"));
        borrador.definirColorSecundario(colorSecundario);
        borrador.definirMaterial(Material.valueOf(req.queryParams("tipoDeMaterial")));
        List<Material> materiales=new LinkedSet<>();
        materiales.add(Material.valueOf(req.queryParams("tipoDeMaterial")));
        //TODO modificar en el constructor el nombre del tipo de prenda por el que debe ir, está harcodeado.
        TipoDePrenda tipoDePrenda=new TipoDePrenda(Categoria.valueOf(req.queryParams("tipoDePrenda")),materiales,Integer.parseInt(req.queryParams("temMC")),Integer.parseInt(req.queryParams("temNC")),"Nombre de Tipo de prenda");
        borrador.definirTipo(tipoDePrenda);
        borrador.definirTrama(Trama.valueOf(req.queryParams("tipoDeTrama")));
        borrador.definirGuardarropa(guardarropa);
        if(req.queryParams("resLLuvia")=="true"){
            borrador.definirEsParaLLuvia(true);}
        else {
            borrador.definirEsParaLLuvia(false);}
        borrador.crearPrenda();
        res.redirect("/perfil");
        return null;
    }

}
