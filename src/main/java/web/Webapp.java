package web;

import controller.ShoppingListController;
import database.FakeShoppingListItemDao;
import database.ShoppingListItemDao;
import io.javalin.Javalin;

public class Webapp {

    public static void main(String[] args) {
        Javalin server = Javalin.create(config -> {
            config.addStaticFiles("/public/");
        });

        ShoppingListItemDao dao = new FakeShoppingListItemDao();
        ShoppingListController controller = new ShoppingListController(dao);

        server.get("/api/shoppingList/", controller::showAll);
        server.post("/api/shoppingList/", controller::addItem);
        server.delete("/api/shoppingList/:id", controller::removeItem);

        server.start(8080);
    }
}