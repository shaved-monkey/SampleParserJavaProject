import com.appdetex.sampleparserjavaproject.retrievers.BasicInfo
import com.appdetex.sampleparserjavaproject.retrievers.googleplay.GooglePlay
import org.jsoup.Jsoup
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class GooglePlayTest {

    private val retriever = GooglePlay()
    private val minecraftUrl = "https://play.google.com/store/apps/details?id=com.mojang.minecraftpe&hl=en-US"
    private val minecraftBasicInfo = BasicInfo("Minecraft",
        "Explore infinite worlds and build everything from the simplest of homes to the grandest of castles. Play in creative mode with unlimited resources or mine deep into the world in survival mode, crafting weapons and armor to fend off dangerous mobs. Create, explore and survive alone or with friends on mobile devices or Windows 10.",
        "Mojang",
        "$7.49",
        4.5
    )

    @Test
    fun `The correct url should be parsable by GooglePlay`() {
        Assertions.assertTrue(retriever.compatibleWith(minecraftUrl))
    }

    @Test
    fun `An invalid url should not be parsable by GooglePlay`() {
        Assertions.assertFalse(retriever.compatibleWith("https://apps.apple.com/us/app/minecraft/id479516143"))
    }

    @Test
    fun `Actual call to GooglePlay for Minecraft`() {
        Assertions.assertEquals(minecraftBasicInfo, retriever.extractFrom(minecraftUrl)[0])
    }

    @Test
    fun `fun parse() should match expected output`() {
        val minecraftHtml = "<!doctype html>\n" +
                "<html lang=\"en\" dir=\"ltr\">\n" +
                "\n" +
                " <body id=\"yDmH0d\" jscontroller=\"pjICDe\" jsaction=\"rcuQ6b:npT2md; click:FAbpgf; auxclick:FAbpgf\" class=\"tQj5Y ghyPEc IqBfM ecJEib EWZcud k8Lt0\" data-has-footer=\"true\" data-iw=\"800\" data-ih=\"600\">\n" +
                "    <script type=\"application/ld+json\" nonce=\"plBN2mTo7sW/aJDk0Ypr2A\">{\"@context\":\"https://schema.org\",\"@type\":\"SoftwareApplication\",\"name\":\"Minecraft\",\"url\":\"https://play.google.com/store/apps/details/Minecraft?id\\u003dcom.mojang.minecraftpe\\u0026hl\\u003den_US\\u0026gl\\u003dUS\",\"description\":\"Explore infinite worlds and build everything from the simplest of homes to the grandest of castles. Play in creative mode with unlimited resources or mine deep into the world in survival mode, crafting weapons and armor to fend off dangerous mobs. Create, explore and survive alone or with friends on mobile devices or Windows 10.\\n\\nEXPAND YOUR GAME:\\nMarketplace - Discover the latest community creations in the marketplace! Get unique maps, skins, and texture packs from your favorite creators. \\n\\nSlash commands - Tweak how the game plays: you can give items away, summon mobs, change the time of day, and more. \\n\\nAdd-Ons - Customize your experience even further with free Add-Ons! If you\\u0027re more tech-inclined, you can modify data-driven behaviors in the game to create new resource packs.\\n\\nMULTIPLAYER\\nRealms - Play with up to 10 friends cross-platform, anytime, anywhere on Realms, your own private server that we host for you. Try a free 30-day trial in-app.\\n\\nMultiplayer - Play with up to 4 friends with a free Xbox Live account online.\\nServers - Join free massive multiplayer servers and play with thousands of others! Discover gigantic community-run worlds, compete in unique mini-games and socialize in lobbies full of new friends!\\n\\nSUPPORT: https:///www.minecraft.net/help\\nLEARN MORE: https:///www.minecraft.net/\",\"operatingSystem\":\"ANDROID\",\"applicationCategory\":\"GAME_ARCADE\",\"image\":\"https://play-lh.googleusercontent.com/VSwHQjcAttxsLE47RuS4PqpC4LT7lCoSjE7Hx5AW_yCxtDvcnsHHvm5CTuL5BPN-uRTP\",\"contentRating\":\"Everyone 10+\",\"author\":{\"@type\":\"Person\",\"name\":\"Mojang\",\"url\":\"http://help.mojang.com\"},\"aggregateRating\":{\"@type\":\"AggregateRating\",\"ratingValue\":\"4.546113967895508\",\"ratingCount\":\"4034846\"},\"offers\":[{\"@type\":\"Offer\",\"price\":\"7.49\",\"priceCurrency\":\"USD\",\"availability\":\"https://schema.org/InStock\"}]}</script>\n" +
                " </body>\n" +
                "</html>"
        Assertions.assertEquals(minecraftBasicInfo, retriever.parse(Jsoup.parse(minecraftHtml))[0])
    }
}