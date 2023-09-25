package org.element.service;

import io.quarkus.elytron.security.common.BcryptUtil;
import io.quarkus.hibernate.reactive.panache.common.WithTransaction;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import org.element.model.User;
import org.hibernate.ObjectNotFoundException;

import java.util.List;

@ApplicationScoped
// declares a singleton instance of the Java class, which will be shared in a single application context
// whenever service gets injected into other beans, the CDI container will inject the same instance to each
public class UserService {

    public Uni<User> findById(long id) {
        return User.<User>findById(id)  // panache built-in
                .onItem().ifNull().failWith(() -> new
                        ObjectNotFoundException(id, "User"));
    }

    public Uni<User> findByName(String name) {
        return User.find("name", name).firstResult();
    }

    public Uni<List<User>> list() {
        return User.listAll();
    }

    @WithTransaction   // TODO: @ReactiveTransactional deprecated
    public Uni<User> create(User user) {
        user.password = BcryptUtil.bcryptHash(user.password);
        return user.persistAndFlush();
    }

    @WithTransaction
    public Uni<User> update(User user) {
        return findById(user.id)
                .chain(u -> User.getSession())
                .chain(s -> s.merge(user));
        // chaining the Uni instance and mapping the emitted item
    }

    public Uni<User> getCurrentUser() {
        // TODO: replace implementation once security is added to the project
        return User.find("order by ID").firstResult();
    }
}
