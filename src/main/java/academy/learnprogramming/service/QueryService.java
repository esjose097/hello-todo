package academy.learnprogramming.service;

import academy.learnprogramming.entity.Todo;
import academy.learnprogramming.entity.User;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.SecurityContext;
import java.util.List;

/**
 * 
 * @author jose casal
 */
@Stateless
public class QueryService {

    @Inject
    EntityManager entityManager;
    @Inject
    private SecurityUtil securityUtil;

    @Context
    private SecurityContext securityContext;

    public User findUserByEmail(String email) {

//TODO
        List<User> userList = entityManager.createNamedQuery(User.FIND_USER_BY_EMAIL, User.class).setParameter("email", email)
                .getResultList();

        if (!userList.isEmpty()) {
            return userList.get(0);
        }
        return null;
    }

    public List countUserByEmail(String email) {
       return entityManager.createNativeQuery("select count (id) from TodoUser where exists (select id from TodoUser where email = ?)"
        ).setParameter(1, email).getResultList();
    }


//    public boolean authenticateUser(String email, String password) {
//        User user = findUserByEmail(email);
//
//        if (user == null) {
//            return false;
//        }
//
//
//        return securityUtil
//                .passwordsMatch(user.getPassword(), user.getSalt(), password);
//    }


    public Todo findTodoById(Long id) {
        List<Todo> resultList = entityManager.createNamedQuery(Todo.FIND_TODO_BY_ID, Todo.class)
                .setParameter("id", id)
                .setParameter("email", securityContext.getUserPrincipal().getName()).getResultList();

        if (!resultList.isEmpty()) {
            return resultList.get(0);
        }

        return null;

    }

    public List<Todo> getAllTodos() {

        return entityManager.createNamedQuery(Todo.FIND_ALL_TODOS_BY_USER, Todo.class)
                .setParameter("email", securityContext.getUserPrincipal().getName()).getResultList();
    }

    public List<Todo> getAllTodosByTask(String taskText) {
        return entityManager.createNamedQuery(Todo.FIND_TODO_BY_TASK, Todo.class)
                .setParameter("email", securityContext.getUserPrincipal().getName())
                .setParameter("task", "%" + taskText + "%").getResultList();
    }
}
