package org.element.service;

import io.quarkus.hibernate.reactive.panache.common.WithSession;
import io.quarkus.hibernate.reactive.panache.common.WithTransaction;
import io.quarkus.security.UnauthorizedException;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.element.model.Element;
import org.hibernate.ObjectNotFoundException;

import java.util.List;

@ApplicationScoped
public class ElementService {

    private final UserService userService;

    @Inject
    public ElementService(UserService userService) {
        this.userService = userService;
    }

    public Uni<Element> findById(long id) {
        return userService.getCurrentUser()
                .chain(user -> Element.<Element>findById(id)
                        .onItem().ifNull().failWith(() -> new
                                ObjectNotFoundException(id, "Element"))
                        .onItem().invoke(element -> {
                            if (!user.equals(element.user)) {
                                throw new UnauthorizedException("You are not allowed to update this element");
                            }
                        }));
    }

    @WithSession
    public Uni<List<Element>> listForUser() {
        return userService.getCurrentUser()
                .chain(user -> Element.find("user", user).list());
    }

    @WithTransaction
    public Uni<Element> create(Element element) {
        return userService.getCurrentUser()
                .chain(user -> {
                    element.user = user;
                    return element.persistAndFlush();
                });
    }
}