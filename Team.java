import java.util.*;




public class Team {
  public int number;
  
  public int matchCount = 0;
  
  public ArrayList<Integer> autoScore = new ArrayList<Integer>();
  public ArrayList<Integer> teleScore = new ArrayList<Integer>();
  public ArrayList<Integer> penalty = new ArrayList<Integer>();
  
  public int elevatorRank = 0;
  public int intakeRank = 0;
  public int autoRank = 0;
  
  public int highestGoal = 0;
  public boolean centerGoal = false;
  public boolean cascade = false;
  public boolean tubeManeuver = false;
  public boolean rampStart = false;
  public boolean parkStart = false;
  
  
  
  public Team(int newNum) {
    number = newNum;
  }
  
  
  
  public void tallyMatch(boolean startPos, int auto, int tele, int pen) {
    if (startPos)
      rampStart = true;
    else
      parkStart = true;
    
    autoScore.add(auto);
    teleScore.add(tele);
    penalty.add(pen);
    matchCount ++;
  }
  
  
  public void lowGoal() {
    if (highestGoal < 1)
      highestGoal = 1;
  }
  
  
  public void midGoal() {
    if (highestGoal < 2)
      highestGoal = 2;
  }
  
  
  public void highGoal() {
    highestGoal = 3;
  }
  
  
  public void centerGoal() {
    centerGoal = true;
  }
  
  
  public void cascade() {
    cascade = true;
  }
  
  
  public void tubeManeuver() {
    tubeManeuver = true;
  }
}