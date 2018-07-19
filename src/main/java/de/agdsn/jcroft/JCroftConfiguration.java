package de.agdsn.jcroft;

import java.io.*;
import java.util.HashMap;

public class JCroftConfiguration {
    private static final File conf = new File("settings/jcroft.cfg");
    private static HashMap<String, String> values = new HashMap<>();

    /**
    * private constructor
    */
    protected JCroftConfiguration () {
        //
    }

    protected static void readConfig() throws IOException {
        if (!conf.exists()) {
            createDefaultConfiguration();
            throw new IllegalArgumentException("Please configure your proxy in settings/jcroft.cfg");
        }

        try (BufferedReader read = new BufferedReader(new InputStreamReader(new FileInputStream(conf)))) {
            String str;
            while ((str = read.readLine()) != null) {
                String[] s = str.split("=", 2);
                if (s.length == 2) {
                    values.put(s[0], s[1]);
                }
            }
        }
    }

    public static String getValue(String key) {
        return values.get(key);
    }

    private static void createDefaultConfiguration() throws IOException {
        conf.getParentFile().mkdirs();
        conf.createNewFile();
        PrintWriter write = new PrintWriter(new FileOutputStream(conf));
        write.println("ldap_host=idm0.agdsn.network");
        write.println("ldap_port=389");
        write.flush();
        write.close();
    }

    public static int getValueInt(String key) {
        return Integer.parseInt(getValue(key));
    }
}

