package com.sebastian_daschner.barista;

import javax.enterprise.context.ApplicationScoped;

import static com.sebastian_daschner.barista.OrderStatus.COLLECTED;
import static com.sebastian_daschner.barista.OrderStatus.FINISHED;

@ApplicationScoped
public class OrderStatusProcessor {

    public OrderStatus process(OrderStatus status) {
        return switch (status) {
            case PREPARING -> FINISHED;
            case FINISHED, COLLECTED -> COLLECTED;
        };
    }

}
