package io.zipcoder;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Make this extend the Copier like `UnsafeCopier`, except use locks to make sure that the actual intro gets printed
 * correctly every time.  Make the run method thread safe.
 */
public class SafeCopier extends Copier {
    private Lock lock = new ReentrantLock();

    public SafeCopier(String toCopy) {
        super(toCopy);
    }

    public void run() {
//        while(stringIterator.hasNext())
//            lock.lock();
//            try{
//                if(stringIterator.hasNext())
//                    copied += stringIterator.next() + " ";
//            }finally {
//                lock.unlock();
//            }
        //The tryLock() method attempts to lock the Lock instance immediately.
        // It returns true if the locking succeeds, false if Lock is already locked. This method never blocks
        Boolean isLocked = false;
        try{
            isLocked = lock.tryLock(1, TimeUnit.MICROSECONDS);

        }catch (InterruptedException ex)
        {
            ex.printStackTrace();
            System.out.println(ex);
        }
        try
        {
            while ((isLocked && stringIterator.hasNext()))
            {
                String nextWord = stringIterator.next();
                copied += nextWord + " ";
            }
        }finally {

        }
    }
}
