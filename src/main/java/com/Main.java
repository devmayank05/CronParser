package com;

import com.cron.CronExpressionEvaluator;

import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {

    private static final Logger logger = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {
        if (args.length == 0) {
            Scanner sc = new Scanner(System.in);
            while (true) {
                logger.log(Level.INFO, "Enter Cron Expression to Parse");
                String expression = sc.nextLine();

                try {
                    logger.log(Level.INFO, CronExpressionEvaluator.evalulateExpression(expression).toString());
                } catch (IllegalArgumentException ex) {
                    logger.log(Level.SEVERE, "Your expression is not valid " + ex.getMessage());
                } catch (Exception ex) {
                    logger.log(Level.SEVERE, "General Exception " + ex.getMessage());
                }

                System.out.println();
            }
        } else {
            logger.log(Level.INFO, CronExpressionEvaluator.evalulateExpression(args[0]).toString());
        }

    }
}