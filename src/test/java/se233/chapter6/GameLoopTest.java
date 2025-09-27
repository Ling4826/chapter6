package se233.chapter6;

import javafx.geometry.Point2D;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import se233.chapter6.controller.GameLoop;
import se233.chapter6.model.Direction;
import se233.chapter6.model.Food;
import se233.chapter6.model.Snake;
import se233.chapter6.view.GameStage;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class GameLoopTest {
    private GameStage gameStage;
    private Snake snake;
    private Food food;
    private GameLoop gameLoop;

    @BeforeEach
    public void setUp() {
        gameStage = new GameStage();
        snake = new Snake(new Point2D(0, 0));
        food = new Food(new Point2D(0, 1));
        Stage stage = new Stage();
        gameLoop = new GameLoop(gameStage, snake, food, stage);
    }


    private void clockTickHelper() throws Exception {
        ReflectionHelper.invokeMethod(gameLoop, "keyProcess", new Class<?>[0]);
        ReflectionHelper.invokeMethod(gameLoop, "checkCollision", new Class<?>[0]);
        ReflectionHelper.invokeMethod(gameLoop, "redraw", new Class<?>[0]);
    }

    @Test
    public void keyProcess_pressRight_snakeTurnRight() throws Exception {
        ReflectionHelper.setField(gameStage, "key", KeyCode.RIGHT);
        snake.setDirection(Direction.DOWN);
        clockTickHelper();
        assertEquals(Direction.RIGHT, snake.getDirection());
    }

    @Test
    public void makesomeing() throws Exception {
        int i = 1+1;
        assertEquals(2 , i, "true");
    }

    @Test
    public void collided_snakeEatFood_shouldGrowAndFoodRespawn() throws Exception {
        clockTickHelper();
        assertTrue(snake.getLength() > 1, "Snake should grow");
        assertNotEquals(new Point2D(0, 1), food.getPosition(), "Food should respawn");
    }

    @Test
    public void collided_snakeHitBorder_shouldDie() throws Exception {
        Snake snake1 = new Snake(new Point2D( 99999,9999));
        Boolean running = !snake1.checkDead();
        assertFalse(running, "Game should stop running");
    }

    @Test
    public void redraw_calledThreeTimes_snakeAndFoodShouldRenderThreeTimes() throws Exception {
        GameStage mockGameStage = Mockito.mock(GameStage.class);
        Snake mockSnake = Mockito.mock(Snake.class);
        Food mockFood = Mockito.mock(Food.class);
        Stage mockStage = Mockito.mock(Stage.class);

        GameLoop localGameLoop = new GameLoop(mockGameStage, mockSnake, mockFood, mockStage);

        ReflectionHelper.invokeMethod(localGameLoop, "redraw", new Class<?>[0]);
        ReflectionHelper.invokeMethod(localGameLoop, "redraw", new Class<?>[0]);
        ReflectionHelper.invokeMethod(localGameLoop, "redraw", new Class<?>[0]);

        verify(mockGameStage, times(3)).render(mockSnake, mockFood);
    }
}

