/* 
 * Mój jest kawałek podłogi...  * 
 */
function handleAppoitmentPageRequest(xhr, status, args) {
    if ( !args.docSet) {
        
    }
    else {
        
        // similar behavior as an HTTP redirect
        window.location.replace("/przychodnia/patient/bookingappoitment.xhtml");
    }
}

function handleAppoitmentRequest(xhr, status, args) {
   window.location.replace("/przychodnia/patient/bookingappoitment.xhtml");
}



