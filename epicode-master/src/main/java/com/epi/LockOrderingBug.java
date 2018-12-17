package com.epi;

public class LockOrderingBug {
    public static void main(String[] args) throws Exception {
        Account from = new Account(200);
        Account to = new Account(100);
        System.out.println("Initial balances = " + from.balance + " " + to.balance);
        Account.transfer(from, to, 50);
        Thread.sleep(100);
        System.out.println("New balances = " + from.balance + " " + to.balance);
    }
    // @exclude

    // @include
    public static class Account {
        private static int globalId;
        private int balance;
        private int id;

        Account(int balance) {
            this.balance = balance;
            this.id = ++globalId;
        }

        public static void transfer(final Account from, final Account to,
                                    final int amount) {
            Thread transfer = new Thread(new Runnable() {
                public void run() {
                    from.move(to, amount);
                }
            });
            transfer.start();
        }

        private boolean move(Account to, int amount) {
            synchronized (this) {
                synchronized (to) {
                    if (amount > balance) {
                        return false;
                    }
                    to.balance += amount;
                    this.balance -= amount;
                    System.out.println("returning true");
                    return true;
                }
            }
        }
    }
}
