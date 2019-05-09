package com.exmple;

import org.junit.Test;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by WeiYang on 5/9/2019.
 *
 * @Author: WeiYang
 * @Package com.exmple
 * @Project: lock-test
 * @Title:
 * @Description: 比较 lock() 和 lockInterruptibly()
 * @Date: 5/9/2019 11:22 AM
 */
public class LockAndInterruptibly {

    /**
     * lock()忽视interrupt(), 拿不到锁就 一直阻塞
     *
     * @throws Exception
     */
    @Test
    public void lockTest() throws Exception {
        final Lock lock = new ReentrantLock();
        lock.lock();
        Thread.sleep(1000);
        Thread t1 = new Thread(() -> {
            lock.lock();
            System.out.println(Thread.currentThread().getName() + " interrupted.");
        });
        t1.start();
        Thread.sleep(1000);
        t1.interrupt();
        Thread.sleep(1000000);
    }

    /**
     * lockInterruptibly()会响应打扰,并catch到InterruptedException
     *
     * @throws Exception
     */
    @Test
    public void interruptiblyTest1() throws Exception {
        final Lock lock = new ReentrantLock();
        lock.lock();
        Thread.sleep(1000);
        Thread t1 = new Thread(() -> {
            try {
                lock.lockInterruptibly();
            } catch (InterruptedException e) {
                e.printStackTrace();
                System.out.println(Thread.currentThread().getName() + " interrupted.");
            }
            System.out.println("go on!");
        });
        t1.start();
        Thread.sleep(2000);
        t1.interrupt();
        Thread.sleep(1000000);
    }

    /**
     * 当线程已经被打扰了（isInterrupted()返回true）。则线程使用lock.lockInterruptibly()，直接会被要求处理InterruptedException。
     *
     * @throws Exception
     */
    @Test
    public void interruptiblyTest2() throws Exception {
        final Lock lock = new ReentrantLock();
        Thread t1 = new Thread(() -> {
            try {
                Thread.sleep(2000);
                lock.lockInterruptibly();
                System.out.println("continue");
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println(Thread.currentThread().getName() + " interrupted.");
            }
            System.out.println("go on!");
        });
        t1.start();
        t1.interrupt();
        Thread.sleep(10000000);
    }


}
