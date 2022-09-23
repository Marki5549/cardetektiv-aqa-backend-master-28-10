package de.cardetektiv.app.database;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;
import java.util.Objects;
import java.util.Properties;

@Slf4j
@Component
public class JsChTunnel {

    @Value("${ssh.server.hostname}")
    private String sshServerHostname;

    @Value("${ssh.server.port}")
    private Integer sshServerPort;

    @Value("${ssh.server.username}")
    private String sshServerUsername;

    @Value("${ssh.key.file}")
    private String sshKeyFile;

    @Value("${jdbc.hostname}")
    private String jdbcHostname;

    @Value("${jdbc.local.port}")
    private Integer jdbcLocalPort;

    @Value("${jdbc.remote.port}")
    private Integer jdbcRemotePort;

    @Value("${jdbc.username}")
    private String jdbcUsername;

    @Value("${jdbc.password}")
    private String jdbcPassword;

    private Session jsChSession;

    public void createSshTunnel() {
        log.info("Creation of SSH database tunnel");

        final JSch jsch = new JSch();
        try {
            jsch.addIdentity(sshKeyFile);

            jsChSession = jsch.getSession(sshServerUsername, sshServerHostname, sshServerPort);
            jsChSession.setConfig(getConfig());

            log.info("Connecting to SSH tunnel");
            jsChSession.connect();
            jsChSession.setPortForwardingL(jdbcLocalPort, jdbcHostname, jdbcRemotePort);
        } catch (JSchException ex) {
            log.error("Can't establish connection with SSH Tunnel", ex);
        }
    }

    @PreDestroy
    private void shutDownMethod() {
        if (Objects.nonNull(jsChSession)) {
            jsChSession.disconnect();
        }
    }

    private Properties getConfig() {
        final Properties config = new Properties();
        config.put("StrictHostKeyChecking", "no");
        return config;
    }
}
