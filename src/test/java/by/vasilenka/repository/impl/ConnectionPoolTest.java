package by.vasilenka.repository.impl;

import by.vasilenka.repository.exception.ConnectionPoolException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Mockito;
import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;


@RunWith(JUnit4.class)
public class ConnectionPoolTest {
    private static final Logger LOGGER = LogManager.getLogger(ConnectionPoolTest.class);
    private static final int N_THREADS = 35 ;
    private static final int POOL_CAPACITY = 5 ;
    @Test
    public void shouldGetConnection () throws InterruptedException, ConnectionPoolException {
        ConnectionPool connectionPool = Mockito. spy (
                ConnectionPool.getInstance ());
        Set< Integer > hashCodes = new HashSet<>();
        ExecutorService executorService = Executors. newFixedThreadPool (N_THREADS);
        for (int i = 0; i<N_THREADS;i++) {
            Runnable run = () -> {
                try (Connection connection = connectionPool.getConnection()) {
                    Thread.sleep(1_00L);
                    Assert.assertTrue(connection instanceof Proxy);
                    int hashCode = connection.hashCode();
                    hashCodes.add(hashCode);
                } catch (SQLException | IllegalStateException|ConnectionPoolException e) {
                    LOGGER.error(e);
                } catch (InterruptedException e) {
                    LOGGER.error(e);
                    throw new RuntimeException(e);
                }
            };
            executorService.execute(run);
        }
        executorService. awaitTermination ( 1L , TimeUnit.SECONDS);
        Assert. assertEquals (POOL_CAPACITY, hashCodes. size ());
        Mockito. verify (((ConnectionPool) connectionPool),
                Mockito. times (N_THREADS)). releaseConnection (Mockito. any ());
    }
}