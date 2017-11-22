/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unitec.org.mongounitec;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author T-107
 */
@RestController
@RequestMapping("/api")             //("/mensajito") //Identifica la clase para mapeo para servicio web, es un URI.
@CrossOrigin //Permite el paso de información nentre servidores
public class ControladorMensajito {
    
    @Autowired RepositorioMensajito repoMensa;
    //A continuación van las 5 operaciones básicas con la entidad del mensaje
    
    
    //Metodo GET buscar todos
    @RequestMapping(value="/mensajito", //Se indica que se hará la uri con el id
            method = RequestMethod.GET, //Request
            headers = {"Accept=application/json"}) //Se da ebncabezado tipo json
    public ArrayList<Mensajito> obtenerToddos()throws Exception{ //se da el id al uri (path) el mismo
        return (ArrayList<Mensajito>)repoMensa.findAll(); //al regresar el valor, el controlador transforma el objeto java a json-
    }
    
    //Metodo GET buscar por ID
    @RequestMapping(value="/mensajito/{id}",  //construccion de uri con variable de ruta {}
            method = RequestMethod.GET,
            headers = {"Accept=application/json"})
    public Mensajito obtenerPorId(@PathVariable String id) throws Exception{
            return repoMensa.findOne(id);
        }
    
    
    //Metodo POST guardar version para clientes variables (web y desktop) (antigua)
    @RequestMapping(value = "/mensajito/{titulo}/{cuerpo}", 
            method = RequestMethod.POST,
            headers = {"Accept=application/json"})
    public Estatus guardarMensajito(@PathVariable String titulo,
            @PathVariable String cuerpo)throws Exception{
        repoMensa.save(new Mensajito(titulo, cuerpo));
        Estatus estatus = new Estatus();
        estatus.setSuccess(true);
        return estatus;
    }
    
    //Metodo POST guardar pero es una version mas pura y efectiva (moderni)
    @RequestMapping(value = "/mensajito",
            method = RequestMethod.POST,
            headers = {"Accept=application/json"})
    public Estatus guardarMensajitoMejorado(@RequestBody String json)
            throws Exception{
        //Transformar el json a java
        ObjectMapper mapper=new ObjectMapper();
        Mensajito mensa = mapper.readValue(json, Mensajito.class); //mapear json a Mensajito
        repoMensa.save(mensa);
        Estatus es= new Estatus();
        es.setSuccess(true);
        return es;
    }
    
    
    
    
    
}
