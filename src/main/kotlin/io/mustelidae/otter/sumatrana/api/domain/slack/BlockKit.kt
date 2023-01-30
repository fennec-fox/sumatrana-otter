package io.mustelidae.otter.sumatrana.api.domain.slack


/**
 * use block kit builder
 * @ref https://app.slack.com/block-kit-builder
 */
class BlockKit {

    interface Block

    /**
     * @ref https://api.slack.com/reference/block-kit#elements
     */
    interface BlockKitElement: Block

    /**
     * https://api.slack.com/reference/block-kit#objects
     */
    interface BlockKitObject: Block

    /**
     * @ref https://api.slack.com/reference/block-kit#blocks
     */
    interface BlockKitBlock: Block

    data class Context(
        /** Only images BlockElement and text CompositionObject are allowed.*/
        val elements: List<BlockKitObject>,
        val blockId: String? = null
    ): BlockKitBlock {
        val type = "context"
    }

    data class Section(
        val text:Text,
        val blockId: String? = null,
        val fields: List<Text>? = null,
        val accessory: BlockKitElement? = null
    ): BlockKitBlock {
        val type = "section"
    }

    class Divider: BlockKitBlock {
        val type = "divider"
    }


    data class Button(
        val text: Text,
        val actionId: String,
        val url: String? = null,
        val value: String? = null,
        val style: Style? = null,
        val confirm: ConfirmationDialog? = null,
        val accessibilityLabel: Text? = null
    ) : BlockKitElement {
        val type = "button"

        enum class Style {
            primary,
            danger
        }
    }

    data class Checkbox(
        val actionId: String,
        val options: List<Option>,
        val initialOptions: List<Option>? = null,
        val confirm: ConfirmationDialog? = null,
        val focusOnLoad: Boolean? = null
    ) : BlockKitElement {
        val type = "checkboxes"
    }

    data class Image(
        val imageUrl: String,
        val altText: String
    ): BlockKitElement, BlockKitObject {
        val type = "image"
    }

    data class Text(
        val type: Type,
        val text: String,
        val emoji: Boolean? = null,
        val verbatim: Boolean? = null
    ): BlockKitObject {
        enum class Type {
            plain_text,
            mrkdwn
        }
    }

    data class ConfirmationDialog(
        val title: Text,
        val text: Text,
        val confirm: Text,
        val deny: Text,
        val style: Style? = null
    ): BlockKitObject {
        enum class Style {
            danger,
            primary
        }
    }

    data class Option(
        val text: Text,
        val value: String
    ): BlockKitObject

    data class OptionGroup(
        val label: Text,
        val options: List<Option>
    ): BlockKitObject
}