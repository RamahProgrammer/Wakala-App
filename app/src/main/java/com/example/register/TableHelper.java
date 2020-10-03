package com.example.register;

import android.content.Context;

import java.util.ArrayList;

public class TableHelper {
    Context c;
    private String[] spaceProbeHeaders={"Wakala","Date","Amount","from"};
    private String[][]  spaceProbes;


    /*
    constructor
     */
public TableHelper(Context c){
    this.c=c;
}
/*
return table headers
 */
public String[] getSpaceProbeHeaders(){
    return spaceProbeHeaders;
}
/*
return 2D table rows-columns data
 */
public String[][] returnSpaceProbesArray(ArrayList<Spacecraft> spacecrafts) {
    spaceProbes = new String[spacecrafts.size()][4];
    Spacecraft s;
    for (int i = 0; i < spacecrafts.size(); i++) {
        s = spacecrafts.get(i);
        spaceProbes[i][0] = s.getFromm();
        spaceProbes[i][1]=s.getMuda();
        spaceProbes[i][3]=s.getRecieved_from();
        spaceProbes[i][2]= s.getAmount();


    }


    return spaceProbes;
}

}
