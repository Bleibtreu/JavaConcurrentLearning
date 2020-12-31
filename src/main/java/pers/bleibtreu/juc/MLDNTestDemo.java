package pers.bleibtreu.juc;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MLDNTestDemo {
    public static void main(String[] args) throws Exception {
        Ticket ticket = new Ticket() ; // 多个线程要共享同一个数据资源
        for (int x = 0 ; x < 6 ; x ++) {
            new Thread(() -> {
                while (true) {
                    ticket.sale();     // 卖票处理
                }
            }).start();
        }
    }
}

class Ticket {
    private Lock myLock = new ReentrantLock();
    private int count = 100; // 一共10张票

    public void sale() { // 进行卖票处理
        myLock.lock(); // 进入到阻塞状态，一直到unlock执行后解除阻塞
        try {
            if (this.count > 0) {
                System.out.println(
                        Thread.currentThread().getName()
                                + "卖票，ticket = " + this.count--);
            }
        } finally {
            myLock.unlock();   // 不管最终如何结果一定要进行解锁
        }
    }
}
