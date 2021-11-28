import java.util.Random;

public class Car {

    public static final String[] CAR_MODELS = {"Лада Нива 4х4", "Лада Калина", "Лада Жигули"};
    public static final Random RANDOM = new Random();
    private final String name;

    public Car() {

        this.name = CAR_MODELS[RANDOM.nextInt(CAR_MODELS.length - 1)];
    }

    @Override
    public String toString() {

        return this.name;
    }
}