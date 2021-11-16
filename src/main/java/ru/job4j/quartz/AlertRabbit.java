package ru.job4j.quartz;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.sql.*;

import static org.quartz.JobBuilder.*;
import static org.quartz.TriggerBuilder.*;
import static org.quartz.SimpleScheduleBuilder.*;

import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.Properties;

public class AlertRabbit {
    private static Connection connection;
    private static Properties config;

    private static void init() {
        try (InputStream in = AlertRabbit.class.getClassLoader()
                .getResourceAsStream("rabbit.properties")) {
            config = new Properties();
            config.load(in);
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(
                    config.getProperty("url"),
                    config.getProperty("username"),
                    config.getProperty("password")
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        AlertRabbit.init();
        try (Statement statement = connection.createStatement()) {
            statement.execute("CREATE TABLE IF NOT EXISTS rabbit(created_date timestamp);");
            Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
            scheduler.start();
            JobDetail job = newJob(Rabbit.class)
                    .build();
            SimpleScheduleBuilder times = simpleSchedule()
                    .withIntervalInSeconds(Integer.parseInt(config.getProperty("rabbit.interval")))
                    .repeatForever();
            Trigger trigger = newTrigger()
                    .startNow()
                    .withSchedule(times)
                    .build();
            scheduler.scheduleJob(job, trigger);
            Thread.sleep(10000);
            scheduler.shutdown();
        } catch (Exception se) {
            se.printStackTrace();
        }
    }

    public static class Rabbit implements Job {
/*          Второй вариант добавления таймстемп в таблицу
            try (Statement statement = connection.createStatement()) {
                statement.execute("INSERT INTO rabbit(created_date) VALUES (current_timestamp)");
            } catch (SQLException e) {
                e.printStackTrace();
            }
*/
        @Override
        public void execute(JobExecutionContext context) throws JobExecutionException {
            try (PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO rabbit(created_date) VALUES (?)"
            )) {
                preparedStatement.setTimestamp(1, Timestamp.valueOf(LocalDateTime.now()));
                preparedStatement.execute();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
    }
}