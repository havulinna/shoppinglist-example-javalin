package controller;

import java.util.List;

import database.ShoppingListItemDao;
import io.javalin.http.Context;
import model.ShoppingListItem;

public class ShoppingListController {
    private final ShoppingListItemDao dao;

    public ShoppingListController(ShoppingListItemDao dao) {
        this.dao = dao;
    }

    public void removeItem(Context ctx) {
        long id = Long.parseLong(ctx.pathParam("id"));
        ShoppingListItem item = dao.getItem(id);
        if (item != null) {
            dao.removeItem(item);
        }
        ctx.json(item);
    }

    public void addItem(Context ctx) {
        ShoppingListItem newItem = ctx.bodyAsClass(ShoppingListItem.class);
        dao.addItem(newItem);
        ctx.json(newItem);
    }

    public void showAll(Context ctx) {
        List<ShoppingListItem> allItems = dao.getAllItems();
        ctx.json(allItems);
    }
}
