package bingfa.demo.CountTest1.atomic;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

@Slf4j
public class CountExample2 {
    private final static Object o = new Object();
    public static int clienttotal = 5000;
    public static  int threadtotal = 200;
    public static AtomicLong count= new AtomicLong(0);

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
        log.info("count:{}",count.get());

    }
    public static  void  add(){
        count.getAndIncrement();
    }
}
