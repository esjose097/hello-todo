/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package academy.learnprogramming.service;

import academy.learnprogramming.entity.Todo;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

/**
 *
 * @author jose casal
 */
@Transactional //<-- Transforma la clase en un "servicio"
public class TodoService {
    
    @PersistenceContext
    EntityManager entityManager;
    
    /**
     * Persiste en la base de datos una entidad del tipo @Todo
     * @param todo <-- Entidad a guardar.
     * @return Entidad guardada en base de datos.
     */
    public Todo createTodo(Todo todo){
        this.entityManager.persist(todo);
        return todo;
    }
    /**
     * Método encargado de actualizar una entidad en base de datos.
     * @param todo <-- Entidad actualizada
     * @return La entidad actualizada
     */
    public Todo updateTodo(Todo todo){
        this.entityManager.merge(todo);
        return todo;
    }
    /**
     * Método encargado de devolver una entidad de la base de datos en base a su ID.
     * @param id <-- ID de la entidad a buscar en BD
     * @return Entidad del ID correspondiente.
     */
    public Todo findById(Long id){
        return this.entityManager.find(Todo.class, id);
    }
    /**
     * Método encargado de devolver una lista con todas las entidades del tipo @Todo de la BD
     * @return Lista de entidades.
     */
    public List<Todo> getTodos(){
        return this.entityManager.createQuery("SELECT t from Todo t", Todo.class).getResultList();
    }
    
}
