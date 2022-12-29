package thread;

/**
 *
 * @author Rifat
 */
public class Atm_Booth_Banking extends Thread {

    static int amount = 1000;

    synchronized public  void Deposit(int amount) {
        try {
            this.amount += amount;
            System.out.println("Depostit : " + amount + " Total balance : " + this.amount);
            notify();
        } catch (Exception e) {
        }
    }

    synchronized public void withdraw(int amount) {
        try {
            if (amount == 500 || amount == 1000) {
                if (this.amount < amount) {
                    System.out.println("Insufficient Balance");
                    wait();
                }
                this.amount -= amount;
                System.out.println("Withdraw : " + amount + " Total balance : " + this.amount);
            }
        } catch (Exception e) {
        }

    }

    public static void main(String[] args) {
        Atm_Booth_Banking b = new Atm_Booth_Banking();
        new Thread() {
            @Override
            public void run() {
                b.withdraw(1000);
            }

        }.start();
        new Thread() {
            @Override
            public void run() {
                b.Deposit(1000);
            }

        }.start();

    }
}
