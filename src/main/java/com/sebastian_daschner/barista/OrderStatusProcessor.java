package com.sebastian_daschner.barista;

import javax.enterprise.context.ApplicationScoped;

import static com.sebastian_daschner.barista.OrderStatus.COLLECTED;
import static com.sebastian_daschner.barista.OrderStatus.FINISHED;

@ApplicationScoped
public class OrderStatusProcessor {

    public OrderStatus process(OrderStatus status) {
        switch (status) {
            case PREPARING:
                return FINISHED;
            case FINISHED:
            case COLLECTED:
                return COLLECTED;
            default:
                throw new IllegalArgumentException("Unknown status " + status);
        }
    }

}
