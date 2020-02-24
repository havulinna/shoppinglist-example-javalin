# Shopping List -esimerkkisovellus

Tämän projektin on Javalin-teknologialla tehty versio [ShoppingListExample](https://github.com/havulinna/shoppinglist-example)-esimerkkisovelluksesta. 

Tutustu sovelluksen käyttöohjeeseen ja kuvaukseen [alkuperäisen version GitHub-sivulla](https://github.com/havulinna/shoppinglist-example).

Javalin-sovelluksen oleellisin ero servlet-pohjaiseen toteutukseen verrattuna on web-palvelimen luonti, reititysten asettaminen ja käynnistys [Webapp](src/main/java/web/Webapp.java)-luokassa:

```java
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
```

Muilta osin sovellus noudattaa alkuperäistä arkkitehtuuria.

---

Tämän oppimateriaalin on kehittänyt Teemu Havulinna ja se on lisensoitu Creative Commons BY-NC-SA -lisenssillä. 
