package bingfa.demo.commonTest;

import bingfa.demo.annations.NotThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

@Slf4j
@NotThreadSafe
public class StringExample1 {
    public static int clienttotal = 5000;
    public static  int threadtotal = 200;
    public static StringBuilder str=new StringBuilder("");

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
        log.info("count:{}",str.length());

    }
    public static  void  add(){
        str.append("1");
    }
}
