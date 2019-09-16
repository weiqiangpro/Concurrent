package bingfa.demo;

import lombok.extern.slf4j.Slf4j;
import java.util.concurrent.*;

@Slf4j
public class BfTest {
    private final static Object o = new Object();
    public static int clienttotal = 5000;
    public static  int threadtotal = 200;
    public static    int count = 0;

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(threadtotal);

        final CountDownLatch  countDownLatch  = new CountDownLatch(clienttotal);
        for (int i = 0 ; i < clienttotal;i++ ) {
                executorService.execute(() -> {
                    try {
                        semaphore.acquire();
                        add();
                        semaphore.release();
                    } catch (Exception e) {
                        log.error("exception", e);
                    }
                    countDownLatch.countDown();
                });
            }
        countDownLatch.await();
        executorService.shutdown();
        log.info("count:{}",count);

    }
    public static  void  add(){
        count++;
        System.out.println(123124);
    }
}
