package thread;

import java.util.Locale;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author malf
 * @Description 同步机制的使用情况示例
 * @project wechatStudy
 * @since 2020/11/23
 */
public class SynchronizedTest {
    public static void main(String[] args) {
        Account account = new Account();
        ExecutorService service = Executors.newFixedThreadPool(100);
        for (int i = 0; i < 100; i++) {
            service.execute(new AddMoneyThread(account, 1));
        }
        service.shutdown();
        while (!service.isTerminated()) {

        }
        System.out.println("账户余额：" + account.getBalance());
    }
}

class AddMoneyThread implements Runnable {
    private Account account;
    private double money;

    public AddMoneyThread(Account account, double money) {
        this.account = account;
        this.money = money;
    }

    @Override
    public void run() {
        // 方法2：synchronized 实现同步
        //synchronized (account) {
            account.deposit(money);
        //}
    }
}

class Account {
    private Lock accountLock = new ReentrantLock();
    private double balance;

    // 存入金额
    // 方法1：加 synchronized
    //public synchronized void deposit(double money) {
    public void deposit(double money) {
        // 方法3：ReentrantLock
        accountLock.lock();
        try {
            double newBalance = balance + money;
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
            }
            balance = newBalance;
        } finally {
            accountLock.unlock();
        }
    }

    // 获得余额
    public double getBalance() {
        return balance;
    }
}