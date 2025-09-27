package se233.chapter6.model;

import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import se233.chapter6.view.GameStage;
import java.util.Random;


public class Food {
    private Point2D position;
    private Random rn;
    private static final Logger logger = LogManager.getLogger(Food.class);

    public FoodType getFoodType() {
        return foodType;
    }

    public void setFoodType(FoodType foodType) {
        this.foodType = foodType;
    }

    private FoodType foodType;

    public Food(Point2D position ) {
        this.rn = new Random();
        this.position = position;
        if (rn.nextInt(2) == 0) {
            this.foodType = FoodType.SPECIAL;
        } else {
            this.foodType = FoodType.NORMAL;
        }
    }

    public Food() {
        this.rn = new Random();
        this.position = new Point2D(rn.nextInt(GameStage.WIDTH), rn.nextInt(GameStage.HEIGHT));
        if (rn.nextInt(2) == 0) {
            this.foodType = FoodType.SPECIAL;
        } else {
            this.foodType = FoodType.NORMAL;
        }
    }

    public void respawn() {
        Point2D prev_position = this.position;
        do {
            this.position = new Point2D(rn.nextInt(GameStage.WIDTH), rn.nextInt(GameStage.HEIGHT));
            if (rn.nextInt(2) == 0) {
                this.foodType = FoodType.SPECIAL;
            } else {
                this.foodType = FoodType.NORMAL;
            }
        } while (prev_position.equals(this.position));
        logger.info("food: x:{} y:{}",this.position.getX(), this.position.getY());
    }
    public Color getColor() {
        if (this.foodType == FoodType.SPECIAL) {
            return Color.GREEN;
        } else {
            return Color.RED;
        }
    }
    public Point2D getPosition() {
        return position;
    }
}