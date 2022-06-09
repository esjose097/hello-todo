package academy.learnprogramming.service;

import academy.learnprogramming.entity.Todo;
import academy.learnprogramming.entity.User;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;

import java.util.logging.Level;
import java.util.logging.Logger;

import static org.junit.Assert.*;
/**
 * 
 * @author jose casal
 */
@RunWith(Arquillian.class)
public class TodoServiceTest {

    @Inject
    private User user;

    @Inject
    TodoService todoService;
    Logger logger;

    @Deployment
    public static WebArchive createDeployment() {
        return ShrinkWrap.create(WebArchive.class, "hello-todo.war")
                .addPackage(Todo.class.getPackage())
                .addPackage(TodoService.class.getPackage())
                .addAsResource("persistence.xml", "META-INF/persistence.xml")
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @Before
    public void setUp() throws Exception {


    }

    @After
    public void tearDown() throws Exception {
    }


    @Test
    public void saveUser() {

    }
}
