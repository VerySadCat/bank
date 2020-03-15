package bank;

import java.util.Random;

public class Runner implements Runnable {

    int bank;
    int employeeCount;

    Random random;
    Thread pool[];
    Employee[] employee;


    public Runner(int employeeCount, int bank) {
        this.employeeCount = employeeCount;
        employee = new Employee[employeeCount];
        pool = new Thread[employeeCount];
        this.random = new Random();
        this.bank = bank;
    }

    synchronized public void run() {
        //Запуск потока работников
        for (int i = 0; i < employeeCount; i++) {
            employee[i] = new Employee(this);

            pool[i] = new Thread(employee[i]);
            pool[i].start();
        }

        while (true) {
            for(int i=0;i<employeeCount;i++){
                if(pool[i].getState() == Thread.State.WAITING){
                    searchSmallerQueue().addClient(new Client());
                }
            }



        }
    }


    private Employee searchSmallerQueue() {
        Employee minQueue;
        minQueue = employee[0];
        for (int i = 0; i < employeeCount; i++) {
            if (employee[i].queue.size() < minQueue.queue.size()) {
                minQueue = employee[i];

            }
        }
        return minQueue;
    }
}
