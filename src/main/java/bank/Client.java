package bank;

import java.util.Random;
import java.util.UUID;

public class Client {
    String id;
    double money;
    int operation; // 0 вклад, 1 изъятие
    int time;

    Client() {
        Random rand = new Random();
        this.id = UUID.randomUUID().toString();
        this.money = rand.nextInt(1000);
        this.operation = rand.nextInt(2);
        this.time = rand.nextInt(10000);
    }

    String getNameOperation() {
        if (this.operation == 0) {
            return "положил";
        } else {
            return "снял";
        }
    }
}
