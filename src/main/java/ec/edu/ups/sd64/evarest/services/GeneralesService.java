package ec.edu.ups.sd64.evarest.services;

import java.util.List;

import ec.edu.ups.sd64.evarest.business.GestionGenerales;
import ec.edu.ups.sd64.evarest.model.General;
import io.opentracing.Tracer;
import config.ConfigJaeger;
import io.opentracing.Span;
import io.opentracing.Tracer;
import io.opentracing.util.GlobalTracer;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("datos")
public class GeneralesService {
	
	private final Tracer tracer = GlobalTracer.get();

    @Inject
    private GestionGenerales gGenerales;
    
    @Inject
    private ConfigJaeger configjaeger;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response crear(General gen) {
        Span spanGuardar = tracer.buildSpan("Crear Elemento").start();
        try {
            if (gen == null) {
                ErrorMessage error = new ErrorMessage(98, "Entidad General es nula");
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity(error)
                        .build();
            }
            gGenerales.guardarGeneral(gen);
            ErrorMessage error = new ErrorMessage(1, "OK");
            return Response.status(Response.Status.CREATED)
                    .entity(error)
                    .build();
        } catch (Exception e) {
            e.printStackTrace(); // Log de la traza del error
            ErrorMessage error = new ErrorMessage(99, "Error al guardar: " + e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(error)
                    .build();
        } finally {
        	spanGuardar.finish();
        }
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response actualizar(General gen) {
    	Span spanActualizarElemento = tracer.buildSpan("Actualizar Elemento").start();
        try {
        	gGenerales.actualizarGeneral(gen);
        	System.out.print("--Elemento Actualizado--");
            return Response.ok(gen).build();
        } catch (Exception e) {
            ErrorMessage error = new ErrorMessage(99, e.getMessage());
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(error)
                    .build();
        }finally {
        	spanActualizarElemento.finish();
        }
    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public String borrar(@QueryParam("codigo") int codigo) {
    	Span spanEliminarElemento = tracer.buildSpan("Eliminar Elemento").start();
        try {
        	gGenerales.borrarElemento(codigo);
        	System.out.print("--Elemento Eliminado: "+codigo+"--");
            return "OK";
        } catch (Exception e) {
            return "Error";
        }finally {
        	spanEliminarElemento.finish();
        }
    }


    @GET
    @Path("{extra}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response leerPorSerial(@PathParam("extra") String serial) {
    	Span spanGetElementoPorCaracteristica= tracer.buildSpan("Obtener Elementos mediante la Informacion").start();
        try {
        	General uni = gGenerales.getElementoPorExtra(serial);
        	System.out.print("--Elemento Obtenido--");
            return Response.ok(uni).build();
        } catch (Exception e) {
            ErrorMessage error = new ErrorMessage(4, "El elemento no Existe");
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(error)
                    .build();
        }finally {
        	spanGetElementoPorCaracteristica.finish();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("list")
    public Response getLibros() {
    	Span spanGetElemento = tracer.buildSpan("Obtener Elementos ").start();
    	try {
    		List<General> uni = gGenerales.getGenerales();
    		if (uni.size() > 0) {
    			return Response.ok(uni).build();
    		}else {
				ErrorMessage error = new ErrorMessage(6, "No se registran Elementos");
				return Response.status(Response.Status.NOT_FOUND)
						.entity(error)
						.build();
			}
    	}catch(Exception e){
	    	ErrorMessage error = new ErrorMessage(6, "No se registran Elementos");
	        return Response.status(Response.Status.NOT_FOUND)
	                .entity(error)
	                .build();
    	}finally {
    		spanGetElemento.finish();
        }
    	
    }
}
