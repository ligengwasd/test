package com.ligeng.test.sync;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizerSource;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * Created by dev on 16-6-17.
 */
public class Mutex implements Lock,Serializable{
    public static void main(String[] args) {
//        sync.getFirstQueuedThread();

    }
    private final Sync sync = new Sync();
    @Override
    public void lock() {
        sync.acquire(1);
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {
        sync.acquireInterruptibly(1);
    }

    @Override
    public boolean tryLock() {
        return sync.tryAcquire(1);
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return sync.tryAcquireNanos(1,unit.toNanos(time));
    }

    @Override
    public void unlock() {
        sync.release(1);
    }

    @Override
    public Condition newCondition() {
        return sync.newCondition();
    }

    public boolean isLocked(){
        return sync.isHeldExclusively();
    }

    public boolean hasQueuedThreads(){
        return sync.hasQueuedThreads();
    }

    private static class Sync extends AbstractQueuedSynchronizerSource {
        //是否独占状态
        protected boolean isHeldExclusively() {
            return getState() == 1;
        }
        protected boolean tryAcquire(int arg) {
            //System.out.println(Thread.currentThread().getName() );
//            "thread2222".equals(Thread.currentThread().getName())
            int i =0;
            if (i == 1){
                throw new RuntimeException();
            }
            if (compareAndSetState(0,1)){
                setExclusiveOwnerThread(Thread.currentThread());
                return true;
            }
            return false;
        }
        protected boolean tryRelease(int arg){
            if (getState() == 0) throw new IllegalMonitorStateException();
            setExclusiveOwnerThread(null);
            setState(0);
            return true;
        }
        Condition newCondition(){
            return new ConditionObject();
        }
    }
}
