package pers.bleibtreu.juc;

import java.util.Random;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class DelayQueueDemo {
    public static void main(String[] args) throws Exception {
        // 利用这个类来模拟一个不同的交卷时间
        Random rand = new Random();
        DelayQueue<Student> students = new DelayQueue<Student>();
        for (int x = 0; x < 45; x++) {
            int time = rand.nextInt(10);
            while (time < 3) {
                // 必须保证考试的时间大于3秒
                time = rand.nextInt(10);
            }
            students.put(new Student("学生 - "
                    + x, time, TimeUnit.SECONDS));
        }
        new Thread(new Teacher(students, 45)).start();
    }
}

class Teacher implements Runnable {    // 老师也设置一个多线程
    private int studentCount = 0; // 参与考试的学生数量
    private int submitCount = 0;  // 保存交卷的学生个数
    private DelayQueue<Student> students = null;

    public Teacher(DelayQueue<Student> students,
                   int studentCount) {
        // 保存所有的学生信息
        this.students = students;
        // 保存学生数量
        this.studentCount = studentCount;
    }

    @Override
    public void run() {
        System.out.println("********** 同学们开始答题 ************");
        try {
            // 还有未交卷
            while (this.submitCount < this.studentCount) {
                Student stu = this.students.poll();
                // 有人出队列了，就表示有人交卷了
                if (stu != null) {
                    stu.exam(); // 交卷处理
                    this.submitCount++;  // 交卷的学生个数加1
                }
            }
        } catch (Exception e) {
        }
        System.out.println("********** 同学们结束考试 ************");
    }
}

class Student implements Delayed {
    private String name;
    // 学生交卷时间，使用毫秒单位
    private long submitTime;
    // 实际的考试时间
    private long workTime;

    public Student(String name, long workTime, TimeUnit unit) {
        // 保存名字
        this.name = name;
        // 毫秒是存储单位
        this.workTime = TimeUnit.MILLISECONDS.convert(workTime, unit);
        // 交卷时间
        this.submitTime = System.currentTimeMillis() + this.workTime;
    }

    public void exam() {   // 考试处理
        System.out.println("【" + this.name + "交卷 -｛" + this.submitTime + "｝】交卷时间："
                + System.currentTimeMillis() + "、花费时间："
                + this.workTime);
    }

    @Override
    public int compareTo(Delayed o) {
        return (int) (this.workTime - this.getDelay(TimeUnit.MILLISECONDS));
    }

    @Override
    public long getDelay(TimeUnit unit) {
        return unit.convert(this.submitTime
                        - System.currentTimeMillis(),
                TimeUnit.MILLISECONDS);
    }
}
