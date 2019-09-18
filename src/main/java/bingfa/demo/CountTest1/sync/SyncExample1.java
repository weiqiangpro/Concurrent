package bingfa.demo.CountTest1.sync;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
public class SyncExample1 {


   public void test1(int j){
        synchronized (this){
            for (int i =0 ; i< 10 ;i++){
                log.info("Test - {} -{}",j,i);
            }
        }
    }
    public synchronized void test2(int j){
        for (int i =0 ; i< 10 ;i++){
            log.info("Test - {} -{}",j,i);
        }
    }

    public static void main(String[] args) {
        SyncExample1 example1 = new SyncExample1();
        SyncExample1 example2 = new SyncExample1();
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(()->{
            example1.test1(1);
        });
        executorService.execute(()->{
            example2.test2(2);
        });
    }
}
