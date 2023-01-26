package communityPortal.tests.PIR_EMPI_Integration;

/*
If after Registration new Citizen(no PIR no Participant acc in imms)
PIR account has not linked to the Participant one automatically:
1.popup message "This client profile is not linked to a PIR record"
2.and no PIR Account number in Person Account Details
but EMPI Verification is green
that's meaning that we lost PIR integration
(Panorama has down or certificates expired issue)
*/

/*
1.If During the Registration new Citizen popup message like
"Match Unsuccessful"
PIR is down but EMPI might work.
2. and I still can click on Forecast Update and
3. Imms Records should goes to Panorama and back with Complited Status.
 */



public class PIR_account_auto_linking {
}
