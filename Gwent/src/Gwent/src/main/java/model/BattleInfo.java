package model;

import java.time.LocalDate;

public class BattleInfo {
    private LocalDate date= LocalDate.now();
   // private String[] players={User.getLoggedInUser().getUsername(),"amir"};
    private Integer[][] roundsPoints=new Integer[][]{{20,10},{10,20},{20,10}};
    private int[] finalPoints=new int[]{50,40};
    private User winner=User.getLoggedInUser();
    String opponent="amir";



}
