package org.jackJew.algorithm;

import java.time.LocalDate;

/**
 * 清理旧持仓.
 */
public class StakingSnapshot {

    public static void main(String... args) {
        String template = "delete from staking_snapshot where snapshot_date='%s' and asset='%s';";
        String templateWithUserId = "delete from staking_snapshot where snapshot_date='%s' and asset='%s'";
        LocalDate startDate = LocalDate.parse("2020-04-01"), endDate = LocalDate.parse("2020-05-01");
        String[] assets = "LSK,LOOM,NEO,KMD,ARK,ARPA,TROY,ONE,VET,TRX,ALGO,FET,KAVA,ERD,ATOM,ONT,QTUM,STRAT,TOMO,XTZ,EOS,THETA".split(",");
        while (endDate.isAfter(startDate)) {
            for (String asset: assets) {
                if ("TRX".equals(asset)) {
                    String sqlPrefix = String.format(templateWithUserId, startDate, asset);
                    System.out.println(sqlPrefix + " and user_id < '15000000';");
                    System.out.println(sqlPrefix + " and user_id < '20000000' and user_id >= '15000000';");
                    System.out.println(sqlPrefix + " and user_id >= '20000000';");
                } else {
                    System.out.println(String.format(template, startDate, asset));
                }
            }
            startDate = startDate.plusDays(1);
        }
    }
}
