package pers.bleibtreu.juc;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 当Executors创建完成了线程池之后可以返回“ExecutorService”接口对象，而这个接口对象里面有两个方法来接收线程的执行
 */
public class ThreadPoolDemo {

    private static Lock lock = new ReentrantLock();

    public static void main(String[] args) throws Exception {
//        singleThreadPool();
        infiniteThreadPool();
//        limitedThreadPool();
//        scheduledThreadPool();
//        scheduledAtFixedRateThreadPool();
    }

    /**
     * 创建单线程池 : public static ScheduledExecutorService newSingleThreadScheduledExecutor();
     */
    public static void singleThreadPool() {
        ExecutorService service = Executors.newSingleThreadExecutor();
        for (int x = 0; x < 100; x++) {
            // 线程池会负责启动
            service.submit(() -> {
                calculation();
            });
        }
        service.shutdown(); // 线程池执行完毕后需要关闭
    }

    /**
     * 创建无大小限制的线程池 : public static ExecutorService newCacheThreadPool();
     */
    public static void infiniteThreadPool() {
        ExecutorService service = Executors.newCachedThreadPool();
        for (int x = 0; x < 100; x++) {
            // 线程池会负责启动
            service.submit(() -> {
                calculation();
            });
        }
        service.shutdown(); // 线程池执行完毕后需要关闭
    }

    /**
     * 创建固定大小的线程池 : public static ExecutorService newFixedThreadPool(int nThreads);
     */
    public static void limitedThreadPool() {
        // 线程池只能够装下3个人
        ExecutorService service = Executors.newFixedThreadPool(3);
        for (int x = 0; x < 100; x++) {
            // 线程池会负责启动
            service.submit(() -> {
                calculation();
            });
        }
        service.shutdown(); // 线程池执行完毕后需要关闭
    }

    /**
     * 创建定时调度池 : public static ScheduledExecutorService newScheduledThreadPool(int corePoolSize);
     */
    public static void scheduledThreadPool() {
        // 创建一个调度池
        ScheduledExecutorService service = Executors.newScheduledThreadPool(1);
        for (int x = 0; x < 100; x++) {
            // 线程池会负责启动
            service.schedule(() -> {
                calculation();
            }, 2, TimeUnit.SECONDS);
        }
        service.shutdown(); // 线程池执行完毕后需要关闭

    }

    /**
     * 创建定时调度池 : public static ScheduledExecutorService newScheduledThreadPool(int corePoolSize);
     */
    public static void scheduledAtFixedRateThreadPool() {
        // 线程池只能够装下3个人
        ScheduledExecutorService service = Executors.newScheduledThreadPool(1);
        for (int x = 0; x < 100; x++) {
            // 线程池会负责启动
            service.scheduleAtFixedRate(() -> {
                calculation();
            }, 2, 2, TimeUnit.SECONDS);
        }
        service.shutdown(); // 线程池执行完毕后需要关闭
    }

    /**
     * 测试方法
     */
    public synchronized static void calculation() {
        Random random = new Random(100);
        System.out.print(Thread.currentThread().getName() + " 执行操作 -> ");
        System.out.println("结果等于：" + random.nextInt() * random.nextInt());
    }
}
