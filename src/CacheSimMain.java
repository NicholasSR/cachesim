import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class CacheSimMain {

    public static void resetStoreA() {
        try {
            PrintWriter pw  = new PrintWriter("test_cases/storeA.txt");
            for (int i = 0; i < 16; ++i) {
                pw.println(Integer.toString(i) + " " + Integer.toString(i));
            }
            pw.close();
        } catch (IOException ioe) {
            System.out.println("Couldn't reset test_cases/storeA.txt");
            System.exit(1);
        }
    }

    public static String parseCacheType() {
        Scanner scanner = new Scanner(System.in);
        String cacheType = "";
        while (cacheType.isEmpty()) {
            System.out.println("Which cache type would you like to simulate?");
            System.out.println("Enter \"L\" for least-recently used, \"D\" for direct-mapped, \"F\" for FIFO.");
            String in = scanner.next();
            if (in.equals("L") || in.equals("D")) {
                cacheType = in;
            }
        }

        return cacheType;
    }

    public static Cache getCache(String cacheType, int capacity, String backingStore) {
        Cache cache;
        switch (cacheType) {
            case "D" : {
                cache = new DirectMappedCache(capacity, backingStore);
                break;
            }
            case "L" : {
                cache = new Cache(capacity, backingStore);
                break;
            }
            default : {
                cache = null;
            }
        }
        return cache;
    }

    public static void main(String[] args) {

        resetStoreA();
        String inputFile = "test_cases/Test5.txt";
        String outputFile = "output.txt";
        int capacity = 0;
        String fname = "";
        ArrayList<Integer> keys = new ArrayList<>();
        ArrayList<Integer> data = new ArrayList<>();

        String cacheType = "D";//parseCacheType();

        try {
            BufferedReader br = new BufferedReader(new FileReader(inputFile));
            String line = br.readLine();
            String[] vals = line.split(" ");
            if (vals.length == 2) {
                capacity = Integer.parseInt(vals[0]);
                fname = vals[1];
            } else {
                throw new NumberFormatException();
            }
            vals = null;

            //accumulate keys one per line
            while ((line = br.readLine()) != null) {
                vals = line.split(" ");
                if (vals.length == 2) {
                    keys.add(Integer.parseInt(vals[0]));
                    data.add(Integer.parseInt(vals[1]));
                } else {
                    keys.add(Integer.parseInt(line));
                    data.add(null);
                }
            }

            Cache cache = getCache(cacheType, capacity, fname);
            CacheSim sim = new CacheSim(cache);
            sim.simulate(keys,data);

            PrintWriter pw = new PrintWriter(outputFile);
            pw.println("Cache Misses: " + Integer.toString(sim.getCacheMisses()));
            pw.println("Total Time: " + Float.toString(sim.getTotalResponseTime()));
            ArrayList<String> history = sim.getCacheHistory();
            for (String h : history) {
                pw.println(h);
            }
            pw.close();

        } catch (IOException ioe) {
            System.out.println("Failed to read or write file.");
            System.out.println(ioe);
        } catch (NumberFormatException nfe) {
            System.out.println("Failed to parse input file.");
            System.out.println(nfe);
        }
    }
}
