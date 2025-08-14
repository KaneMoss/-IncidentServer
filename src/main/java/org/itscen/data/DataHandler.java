package org.itscen.data;


import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * Responsible for retrieving data from stored location. At the moment data is hardcoded
 * in this class.
 * */
public final class DataHandler
{
    private final List<String> mChallengeList;
    private final List<String> mIncidentList;
    private final List<String> mTroubleStepsList;


    public DataHandler() {
        mChallengeList = populateChallenges();
        mIncidentList = populateIncidents();
        mTroubleStepsList = populateSteps();
    }

    @NotNull private static List<String> populateChallenges() {
        return List.of(
                "Forgot password",
                "DNS is down",
                "No one can find the server"
        );
    }

    @NotNull private static List<String> populateIncidents() {
        return List.of(
                "Too many log in attempts",
                "Bad actor got past firewall",
                "Server capacity at 100%"
        );
    }

    @NotNull private static List<String> populateSteps() {
        return List.of(
                "Reset password",
                "Tighten the firewall",
                "Add more storage"
        );
    }

    @NotNull public String RetrieveChallenge(int ind)
    {
        if (ind < 0 || ind >= mChallengeList.size()) {
            return "";
        }

        return mChallengeList.get(ind);
    }

    @NotNull public String RetrieveIncident(int ind)
    {
        if (ind < 0 || ind >= mChallengeList.size()) {
            return "";
        }

        return mIncidentList.get(ind);
    }

    @NotNull public String RetrieveTroubleStep(int ind)
    {
        if (ind < 0 || ind >= mChallengeList.size()) {
            return "";
        }

        return mTroubleStepsList.get(ind);
    }
}
