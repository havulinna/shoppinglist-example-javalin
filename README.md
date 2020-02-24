# Shopping List -esimerkkisovellus

Tämän projekti on [Javalin-teknologialla](https://javalin.io/) tehty versio [ShoppingListExample](https://github.com/havulinna/shoppinglist-example)-esimerkkisovelluksesta. 

> "Unlike other Java and Kotlin web frameworks, Javalin has very few concepts that you need to learn. You never extend classes and you rarely implement interfaces."
>
> https://javalin.io/

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
```

Muilta osin sovellus noudattaa alkuperäistä arkkitehtuuria.

---

Tämän oppimateriaalin on kehittänyt Teemu Havulinna ja se on lisensoitu Creative Commons BY-NC-SA -lisenssillä. 
