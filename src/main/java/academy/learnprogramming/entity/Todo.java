/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package academy.learnprogramming.entity;

import java.time.LocalDate;
import javax.json.bind.annotation.JsonbDateFormat;
import javax.persistence.*;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author Jose Casal
 */


@Entity
@NamedQuery(name = Todo.FIND_TODO_BY_TASK, query = "select t from Todo t where t.task like :task and t.todoOwner.email = :email")
@NamedQuery(name = Todo.FIND_ALL_TODOS_BY_USER, query = "select t from Todo t where t.todoOwner.email = :email")
@NamedQuery(name = Todo.FIND_TODO_BY_ID, query = "select t from Todo t where t.id = :id and t.todoOwner.email = :email")
public class Todo extends AbstractEntity{

    public static final String FIND_TODO_BY_TASK = "Todo.findByTask";
    public static final String FIND_ALL_TODOS_BY_USER = "Todo.findByUser";
    public static final String FIND_TODO_BY_ID = "Todo.findById";


    @NotEmpty(message = "Task must be set")
    @Size(min = 10, message = "Task should not be less than 10 characters")
    private String task;


    @NotNull(message = "Due must be set")
    @FutureOrPresent(message = "Dude must be in the present or future")
    @JsonbDateFormat(value = "yyyy-MM-dd")
    private LocalDate dueDate;


    private boolean isCompleted;
    private LocalDate dateCompleted;
    private LocalDate dateCreated;

    @ManyToOne
    private User todoOwner;

    @PrePersist
    private void init() {
        setDateCreated(LocalDate.now());
    }


    public User getTodoOwner() {
        return todoOwner;
    }

    public void setTodoOwner(User todoOwner) {
        this.todoOwner = todoOwner;
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

    public boolean isIsCompleted() {
        return isCompleted;
    }

    public void setIsCompleted(boolean isCompleted) {
        this.isCompleted = isCompleted;
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
