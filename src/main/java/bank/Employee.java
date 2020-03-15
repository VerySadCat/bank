package bank;

import java.util.ArrayDeque;
import java.util.Queue;

import static java.lang.Thread.sleep;

public class Employee implements Runnable {

    Runner runner;

    Queue<Client> queue = new ArrayDeque<Client>();

    public Employee(Runner runner) {
        this.runner = runner;
        queue.add(new Client());
    }

    synchronized public void addClient(Client client) {
        queue.add(client);
        notify();

    }

    synchronized public void run() {
        while (true) {
            if (queue.size() > 0) {
                try {
                    Long kassa = Thread.currentThread().getId() - 13l;
                    System.out.println("Клиент " + queue.peek().id + " обслуживается в кассе номер " +kassa);
                    System.out.println("Операция " + queue.peek().getNameOperation() + queue.peek().money);
                    if (queue.peek().operation == 0) {
                        runner.bank += queue.peek().money;

                        System.out.println("Остаток на счету:" + runner.bank);
                        sleep(queue.peek().time);
                        queue.remove();
                    } else {
                        if (queue.peek().money < runner.bank) {
                            runner.bank -= queue.peek().money;

                            System.out.println("Остаток на счету:" + runner.bank);
                            sleep(queue.peek().time);
                            queue.remove();
                        } else {
                            System.out.println("В хранилище недостаточная сумма! Клиент " + queue.peek().id + " не может быть обслужен!");
                        }
                    }

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }else {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }
    }
}


