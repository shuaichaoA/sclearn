package com.mooner.all;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * 功能描述: 实现callable接口
 *
 * @author: $
 * @date: $ $
 */
public class TestCallable {
    public static void main(String[] args) {
        ThreadDemo td = new ThreadDemo();
        FutureTask<Integer> futureTask = new FutureTask(td);

        new Thread(futureTask).start();
        Integer integer = null;
        try {
            integer = futureTask.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println(integer);

    }

}

class ThreadDemo implements Callable<Integer> {


    public Integer call() throws Exception {
        int sum = 0;
        for (int i = 0; i < 1000; i++) {
            sum += i;
        }

        return sum;
    }
}
