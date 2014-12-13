import java.util.ArrayList;

/**
 * Created by jkunimune15 on 11/26/14.
 */
public class Team {
  public int number;
  
  public int matchCount = 0;
  
  public ArrayList<Integer> autoScore = new ArrayList<Integer>();
  public ArrayList<Integer> teleScore = new ArrayList<Integer>();
  public ArrayList<Integer> penalty = new ArrayList<Integer>();
  public ArrayList<Team> ally = new ArrayList<Team>();
  public ArrayList<String> comments = new ArrayList<String>();
  
  public double elevatorRank = 0;
  public double intakeRank = 0;
  public double driveRank = 0;
  public double autoRank = 0;
  
  public int highestGoal = 0;
  public boolean cascade = false;
  public boolean tubeManeuver = false;
  public boolean rampStart = false;
  public boolean parkStart = false;
  
  
  
  public Team() {}
  public Team(int newNum) {
    number = newNum;
  }
  
  
  
  public void tallyMatch(Team allianceMember, boolean startPos, int auto, int tele, int pen) {
    if (startPos)
      rampStart = true;
    else
      parkStart = true;
    
    ally.add(allianceMember);
    autoScore.add(auto);
    teleScore.add(tele);
    penalty.add(pen);
    matchCount ++;
  }
  
  
  public void rank(int auto, int elev, int intake, int drive) {
    elevatorRank = (autoRank + (double)auto/10) / 2;
    elevatorRank = (elevatorRank + (double)elev/10) / 2;
    intakeRank = (intakeRank + (double)intake/10) / 2;
    driveRank = (driveRank + (double)drive/10) / 2;
  }
  
  
  public void comment(String newComment) {
    comments.add(newComment);
  }
  
  
  public void newHighestGoal(int newHighGoal) {
    if (newHighGoal > highestGoal)
      highestGoal = newHighGoal;
  }
  
  
  public void cascade() {
    cascade = true;
  }
  
  
  public void tubeManeuver() {
    tubeManeuver = true;
  }
  
  
  public int avgScore() {
    if (matchCount <= 0)
      return 0;
    
    int sum = 0;
    
    for (int i = 0; i < matchCount; i ++) {
      sum += autoScore.get(i) + teleScore.get(i) - penalty.get(i);
    }
    
    return sum/matchCount;
  }
  
  
  public int weightedAvgScore() {
    if (matchCount <= 0)
      return 0;
    
    int sum = 0;
    
    for (int i = 0; i < matchCount; i ++) {
      sum += autoScore.get(i) + teleScore.get(i) - penalty.get(i) - ally.get(i).avgScore()/2;
    }
    
    return sum/matchCount;
  }
  
  
  private int or(boolean a, boolean b) {
    if (a || b)
      return 1;
    else
      return 0;
  }
  
  
  private int getBestGoal(int highest1, int highest2) {
    if (highest1 > highest2)  return highest1;
    else                      return highest2;
  }
  
  
  public double compatibleWith(Team them) {
    return avgScore() * (them.autoRank + this.autoRank + or(them.cascade, this.cascade) + 1) *
      (them.driveRank + this.driveRank + or(them.tubeManeuver, this.tubeManeuver) + 1) *
      (them.intakeRank*them.elevatorRank + this.intakeRank*this.elevatorRank + getBestGoal(them.highestGoal, this.highestGoal) + 1);
  }
  
  
  public String report() {
    return "Hi.";
  }
}
