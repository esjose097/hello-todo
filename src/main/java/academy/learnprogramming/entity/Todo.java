/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package academy.learnprogramming.entity;

import java.time.LocalDate;
import javax.json.bind.annotation.JsonbDateFormat;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author jose casal
 */
@Entity
public class Todo {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @NotEmpty(message = "Task must be set") //<-- Se le añaden parametros o validaciones a la tabla.
    @Size(min=10, message="Task should not be less than 10 characters")
    private String task;
    
    @NotNull(message = "Due must be set")//<-- Indica que el valor no puede ser Nulo
    @FutureOrPresent(message = "Dude must be in the present or future")//<-- Etiqueta que solo permite fechas a partir de la fecha actual no menores.
    @JsonbDateFormat(value = "yyyy-MM-dd")//<-- Establece un formato de fechas valido para ambos extremos
    private LocalDate dueDate;
    
    private boolean isComplete;
    private LocalDate dateCompleted;
    private LocalDate dateCreated;

    @PrePersist //<-- Realiza una callback preinicializando ese parametro.
    private void init(){
        setDateCreated(LocalDate.now());
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public boolean isIsComplete() {
        return isComplete;
    }

    public void setIsComplete(boolean isComplete) {
        this.isComplete = isComplete;
    }

    public LocalDate getDateCompleted() {
        return dateCompleted;
    }

    public void setDateCompleted(LocalDate dateCompleted) {
        this.dateCompleted = dateCompleted;
    }

    public LocalDate getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(LocalDate dateCreated) {
        this.dateCreated = dateCreated;
    }
    
    
}
