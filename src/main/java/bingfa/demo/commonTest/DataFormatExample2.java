package bingfa.demo.commonTest;

import bingfa.demo.annations.NotThreadSafe;
import bingfa.demo.annations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

@Slf4j
@ThreadSafe
public class DataFormatExample2 {
    public static int clienttotal = 5000;
    public static  int threadtotal = 200;

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

    }
    public static  void  add(){
        try {
            SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyymmdd");

            simpleDateFormat.parse("20190925");
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
