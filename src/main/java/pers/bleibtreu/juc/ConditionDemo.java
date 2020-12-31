package pers.bleibtreu.juc;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConditionDemo {

    public static void main(String[] args) throws Exception {
        DataBuffer<String> buffer = new DataBuffer<String>();
        for (int x = 0; x < 3; x++) {   // 创建三个写线程
            new Thread(() -> {
                for (int y = 0; y < 2; y++) {
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    buffer.put(Thread.currentThread().getName() + "写入数据，y = " + y);
                }
            }, "生产者-" + x).start();
        }
        for (int x = 0; x < 5; x++) {   // 创建五个读线程
            new Thread(() -> {
                while (true) {
                    try {
                        TimeUnit.SECONDS.sleep(3);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("【（" + Thread.currentThread().getName() + "）CONSUMER】" + buffer.get());
                }
            }, "消费者-" + x).start();
        }
    }
}

// 进行数据的缓冲的操作控制，该缓冲可以保存各种数据类型
class DataBuffer<T> {
    // 该类之中保存的数组的长度个数为5
    private static final int MAX_LENGTH = 5;
    // 定义一个数组进行全部数据的保存控制
    private Object[] data = new Object[MAX_LENGTH];
    // 创建数据锁
    private Lock myLock = new ReentrantLock();
    // 数据保存的Condition控制
    private Condition putCondition = myLock.newCondition();
    // 数据取得的Condition控制
    private Condition getCondition = myLock.newCondition();
    private int putIndex = 0; // 保存数据的索引
    private int getIndex = 0;     // 读取数据的索引
    private int count = 0;    // 当前保存的元素个数

    public T get() {   // 根据缓冲读取数据
        Object takeObject = null;
        this.myLock.lock();
        try {
            if (this.count == 0) { // 没有写入
                // 读取的线程要进行等待
                this.getCondition.await();
            }
            // 读取指定索引数据
            takeObject = this.data[this.getIndex++];
            if (this.getIndex == MAX_LENGTH) {
                this.getIndex = 0;    // 重新开始读
            }
            // 因为读取了一个数据之后，现在需要减少个数
            this.count--;
            // 告诉写线程可以写入
            this.putCondition.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.myLock.unlock();
        }
        return (T) takeObject;
    }

    // 进行缓冲数据的写入处理
    public void put(T t) {
        // 进入独占锁定状态
        this.myLock.lock();
        try {
            // 保存的数据量已经满了
            if (this.count == MAX_LENGTH) {
                // 暂时先别进行数据保存了
                this.putCondition.await();
            }
            // 保存当前的数据
            this.data[this.putIndex++] = t;
            // 现在索引已经写满
            if (this.putIndex == MAX_LENGTH) {
                // 重置数组操作的索引脚标
                this.putIndex = 0;
            }
            // 保存的个数需要做一个追加
            this.count++;
            this.getCondition.signal(); // 唤醒消费线程
            System.out.println("【（" + Thread.currentThread().getName() + "）写入缓冲-put()】" + t);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 不管如何最终一定要进行解锁
            this.myLock.unlock();
        }
    }

}
