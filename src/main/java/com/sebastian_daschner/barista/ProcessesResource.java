package com.sebastian_daschner.barista;

import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

@Path("processes")
public class ProcessesResource {

    @Inject
    OrderStatusProcessor processor;

    @POST
    public JsonObject process(JsonObject order) {
        OrderStatus oldStatus = parseStatus(order);

        OrderStatus newStatus = processor.process(oldStatus);

        return buildResponse(newStatus);
    }

    private OrderStatus parseStatus(JsonObject order) {
        OrderStatus status = OrderStatus.fromString(order.getString("status", null));

        if (status == null)
            throw new BadRequestException("No or invalid status provided");

        return status;
    }

    private JsonObject buildResponse(OrderStatus newStatus) {
        return Json.createObjectBuilder()
                .add("status", newStatus.name())
                .build();
    }

}
