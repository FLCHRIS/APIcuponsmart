/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.DELETE;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;


/**
 * REST Web Service
 *
 * @author Admin
 */
@Path("empresa")
public class EmpresaWS {

    @Context
    private UriInfo context;


    /**
     * Creates a new instance of EmpresaWS
     */
    public EmpresaWS() {
    }
    
    @GET
    @Path("buscarEmpresa/{parametro}")
    @Produces(MediaType.APPLICATION_JSON)
    public String buscarEmpresaBy_Name_RFC_REPRESENTANTENAME(@PathParam("parametro") String parametro) {

        if (parametro.length() == 8) {
            //validaciones para el tipo de consulta
        }

        return parametro;
    }

    @POST
    @Path("registroEmpresa")
    @Produces(MediaType.APPLICATION_JSON)
    public String registroEmpresa() {

        return "put";
    }

    @PUT
    @Path("edicionEmpresa/{idEmpresa}")
    @Produces(MediaType.APPLICATION_JSON)
    public String edicionEmpresa(@PathParam("idEmpresa") Integer idEmpresa) {

        return "" + idEmpresa;
    }

    @DELETE
    @Path("eliminarEmpresa/{idEmpresa}")
    @Produces(MediaType.APPLICATION_JSON)
    public String eliminarEmpresa(@PathParam("idEmpresa") Integer idEmpresa) {

        return "" + idEmpresa;
    }
  
}
