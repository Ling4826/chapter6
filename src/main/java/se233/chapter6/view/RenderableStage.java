package se233.chapter6.view;

import javafx.scene.input.KeyCode;
import se233.chapter6.model.Food;
import se233.chapter6.model.Snake;

public interface RenderableStage {
    void render(Snake snake, Food food);
    KeyCode getKey();
}

