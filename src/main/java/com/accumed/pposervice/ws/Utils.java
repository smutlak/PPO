/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.accumed.pposervice.ws;

/**
 *
 * @author smutlak
 */
public class Utils {

    public static com.haad.ClaimSubmission setParents(com.haad.ClaimSubmission claimSubmission) {
        if (claimSubmission != null) {

            if (claimSubmission.getHeader() != null) {

                claimSubmission.getHeader().setClaimSubmission(claimSubmission);
            }

            //Claim
            if (claimSubmission.getClaim() != null) {
                for (com.haad.Claim claim : claimSubmission.getClaim()) {
                    claim.setClaimSubmission(claimSubmission);

                    if (claim.getEncounter() != null) {
                        for (com.haad.Encounter encounter : claim.getEncounter()) {
                            encounter.setClaim(claim);
                        }
                    }

                    if (claim.getDiagnosis() != null) {
                        for (com.haad.Diagnosis diagnosis : claim.getDiagnosis()) {
                            diagnosis.setClaim(claim);

                            if (diagnosis.getDxInfo() != null) {
                                for (com.haad.DxInfo dxInfo : diagnosis.getDxInfo()) {
                                    dxInfo.setDiagnosis(diagnosis);
                                }
                            }
                        }
                    }

                    if (claim.getActivity() != null) {
                        for (com.haad.Activity activity : claim.getActivity()) {
                            activity.setClaim(claim);

                            if (activity.getObservation() != null) {
                                for (com.haad.Observation observation : activity.getObservation()) {
                                    observation.setActivity(activity);
                                }
                            }
                        }
                    }

                    if (claim.getResubmission() != null) {
                        claim.getResubmission().setClaim(claim);
                    }
                    if (claim.getContract() != null) {
                        claim.getContract().setClaim(claim);
                    }
                }
            }
        }
        return claimSubmission;
    }
}
