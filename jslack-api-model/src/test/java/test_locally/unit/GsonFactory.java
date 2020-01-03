package test_locally.unit;

import com.github.seratch.jslack.api.model.block.ContextBlockElement;
import com.github.seratch.jslack.api.model.block.LayoutBlock;
import com.github.seratch.jslack.api.model.block.composition.TextObject;
import com.github.seratch.jslack.api.model.block.element.BlockElement;
import com.github.seratch.jslack.api.model.block.element.RichTextElement;
import com.github.seratch.jslack.common.json.*;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class GsonFactory {
    private GsonFactory() {
    }

    public static Gson createSnakeCase() {
        return createSnakeCase(false, true);
    }

    public static Gson createSnakeCaseWithoutUnknownPropertyDetection(boolean failOnUnknownProperties) {
        return createSnakeCase(failOnUnknownProperties, false);
    }

    public static Gson createSnakeCase(boolean failOnUnknownProperties, boolean unknownPropertyDetection) {
        GsonBuilder builder = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .registerTypeAdapter(LayoutBlock.class, new GsonLayoutBlockFactory(failOnUnknownProperties))
                .registerTypeAdapter(BlockElement.class, new GsonBlockElementFactory(failOnUnknownProperties))
                .registerTypeAdapter(ContextBlockElement.class, new GsonContextBlockElementFactory(failOnUnknownProperties))
                .registerTypeAdapter(TextObject.class, new GsonTextObjectFactory(failOnUnknownProperties))
                .registerTypeAdapter(RichTextElement.class, new GsonRichTextElementFactory(failOnUnknownProperties));

        if (unknownPropertyDetection) {
            return builder.registerTypeAdapterFactory(new UnknownPropertyDetectionAdapterFactory()).create();
        } else {
            return builder.create();
        }
    }
}
