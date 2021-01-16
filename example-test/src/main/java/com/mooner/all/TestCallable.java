package com.mooner.all;

import java.util.HashMap;
import java.util.Map;
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
    public static void main(String[] args) throws InterruptedException {
        Map<String,Tom> map = new HashMap<String,Tom>();
        int counter = 1;
        while(true) {
            Thread.sleep(10);
            Tom tom = new Tom();
            String [] friends = new String[counter];
            for (int i = 0; i < friends.length; i++) {
                friends[i] = "friends"+i;
            }
            tom.setAge(counter);
            tom.setName("tom"+counter);
            tom.setFriends(friends);
            map.put(tom.getName(),tom);
            if(counter%100==0)
                System.out.println("put"+counter);
            counter++;
        }
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
