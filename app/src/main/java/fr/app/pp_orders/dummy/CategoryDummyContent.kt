package fr.app.pp_orders.dummy

import fr.app.pp_orders.model.CategoryItem
import fr.app.pp_orders.model.PlateItem
import java.time.ZonedDateTime
import java.util.*
import kotlin.collections.ArrayList

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 *
 */
object CategoryDummyContent {

    /**
     * An array of sample (dummy) items.
     */
    val ITEMS: MutableList<CategoryItem> = ArrayList()

    /**
     * A map of sample (dummy) items, by ID.
     */
    val ITEM_MAP: MutableMap<String, CategoryItem> = HashMap()

    init {

        val accompagnementPlates = listOf(
            PlateItem("Demi pain céréales", "", 0.5, ZonedDateTime.parse("2020-03-09T01:00:00.717Z"), "Accompagnements"),
            PlateItem("Demi pain traditionnel blanc", "", 0.4, ZonedDateTime.parse("2020-03-09T01:00:00.717Z"), "Accompagnements"),
            PlateItem("Grand pain céréales", "", 1.0, ZonedDateTime.parse("2020-03-09T01:00:00.717Z"), "Accompagnements"),
            PlateItem("Grand pain traditionnel blanc", "", 0.8, ZonedDateTime.parse("2020-03-09T01:00:00.716Z"), "Accompagnements"),
            PlateItem("Ingrédient supplémentaire au choix sur la Carte", "", 1.0, ZonedDateTime.parse("2020-03-09T01:00:00.718Z"), "Accompagnements"),
            PlateItem("La Balsamique  (Made By LPP)", "Vinaigrette à base d'huile d'olive et de vinaigre balsamique", 0.5, ZonedDateTime.parse("2020-03-09T01:00:00.717Z"), "Accompagnements"),
            PlateItem("La French Dressing  (Made By LPP)", "Vinaigrette à base de mayonnaise", 0.5, ZonedDateTime.parse("2020-03-09T01:00:00.718Z"), "Accompagnements"),
            PlateItem("La Tandoori  (Made By LPP)", "Vinaigrette à base d'épices Tandoori", 0.5, ZonedDateTime.parse("2020-03-09T01:00:00.718Z"), "Accompagnements")
        )

        val boissonsPlates = listOf(
            PlateItem("Bière Kronenbourg  33cl (1)", "", 2.1, ZonedDateTime.parse("2020-03-09T01:00:00.710Z"), "Boissons"),
            PlateItem("Bière Kronenbourg 50cl (1)", "", 2.6, ZonedDateTime.parse("2020-03-09T01:00:00.711Z"), "Boissons"),
            PlateItem("Coca Cola 33cl", "", 1.6, ZonedDateTime.parse("2020-03-09T01:00:00.702Z"), "Boissons")
        )

        ITEMS.addAll(listOf(
            CategoryItem("1", "Accompagnements", ZonedDateTime.parse("2020-03-09T01:00:00.668Z"), accompagnementPlates),
            CategoryItem("2", "Boissons", ZonedDateTime.parse("2020-03-09T01:00:00.668Z"), boissonsPlates),
            CategoryItem("3", "Desserts", ZonedDateTime.parse("2020-03-09T01:00:00.668Z"), ArrayList()),
            CategoryItem("4", "Plats du Jour", ZonedDateTime.parse("2020-03-09T01:00:00.668Z"), ArrayList()),
            CategoryItem("5", "Salades", ZonedDateTime.parse("2020-03-09T01:00:00.668Z"), ArrayList()),
            CategoryItem("6", "Sandwichs", ZonedDateTime.parse("2020-03-09T01:00:00.668Z"), ArrayList()),
            CategoryItem("7", "Sandwichs Chauds ", ZonedDateTime.parse("2020-03-09T01:00:00.668Z"), ArrayList()),
            CategoryItem("8", "Soupes", ZonedDateTime.parse("2020-03-09T01:00:00.668Z"), ArrayList())
        ))

        ITEMS.forEach{
            ITEM_MAP.put(it.id, it)
        }
    }
}
