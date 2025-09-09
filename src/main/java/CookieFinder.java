import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class CookieFinder {

    public static void main(String[] args) {
        String fileName = null;
        String date = null;

        // Simple argument parsing
        for (int i = 0; i < args.length; i++) {
            switch (args[i]) {
                case "-f":
                    if (i + 1 < args.length) fileName = args[++i];
                    break;
                case "-d":
                    if (i + 1 < args.length) date = args[++i];
                    break;
            }
        }

        if (fileName == null || date == null) {
            System.err.println("Usage: java -jar most-active-cookie-1.0-SNAPSHOT.jar -f <filename> -d <YYYY-MM-DD>");
            System.exit(1);
        }

        try {
            List<String> cookies = loadCookies(fileName, date);
            List<String> mostActive = mostActiveCookies(cookies);
            for (String cookie : mostActive) {
                System.out.println(cookie);
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
            System.exit(1);
        }
    }

    private static List<String> loadCookies(String fileName, String date) throws IOException {
        List<String> cookies = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line = br.readLine(); // skip header
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length != 2) continue;
                String cookie = parts[0];
                String timestamp = parts[1];
                if (timestamp.startsWith(date)) {
                    cookies.add(cookie);
                }
            }
        }
        return cookies;
    }

    public static List<String> mostActiveCookies(List<String> cookies) {
        if (cookies.isEmpty()) return Collections.emptyList();
        Map<String, Integer> countMap = new HashMap<>();
        int maxCount = 0;
        for (String cookie : cookies) {
            int newCount = countMap.getOrDefault(cookie, 0) + 1;
            countMap.put(cookie, newCount);
            maxCount = Math.max(maxCount, newCount);
        }
        List<String> result = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : countMap.entrySet()) {
            if (entry.getValue() == maxCount) result.add(entry.getKey());
        }
        return result;
    }
}
