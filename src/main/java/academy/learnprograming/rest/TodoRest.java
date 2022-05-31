/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package academy.learnprograming.rest;

import academy.learnprogramming.entity.Todo;
import academy.learnprogramming.service.TodoService;
import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Esta clase es como el Routes en Node JS se establecen los "Path's" y se definen el tipo de
 * petición HTTP mediante tag's.
 * @author jose casal
 */
@Path("todo") //<-- Se establece la ruta para este REST
@Consumes(MediaType.APPLICATION_JSON) //Tipo de dato que consume o leera
@Produces(MediaType.APPLICATION_JSON) //Tipo de dato que regresa
public class TodoRest {
    
    @Inject //Se inyecta el servicio a utilizar, puedes verlo como cuando inyectabas los services en Angular.
    TodoService todoService;
    
    /**
     * Método que recibe una petición HTTP de tipo POST que se encarga de guardar en BD una entidad
     * @param todo <-- Entidad a guardar en BD
     * @return Response con un código de estado 200 y un json con la entidad guardada en BD
     */
    @Path("new") //<-- api/v1/todo/new
    @POST //<--Tipo de peticiones HTTP que esta ruta recibirá
    public Response createTodo(Todo todo){
        this.todoService.createTodo(todo);
        return Response.ok(todo).build(); //<-- Devuelve una response con un código de estado.
    }
    
    /**
     * Método que recibe una petición HTTP de tipo PUT y se encarga de editar una entidad en BD.
     * @param todo <-- Entidad a editar
     * @return Response con un código de estado 200 y un json con la entidad editada en BD
     */
    @Path("update") //<-- api/v1/todo/update
    @PUT //<-- Petición HTTP del tipo PUT
    public Response updateTodo(Todo todo){
        this.todoService.updateTodo(todo);
        return Response.ok(todo).build();
    }
    
    /**
     * Método que recibe una petición HTTP de tipo GET y se encarga de obtener una entidad en BD en base al ID proporcionado.
     * @param id <-- ID de la entidad a buscar en BD
     * @return Json con la entidad obtenida de la BD
     */
    @Path("{id}") //<-- api/v1/todo:id
    @GET //<-- Petición HTTP Del tipo GET
    public Todo getTodo(@PathParam("id")Long id){
        return todoService.findById(id);
    }
    
    /**
     * Método que recibe una petición HTTP de tipo GET y se encarga de obtener todas las entidades existentes de la BD
     * @return List<Todo> Con las entidades encontradas en BD
     */
    @Path("list")
    @GET
    public List<Todo> getTodos(){
        return this.todoService.getTodos();
    }
    
    /**
     * Método que recibe una petición HTTP de tipo POST que se encarga de editar la propiedad "isComplete" 
     * de una entidad en BD en base un ID especifico.
     * @param id <-- ID de la entidad a editar.
     * @return Response con un código de estado 200 y un JSON con la entidad editada en BD.
     */
    @Path("status")//<-- api/v1/todo/status
    @POST//<-- Petición HTTP POST
    public Response markAsComplete(@QueryParam("id")Long id){
        Todo todo = todoService.findById(id);//Obtenemos la entidad de la bd
        todo.setIsComplete(true);//Le cambiamos el estado a la entidad
        todoService.updateTodo(todo);//Atualizamos la entidad en BD
        
        return Response.ok(todo).build();//Devolvemos una response con un codigo de estado positivo
        
    }
}
