package edu.baylor.ecs.csi3471.groupProject.Business;

import java.util.Objects;

public class CharacterVotes {
    private String matchAChoice;
    private String User;
    private String matchBChoice;
    private String matchCChoice;
    private String matchDChoice;
    private Integer matchABet;
    private Integer matchBBet;
    private Integer matchCBet;
    private Integer matchDBet;

    public CharacterVotes(){
        this.User = "";
        this.matchAChoice = "";
        this.matchBChoice = "";
        this.matchCChoice = "";
        this.matchDChoice = "";
        this.matchABet = -1;
        this.matchBBet = -1;
        this.matchCBet = -1;
        this.matchDBet = -1;
    }
    public CharacterVotes(String[] a){
        this.User = a[0];
        this.matchAChoice = a[1];
        this.matchBChoice = a[2];
        this.matchCChoice = a[3];
        this.matchDChoice = a[4];
        this.matchABet = Integer.parseInt(a[5]);
        this.matchBBet = Integer.parseInt(a[6]);
        this.matchCBet = Integer.parseInt(a[7]);
        this.matchDBet = Integer.parseInt(a[8]);
    }

    public Integer getMatchABet() {
        return matchABet;
    }

    public Integer getMatchBBet() {
        return matchBBet;
    }

    public Integer getMatchCBet() {
        return matchCBet;
    }

    public Integer getMatchDBet() {
        return matchDBet;
    }

    public String getMatchAChoice() {
        return matchAChoice;
    }

    public String getMatchBChoice() {
        return matchBChoice;
    }

    public String getMatchCChoice() {
        return matchCChoice;
    }

    public void setMatchABet(Integer matchABet) {
        this.matchABet = matchABet;
    }

    public void setMatchAChoice(String matchAChoice) {
        this.matchAChoice = matchAChoice;
    }

    public void setMatchBBet(Integer matchBBet) {
        this.matchBBet = matchBBet;
    }

    public void setMatchBChoice(String matchBChoice) {
        this.matchBChoice = matchBChoice;
    }

    public void setMatchCBet(Integer matchCBet) {
        this.matchCBet = matchCBet;
    }

    public void setMatchCChoice(String matchCChoice) {
        this.matchCChoice = matchCChoice;
    }

    public void setMatchDBet(Integer matchDBet) {
        this.matchDBet = matchDBet;
    }

    public String getMatchDChoice() {
        return matchDChoice;
    }

    public void setMatchDChoice(String matchDChoice) {
        this.matchDChoice = matchDChoice;
    }

    public void setUser(String user) {
        User = user;
    }

    public String getUser() {
        return User;
    }

    @Override
    public String toString() {
        return "CharacterVotes{" +
                "matchAChoice='" + matchAChoice + '\'' +
                ", User='" + User + '\'' +
                ", matchBChoice='" + matchBChoice + '\'' +
                ", matchCChoice='" + matchCChoice + '\'' +
                ", matchDChoice='" + matchDChoice + '\'' +
                ", matchABet=" + matchABet +
                ", matchBBet=" + matchBBet +
                ", matchCBet=" + matchCBet +
                ", matchDBet=" + matchDBet +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CharacterVotes)) return false;
        CharacterVotes that = (CharacterVotes) o;
        return Objects.equals(getUser(), that.getUser());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUser());
    }
}
