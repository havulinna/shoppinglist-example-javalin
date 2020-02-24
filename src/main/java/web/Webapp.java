package web;

import controller.ShoppingListController;
import database.FakeShoppingListItemDao;
import database.ShoppingListItemDao;
import io.javalin.Javalin;

public class Webapp {

    public static void main(String[] args) {
        Javalin app = Javalin.create(config -> {
            config.addStaticFiles("/public/");
        });

        ShoppingListItemDao dao = new FakeShoppingListItemDao();
        ShoppingListController controller = new ShoppingListController(dao);

        app.get("/api/shoppingList/", controller::showAll);
        app.post("/api/shoppingList/", controller::addItem);
        app.delete("/api/shoppingList/:id", controller::removeItem);

        app.start(8080);
    }
}