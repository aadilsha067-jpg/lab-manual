import java.util.concurrent.Semaphore;

class Foo {
    private Semaphore sem2;
    private Semaphore sem3;

    public Foo() {
        // Initialize semaphores with 0 permits so second() and third() wait
        sem2 = new Semaphore(0);
        sem3 = new Semaphore(0);
    }

    public void first(Runnable printFirst) throws InterruptedException {
        // printFirst.run() outputs "first". Do not change or remove this line.
        printFirst.run();
        // Release permit to allow second() to proceed
        sem2.release();
    }

    public void second(Runnable printSecond) throws InterruptedException {
        // Wait for first() to finish
        sem2.acquire();
        // printSecond.run() outputs "second". Do not change or remove this line.
        printSecond.run();
        // Release permit to allow third() to proceed
        sem3.release();
    }

    public void third(Runnable printThird) throws InterruptedException {
        // Wait for second() to finish
        sem3.acquire();
        // printThird.run() outputs "third". Do not change or remove this line.
        printThird.run();
    }
}