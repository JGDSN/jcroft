package de.agdsn.jcroft.security;

import de.agdsn.jcroft.JCroftConfiguration;
import org.apache.directory.api.ldap.model.cursor.CursorException;
import org.apache.directory.api.ldap.model.cursor.SearchCursor;
import org.apache.directory.api.ldap.model.entry.Attribute;
import org.apache.directory.api.ldap.model.entry.Entry;
import org.apache.directory.api.ldap.model.entry.Value;
import org.apache.directory.api.ldap.model.exception.LdapAuthenticationException;
import org.apache.directory.api.ldap.model.exception.LdapException;
import org.apache.directory.api.ldap.model.message.*;
import org.apache.directory.api.ldap.model.name.Dn;
import org.apache.directory.ldap.client.api.LdapConnection;
import org.apache.directory.ldap.client.api.LdapNetworkConnection;
import org.apache.directory.ldap.client.api.exception.InvalidConnectionException;
import org.springframework.security.authentication.BadCredentialsException;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LDAPConnection {

    protected static final String ERROR_WHILE_AUTHENTIFICATING = "Error while authenticating you. Please try again soon";

    protected static final int TIMEOUT_LIMIT_SECONDS = 3;

    /**
     * private constructor
     */
    protected LDAPConnection() {
        //
    }

    /**
     * Authenticates a user and returns his group id
     *
     * @param username
     * @param password
     * @return group id
     * @throws org.springframework.security.authentication.BadCredentialsException when user is not allowed to use jcroft or the LDAP request times out
     */
    public static int check(String username, String password) {

        //TODO Make this code readable and convenient

        if(password.equals("test") && JCroftConfiguration.contains("auth_test") && JCroftConfiguration.getValue("auth_test").equalsIgnoreCase("true"))return 0;

        int group = -1;

        try {
            // Data to check
            String privilege = "JUserman";
            String privilege2 = "JUserman-Admin";

            // LDAP data
            String host = JCroftConfiguration.getValue("ldap_host");
            int port = JCroftConfiguration.getValueInt("ldap_port");

            try (LdapConnection conn = new LdapNetworkConnection(host, port)) {
                //set timeout
                ((LdapNetworkConnection) conn).getConfig().setTimeout(TIMEOUT_LIMIT_SECONDS * 1000L);

                conn.bind("uid=" + username + ",cn=users,cn=accounts,dc=agdsn,dc=de", password);

                SearchRequest req = new SearchRequestImpl();
                req.setTimeLimit(TIMEOUT_LIMIT_SECONDS);
                req.setScope(SearchScope.SUBTREE);
                req.addAttributes("memberOf");
                req.setBase(new Dn("cn=users,cn=accounts,dc=agdsn,dc=de"));
                req.setFilter("(&(uid=" + username + ")(memberOf=cn=" + privilege + ",cn=privileges,cn=pbac,dc=agdsn,dc=de))");

                SearchCursor cursor = conn.search(req);

                if (cursor.next()) {
                    //seems fine so far
                } else {
                    group = -1;
                    throw new BadCredentialsException("You don't have access to JCroft. Please ask the ops for access!");
                }
                req.abandon();

                SearchRequest req2 = new SearchRequestImpl();
                req2.setTimeLimit(TIMEOUT_LIMIT_SECONDS);
                req2.setScope(SearchScope.SUBTREE);
                req2.addAttributes("memberOf");
                req2.setBase(new Dn("cn=users,cn=accounts,dc=agdsn,dc=de"));
                req2.setFilter("(&(uid=" + username + ")(memberOf=cn=" + privilege2 + ",cn=privileges,cn=pbac,dc=agdsn,dc=de))");

                SearchCursor cursor2 = conn.search(req2);

                if (cursor2.next()) {
                    group = 1;
                } else {
                    group = 0;
                }

                req2.abandon();

                //search for user groups (privileges)
                SearchRequest req3 = new SearchRequestImpl();
                req3.setTimeLimit(TIMEOUT_LIMIT_SECONDS);
                req3.setScope(SearchScope.SUBTREE);
                req3.addAttributes("memberOf");
                req3.setBase(new Dn("cn=users,cn=accounts,dc=agdsn,dc=de"));
                req3.setFilter("(&(uid=" + username + ")(memberOf=*))");

                SearchCursor cursor3 = conn.search(req3);

                //filter for privileges
                if (cursor3.next()) {
                    Entry entry = cursor3.getEntry();

                    String dn = entry.getDn().getName();
                    Logger.getAnonymousLogger().log(Level.INFO, "dn: " + dn);

                    for (Attribute attr : entry) {
                        for (Value<?> value : attr) {
                            if (value.getString().contains("cn=privileges")) {
                                String groupName = value.getString().replace(",cn=privileges,cn=pbac,dc=agdsn,dc=de", "").replace("cn=", "");
                                Logger.getAnonymousLogger().log(Level.INFO, "ldap groupName: " + groupName);
                            }
                        }
                    }
                }

                req3.abandon();

                conn.unBind();
            } catch (InvalidConnectionException e) {
                throw new BadCredentialsException("LDAP server is down. Please try again later.");
            } catch (IOException e) {
                e.printStackTrace();
                throw new BadCredentialsException(ERROR_WHILE_AUTHENTIFICATING);
            } catch (LdapAuthenticationException ex) {
                throw new BadCredentialsException("Your credentials were not accepted!");
            }


        } catch (LdapException | CursorException e) {
            e.printStackTrace();
            throw new BadCredentialsException(ERROR_WHILE_AUTHENTIFICATING);
        }

        return group;
    }
}
