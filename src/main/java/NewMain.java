import by.shiler.neurox.event.NewEventBus;

/**
 * Created by Evgeny Yushkevich on 13.06.2017.
 */
public class NewMain {
    public static void main(String[] args) {
        NewEventBus.start();
        NewEventBus.events().subscribe(System.out::println);
    }
}
