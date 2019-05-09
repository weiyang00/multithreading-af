package com.exmple;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by WeiYang on 5/9/2019.
 *
 * @Author: WeiYang
 * @Package com.exmple
 * @Project: lock-test
 * @Title:
 * @Description: Please fill description of the file here
 * @Date: 5/9/2019 9:39 AM
 */
public class LockTest {


    public static void main(String[] args) {

        ReentrantLock reentrantLock = new ReentrantLock(true);
        reentrantLock.lock();

        reentrantLock.unlock();

    }


}
