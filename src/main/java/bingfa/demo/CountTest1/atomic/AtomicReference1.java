package bingfa.demo.CountTest1.atomic;

import bingfa.demo.annations.ThreadSafe;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicReference;

@Slf4j
@ThreadSafe
public class AtomicReference1 {
    private static AtomicReference<Integer> atomicReference = new AtomicReference<>(0);
    public static void main(String[] args) {
        atomicReference.compareAndSet(0,2);
        atomicReference.compareAndSet(0,1);
        atomicReference.compareAndSet(1,3);
        atomicReference.compareAndSet(2,4);
         log.info("{}",atomicReference);
    }
}
