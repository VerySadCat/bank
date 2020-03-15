package bank;

public class Bank {
    public static void main(String[] args) {
        Runner runner = new Runner(2,1000);
        Thread generatorThread = new Thread(runner);
        generatorThread.start();
    }
}
