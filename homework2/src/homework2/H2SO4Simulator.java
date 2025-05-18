package homework2;

import java.util.concurrent.Semaphore;

public class H2SO4Simulator {
    static final int COUNT = 1000;

    static Semaphore s = new Semaphore(0);
    static Semaphore o2a = new Semaphore(0);
    static Semaphore o2b = new Semaphore(0);
    static Semaphore h2o = new Semaphore(0);
    static Semaphore so2 = new Semaphore(0);
    static Semaphore so3 = new Semaphore(0);

    public static void main(String[] args) {
        for (int i = 1; i <= COUNT; i++) {
            int n = i;

            
            new Thread(() -> {
                System.out.println("NUM=" + n + " S Created");
                s.release();
            }).start();

           
            new Thread(() -> {
                System.out.println("NUM=" + n + " O2 (1) Created");
                o2a.release();
            }).start();

         
            new Thread(() -> {
                System.out.println("NUM=" + n + " O2 (2) Created");
                o2b.release();
            }).start();

         
            new Thread(() -> {
                System.out.println("NUM=" + n + " H2O Created");
                h2o.release();
            }).start();

     
            new Thread(() -> {
                try {
                    s.acquire();
                    o2a.acquire();
                    System.out.println("NUM=" + n + " SO2 Created");
                    so2.release();
                } catch (Exception e) {
                }
            }).start();

          
            new Thread(() -> {
                try {
                    so2.acquire();
                    o2b.acquire();
                    System.out.println("NUM=" + n + " SO3 Created");
                    so3.release();
                } catch (Exception e) {
                }
            }).start();

            new Thread(() -> {
                try {
                    so3.acquire();
                    h2o.acquire();
                    System.out.println("NUM=" + n + " H2SO4 Created");
                } catch (Exception e) {
                }
            }).start();
        }
    }
}
