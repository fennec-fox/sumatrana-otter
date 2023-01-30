package io.mustelidae.otter.sumatrana.api.domain.slack

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test


class BlockKitTest {
    private val mapper = SlackResources.getMapper()
    @Test
    fun textTest() {

        val payload = SlackResources.Payload(
            listOf(
                BlockKit.Section(
                    BlockKit.Text(
                        BlockKit.Text.Type.mrkdwn,
                        "Hello, Assistant to the Regional Manager Dwight! *Michael Scott* wants to know where you'd like to take the Paper Company investors to dinner tonight.\n\n *Please select a restaurant:*"
                    ),
                ),
                BlockKit.Divider(),
                BlockKit.Section(
                    BlockKit.Text(
                        BlockKit.Text.Type.mrkdwn,
                        "*Farmhouse Thai Cuisine*\n:star::star::star::star: 1528 reviews\n They do have some vegan options, like the roti and curry, plus they have a ton of salad stuff and noodles can be ordered without meat!! They have something for everyone here"
                    ),
                    accessory = BlockKit.Image(
                        "https://s3-media3.fl.yelpcdn.com/bphoto/c7ed05m9lC2EmA3Aruue7A/o.jpg",
                        "alt text for image"
                    )
                )
            )
        )

        val json = mapper.writeValueAsString(payload)
        json shouldBe """
            {"blocks":[{"text":{"type":"mrkdwn","text":"Hello, Assistant to the Regional Manager Dwight! *Michael Scott* wants to know where you'd like to take the Paper Company investors to dinner tonight.\n\n *Please select a restaurant:*"},"type":"section"},{"type":"divider"},{"text":{"type":"mrkdwn","text":"*Farmhouse Thai Cuisine*\n:star::star::star::star: 1528 reviews\n They do have some vegan options, like the roti and curry, plus they have a ton of salad stuff and noodles can be ordered without meat!! They have something for everyone here"},"accessory":{"image_url":"https://s3-media3.fl.yelpcdn.com/bphoto/c7ed05m9lC2EmA3Aruue7A/o.jpg","alt_text":"alt text for image","type":"image"},"type":"section"}]}
        """.trimIndent()
    }

    @Test
    fun button() {
        val payload = SlackResources.Payload(
            listOf(
                BlockKit.Context(
                    listOf(
                        BlockKit.Text(
                            BlockKit.Text.Type.plain_text,
                            "Author: K A Applegate",
                            true
                        ),
                        BlockKit.Image(
                            "https://pbs.twimg.com/profile_images/625633822235693056/lNGUneLX_400x400.jpg",
                            "cute cat"
                        )
                    )
                ),
                BlockKit.Section(
                    BlockKit.Text(
                        BlockKit.Text.Type.mrkdwn,
                        "This is a section block with a button."
                    ),
                    accessory = BlockKit.Button(
                        text = BlockKit.Text(
                            BlockKit.Text.Type.plain_text,
                            "Click Me",
                            true
                        ),
                        value = "click_me_123",
                        url = "https://google.com",
                        actionId = "button-action"
                    )
                )
            )
        )
        val json = mapper.writeValueAsString(payload)
        json shouldBe """
            {"blocks":[{"elements":[{"type":"plain_text","text":"Author: K A Applegate","emoji":true},{"image_url":"https://pbs.twimg.com/profile_images/625633822235693056/lNGUneLX_400x400.jpg","alt_text":"cute cat","type":"image"}],"type":"context"},{"text":{"type":"mrkdwn","text":"This is a section block with a button."},"accessory":{"text":{"type":"plain_text","text":"Click Me","emoji":true},"action_id":"button-action","url":"https://google.com","value":"click_me_123","type":"button"},"type":"section"}]}
        """.trimIndent()

    }
}