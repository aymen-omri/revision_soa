package tn.jpa.entities;

import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.*;

/**
 * Session Bean implementation class Controller
 */
@Stateless
@LocalBean

@Path("/learner")
public class Controller {
	@PersistenceContext(unitName="Workshop3")
    public EntityManager em ; 
    
    public Controller() {
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/test")
    public String testApplication() {
    	return "Hello Client!";
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllLearners() {
    	List<Learner> learners = new ArrayList<Learner>();
    	TypedQuery<Learner> query = em.createNamedQuery("Learner.findAll" , Learner.class);
    	learners = query.getResultList();
    	if(learners.size() == 0) {
    		return Response.status(404).entity("databse is empty!").build();
    	}
    	return Response.ok().entity(learners).build();
    }
    
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createLearner(Learner l) {
    	try {
    		em.persist(l);
    		return Response.ok().entity("added successfully!").build();
    	}catch(Exception e) {
    		return Response.status(500).entity(e.getMessage()).build();
    	}
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/find/{id}")
    public Response getLearnerById(@PathParam("id") int id) {
    		Learner l = em.find(Learner.class, id) ;
    		if(l != null) {
    			return Response.ok().entity(l).build();
    		}
    		return Response.status(500).entity("id not found!").build();
    			
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/email")
    public Response getLearnerByEmail(@QueryParam("email") String email) {
    	TypedQuery<Learner> query = em.createNamedQuery("Learner.Email", Learner.class).setParameter(1,email);
    	Learner l = query.getSingleResult();
    	if(l != null) {
    		return Response.ok().entity(l).build(); 
    	}
    	return Response.status(500).entity("email not found!").build();	
    }
    
    
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/update/{id}")
    public Response updateLearner(Learner l, @PathParam("id") int id) {
    	try {
    		Learner ll = em.find(Learner.class, id);
    		if(ll!= null) {
    			ll.setCity(l.getCity());
    			ll.setEmail(l.getEmail());
    			ll.setName(l.getName());
    			em.merge(ll);
    			return Response.ok().entity(ll).build();
    		}else {
    			return Response.status(500).entity("id not found!").build();
    		}
    		
    	}catch(Exception e) {
    		return Response.status(500).entity("server error!").build();
    	}
    }
    
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Path("delete/{id}")
    public Response deleteLearner(@PathParam("id") int id) {
    	try {
    		em.remove(em.find(Learner.class, id));
    		return Response.ok().entity("Deleted successfully!").build();
    	}catch(Exception e) {
    		return Response.status(500).entity("id not found!").build();
    	}
    }
    

}















