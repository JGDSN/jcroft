package de.agdsn.jcroft;

import java.io.*;
import java.util.HashMap;
import java.util.Objects;

public class JCroftConfiguration {

    private static HashMap<String, String> values = new HashMap<>();

    /**
     * private constructor
     */
    protected JCroftConfiguration() {
        //
    }

    protected static void readConfig(File conf) throws IOException {
        if (!conf.exists()) {
            createDefaultConfiguration(conf);
            throw new IllegalArgumentException("Please configure your proxy in settings/jcroft.cfg");
        }

        try (BufferedReader read = new BufferedReader(new InputStreamReader(new FileInputStream(conf)))) {
            String str;
            while ((str = read.readLine()) != null) {
                if (str.startsWith(";") || str.isEmpty()) {
                    //ignore comment lines
                    continue;
                }

                String[] s = str.split("=", 2);
                if (s.length == 2) {
                    values.put(s[0], s[1]);
                } else {
                    throw new IllegalStateException("invalid configuration file: " + conf.getAbsolutePath());
                }
            }
        }
    }

    public static String getValue(String key) {
        Objects.requireNonNull(key);

        if (!values.containsKey(key)) {
            throw new IllegalStateException("configuration key '" + key + "' doesnt exists in config file settings/jcroft.cfg .");
        }

        return values.get(key);
    }

    protected static void createDefaultConfiguration(File conf) throws IOException {
        conf.getParentFile().mkdirs();
        conf.createNewFile();
        PrintWriter write = new PrintWriter(new FileOutputStream(conf));
        write.println("ldap_host=idm0.agdsn.network");
        write.println("ldap_port=389");
        write.println("");
        write.println("; database connection");
        write.println("jdbc_type=postgresql");
        write.println("jdbc_ip=localhost");
        write.println("jdbc_port=5432");
        write.println("jdbc_user=jcroft");
        write.println("jdbc_password=ENTER YOUR PASSWORD HERE");
        write.println("jdbc_database=jcroft");
        write.println("");
        write.println("; hibernate configuration (only change this, if you know, what you do)");
        write.println("hibernate.hbm2ddl.auto=update");
        write.println("hibernate.dialect=org.hibernate.dialect.PostgreSQL95Dialect");
        write.flush();
        write.close();
    }

    public static int getValueInt(String key) {
        return Integer.parseInt(getValue(key));
    }
}

