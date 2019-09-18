package bingfa.demo.CountTest1.sync;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
public class SyncExample2 {


   public void test1(int j){
        synchronized (SyncExample2.class){
            for (int i =0 ; i< 10 ;i++){
                log.info("Test - {} -{}",j,i);
            }
        }
    }
    public static synchronized void test2(int j){
        for (int i =0 ; i< 10 ;i++){
            log.info("Test - {} -{}",j,i);
        }
    }

    public static void main(String[] args) {
        SyncExample2 example1 = new SyncExample2();
        SyncExample2 example2 = new SyncExample2();
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(()->{
            example1.test1(1);
        });
        executorService.execute(()->{
            example2.test2(2);
        });
    }
}
