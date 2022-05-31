FROM airhacks/glassfish
COPY ./target/hello-todo.war ${DEPLOYMENT_DIR}
